package MultiThread.Lock;

public class SafeLock {
    public static void main(String[] args){
        final Friend al=new Friend("al");
        final Friend gas=new Friend("gas");

        //multi-thread for crazy bowing
        new Thread(new BowLoop(al,gas)).start();
        new Thread(new BowLoop(gas,al)).start();
    }
}
