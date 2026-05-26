package org.priyanka.basics;

public class Synchronization {

    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);
        t1.start();
        t2.start();

        try
        {
            t1.join();t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCount());
        System.out.println(counter.getB());
    }

}

class Counter{
    private int a =0;
    private int b =0;

    public int getCount(){
        return a;
    }
    public int getB(){
        return b;
    }

    public void  increment(){
        synchronized(this){
a++;
        }
        b++;
    }
}

class MyThread extends Thread{
    private Counter counter;

    public MyThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i< 5000; i++) {
            counter.increment();
        }
    }
}