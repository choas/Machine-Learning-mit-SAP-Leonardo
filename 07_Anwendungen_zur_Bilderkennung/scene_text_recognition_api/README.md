## This source has been genereated using swagger codegen (https://github.com/swagger-api/swagger-codegen/tree/v2.2.2)

# scene_text_recognition_api

## In case if you want to customize or enhance the generated project, import the project to an IDE of your choice and then follow the instructions given below to build, deploy and consume the library. 

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, execute the following command:

```shell
mvn install
```

To deploy the API client library to a remote Maven repository, configure the settings of the repository and execute the following command:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add the following dependency to your project's POM:

```xml
<dependency>
    <groupId>com.sap.apibhub</groupId>
    <artifactId>scene_text_recognition_api</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add the following dependency to your project's build file:

```groovy
compile "com.sap.apibhub:scene_text_recognition_api:1.0.0"
```

### Others

Generate the JAR by executing the following command:

    mvn package

Manually install the following JARs:

* target/scene_text_recognition_api-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instructions given above and execute the following sample Java code to consume an API:

```java

import com.sap.apibhub.sdk.client.*;
import com.sap.apibhub.sdk.client.auth.*;
import com.sap.apibhub.sdk.scene_text_recognition_api.model.*;
import com.sap.apibhub.sdk.scene_text_recognition_api.api.SceneTextRecognitionApi;

import java.io.File;
import java.util.*;

public class SceneTextRecognitionApiExample {

    public static void main(String[] args) {
    
		ApiClient defaultClient = Configuration.getDefaultApiClient(); 
		Map<String, Authentication> authentications = defaultClient.getAuthentications();       
		
		//Currently Base Path points to sandbox system, change it to call your API Endpoint
		defaultClient.setBasePath("https://sandbox.api.sap.com/mlfs/api/v2/image");
		//You can obtain your API key on the Settings page of SAP API Business Hub. In the Settings page, choose the Show API Key toggle button to display and copy your API key. You have to be logged in to view your API Key.
		defaultClient.addDefaultHeader("APIKey","<YOUR APIBHUB SANDBOX APIKEY>"); 		
		authentications.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
		            
        
		*/
        
		//Change Base Path, uncomment below code and configure OAuth2 Authorization to call your API Endpoint: Oauth2_ClientCredentials
		authentications.put("Oauth2_ClientCredentials", new OAuth());
		/* OAuth Oauth2_ClientCredentials = (OAuth) defaultClient.getAuthentication("Oauth2_ClientCredentials");
		Oauth2_ClientCredentials.setAccessToken("<YOUR_ACCESS_TOKEN>");
		*/		
        SceneTextRecognitionApi apiInstance = new SceneTextRecognitionApi();
        apiInstance.setApiClient(defaultClient);
        File files = new File("/path/to/file.txt"); // File | <p>Image from which to extract texts. Please provide a single color image file having dimensions equal or greater than 64 pixels. **<ul> Accepted file extensions:   <li>Image file: jpg jpe jpeg png**
        try {
            ResponseOk result = apiInstance.pOSTInferenceSync(files);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SceneTextRecognitionApi#pOSTInferenceSync");
            System.err.println("API Response : \n"+e.getResponseBody()); 
        }
    }
}

```

## Documentation for Authorization


Authentication schemes defined for the API:
### Oauth2_ClientCredentials

- **Type**: OAuth
- **Flow**: application
- **Authorizatoin URL**: 
- **Scopes**: N/A

 

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

