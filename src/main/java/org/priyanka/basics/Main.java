package org.priyanka.basics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private int balance = 100;

    private final Lock lock = new ReentrantLock();
    void withdraw(int amount) {
        {
            System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
            try{
                if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                    if (balance >= amount) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " proceeding to withdraw" + amount);
                            Thread.sleep(3000);
                            balance -= amount;
                            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining amount: " + balance);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " insuffiecient balance");
                    }
                    System.out.println("remaining balance" + balance);
                }else{
                    System.out.println(Thread.currentThread().getName() +"Could not acquire the locck, will try later.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

public class Main{
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(20);
            }
        };

        Thread t1 = new Thread(task, "thread1");
        Thread t2 = new Thread(task, "thread2");
        t1.start();
        t2.start();
    }
}
