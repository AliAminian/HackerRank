package multithreading.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFrameworkExample {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed-size thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the executor
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simulate some task execution time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " has completed.");
            });
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}
