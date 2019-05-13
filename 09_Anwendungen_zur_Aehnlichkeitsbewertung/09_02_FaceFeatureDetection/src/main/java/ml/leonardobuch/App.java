package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

public class App {

  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws IOException, ApiException {
    // TODO feature extraction

    FaceFeatureExtraction ffM = new FaceFeatureExtraction(); 
    FaceFeatureExtraction ffT = new FaceFeatureExtraction();
    ffM.extraction(Settings.PATH + "people/man-3365368_640.jpg");
    ffT.extraction(Settings.PATH + "people/african-american-3548081_640.jpg");

    // TODO similarity scoring
    ResponseOk scores = SimilarityScoring.similarityScoring(ffM.faceFeatures, ffT.faceFeatures);

    // TODO grid
    JFrame frame = new JFrame();
    frame.add(new Grid(ffM.faceImages, ffT.faceImages, 
        scores, FaceFeatureExtraction.SIZE, 
        FaceFeatureExtraction.SIZE,
        Color.BLACK));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

  }
}
