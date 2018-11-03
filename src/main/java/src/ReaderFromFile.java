package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Max Hluhov on 02.11.2018.
 */
public class ReaderFromFile implements Runnable {

    public ReaderFromFile() {
        new Thread(this);
    }

    @Override
    public void run() {
        meth();
    }

    public synchronized void meth() {
        long start = System.currentTimeMillis();
        try {
            List<File> filesInFolder = Files.walk
                    (Paths.get("src\\main\\resources\\files"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File file : filesInFolder) {
                String fileName = file.getName();
                String fileContext;
                int sum = 0;
                fileContext =
                            new String(
                                    Files.readAllBytes(Paths.get(file.getPath())));
                char[] fileContextChars = fileContext.toCharArray();
                    for (int i = 0; i < fileContextChars.length; i++) {
                        sum += fileContextChars[i];
                    }
                System.out.println("file name: " + fileName + ", \n" +
                        "number of characters [" + sum + "]");
                long end = System.currentTimeMillis();
                System.out.println("time = " + (end - start) + " ms");
                System.out.println("|===========================|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
