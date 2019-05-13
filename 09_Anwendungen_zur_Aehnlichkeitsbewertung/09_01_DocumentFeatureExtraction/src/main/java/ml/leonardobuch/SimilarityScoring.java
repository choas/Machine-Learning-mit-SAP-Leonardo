package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.similarity_scoring_api.api.SimilarityScoringApi;
import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SimilarityScoring {

  /**
   * similarityScoring Methode.
   * 
   * @param features0 1. feature Wert
   * @param features1 2. feature Wert
   * @param ids0 1. IDs (optional)
   * @param ids1 2. IDs (optional)
   * @return Similarity Scoring Ergebnis
   * @throws ApiException API Exception
   */
  public ResponseOk similarityScoring(
      List<List<BigDecimal>> features0, List<List<BigDecimal>> features1,
      List<String> ids0, List<String> ids1) throws ApiException {

    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.setBasePath("https://sandbox.api.sap.com/ml/similarityscoring");
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    SimilarityScoringApi apiInstance = new SimilarityScoringApi();
    apiInstance.setApiClient(apiClient);

    String options = " {\"numSimilarVectors\":" + (features0.size() - 1) + "}";

    String texts;
    texts = "{\"0\":"; // 1. Datensatz
    texts += featureVectors(features0, "m", ids0);
    if (features1 != null) {
      texts += ",";
      texts += "\"1\":"; // 2. Datensatz
      texts += featureVectors(features1, "", ids1);

      options = " {\"numSimilarVectors\":" + features1.size() + "}";
    }
    texts += "}";

    ResponseOk result = apiInstance.similarityScoringPost(options, null, texts);
    System.out.println(result);

    return result;
  }

  private String featureVectors(
      List<List<BigDecimal>> faceFeatures, String idPrefix, List<String> ids) {
    String vecTxt = "[";
    for (int index = 0; index < faceFeatures.size(); index++) {
      String id = idPrefix + index;
      if (ids != null) {
        id = ids.get(index);
      }
      List<BigDecimal> vector = faceFeatures.get(index);
      vecTxt += "{" + "\"id\": \"" + id + "\", \"vector\": " + vector + "}";
      if (index < faceFeatures.size() - 1) {
        vecTxt += ",";
      }
    }
    vecTxt += "]";
    return vecTxt;
  }

}