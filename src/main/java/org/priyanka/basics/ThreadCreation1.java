package org.priyanka.basics;

public class ThreadCreation1 {
    public static void main(String[] args) {
        World world = new World();
        world.start();
//        for (int i = 0; i < 100; i++) {
        for(; ;){

            System.out.println(Thread.currentThread().getName()); //main thread
        }    }
}

 class World extends Thread{
     @Override
     public void run() {
//         for (int i = 0; i < 100; i++) {
             for(; ;){
             System.out.println(Thread.currentThread().getName()); //Thread 0
         }
     }
 }