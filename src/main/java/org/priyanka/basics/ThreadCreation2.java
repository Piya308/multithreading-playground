package org.priyanka.basics;

public class ThreadCreation2{

    public static void main(String[] args) {

        World1 world = new World1();
        Thread t1 = new Thread(world);
        t1.start();

        for (; ;){
            System.out.println("Hello");
        }

    }
}

class World1 implements Runnable{
    @Override
    public void run() {
//        for (int i = 0; i < 100; i++) {
             for(; ;){
                 System.out.println("World");
//            System.out.println(Thread.currentThread().getName());
        }
    }
}
