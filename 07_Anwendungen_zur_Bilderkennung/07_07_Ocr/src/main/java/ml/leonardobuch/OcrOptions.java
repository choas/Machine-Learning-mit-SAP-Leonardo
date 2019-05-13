package ml.leonardobuch;

import java.util.ArrayList;
import java.util.List;

public class OcrOptions {
  String[] lang;
  String outputType;
  Integer pageSegMode;
  String modelType;

  /**
   * Gibt den options String zurueck.
   * 
   * @return options String
   */
  public String options() {
    List<String> options = new ArrayList<String>();
    if (lang != null) {
      options.add("\"lang\":\"" + String.join(",", lang) + "\"");
    }
    if (outputType != null) {
      options.add("\"outputType\":\"" + outputType + "\"");
    }
    if (pageSegMode != null) {
      options.add("\"pageSegMode\":\"" + pageSegMode + "\"");
    }
    if (modelType != null) {
      options.add("\"modelType\":\"" + modelType + "\"");
    }

    String ret = "{" + String.join(",", options) + "}";
    System.out.println("OPTIONS: " + ret);

    return ret;
  }

  public void setLang(String[] lang) {
    this.lang = lang;
  }

  public void setOutputType(String outputType) {
    this.outputType = outputType;
  }

  public void setPageSegMode(Integer pageSegMode) {
    this.pageSegMode = pageSegMode;
  }

  public void setModelType(String modelType) {
    this.modelType = modelType;
  }
}