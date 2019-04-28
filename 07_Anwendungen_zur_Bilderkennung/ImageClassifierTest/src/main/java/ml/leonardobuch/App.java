package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.image_classification_api.api.ImageClassificationApi;
import com.sap.apibhub.sdk.image_classification_api.model.ResponseOk;

import java.io.File;
import java.util.Map;

/**
 * Image Classification Test Application.
 *
 */
public class App {
  
  /**
   * Die main Methode der ImageClassifierTest-Klasse.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) {
    
    final String fileName = Settings.PATH + "/elephant-114543_640.jpg";

    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    ImageClassificationApi apiInstance = new ImageClassificationApi();
    apiInstance.setApiClient(apiClient);

    File files = new File(fileName);
    try {
      ResponseOk result = apiInstance.pOSTInferenceSync(files);
      System.out.println(result);
      System.out.println(result.getPredictions().get(0).getResults().get(0).getLabel());
    } catch (ApiException e) {
      System.err.println("ApiException:");
      System.err.println(e.getResponseBody());
    }
  }

}
