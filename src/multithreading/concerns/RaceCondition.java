package multithreading.concerns;

public class RaceCondition {
    /*
    However, keep in mind that while volatile provides visibility guarantees, it does not guarantee atomicity
    for compound operations, and in more complex scenarios, you might need additional synchronization mechanisms.
     */
    private static int counter = 0;
    // fix issue: private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 100000; i++)
                counter++; // fix issue: counter.getAndAdd(1);
        };

        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r);

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e ) {
            e.printStackTrace();
        }

        System.out.println("final counter value is " + counter);
    }
}
