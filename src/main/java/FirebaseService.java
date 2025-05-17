

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class FirebaseService {
    private DatabaseReference dbRef;

    public FirebaseService(String serviceAccountFilePath, String databaseUrl) throws IOException {
        FileInputStream serviceAccount = new FileInputStream(serviceAccountFilePath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public void set(String key, String value) {
        ApiFuture<Void> future = dbRef.child(key).setValueAsync(value);
        try {
            future.get();  // blocks until write completes or fails
            System.out.println("Write to key '" + key + "' succeeded.");
        } catch (Exception e) {
            System.err.println("Write to key '" + key + "' failed: " + e.getMessage());
        }
    }

    public CompletableFuture<String> get(String key) {
        CompletableFuture<String> future = new CompletableFuture<>();
        dbRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    future.complete(snapshot.getValue(String.class));
                } else {
                    future.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(new RuntimeException("Database error: " + error.getMessage()));
            }
        });
        return future;
    }
}
