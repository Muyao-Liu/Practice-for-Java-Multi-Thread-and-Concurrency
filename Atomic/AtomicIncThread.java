package MultiThread.Atomic;

public class AtomicIncThread implements Runnable{
    @Override
    public void run(){
        AtomicCounter atomicCounter=new AtomicCounter();
        atomicCounter.increment();
        atomicCounter.increment();
        System.out.println("The count value is "+atomicCounter.getValue());
    }
}
