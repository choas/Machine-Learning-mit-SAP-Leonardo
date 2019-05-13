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
   * @return Similarity Scoring Ergebnis
   * @throws ApiException API Exception
   */
  public static ResponseOk similarityScoring(
      List<List<BigDecimal>> features0, List<List<BigDecimal>> features1)
      throws ApiException {

    // TODO options and texts
    String options = " {\"numSimilarVectors\":" + (features0.size() - 1) + "}";

    String texts;
    texts = "{\"0\":"; // 1. Datensatz
    texts += featureVectors(features0, "m");
    if (features1 != null) {
      options = " {\"numSimilarVectors\":" + features1.size() + "}";
      texts += ",";
      texts += "\"1\":"; // 2. Datensatz
      texts += featureVectors(features1, "");
    }
    texts += "}";

    // TODO API call
    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.setBasePath("https://sandbox.api.sap.com/ml/similarityscoring");
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    SimilarityScoringApi apiInstance = new SimilarityScoringApi();
    apiInstance.setApiClient(apiClient);

    ResponseOk result = apiInstance.similarityScoringPost(options, null, texts);
    System.out.println(result);

    return result;
  }

  private static String featureVectors(List<List<BigDecimal>> features, String idPrefix) {

    // TODO featureVectors
    String vecTxt = "[";
    for (int index = 0; index < features.size(); index++) {
      String id = idPrefix + index;
      List<BigDecimal> vector = features.get(index);

      vecTxt += "{" + "\"id\": \"" + id + "\", \"vector\": " + vector + "}";
      if (index < features.size() - 1) {
        vecTxt += ",";
      }
    }
    vecTxt += "]";
    return vecTxt;
  }

}