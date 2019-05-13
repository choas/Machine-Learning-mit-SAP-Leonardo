/*
 * Inference Service for Face Feature Extraction
 * A functional service for detecting faces in an image / images and extracting feature vectors out of them
 *
 * OpenAPI spec version: Alpha
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.face_feature_extraction_api.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sap.apibhub.sdk.face_feature_extraction_api.model.ImageFaces;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * ResponseOk
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-05-12T16:21:48.336Z")
public class ResponseOk {
  @SerializedName("id")
  private String id = null;

  @SerializedName("predictions")
  private List<ImageFaces> predictions = null;

  @SerializedName("processedTime")
  private DateTime processedTime = null;

  /**
   * Indicator of calculation success
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    DONE("DONE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("status")
  private StatusEnum status = null;

  public ResponseOk id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Job Id
   * @return id
  **/
  @ApiModelProperty(value = "Job Id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResponseOk predictions(List<ImageFaces> predictions) {
    this.predictions = predictions;
    return this;
  }

  public ResponseOk addPredictionsItem(ImageFaces predictionsItem) {
    if (this.predictions == null) {
      this.predictions = new ArrayList<ImageFaces>();
    }
    this.predictions.add(predictionsItem);
    return this;
  }

   /**
   * Get predictions
   * @return predictions
  **/
  @ApiModelProperty(value = "")
  public List<ImageFaces> getPredictions() {
    return predictions;
  }

  public void setPredictions(List<ImageFaces> predictions) {
    this.predictions = predictions;
  }

  public ResponseOk processedTime(DateTime processedTime) {
    this.processedTime = processedTime;
    return this;
  }

   /**
   * Get processedTime
   * @return processedTime
  **/
  @ApiModelProperty(value = "")
  public DateTime getProcessedTime() {
    return processedTime;
  }

  public void setProcessedTime(DateTime processedTime) {
    this.processedTime = processedTime;
  }

  public ResponseOk status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Indicator of calculation success
   * @return status
  **/
  @ApiModelProperty(value = "Indicator of calculation success")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseOk responseOk = (ResponseOk) o;
    return Objects.equals(this.id, responseOk.id) &&
        Objects.equals(this.predictions, responseOk.predictions) &&
        Objects.equals(this.processedTime, responseOk.processedTime) &&
        Objects.equals(this.status, responseOk.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, predictions, processedTime, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseOk {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    predictions: ").append(toIndentedString(predictions)).append("\n");
    sb.append("    processedTime: ").append(toIndentedString(processedTime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
