/*
 * Inference Service for Customizable Image Classification
 * Service for pre-trained and customized image classification models. The pre-trained model detects the dominant objects present in an image from a set of [1000](http://image-net.org/challenges/LSVRC/2014/browse-synsets) categories such as trees, animals, food, vehicles, people, and more.
 *
 * OpenAPI spec version: 2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.image_classification_api.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sap.apibhub.sdk.image_classification_api.model.ErrorObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ResponseError
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-11T20:04:29.401Z")
public class ResponseError {
  @SerializedName("error")
  private ErrorObject error = null;

  public ResponseError error(ErrorObject error) {
    this.error = error;
    return this;
  }

   /**
   * Get error
   * @return error
  **/
  @ApiModelProperty(value = "")
  public ErrorObject getError() {
    return error;
  }

  public void setError(ErrorObject error) {
    this.error = error;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseError responseError = (ResponseError) o;
    return Objects.equals(this.error, responseError.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseError {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

