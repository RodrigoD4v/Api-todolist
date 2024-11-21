package com.reclamala.todolist.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

  @Bean
  public Firestore firestore() throws IOException {
      // Obtém a variável de ambiente do Railway
      String firebaseCredentials = System.getenv("FIREBASE_KEY");

      if (firebaseCredentials == null) {
          throw new IllegalArgumentException("FIREBASE_KEY environment variable is not set.");
      }

      // Converte o conteúdo da variável de ambiente para um fluxo de entrada
      ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseCredentials.getBytes());

      FirebaseOptions options = new FirebaseOptions.Builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      // Inicializa o Firebase se ainda não foi inicializado
      if (FirebaseApp.getApps().isEmpty()) {
          FirebaseApp.initializeApp(options);
          System.out.println("Firebase App initialized successfully.");
      }

      // Obtém a instância do Firestore
      Firestore firestore = FirestoreClient.getFirestore();
      System.out.println("Firestore initialized successfully.");
      return firestore;
  }
}
