package com.example.language_translation_demo.dto;

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
public class TranslateTextAppResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 376279592775557286L;

  private String translatedText;
  private Status status;
  private String statusDescription;
}
