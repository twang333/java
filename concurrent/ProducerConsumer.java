package client;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumer {

    public static class Producer implements Runnable {
        private BlockingQueue<String> drop;

        public Producer(BlockingQueue<String> drop) {
            this.drop = drop;
        }

        public void run() {
            String importantInfo[] = {
                "Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too" };
            Random random = new Random();

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    String message = importantInfo[i];
                    System.out.format("MESSAGE PUT: %s%n", message);
                    drop.put(message);
                }
                drop.put("DONE");

            } catch (InterruptedException e) {
            }
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<String> drop;

        public Consumer(BlockingQueue<String> drop) {
            this.drop = drop;
        }

        public void run() {
            Random random = new Random();
            try {
                for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
                    System.out.format("MESSAGE RECEIVED: %s%n", message);

                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> drop = new SynchronousQueue<String>();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
