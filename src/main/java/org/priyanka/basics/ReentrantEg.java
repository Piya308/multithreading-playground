package org.priyanka.basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantEg {

    private final Lock lock = new ReentrantLock();

    synchronized void outerMethod(){
//        lock.lock();
        try{
            System.out.println("Outer method");
            innerMethod();
            System.out.println("execution end");
        }
        finally {
//            lock.unlock();
        }
    }

  synchronized   void innerMethod(){
//        lock.lock();
        try{
            System.out.println("Inner method");
        }
        finally {
//            lock.unlock();
//            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantEg reentrantEg = new ReentrantEg();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reentrantEg.outerMethod();
            }
        };
        Thread t1 = new Thread(runnable)
;
        Thread t2  = new Thread(runnable);

        t1.start();
        t2.start();
    }
}
