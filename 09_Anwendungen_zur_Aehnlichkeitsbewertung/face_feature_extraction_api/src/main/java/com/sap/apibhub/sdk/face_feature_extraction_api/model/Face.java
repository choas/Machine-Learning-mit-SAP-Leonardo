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
import com.sap.apibhub.sdk.face_feature_extraction_api.model.BoundaryBox;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Info about a face, consisting a boundary box and feature vector
 */
@ApiModel(description = "Info about a face, consisting a boundary box and feature vector")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-05-12T16:21:48.336Z")
public class Face {
  @SerializedName("face_location")
  private BoundaryBox faceLocation = null;

  @SerializedName("face_feature")
  private List<BigDecimal> faceFeature = null;

  public Face faceLocation(BoundaryBox faceLocation) {
    this.faceLocation = faceLocation;
    return this;
  }

   /**
   * Get faceLocation
   * @return faceLocation
  **/
  @ApiModelProperty(value = "")
  public BoundaryBox getFaceLocation() {
    return faceLocation;
  }

  public void setFaceLocation(BoundaryBox faceLocation) {
    this.faceLocation = faceLocation;
  }

  public Face faceFeature(List<BigDecimal> faceFeature) {
    this.faceFeature = faceFeature;
    return this;
  }

  public Face addFaceFeatureItem(BigDecimal faceFeatureItem) {
    if (this.faceFeature == null) {
      this.faceFeature = new ArrayList<BigDecimal>();
    }
    this.faceFeature.add(faceFeatureItem);
    return this;
  }

   /**
   * feature vector of the face
   * @return faceFeature
  **/
  @ApiModelProperty(value = "feature vector of the face")
  public List<BigDecimal> getFaceFeature() {
    return faceFeature;
  }

  public void setFaceFeature(List<BigDecimal> faceFeature) {
    this.faceFeature = faceFeature;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Face face = (Face) o;
    return Objects.equals(this.faceLocation, face.faceLocation) &&
        Objects.equals(this.faceFeature, face.faceFeature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faceLocation, faceFeature);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Face {\n");
    
    sb.append("    faceLocation: ").append(toIndentedString(faceLocation)).append("\n");
    sb.append("    faceFeature: ").append(toIndentedString(faceFeature)).append("\n");
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

