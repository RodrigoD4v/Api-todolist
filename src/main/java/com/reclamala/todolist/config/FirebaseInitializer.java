package com.reclamala.todolist.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    @Bean
    public Firestore firestore() throws IOException {
        String serviceAccountPath = System.getenv("FIREBASE_KEY");

        if (serviceAccountPath == null || serviceAccountPath.isEmpty()) {
            throw new IllegalArgumentException("FIREBASE_KEY não está configurado nas variáveis de ambiente do Railway.");
        }

        FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Inicialize o Firebase App se ainda não foi inicializado
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase App initialized successfully.");
        }

        // Inicialize o Firestore
        Firestore firestore = FirestoreClient.getFirestore();
        System.out.println("Firestore initialized successfully.");
        return firestore;
    }
}
