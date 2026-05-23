package org.priyanka.basics;

class SharedResource {

    synchronized void useResource() {

        System.out.println(
                Thread.currentThread().getName()
                        + " acquired lock"
        );

        try {
            Thread.sleep(4000); // TIMED_WAITING
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadLifecycle1 {

    public static void main(String[] args)
            throws InterruptedException {

        SharedResource resource = new SharedResource();

        // ==========================
        // THREAD-1
        // Gets lock first
        // ==========================
        Thread t1 = new Thread(() -> {
            resource.useResource();
        }, "Thread-1");


        // ==========================
        // THREAD-2
        // Will become BLOCKED
        // ==========================
        Thread t2 = new Thread(() -> {
            resource.useResource();
        }, "Thread-2");


        // ==========================
        // THREAD-3
        // Pure CPU work -> RUNNABLE
        // ==========================
        Thread t3 = new Thread(() -> {

            long sum = 0;

            for (long i = 0; i < 1_000_000_000L; i++) {
                sum += i;

                if (i % 300_000_000 == 0) {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " working..."
                    );
                }
            }

        }, "Thread-3");


        // ==========================
        // THREAD-4
        // WAITING using join()
        // ==========================
        Thread t4 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-4");


        // ==========================
        // NEW STATES
        // ==========================
        System.out.println("t1: " + t1.getState());
        System.out.println("t2: " + t2.getState());
        System.out.println("t3: " + t3.getState());
        System.out.println("t4: " + t4.getState());


        // ==========================
        // START THREADS
        // ==========================
        t1.start();
        t2.start();
        t3.start();
        t4.start();


        // Allow states to stabilize
        Thread.sleep(500);


        // ==========================
        // OBSERVE STATES
        // ==========================
        System.out.println("\n--- STATES DURING EXECUTION ---");

        System.out.println("t1: " + t1.getState());
        System.out.println("t2: " + t2.getState());
        System.out.println("t3: " + t3.getState());
        System.out.println("t4: " + t4.getState());


        // ==========================
        // WAIT FOR ALL
        // ==========================
        t1.join();
        t2.join();
        t3.join();
        t4.join();


        // ==========================
        // TERMINATED STATES
        // ==========================
        System.out.println("\n--- FINAL STATES ---");

        System.out.println("t1: " + t1.getState());
        System.out.println("t2: " + t2.getState());
        System.out.println("t3: " + t3.getState());
        System.out.println("t4: " + t4.getState());
    }
}