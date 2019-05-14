## This source has been genereated using swagger codegen (https://github.com/swagger-api/swagger-codegen/tree/v2.2.2)

# similarity_scoring_api

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
    <artifactId>similarity_scoring_api</artifactId>
    <version>2.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add the following dependency to your project's build file:

```groovy
compile "com.sap.apibhub:similarity_scoring_api:2.0.0"
```

### Others

Generate the JAR by executing the following command:

    mvn package

Manually install the following JARs:

* target/similarity_scoring_api-2.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instructions given above and execute the following sample Java code to consume an API:

```java

import com.sap.apibhub.sdk.client.*;
import com.sap.apibhub.sdk.client.auth.*;
import com.sap.apibhub.sdk.similarity_scoring_api.model.*;
import com.sap.apibhub.sdk.similarity_scoring_api.api.SimilarityScoringApi;

import java.io.File;
import java.util.*;

public class SimilarityScoringApiExample {

    public static void main(String[] args) {
    
		ApiClient defaultClient = Configuration.getDefaultApiClient(); 
		Map<String, Authentication> authentications = defaultClient.getAuthentications();       
		
		//Currently Base Path points to sandbox system, change it to call your API Endpoint
		defaultClient.setBasePath("https://sandbox.api.sap.com/mlfs/api/v2");
		//You can obtain your API key on the Settings page of SAP API Business Hub. In the Settings page, choose the Show API Key toggle button to display and copy your API key. You have to be logged in to view your API Key.
		defaultClient.addDefaultHeader("APIKey","<YOUR APIBHUB SANDBOX APIKEY>"); 		
		authentications.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
		            
        
		*/
        
		//Change Base Path, uncomment below code and configure OAuth2 Authorization to call your API Endpoint: Oauth2_ClientCredentials
		authentications.put("Oauth2_ClientCredentials", new OAuth());
		/* OAuth Oauth2_ClientCredentials = (OAuth) defaultClient.getAuthentication("Oauth2_ClientCredentials");
		Oauth2_ClientCredentials.setAccessToken("<YOUR_ACCESS_TOKEN>");
		*/		
        SimilarityScoringApi apiInstance = new SimilarityScoringApi();
        apiInstance.setApiClient(defaultClient);
        String options = "options_example"; // String | Options parameters must be in json format. Possible values are:    - numSimilarVectors - Required. Number of most similar vectors to return in response <ul>   Example: {\"numSimilarVectors\":2}
        File files = new File("/path/to/file.txt"); // File | The set of vectors which should be compared. Must be either one archive file, or two archive files which include vector files (text file containing a vector). In case of a single archive file, each vector file will be compared to each other. In case of two files, vector files in first archive file will be reference vectors and will be compared to each vector in the second archive file. Either files input, or texts input should be provided. Exactly one of them, not both. <ul> Accepted file extensions:**  <li>Archive file: zip tar gz tgz   <li>Vector files in archive file: <None> txt  <li>What a vector file should include: [number, number, number, ..., number]  <li>Example: [0.20161056518554688, 1.0347602367401123, ..., 1.5012381076812744]**
        String texts = "texts_example"; // String | This is a json format string which represents the set(s) of vectors which should be compared. Must include either one or two sets of vectors.  Each vector is represented as an object which includes two key-value pairs; id, and vector.  The id is a string, and vector is an array of numbers.  A set of vectors is simply represented as an array of vector objects.  The input json object should include one or two key-value pairs. If one set will be provided, the object should include one key-value pair, where name is '0', and value is a set of vectors. In this case each vector in the set will be compared to the other vectors in this set.  If two sets are provided, the object should include two key-value pairs, where the names are '0' and '1' and with both values being sets of vectors. In this case, for each vector from set '0' top numSimilarVectors similar vectors from set '1' will be found. Either files input, or texts input should be provided. Exactly one of them, not both. <ul>   ** Example: {\"0\": [{\"id\": \"v0\", \"vector\": [0.22, ..., 0.93]}, {\"id\": \"v1\", \"vector\": [0.39, ..., 0.69]}]}**
        try {
            ResponseOk result = apiInstance.similarityScoringPost(options, files, texts);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SimilarityScoringApi#similarityScoringPost");
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

