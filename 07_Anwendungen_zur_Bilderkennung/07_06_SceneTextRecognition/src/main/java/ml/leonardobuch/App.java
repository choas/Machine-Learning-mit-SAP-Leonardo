package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.scene_text_recognition_api.model.ExtractedBoxesDetails;
import com.sap.apibhub.sdk.scene_text_recognition_api.model.ResponseOk;

import java.awt.image.BufferedImage;

/**
 * Scene Text Recognition.
 *
 */
public class App {

  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws Exception {

    // TODO Bild generieren
    TextSceneImage tsimg = new TextSceneImage();
    tsimg.fontSize = 12;
    BufferedImage img = tsimg.generate();

    // TODO Scene Text Recognition
    SceneTextRecognition str = new SceneTextRecognition();
    String response = str.sceneTextRecognition(img);

    ApiClient apiClient = new ApiClient();
    ResponseOk responseOk = apiClient.getJSON().deserialize(response, ResponseOk.class);

    // TODO Ausgabe
    for (ExtractedBoxesDetails result : responseOk.getPredictions().get(0).getResults()) {
      System.out.println(result.getText());
    }

  }

}
