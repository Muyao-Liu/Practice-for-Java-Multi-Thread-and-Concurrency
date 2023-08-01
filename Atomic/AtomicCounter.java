package MultiThread.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger count=new AtomicInteger(0);

    public void increment(){
        count.incrementAndGet();
    }

    public void decrement(){
        count.decrementAndGet();
    }

    public int getValue(){
        return count.get();
    }

    public static void main(String[] args){
        new Thread(new AtomicIncThread()).start();
        new Thread(new AtomicDecThread()).start();
    }
}
