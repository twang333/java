package client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static class T1 extends Thread {
        private List<Integer> list;
        
        public T1(List<Integer> list) {
            this.list = list;
        }
        
        public void run() {
            for(Integer i: list) {
                System.out.println(i);    
            }
        }
    }
    
    public static class T2 extends Thread {
        private List<Integer> list;
        
        public T2(List<Integer> list) {
            this.list = list;
        }
        
        public void run() {
            for(int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for( int i = 0; i < 100; i++) {
            list.add(i);
        }
        
        Thread t1 = new T1(list);
        Thread t2 = new T2(list);
        t1.start();
        t2.start();
    }
}
