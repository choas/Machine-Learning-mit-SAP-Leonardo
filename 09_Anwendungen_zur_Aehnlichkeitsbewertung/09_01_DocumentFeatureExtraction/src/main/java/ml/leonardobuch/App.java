package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.document_feature_extraction_api.api.DocumentFeatureExtractionApi;
import com.sap.apibhub.sdk.document_feature_extraction_api.model.DocVectors;
import com.sap.apibhub.sdk.document_feature_extraction_api.model.Response;
import com.sap.apibhub.sdk.similarity_scoring_api.model.IdScore;
import com.sap.apibhub.sdk.similarity_scoring_api.model.IdTopSimilarVectors;
import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Document Feature Extraction.
 *
 */
public class App {
  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws ApiException, InterruptedException, IOException {

    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.setBasePath("https://sandbox.api.sap.com/ml/docfeatureextraction");

    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    DocumentFeatureExtractionApi apiInstance = new DocumentFeatureExtractionApi();
    apiInstance.setApiClient(apiClient);
    
    String fileName = Settings.PATH + "wikipedia_en.zip";

    File files = new File(fileName);
    Response result = apiInstance.inferenceSyncPost(files, null);
    System.out.println(result);

    List<String> docNames = new ArrayList<String>();

    List<List<BigDecimal>> features = new ArrayList<List<BigDecimal>>();
    for (DocVectors docVectors : result.getDocVectors()) {
      List<BigDecimal> feature = new ArrayList<BigDecimal>();
      features.add(feature);
      for (Float f : docVectors.getEmbedding()) {
        feature.add(new BigDecimal(Float.toString(f)));
      }
      docNames.add(docVectors.getId().split("\\.")[0]);
    }

    SimilarityScoring scoring = new SimilarityScoring();
    ResponseOk scores = scoring.similarityScoring(features, features, null, null);

    for (IdTopSimilarVectors pred : scores.getPredictions()) {
      int id = Integer.valueOf(pred.getId().substring(1));
      IdScore idScore0 = pred.getSimilarVectors().get(0);
      int id0 = Integer.valueOf(idScore0.getId());
      BigDecimal score0 = idScore0.getScore();
      IdScore idScore1 = pred.getSimilarVectors().get(1);
      int id1 = Integer.valueOf(idScore1.getId());
      BigDecimal score1 = idScore1.getScore();
      String f = String.format("%-21s ~ %-21s %.6f (%.6f %s)", 
          docNames.get(id), docNames.get(id0), score0.floatValue(),
          score1.floatValue(), docNames.get(id1));
      System.out.println(f);
    }

  }
}
