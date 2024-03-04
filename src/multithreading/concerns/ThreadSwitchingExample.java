package multithreading.concerns;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadSwitchingExample {
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private static int thread2Seconds = 0;

    public static void main(String[] args) {
        Runnable thread1 = () -> {
            int counter = 0;
            while (true) {
                counter++;

                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2 has been running for " + thread2Seconds + " seconds");
            }
        };

        Runnable thread2 = () -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                thread2Seconds++;
            }
        };

        scheduler.scheduleAtFixedRate(thread1, 0, 2, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(thread2, 1, 2, TimeUnit.SECONDS);
    }
}

/*public class ThreadSwitchingExample {
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private static volatile int progress = 0;
    public static void main(String[] args) {
        Runnable thread1 = () -> {
            // Your Thread 1 logic here
        };

        Runnable thread2 = () -> {
            System.out.println("Measuring " + progress++);
            // Your Thread 2 logic here
        };

        scheduler.scheduleAtFixedRate(thread1, 0, 2, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(thread2, 1, 2, TimeUnit.SECONDS);
    }
}*/

/*public class ThreadSwitchingExample {
    private static volatile boolean runThread1 = true;
    private static volatile int progress = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (runThread1) {
                    //System.out.println("Thread 1 is running");
                    // Your Thread 1 logic here

                    try {
                        progress++;
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runThread1 = false; // Switch to Thread 2
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                if (!runThread1) {
                    System.out.print("Thread 2 is running > ");
                    // Your Thread 2 logic here

                    try {
                        System.out.println(progress);
                        Thread.sleep(1);// Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runThread1 = true; // Switch to Thread 1
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}*/
