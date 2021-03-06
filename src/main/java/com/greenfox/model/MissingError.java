package com.greenfox.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissingError extends StatusOkMessage{
  private String message;

  public MissingError() {
    super();
    this.message = "Missing field(s):";
  }

  public MissingError(String status, String message) {
    super(status);
    this.message = message;
  }
}
