package multithreading.concerns;

public class MonitorExample {
    private static int sharedResource = 0;

    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (MonitorExample.class) {
                    sharedResource++;
                }
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Shared Resource Value: " + sharedResource);
    }
}
