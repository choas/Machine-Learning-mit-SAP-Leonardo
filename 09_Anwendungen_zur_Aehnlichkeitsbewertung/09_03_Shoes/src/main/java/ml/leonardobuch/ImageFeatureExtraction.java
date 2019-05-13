package ml.leonardobuch;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.img_feature_extraction_api.api.FeatureExtractionApi;
import com.sap.apibhub.sdk.img_feature_extraction_api.model.FeatureVector;
import com.sap.apibhub.sdk.img_feature_extraction_api.model.ResponseOk;

public class ImageFeatureExtraction {
	
	public static List<List<BigDecimal>> imageFeatureExtraction(File files) throws ApiException {

		// TODO API call
		ApiClient apiClient = Configuration.getDefaultApiClient();
		apiClient.setBasePath("https://sandbox.api.sap.com/ml/imagefeatureextraction");
		apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

		Map<String, Authentication> auths = 
				apiClient.getAuthentications();
		auths.put("APIBHUB_SANDBOX_APIKEY", 
				new ApiKeyAuth("header", "APIKey"));
		auths.put("Oauth2_ClientCredentials", 
				new OAuth());

		FeatureExtractionApi apiInstance = 
				new FeatureExtractionApi();
		apiInstance.setApiClient(apiClient);
        //34567890123456789012345678901234567890123456789012345678901
		ResponseOk result = apiInstance.pOSTInferenceSync(
				files,
				null);
		System.out.println(result);
		
		// TODO feature vectors

		List<List<BigDecimal>> features = 
				new ArrayList<List<BigDecimal>>();

		for (FeatureVector prediction : result.getPredictions()) {
			features.add(prediction.getFeatureVectors());
		}

		return features;
	}

}
