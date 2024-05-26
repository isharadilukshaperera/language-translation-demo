package com.example.language_translation_demo.service;

import com.example.language_translation_demo.dto.DetectLanguageAppRequest;
import com.example.language_translation_demo.dto.DetectLanguageAppResponse;
import com.example.language_translation_demo.dto.TranslateTextAppRequest;
import com.example.language_translation_demo.dto.TranslateTextAppResponse;

public interface TextManagementService {

  DetectLanguageAppResponse detectLanguage(DetectLanguageAppRequest request);

  TranslateTextAppResponse translateText(TranslateTextAppRequest request);
}
