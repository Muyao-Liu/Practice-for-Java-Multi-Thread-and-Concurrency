package MultiThread.Atomic;

public class AtomicDecThread implements Runnable{
    @Override
    public void run(){
        AtomicCounter atomicCounter=new AtomicCounter();
        atomicCounter.decrement();
        atomicCounter.decrement();
        System.out.println("The count value is "+atomicCounter.getValue());
    }
}
