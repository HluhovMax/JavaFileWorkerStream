package src;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Max Hluhov on 02.11.2018.
 */
public class ReaderDemo {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        System.out.println("Запуск потоков");
        executorService.execute(new ReaderFromFile());
        executorService.shutdown();
    }
}
