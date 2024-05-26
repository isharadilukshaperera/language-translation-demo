package com.example.language_translation_demo.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectLanguageAppResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -6485161329629182111L;

  private String detectedLanguageCode;
  private Status status;
  private String statusDescription;
}
