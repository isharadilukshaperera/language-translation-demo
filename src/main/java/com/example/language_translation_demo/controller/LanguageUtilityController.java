package com.example.language_translation_demo.controller;

import com.example.language_translation_demo.controller.common.RequestMappingConstants;
import com.example.language_translation_demo.dto.DetectLanguageAppRequest;
import com.example.language_translation_demo.dto.DetectLanguageAppResponse;
import com.example.language_translation_demo.dto.TranslateTextAppRequest;
import com.example.language_translation_demo.dto.TranslateTextAppResponse;
import com.example.language_translation_demo.service.TextManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestMappingConstants.UTILITY)
public class LanguageUtilityController {

  private final TextManagementService textManagementService;

  //   Detect language from given text
  @PostMapping(value = RequestMappingConstants.DETECT)
  public ResponseEntity<DetectLanguageAppResponse> detectLanguage(
      @Valid @RequestBody DetectLanguageAppRequest detectLanguageRequest) {

    final DetectLanguageAppResponse response =
        textManagementService.detectLanguage(detectLanguageRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  // Translate specific text from given language code
  @PostMapping(value = RequestMappingConstants.TRANSLATE)
  public ResponseEntity<TranslateTextAppResponse> translate(
      @Valid @RequestBody TranslateTextAppRequest translateTextAppRequest) {

    final TranslateTextAppResponse response =
        textManagementService.translateText(translateTextAppRequest);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
