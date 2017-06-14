package client;

public class NoVisibility {
    private static boolean ready;
    private static int number;
    
    private static class ReaderThread extends Thread {
        public void run() {
            int i = 0;
            while(!ready) i++;
            System.out.println(number);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
