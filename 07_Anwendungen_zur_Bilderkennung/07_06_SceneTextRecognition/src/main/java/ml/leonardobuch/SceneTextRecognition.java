package ml.leonardobuch;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class SceneTextRecognition {

  /**
   * Scene Text Recognition API Aufruf.
   * 
   * @param image Bild fuer die Scene Text Recognition
   * @return Ergebnis des Scene Text Recognition API Aufrufs
   * @throws Exception Exception
   */
  public String sceneTextRecognition(BufferedImage image) throws Exception {

    String url = "https://sandbox.api.sap.com/ml/"
        + "scenetextrecognition/scene-text-recognition";
    URL urlObj = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
    connection.setRequestMethod("POST");

    connection.setRequestProperty("content-type", "multipart/form-data; " 
        + "boundary=---011000010111000001101001");
    connection.setRequestProperty("APIKey", Settings.API_KEY);
    connection.setDoInput(true);

    connection.setDoOutput(true);
    DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
    dataOut.writeBytes("-----011000010111000001101001\r\n" 
        + "Content-Disposition: form-data; " 
        + "name=\"files\"; " + "filename=\"");
    // <file_name>
    dataOut.writeBytes("file.jpg");
    dataOut.writeBytes("\"\r\nContent-Type: ");
    // <file_type>
    dataOut.writeBytes("image/jpeg");
    dataOut.writeBytes("\r\n\r\n");
    // <file_contents>
    ImageIO.write(image, "jpg", connection.getOutputStream());
    dataOut.writeBytes("\r\n-----011000010111000001101001--");
    dataOut.flush();

    connection.getResponseCode();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
      response.append("\r\n");
    }
    return response.toString();
  }

}
