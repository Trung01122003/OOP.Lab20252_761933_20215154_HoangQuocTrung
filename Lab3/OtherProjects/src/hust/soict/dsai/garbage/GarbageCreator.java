package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "test.exe"; // Assume reading a large file e.g. executable
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        try {
            // Generating a dummy file for testing because we need a large file
            System.out.println("Generating dummy large file...");
            byte[] dummyData = new byte[2 * 1024 * 1024]; // 2 MB
            Files.write(Paths.get(filename), dummyData);
            
            inputBytes = Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        startTime = System.currentTimeMillis();
        String outputString = "";
        for (byte b : inputBytes) {
            outputString += (char) b;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Processing time with + operator: " + (endTime - startTime) + " ms");
        
        // Clean up dummy file
        try {
            Files.deleteIfExists(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
