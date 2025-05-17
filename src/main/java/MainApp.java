

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MainApp {
    private static FirebaseService database;

    public static void main(String[] args) {
        try {
            // Copy the resource to a temp file
            InputStream jsonStream = MainApp.class.getResourceAsStream("/health.json");
            if (jsonStream == null) {
                throw new RuntimeException("Resource not found: health.json");
            }

            File tempFile = File.createTempFile("firebase-service-account", ".json");
            tempFile.deleteOnExit();  // Clean up after exit

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                jsonStream.transferTo(out);
            }

            // Use the temp file path for FirebaseService
            database = new FirebaseService(tempFile.getAbsolutePath(), "https://healthapp-733e8-default-rtdb.firebaseio.com/");
            System.out.println("FirebaseService initialized successfully");
            database.set("anotherTest", "Hello, World!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
