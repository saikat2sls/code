import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface CallBack
{
    //c denotes event code
    // 1: denotes increment
    // 2: denotes decrement
    void handle(int c);
}

class MyCallback implements CallBack
{
    Integer val =0;

    @Override
    public void handle(int c) {
         //handle the callback actions here
        System.out.println("callback invoked");

        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        Semaphore sem  =new Semaphore(0);
        try {
            sem.acquire();
        }catch (Exception e)
        {
            //handle exception
        }
        sem.release();

        synchronized (val) {
            switch (c) {
                case 1:
                    val++;
                    break;
                case 2:
                    val--;
                    break;
            }
        }
    }
}

class MyConsumer implements Runnable
{
    CallBack callBack;

   MyConsumer(CallBack cb)
   {
       callBack = cb;
   }

    @Override
    public void run() {
          System.out.println("Consumer thread invoked");
          if(callBack!=null)
          {
              callBack.handle(1);
          }
    }
}

public class CallbackMultiThreading {


    public static void main(String arg[])
    {
        try {
            CallBack cb = new MyCallback();
            MyConsumer consumertask = new MyConsumer(cb);
            Thread consumerTask = new Thread(consumertask);
            consumerTask.start();
            consumerTask.join();
        }catch(InterruptedException E)
        {
            //handle exception
        }
    }
}
