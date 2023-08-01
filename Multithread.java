package MultiThread;

public class Multithread extends Thread{
    @Override
    public void run(){
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                throw new IllegalArgumentException("Wrong argument found");
            }
        }
    }

    public static void main(String[] args){

        SleepThread sleepThread=new SleepThread();
        sleepThread.start();

        Multithread multithread1=new Multithread();
        multithread1.start();

    }
}
