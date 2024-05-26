package com.example.language_translation_demo.service;

import com.example.language_translation_demo.dto.*;
import com.google.cloud.translate.v3.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TextManagementServiceImpl implements TextManagementService {

  private static final String DEFAULT_MIME_TYPE = "text/plain";

  @Value("${google.cloud.translate.default.project.id}")
  private String googleCloudTranslateDefaultProjectId;

  private final GoogleCloudTranslateCredentialsProvider credentialsProvider;

  @Override
  public DetectLanguageAppResponse detectLanguage(
      final DetectLanguageAppRequest detectLanguageAppRequest) {

    try (TranslationServiceClient client = credentialsProvider.getTranslationServiceClient()) {

      final String modelLocation = detectLanguageAppRequest.getModelLocation();
      final String content = detectLanguageAppRequest.getText();

      LocationName parent = LocationName.of(googleCloudTranslateDefaultProjectId, modelLocation);

      final DetectLanguageRequest request =
          DetectLanguageRequest.newBuilder()
              .setParent(parent.toString())
              .setMimeType(DEFAULT_MIME_TYPE)
              .setContent(content)
              .build();

      final DetectLanguageResponse response = client.detectLanguage(request);

      if (!response.getLanguagesList().isEmpty()) {
        final String languageCode = response.getLanguagesList().get(0).getLanguageCode();
        final String displayName = Locale.forLanguageTag(languageCode).getDisplayLanguage();
        return DetectLanguageAppResponse.builder()
            .detectedLanguageCode(languageCode)
            .status(Status.SUCCESS)
            .statusDescription("Detected language : " + displayName)
            .build();
      }
    } catch (IOException e) {
      return DetectLanguageAppResponse.builder()
          .status(Status.ERROR)
          .statusDescription(e.getMessage())
          .build();
    }

    return DetectLanguageAppResponse.builder()
        .status(Status.ERROR)
        .statusDescription("No languages detected")
        .build();
  }

  @Override
  public TranslateTextAppResponse translateText(
      final TranslateTextAppRequest translateTextAppRequest) {

    try (TranslationServiceClient client = credentialsProvider.getTranslationServiceClient()) {

      final String modelLocation = translateTextAppRequest.getModelLocation();
      // Supported Locations: `global`, [glossary location], or [model location]
      // Glossaries must be hosted in `us-central1`
      // Custom Models must use the same location as your model. (us-central1)
      final LocationName parent =
          LocationName.of(googleCloudTranslateDefaultProjectId, modelLocation);

      final TranslateTextRequest request =
          TranslateTextRequest.newBuilder()
              .setParent(parent.toString())
              .setMimeType(DEFAULT_MIME_TYPE)
              .setSourceLanguageCode(translateTextAppRequest.getSourceLanguage())
              .setTargetLanguageCode(translateTextAppRequest.getTargetLanguage())
              .addContents(translateTextAppRequest.getText())
              .build();

      final TranslateTextResponse response = client.translateText(request);

      // Return the translated String
      if (!response.getTranslationsList().isEmpty()) {
        return TranslateTextAppResponse.builder()
            .translatedText(response.getTranslationsList().get(0).getTranslatedText())
            .status(Status.SUCCESS)
            .statusDescription(Status.SUCCESS.name())
            .build();
      }
    } catch (IOException e) {
      return TranslateTextAppResponse.builder()
          .status(Status.ERROR)
          .statusDescription(e.getMessage())
          .build();
    }

    return TranslateTextAppResponse.builder()
        .status(Status.ERROR)
        .statusDescription("No languages detected")
        .build();
  }
}
