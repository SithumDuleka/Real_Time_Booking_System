package ticketbooking.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class logger {
    public logger(){
        File file = new File("Logger.txt");

        // If the file already exists, delete it
        if (file.exists()) {
            file.delete();
        }
    }
    public void logthis(String text) {
        FileWriter fw;
        try {
            fw = new FileWriter("Logger.txt", true); //
            fw.write(text + "\n");
            fw.flush();
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException("Error writing to the log file: " + e.getMessage());
        }
    }
}