/*
 * Inference Service for Optical Character Recognition (OCR)
 * Optical Character Recognition service takes an uploaded file and returns the text characters detected in the input.
 *
 * OpenAPI spec version: 2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.ocr_api.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * EmbeddedResponseError
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-29T21:35:14.638Z")
public class EmbeddedResponseError {
  @SerializedName("code")
  private String code = null;

  @SerializedName("message")
  private String message = null;

  public EmbeddedResponseError code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Technical code of the error, to be used for support purposes.
   * @return code
  **/
  @ApiModelProperty(value = "Technical code of the error, to be used for support purposes.")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public EmbeddedResponseError message(String message) {
    this.message = message;
    return this;
  }

   /**
   * An error message.
   * @return message
  **/
  @ApiModelProperty(value = "An error message.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmbeddedResponseError embeddedResponseError = (EmbeddedResponseError) o;
    return Objects.equals(this.code, embeddedResponseError.code) &&
        Objects.equals(this.message, embeddedResponseError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmbeddedResponseError {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

