package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.ocr_api.api.OCRApi;
import com.sap.apibhub.sdk.ocr_api.model.ResponseJobSubmitted;
import com.sap.apibhub.sdk.ocr_api.model.ResponseResult;

import java.io.File;
import java.util.Map;

/**
 * OCR.
 *
 */
public class OCR {

  OCRApi apiInstance;

  /**
   * Konstruktor.
   */
  public OCR() {
    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    apiInstance = new OCRApi();
    apiInstance.setApiClient(apiClient);
  }

  public ResponseResult ocr(String fileName, String options) throws ApiException {
    File files = new File(fileName);
    return apiInstance.ocrPost(files, options);
  }

  public ResponseJobSubmitted ocrJob(String fileName, String options) throws ApiException {
    File files = new File(fileName);
    return apiInstance.ocrJobsPost(files, options);
  }

  public ResponseResult getOcrJob(String id) throws ApiException {
    return apiInstance.ocrJobsIdGet(id);
  }

}
