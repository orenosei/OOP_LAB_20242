package hust.soict.hedspi.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "/Users/thanh/Documents/HUST/2024_2/OOP_lab/OtherProjects/src/hust/soict/hedspi/garbage/trashOnly.txt";
        try {
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            long startTime = System.currentTimeMillis();

            StringBuilder outputStringBuilder = new StringBuilder();
            for (byte b : inputBytes) {
                outputStringBuilder.append((char) b);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken (NoGarbage): " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
