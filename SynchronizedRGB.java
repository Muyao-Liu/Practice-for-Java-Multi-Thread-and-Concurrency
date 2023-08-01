package MultiThread;

final public class SynchronizedRGB {
    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    public SynchronizedRGB(int red,int green,int blue,String name){
        check(red,green,blue);
        this.red=red;
        this.green=green;
        this.blue=blue;
        this.name=name;
    }
    private void check(int red,int green,int blue){
        if(red<0||red>255||green<0||green>255||blue<0||blue>255) throw new IllegalArgumentException();
    }

    //make object immutable
    /*
    public void set(int red,int green,int blue,String name){
        check(red,green,blue);
        //synchronized block
        synchronized (this){
            this.red=red;
            this.green=green;
            this.blue=blue;
            this.name=name;
        }
    }*/

    public synchronized int getRGB(){
        //32-bit: red-green-blue
        return ((red<<16)|(green<<8)|(blue));
    }

    public synchronized String getName(){
        return  name;
    }

    public synchronized SynchronizedRGB invert(){
        //return a new object rather than modifying the current object
        return new SynchronizedRGB(255-red,255-green,255-blue,"Inverse of "+name);
        /*
        red=255-red;
        green=255-green;
        blue=255-blue;
        name="Inverse of "+name;*/
    }

    public static void main(String[] args){
        SynchronizedRGB synchronizedRGB=new SynchronizedRGB(30,40,50,"Random Color");
        int myColor=synchronizedRGB.getRGB();
        System.out.println(myColor);
        String colorName=synchronizedRGB.getName();
        System.out.println(colorName);
    }
}
