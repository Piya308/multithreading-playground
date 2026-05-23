package org.priyanka.basics;

class SharedLock {
    synchronized void lockedMethod() {
        System.out.println(Thread.currentThread().getName() + " acquired lock");
        try {
            Thread.sleep(5000); // keeps lock occupied
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    public class ThreadLifecycle extends Thread {

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            });

            System.out.println("Before start: " + t.getState()); // NEW

            t.start();
            System.out.println("After start: " + t.getState()); // RUNNABLE

            Thread.sleep(1000); // Let thread enter TIMED_WAITING
            System.out.println("During sleep: " + t.getState()); // TIMED_WAITING
            t.join(); // Wait for it to finish
            System.out.println("After join: " + t.getState()); // TERMINATED

        }

        @Override
        public void run() {
            try {
                ThreadLifecycle.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }