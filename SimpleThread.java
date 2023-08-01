package MultiThread;

public class SimpleThread {
    static void threadMessage(String message){
        //if child thread takes too long time to finish, main thread interrupts it
        String threadName=Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
    }

    private static class MessageLoop implements Runnable{
        public void run(){
            String[] info={
                    "M eats O",
                    "D eats O",
                    "L eats I",
                    "K eats I too"
            };
            try{
                for(int i=0;i<info.length;i++){
                    Thread.sleep(4000);//catch the exception when other threads interrupt the current thread
                    threadMessage(info[i]);
                }
            }catch (InterruptedException e){
                threadMessage("The current thread is interrupted by other thread");

            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        long patience=1000*6;
        if(args.length>0){
            try{
                patience=Long.parseLong(args[0])*1000;
            }catch (NumberFormatException e){
                System.err.println("Argument must be an integer");
                System.exit(1);
            }
        }

        threadMessage("Start messageLoop thread");
        long startTime=System.currentTimeMillis();
        Thread t=new Thread(new MessageLoop());
        t.start();

        threadMessage("Wait messageLoop thread to finish");

        while(t.isAlive()){
            threadMessage("Still waiting");
            t.join(1000);

            if(((System.currentTimeMillis()-startTime)>patience)&&t.isAlive()){
                threadMessage("Tired of waiting");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Thread execution finished");
    }
}

