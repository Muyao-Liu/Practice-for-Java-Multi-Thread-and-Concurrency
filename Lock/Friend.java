package MultiThread.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Friend{
    private final String name;
    private final Lock lock=new ReentrantLock();//deal with deadlock

    public Friend(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    //if it could get locks w/o deadlock
    public boolean impendingLock(Friend bower){
        Boolean myLock=false;
        Boolean friendLock=false;
        try{
            myLock=lock.tryLock();
            friendLock=bower.lock.tryLock();
        }finally {
            if(!(myLock&&friendLock)){
                if(myLock){
                    lock.unlock();
                }else if(friendLock){
                    bower.lock.unlock();
                }
            }
        }
        return myLock&&friendLock;
    }

    public void bow(Friend bower){
        if(impendingLock(bower)){
            //get both locks
            try{
                System.out.format("%s: %s  has"+" bowed to me!%n",this.name,bower.getName());
                bower.bowBack(this);
            }finally {
                lock.unlock();
                bower.lock.unlock();
            }
        }else{
            System.out.format("%s: %s started"
                            + " to bow to me, but saw that"
                            + " I was already bowing to"
                            + " him.%n",
                    this.name, bower.getName());
        }
    }

    public void bowBack(Friend bower){
        System.out.format("%s: %s has" +
                        " bowed back to me!%n",
                this.name, bower.getName());
    }
}
