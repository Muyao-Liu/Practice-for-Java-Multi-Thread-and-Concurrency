package MultiThread;

public class SleepThread extends Thread{
    //public static void main(String[] args) throws InterruptedException{
    @Override
    public void run(){
        String[] info={
                "M eats O",
                "D eats O",
                "L eats I",
                "K eats I too"
        };

        for(int i=0;i<info.length;i++){
            try {
                Thread.sleep(4000);//catch the exception when other threads interrupt the current thread
            }catch (InterruptedException e){
                System.out.println("The current thread is interrupted by other thread");
            }
            System.out.println(info[i]);
        }
    }
}
