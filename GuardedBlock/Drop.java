package MultiThread.GuardedBlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Drop {
    //private String message;
    //private boolean isEmpty;
    private BlockingQueue<String> bq=new ArrayBlockingQueue<>(1);
    //provide a thread-safe way for multi-threads to communicate and share data efficiently.
    //guarded block
    //no blocking
    public String take() throws InterruptedException{
        /*
        while(isEmpty){
            try {
                wait();
            }catch (InterruptedException e){}
        }
        isEmpty=true;
        notifyAll();
        return message;*;
         */
        return bq.take();
    }

    public void put(String message) throws InterruptedException{
        /*
        while(!isEmpty){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        isEmpty=false;
        this.message=message;
        notifyAll();*/
        bq.put(message);
    }
}
