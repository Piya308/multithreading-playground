package org.priyanka.basics;
/*Why daemon still prints after main dies?
Because daemon threads continue running as long as:
AT LEAST ONE user thread exists
Here UserTyping thread is alive for 5 seconds.
So daemon thread also keeps running during that time.
Later that it automatically gets killed by jvm
*/
class AutoSave extends Thread {

    public void run() {

        while(true) {

            System.out.println("Document Autosaved");

            try {
                Thread.sleep(3000);
            }
            catch(Exception e) {}
        }
    }
}

class UserTyping extends Thread {

    public void run() {

        for (int i = 0; i < 5; i++){

            System.out.println("User Typing");

            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {}
        }
    }
}

public class ThreadMethodDaemon {

    public static void main(String[] args) throws InterruptedException {
        AutoSave auto = new AutoSave();
        UserTyping userTyping = new UserTyping();

        userTyping.start();

        auto.setDaemon(true);
        auto.start();

        System.out.println("User closed application");

        userTyping.join();
        System.out.println("usertyping thread state"+userTyping.getState()+"autosave thread state"+auto.getState());

        System.out.println("Main thread ends");
    }
}

//Output:
//User closed application
//Document Autosaved
//User Typing
//User Typing
//User Typing
//Document Autosaved
//User Typing
//User Typing
//usertyping thread stateTERMINATEDautosave thread stateTIMED_WAITING
//Main thread ends