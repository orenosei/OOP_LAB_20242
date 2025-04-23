package hust.soict.hedspi.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "/Users/thanh/Documents/HUST/2024_2/OOP_lab/OtherProjects/src/hust/soict/hedspi/garbage/trashOnly.txt";
        try {
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            long startTime = System.currentTimeMillis();

            String outputString = "";
            for (byte b : inputBytes) {
                outputString += (char) b;
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken (GarbageCreator): " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
