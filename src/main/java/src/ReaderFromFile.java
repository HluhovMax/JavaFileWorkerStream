package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
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
        Instant start = Instant.now();
        try {
            List<File> filesInFolder = Files.walk
                    (Paths.get("src\\main\\resources\\files"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File file : filesInFolder) {
                File localFile = file;
                String fileName = localFile.getName();
                String fileContext;
                try (BufferedReader reader =
                             new BufferedReader(new FileReader(localFile))) {
                    while ((fileContext = reader.readLine()) != null) {
                        fileContext = fileContext.trim();
                        char[] fileContextChars = fileContext.toCharArray();
                        Instant end = Instant.now();
                        System.out.println("file name: " + fileName + ", \n" +
                                "number of characters [" + fileContextChars.length + "]");
                        System.out.println("time = " + Duration.between(start, end) + " ms");
                        System.out.println("|===========================|");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
