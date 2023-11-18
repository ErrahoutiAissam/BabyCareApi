package com.errahouti.BabyCareApi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseStorageConfig {

   @Value("${firebase.serviceAccountKeyPath}")
   private String firebaseConfigPath;


   @Bean
   public Storage firebaseStorage() throws IOException {
      FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
      StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials).build();
      return options.getService();
   }

}
