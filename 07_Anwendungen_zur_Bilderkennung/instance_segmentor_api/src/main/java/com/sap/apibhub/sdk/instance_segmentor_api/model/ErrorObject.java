/*
 * Inference Service for Multi-Instance Image Segmentation
 * A functional service for multi-instance segmentation of consumer products and retail images.
 *
 * OpenAPI spec version: Alpha
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.instance_segmentor_api.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sap.apibhub.sdk.instance_segmentor_api.model.EmbeddedResponseError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ErrorObject
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-01-18T23:16:04.173Z")
public class ErrorObject {
  @SerializedName("requestId")
  private String requestId = null;

  @SerializedName("code")
  private String code = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("details")
  private List<EmbeddedResponseError> details = null;

  public ErrorObject requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

   /**
   * ID to trace down the request in logs.
   * @return requestId
  **/
  @ApiModelProperty(value = "ID to trace down the request in logs.")
  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public ErrorObject code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Technical code of the error situation to be used for support purposes.
   * @return code
  **/
  @ApiModelProperty(required = true, value = "Technical code of the error situation to be used for support purposes.")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorObject message(String message) {
    this.message = message;
    return this;
  }

   /**
   * An error message.
   * @return message
  **/
  @ApiModelProperty(required = true, value = "An error message.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorObject details(List<EmbeddedResponseError> details) {
    this.details = details;
    return this;
  }

  public ErrorObject addDetailsItem(EmbeddedResponseError detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<EmbeddedResponseError>();
    }
    this.details.add(detailsItem);
    return this;
  }

   /**
   * Get details
   * @return details
  **/
  @ApiModelProperty(value = "")
  public List<EmbeddedResponseError> getDetails() {
    return details;
  }

  public void setDetails(List<EmbeddedResponseError> details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorObject errorObject = (ErrorObject) o;
    return Objects.equals(this.requestId, errorObject.requestId) &&
        Objects.equals(this.code, errorObject.code) &&
        Objects.equals(this.message, errorObject.message) &&
        Objects.equals(this.details, errorObject.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, code, message, details);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorObject {\n");
    
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

