package multithreading.concerns;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static int sharedResource = 0;
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 100000; i++) {
                acquireSemaphoreAndIncrement();
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

    private static void acquireSemaphoreAndIncrement() {
        try {
            semaphore.acquire();
            sharedResource++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
