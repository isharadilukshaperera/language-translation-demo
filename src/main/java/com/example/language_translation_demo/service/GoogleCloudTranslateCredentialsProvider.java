package com.example.language_translation_demo.service;

import org.springframework.beans.factory.annotation.Value;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.v3.TranslationServiceClient;
import com.google.cloud.translate.v3.TranslationServiceSettings;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class GoogleCloudTranslateCredentialsProvider {

  // Google credentials file path
  @Value("#{'/' + '${google.cloud.translate.default.credentials.file.path}'}")
  private String credentialsPath;

  public TranslationServiceClient getTranslationServiceClient() throws IOException {

    try (final InputStream credentialsStream = getClass().getResourceAsStream(credentialsPath)) {
      // Initialize Google credentials object
      assert credentialsStream != null;
      final GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

      // Initialize Google FixedCredentialsProvider
      final FixedCredentialsProvider credentialsProvider =
          FixedCredentialsProvider.create(credentials);

      // Initialize TranslationServiceSettings object
      final TranslationServiceSettings translateServiceSettings =
          TranslationServiceSettings.newBuilder()
              .setCredentialsProvider(credentialsProvider)
              .build();

      // Return TranslationServiceClient object
      return TranslationServiceClient.create(translateServiceSettings);
    }
  }
}
