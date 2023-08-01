package MultiThread.Lock;

import java.util.Random;

class BowLoop implements Runnable{
    private Friend bower;
    private Friend bowee;

    public BowLoop(Friend bower,Friend bowee){
        this.bower=bower;
        this.bowee=bowee;
    }

    public void run(){
        Random random=new Random();

        for(;;){
            try{
                Thread.sleep(random.nextInt(10));
            }catch (InterruptedException e){}
            bowee.bow(bower);
        }
    }
}
