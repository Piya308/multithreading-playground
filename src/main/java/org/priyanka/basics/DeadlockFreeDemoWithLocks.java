package org.priyanka.basics;

import java.util.concurrent.locks.*;

class DeadlockFreeDemoWithLocks {
    private final Lock lock = new ReentrantLock();

    // Outer method
     void outerMethod(DeadlockFreeDemoWithLocks other) {
        boolean myLock = false;
        try {
            myLock = lock.tryLock();
            if (myLock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock in outerMethod");
                Thread.sleep(100);

                // Call innerMethod of the other object
                other.innerMethod(this);
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock in outerMethod");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (myLock) lock.unlock();
        }
    }

    // Inner method
     void innerMethod(DeadlockFreeDemoWithLocks other) {
        boolean myLock = false;
        try {

            myLock = lock.tryLock();
            if (myLock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock in innerMethod");
                Thread.sleep(100);

                // Call outerMethod of the other object
                other.outerMethod(this);
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock in innerMethod");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (myLock) lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockFreeDemoWithLocks obj1 = new DeadlockFreeDemoWithLocks();
        DeadlockFreeDemoWithLocks obj2 = new DeadlockFreeDemoWithLocks();

        Thread t1 = new Thread(() -> obj1.outerMethod(obj2), "Thread-1");
        Thread t2 = new Thread(() -> obj2.outerMethod(obj1), "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(new StringBuilder().append("T1").append(t1.getState()).append("\nt2").append(t2.getState()));
    }
}

//here threads will get terminated because we are using  myLock = lock.tryLock();