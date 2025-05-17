

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.google.firebase.FirebaseApp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class MainApp {
    private static FirebaseService database;
    private static Timeline gameTimeline = new Timeline();
    private static int pageNumber = 0;
    static double width;
    static double height;
    static String font = "monospaced";
    static Scene scene;

    public static void main(String[] args) {

        try {
            // Copy the resource to a temp file
            InputStream jsonStream = MainApp.class.getResourceAsStream("/test.json");
            if (jsonStream == null) {
                throw new RuntimeException("Resource not found: health.json");
            }

            File tempFile = File.createTempFile("firebase-service-account", ".json");
            tempFile.deleteOnExit();  // Clean up after exit

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                jsonStream.transferTo(out);
            }

            // Use the temp file path for FirebaseService
            database = new FirebaseService(tempFile.getAbsolutePath(), "https://test-ce488-default-rtdb.firebaseio.com/");
            Thread.sleep(1000);
            System.out.println("FirebaseService initialized successfully");
            database.set("test3", "higgg");
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void frameEvent(Runnable action) {
        KeyFrame frame = new KeyFrame(Duration.millis(10), event -> {
            action.run();
        });
        gameTimeline.getKeyFrames().add(frame);
        gameTimeline.play();
    }
    public static Scene getScene() {
        return scene;
    }
    public static int getPageNumber() {
        return pageNumber;
    }
    public static void setPageNumber(int newPageNumber) {
        pageNumber = newPageNumber;
    }
    public static double getWidth() {
        return width;
    }
    public static double getHeight() {
        return height;
    }
    public static String getFont() {
        return font;
    }

}
