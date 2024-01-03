package multithreading.concerns;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLockExample {
    private static int sharedResource = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 100000; i++) {
                acquireLockAndIncrement();
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

    private static void acquireLockAndIncrement() {
        lock.lock();
        try {
            sharedResource++;
        } finally {
            lock.unlock();
        }
    }
}
