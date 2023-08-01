package MultiThread.ForkJoin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {
    private int[] mSource;
    private int[] mDestination;
    private int mStart;
    private int mLength;
    private int mBlurWidth=15;

    protected static int sThreshold=100000;

    public ForkBlur(int[] src,int start,int length,int[] dst){
        mSource=src;
        mStart=start;
        mLength=length;
        mDestination=dst;
    }

    @Override
    protected void compute(){
        if(mLength<sThreshold){
            computeDiectly();
            return;
        }

        int split=mLength/2;//it's up to you
        invokeAll(new ForkBlur(mSource,mStart,split,mDestination),new ForkBlur(mSource,mStart+split,mLength-split,mDestination));
    }

    protected void computeDiectly(){
        int sidePixels=(mBlurWidth-1)/2;
        for(int i=mStart;i<mStart+mLength;i++){
            float rt=0,gt=0,bt=0;
            for(int mi=-sidePixels;mi<=sidePixels;mi++){
                int mindex=Math.min(Math.max(mi+i,0),mSource.length-1);
                int pixel=mSource[mindex];
                rt+=(float)((pixel & 0x00ff0000) >> 16)/mBlurWidth;
                gt+=(float)((pixel & 0x00ff0000) >> 8)/mBlurWidth;
                bt+=(float)((pixel & 0x00ff0000))/mBlurWidth;
            }
            //reassemble pixel
            int dpixel=(0xff000000)|(((int)rt) << 16)|(((int)gt) << 8)|(((int)bt) << 16);
            mDestination[i]=dpixel;
        }
    }

    public static void main(String[] args) throws Exception {
        String srcName = "srcImage.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);

        System.out.println("Source image: " + srcName);

        BufferedImage blurredImage = blur(image);

        String dstName = "blurred-srcImage.jpg";
        File dstFile = new File(dstName);
        ImageIO.write(blurredImage, "jpg", dstFile);

        System.out.println("Output image: " + dstName);
    }

    public static BufferedImage blur(BufferedImage srcImage){
        int weight=srcImage.getWidth();
        int height=srcImage.getHeight();

        int[] src=srcImage.getRGB(0,0,weight,height,null,0,weight);
        int[] dst=new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + sThreshold);

        int processors=Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors)+" processor"+(processors!=1?"s are ":" is ")+"available");

        ForkBlur fb=new ForkBlur(src,0,src.length,dst);

        ForkJoinPool pool=new ForkJoinPool();

        long startTime=System.currentTimeMillis();
        pool.invoke(fb);
        long endTime=System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, weight, height, dst, 0, weight);

        return dstImage;
    }
}
