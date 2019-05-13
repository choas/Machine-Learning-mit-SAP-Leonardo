package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.face_feature_extraction_api.api.FaceFeatureExtractionApi;
import com.sap.apibhub.sdk.face_feature_extraction_api.model.BoundaryBox;
import com.sap.apibhub.sdk.face_feature_extraction_api.model.Face;
import com.sap.apibhub.sdk.face_feature_extraction_api.model.ResponseOk;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class FaceFeatureExtraction {

  public static final int SIZE = 80;

  List<Image> faceImages = new ArrayList<Image>();
  List<List<BigDecimal>> faceFeatures = new ArrayList<List<BigDecimal>>();

  /**
   * extraction Methode.
   * 
   * @param fileName Dateiname
   * @throws IOException IO Exception
   * @throws ApiException API Exception
   */
  public void extraction(String fileName) throws IOException, ApiException {
    // TODO API call
    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    FaceFeatureExtractionApi apiInstance = new FaceFeatureExtractionApi();
    apiInstance.setApiClient(apiClient);

    File files = new File(fileName);
    ResponseOk result = apiInstance.pOSTInferenceSync(files);
    System.out.println(result);

    // TODO face images and features
    BufferedImage image = ImageIO.read(new File(fileName));
    for (Face face : result.getPredictions().get(0).getFaces()) {

      BoundaryBox boundaryBox = face.getFaceLocation();
      int x = boundaryBox.getLeft().intValue();
      int y = boundaryBox.getTop().intValue();
      int width = boundaryBox.getRight().subtract(boundaryBox.getLeft()).intValue();
      int height = boundaryBox.getBottom().subtract(boundaryBox.getTop()).intValue();

      Image faceImage = image.getSubimage(x, y, width, height)
          .getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);

      faceImages.add(faceImage);
      faceFeatures.add(face.getFaceFeature());
    }
  }

}
