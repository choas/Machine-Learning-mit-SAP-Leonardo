package ml.leonardobuch;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OAuth2 {

  public static void main(String[] args) {
    OAuth2 oauth2 = new OAuth2();
    oauth2.generateToken();
  }

  /**
   * Generiert einen OAuth2 Token.
   * 
   * @return OAuth2 Token
   */
  public String generateToken() {

    DataOutputStream dataOut = null;
    BufferedReader in = null;

    try {
      URL urlObj = new URL(Settings.OAUTH2_URL + "/oauth/token");
      HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
      connection.setRequestMethod("POST");

      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setDoInput(true);
      connection.setDoOutput(true);

      String clientid = URLEncoder.encode(Settings.OAUTH2_CLIENT_ID, "utf-8");
      String clientsecret = URLEncoder.encode(Settings.OAUTH2_CLIENT_SECRET, "utf-8");
      String body = "grant_type=client_credentials&client_id=" 
          + clientid + "&client_secret=" + clientsecret;
      
      dataOut = new DataOutputStream(connection.getOutputStream());
      dataOut.writeBytes(body);
      dataOut.flush();

      connection.getResponseCode();

      InputStream inputStream = null;
      try {
        inputStream = connection.getInputStream();
      } catch (IOException exp) {
        inputStream = connection.getErrorStream();
      }
      in = new BufferedReader(new InputStreamReader(inputStream));

      // in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
        response.append("\r\n");
      }

      // printing response
      System.out.println(response.toString());
      System.out.println("\n");

      String token = "Bearer " + response.toString().split("\"")[3];
      System.out.println(token);

      // Copy token to Clipboard
      StringSelection stringSelection = new StringSelection(token);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);

      return token;

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

    return null;

  }

}
