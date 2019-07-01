package firebase;

import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Database {

    private  final String PRIVATEKEYLOCATION = "/iipsen-f7b65-firebase-adminsdk-si9zo-13167bfb98.json";
    private static final String DATABASEURL = "https://iipsen-f7b65.firebaseio.com";
    private Firestore db;


    public Database() {

        try {
            InputStream tmp = getClass().getResourceAsStream(PRIVATEKEYLOCATION);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(tmp))
                    .setDatabaseUrl(DATABASEURL)
                    .build();

            FirebaseApp.initializeApp(options);
            this.db = FirestoreClient.getFirestore();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirestoreDatabase() {
        return this.db;
    }
}