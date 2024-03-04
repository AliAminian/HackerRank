package multithreading.concerns;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadSwitcher {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final Semaphore semaphore = new Semaphore(1);

    public void startThreads() {
        scheduler.scheduleAtFixedRate(this::runThreadA, 0, 2, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::runThreadB, 1, 2, TimeUnit.SECONDS);
    }

    private void runThreadA() {
        try {
            semaphore.acquire();
            System.out.println("Thread A is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void runThreadB() {
        try {
            semaphore.acquire();
            System.out.println("Thread B is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ThreadSwitcher switcher = new ThreadSwitcher();
        switcher.startThreads();

        // Keep the program running for a while to observe thread switching
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            switcher.scheduler.shutdownNow();
        }
    }
}
