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
public class DetectLanguageAppRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 108708305662415313L;

  @Builder.Default
  private String modelLocation = "global";

  @NotEmpty(message = "Text is required")
  private String text;
}
