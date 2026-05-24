package org.priyanka.basics;

public class ThreadMethods extends Thread {

    public ThreadMethods(String threadName) {
        super(threadName);
    }
    @Override
     public void run() {
        while (true) {
            System.out.println("Daemon Working");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Thread.currentThread().getName()+ "- Priority " + Thread.currentThread().getPriority() + "-count " + i);
//            Thread.yield();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadMethods tl = new ThreadMethods("Low");
        ThreadMethods tm = new ThreadMethods("Medium");
        ThreadMethods th = new ThreadMethods("High");
//        tl.setPriority(Thread.MIN_PRIORITY);
//        tm.setPriority(Thread.NORM_PRIORITY);
//        th.setPriority(Thread.MAX_PRIORITY);
        tl.setDaemon(true);
        tl.start();
//        tm.start();
//        th.start();
//        tm.setName("MyCustomThread");
//th.interrupt();
        System.out.println("hello1");
//        tl.join();
//        tm.join();
//        th.join();
        System.out.println("this line will be printed when All thread will complete execution");
    }


}
