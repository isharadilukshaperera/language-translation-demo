package com.example.language_translation_demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateTextAppRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = -7999224810714046474L;

  @NotEmpty(message = "Source language is missing")
  private String sourceLanguage;

  @NotEmpty(message = "Target language is missing")
  private String targetLanguage;

  @Builder.Default
  private String modelLocation = "global";

  @NotEmpty
  private String text;
}
