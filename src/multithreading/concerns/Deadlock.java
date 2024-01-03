package multithreading.concerns;

public class Deadlock {
    private static final Integer a = 2;
    private static final Integer b = 3;

    public static void main(String[] args) {
        Runnable r1 = () -> {
            synchronized (a) {
                System.out.println("thread 1: hold lock of a");
                try{
                   Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread 1: waiting for b");
                synchronized (b) {
                    System.out.println("thread 1: hold lock a and b");
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (b) { // fix: should be a
                System.out.println("thread 2: hold lock b");
                try{
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2: waiting for a");
                synchronized (a) { // fix: should be b
                    System.out.println("thread 2: hold lock a and b");
                }
            }
        };


        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

    }

}
