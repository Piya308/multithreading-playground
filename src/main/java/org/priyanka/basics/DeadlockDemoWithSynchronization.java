package org.priyanka.basics;

class DeadlockDemoWithSynchronization {
    // Outer method
     synchronized void outerMethod(DeadlockDemoWithSynchronization other) {
        System.out.println(Thread.currentThread().getName() + " entered outerMethod");
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        // Calls innerMethod of the other object
        other.innerMethod(this);
    }

    // Inner method
     synchronized void innerMethod(DeadlockDemoWithSynchronization other) {
        System.out.println(Thread.currentThread().getName() + " entered innerMethod");
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        // Calls outerMethod of the other object
        other.outerMethod(this);
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
        System.out.println(new StringBuilder().append("T1").append(t1.getState()).append("\n t2").append(t2.getState()));
    }
}

//due to deadlock--> threads never terminates and goes into wating state forever