import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronised blocks protected by lock should be protected by try clause
 */


public class ThreadLocks {


    public static class Bank{
        int totalbalance;
        private int[] accounts = new int[MAX_USERS];
        public static final int MAX_USERS= 10;
        private Lock lock = new ReentrantLock();


        Bank(int start)
        {
            for(int i=0;i<MAX_USERS;i++)
            {
                accounts[i]=10;
                totalbalance+=accounts[i];
            }
        }

        public boolean transact(int amount, int from, int to)
        {
            boolean ret=true;
            lock.lock();
            try {
                if(accounts[from]-amount<0)
                {
                    return false;
                }

                accounts[from]-=amount;
                totalbalance-=amount;
                accounts[to] += amount;
                totalbalance+=amount;
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread:" + threadName + "deposit:" + amount + "Overallbalance:" + totalbalance);
            }
            finally {
                lock.unlock();
            }
            return ret;
        }

        public void withdraw(int amount)
        {
            lock.lock();
            try {
                totalbalance -= amount;
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread:" + threadName + "Withdraw:" + amount + "balance:" + totalbalance);
            }
            finally {
                lock.unlock();
            }
        }
    }



    public static void main(String args[])
    {
        //create 10 threads which simultaneosly deposit and withdraw
        Bank myBank = new Bank(1000);

        //Random transaction
        for (int i = 0; i < Bank.MAX_USERS; i++) {
            Thread t = new Thread(
                    ()->{
                        int from  = (int)(Math.random()*Bank.MAX_USERS);
                        int to  = (int)(Math.random()*Bank.MAX_USERS);
                        myBank.transact(10,from,to);

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //myBank.withdraw(10);
                    });
            t.start();
        }

    }
}
