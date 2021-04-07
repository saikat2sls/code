import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer consumer problem using blocking queue
 */


public class ProducerConsumerBlockingQueue {
    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        ProducerTask producer = new ProducerTask(queue);
        ConsumerTask consumer = new ConsumerTask(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}


class ConsumerTask implements Runnable{

    protected BlockingQueue queue = null;

    public ConsumerTask(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("consumed"+queue.take());
            System.out.println("consumed"+queue.take());
            System.out.println("consumed"+queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerTask implements Runnable{

    protected BlockingQueue queue = null;

    public ProducerTask(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("1");
            System.out.println("produced 1");
            Thread.sleep(100);
            queue.put("2");
            System.out.println("produced 2");
            Thread.sleep(100);
            queue.put("3");
            System.out.println("produced 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
