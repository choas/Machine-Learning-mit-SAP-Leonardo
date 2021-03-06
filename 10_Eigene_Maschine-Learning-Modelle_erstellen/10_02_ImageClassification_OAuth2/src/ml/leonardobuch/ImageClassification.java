package ml.leonardobuch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ImageClassification {
  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) {

    DataOutputStream dataOut = null;
    BufferedReader in = null;

    try {
      // API endpoint for API sandbox
      String url = "https://sandbox.api.sap.com/ml/imageclassification/classification";

      Boolean useSandbox = false;
      if (!useSandbox) {
        url = "https://mlftrial-image-classifier.cfapps.eu10.hana.ondemand.com/api/v2/image/classification/";
        url += "models/fruits/versions/1";
      }

      URL urlObj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
      // setting request method
      connection.setRequestMethod("POST");

      // adding headers
      connection.setRequestProperty("content-type", 
          "multipart/form-data; boundary=---011000010111000001101001");
      // API Key for API Sandbox

      if (useSandbox) {
        connection.setRequestProperty("APIKey", Settings.API_KEY);
      } else {

        OAuth2 oauth2 = new OAuth2();
        String token = oauth2.generateToken();
        connection.setRequestProperty("Authorization", token);

      }

      connection.setDoInput(true);
      // sending POST request
      connection.setDoOutput(true);
      dataOut = new DataOutputStream(connection.getOutputStream());

      String fileName = Settings.PATH + "berry-210778_640.jpg";

      String fileType = "image/jpeg";

      dataOut.writeBytes("-----011000010111000001101001\r\n");
      dataOut.writeBytes("Content-Disposition: form-data; name=\"files\"; filename=\"");
      // <file_name>
      dataOut.writeBytes(fileName);
      dataOut.writeBytes("\"\r\nContent-Type: ");
      // <file_type>
      dataOut.writeBytes(fileType);
      dataOut.writeBytes("\r\n\r\n");
      // <file_contents>
      Path filePath = FileSystems.getDefault().getPath(fileName);
      Files.copy(filePath, connection.getOutputStream());
      dataOut.writeBytes("\r\n-----011000010111000001101001--");
      dataOut.flush();

      InputStream inputStream = null;
      try {
        inputStream = connection.getInputStream();
      } catch (IOException exp) {
        inputStream = connection.getErrorStream();
      }
      in = new BufferedReader(new InputStreamReader(inputStream));

      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
        response.append("\r\n");
      }

      // printing response
      System.out.println(response.toString());
      System.out.println("\n");

      String script = "var getLabel = function(str) {" + "   var json = JSON.parse(str);"
          + "   return json.predictions[0].results[0].label;" + "};";

      ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
      engine.eval(script);
      Invocable inv = (Invocable) engine;
      Object label = inv.invokeFunction("getLabel", response);
      System.out.println(label);

    } catch (Exception e) {
      // do something with exception
      e.printStackTrace();
    } finally {
      try {
        if (dataOut != null) {
          dataOut.close();
        }
        if (in != null) {
          in.close();
        }

      } catch (IOException e) {
        // do something with exception
        e.printStackTrace();
      }
    }

  }

}
