/*
 * Inference Service for Scene Text Recognition
 * A functional service for extracting texts from images.
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.sap.apibhub.sdk.scene_text_recognition_api.api;

import com.sap.apibhub.sdk.client.ApiCallback;
import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.ApiResponse;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.Pair;
import com.sap.apibhub.sdk.client.ProgressRequestBody;
import com.sap.apibhub.sdk.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.io.File;
import com.sap.apibhub.sdk.scene_text_recognition_api.model.ResponseError;
import com.sap.apibhub.sdk.scene_text_recognition_api.model.ResponseOk;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneTextRecognitionApi {
    private ApiClient apiClient;

    public SceneTextRecognitionApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SceneTextRecognitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for pOSTInferenceSync */
    private com.squareup.okhttp.Call pOSTInferenceSyncCall(File files, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/scene-text-recognition".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (files != null)
        localVarFormParams.put("files", files);

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {"APIBHUB_SANDBOX_APIKEY", "Oauth2_ClientCredentials" };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call pOSTInferenceSyncValidateBeforeCall(File files, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'files' is set
        if (files == null) {
            throw new ApiException("Missing the required parameter 'files' when calling pOSTInferenceSync(Async)");
        }
        
        
        com.squareup.okhttp.Call call = pOSTInferenceSyncCall(files, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Synchronously extract texts from images.
     * Extract detected texts from each image and additionally return bounding boxes and information regarding detected texts.
     * @param files &lt;p&gt;Image from which to extract texts. Please provide a single color image file having dimensions equal or greater than 64 pixels. **&lt;ul&gt; Accepted file extensions:   &lt;li&gt;Image file: jpg jpe jpeg png** (required)
     * @return ResponseOk
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ResponseOk pOSTInferenceSync(File files) throws ApiException {
        ApiResponse<ResponseOk> resp = pOSTInferenceSyncWithHttpInfo(files);
        return resp.getData();
    }

    /**
     * Synchronously extract texts from images.
     * Extract detected texts from each image and additionally return bounding boxes and information regarding detected texts.
     * @param files &lt;p&gt;Image from which to extract texts. Please provide a single color image file having dimensions equal or greater than 64 pixels. **&lt;ul&gt; Accepted file extensions:   &lt;li&gt;Image file: jpg jpe jpeg png** (required)
     * @return ApiResponse&lt;ResponseOk&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ResponseOk> pOSTInferenceSyncWithHttpInfo(File files) throws ApiException {
        com.squareup.okhttp.Call call = pOSTInferenceSyncValidateBeforeCall(files, null, null);
        Type localVarReturnType = new TypeToken<ResponseOk>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Synchronously extract texts from images. (asynchronously)
     * Extract detected texts from each image and additionally return bounding boxes and information regarding detected texts.
     * @param files &lt;p&gt;Image from which to extract texts. Please provide a single color image file having dimensions equal or greater than 64 pixels. **&lt;ul&gt; Accepted file extensions:   &lt;li&gt;Image file: jpg jpe jpeg png** (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call pOSTInferenceSyncAsync(File files, final ApiCallback<ResponseOk> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = pOSTInferenceSyncValidateBeforeCall(files, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ResponseOk>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
