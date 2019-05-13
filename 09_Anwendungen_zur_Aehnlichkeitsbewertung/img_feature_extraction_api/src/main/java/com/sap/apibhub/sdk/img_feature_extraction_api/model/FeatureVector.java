/*
 * Inference Service For Customizable Image Feature Extraction
 * Extracts feature vectors for any given image which can be used for comparison, information retrieval, clustering, or further processing. Use this service with a pre-trained or your custom model.
 *
 * OpenAPI spec version: 2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.img_feature_extraction_api.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * FeatureVector
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-05-12T16:41:40.208Z")
public class FeatureVector {
  @SerializedName("featureVectors")
  private List<BigDecimal> featureVectors = null;

  @SerializedName("name")
  private String name = null;

  public FeatureVector featureVectors(List<BigDecimal> featureVectors) {
    this.featureVectors = featureVectors;
    return this;
  }

  public FeatureVector addFeatureVectorsItem(BigDecimal featureVectorsItem) {
    if (this.featureVectors == null) {
      this.featureVectors = new ArrayList<BigDecimal>();
    }
    this.featureVectors.add(featureVectorsItem);
    return this;
  }

   /**
   * Get featureVectors
   * @return featureVectors
  **/
  @ApiModelProperty(value = "")
  public List<BigDecimal> getFeatureVectors() {
    return featureVectors;
  }

  public void setFeatureVectors(List<BigDecimal> featureVectors) {
    this.featureVectors = featureVectors;
  }

  public FeatureVector name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the corresponding image.
   * @return name
  **/
  @ApiModelProperty(value = "The name of the corresponding image.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeatureVector featureVector = (FeatureVector) o;
    return Objects.equals(this.featureVectors, featureVector.featureVectors) &&
        Objects.equals(this.name, featureVector.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureVectors, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeatureVector {\n");
    
    sb.append("    featureVectors: ").append(toIndentedString(featureVectors)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
