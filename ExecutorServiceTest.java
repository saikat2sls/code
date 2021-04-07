
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Creating thread and executing is the old way of processing tasks in separate contexts.
 * Thread basically implements a runnable and on invoking start() on the thread it calls the run() api
 *
 * Executor service gives the flexibility to create a pool of threads which can keep executing
 * as and when runnable units of tasks are submitted to the executorService
 */

public class ExecutorServiceTest {

    public static class myRunnableTask implements Runnable
    {
        String arg;

        myRunnableTask(String param)
        {
            arg = param;
        }


        public void run() {
            System.out.println(Thread.currentThread().getName()+" Starting thread with arg:"+arg);
        }
    }

    public static void main(String arg[])
    {
        Thread t = new Thread(
            () -> {
                System.out.print("hello" );
                //get thread params
            });

        t.start();

        /* Way 1 :of creating executorService with a fixed poolsize which cannot grow the number of threads
        // internally it has a blockingQueue unbounded capacity because of which you can keep submitting tasks
        // even when all threads in the threadpool are busy
        */

        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(10);

        //Way 2: creating executor service which is flexible to encorporate more threads depending on load
        // and have a buffer to store the runnable tasks submitted
        // the size of the blocking queue indicates how much buffer can be held before .submit() will start to clock

        int corePoolSize=10;
        int maxPoolSize=20;
        long keepAliveTime = 3000;
        java.util.concurrent.ExecutorService executorService1 = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(128));

        executorService.execute(new myRunnableTask("task1"));

        executorService.submit(new myRunnableTask("task2"));     // <= returns a future whether a task is completed

        executorService.shutdown();

    }
}
