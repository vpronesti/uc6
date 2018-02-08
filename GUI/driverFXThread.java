package GUI;

public class driverFXThread {
    public static void main (String args[]){
        Thread t1 = new Thread(new GuiMain());
        System.out.println(Thread.currentThread().getId());
        /*
        from java error
        -Exception in thread "Thread-0" java.lang.IllegalStateException: Application launch must not be called more than once-
        proof of javafx cannot be multithread :(
         */
        t1.start();
     }
}
