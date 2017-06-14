package client;

public class ThreadTest {

    static boolean done = false;

    private static class CorrectorThread
        extends Thread {

        public void run() {
            int i = 0;
            while(!done) {
                i++;
            }
            System.out.println("done");
        }
    }

    public static void main(String args[])
        throws InterruptedException {

        (new CorrectorThread()).start();
        
        Thread.sleep(1000);
        done = true;
        System.out.println("set done");
    }
}
