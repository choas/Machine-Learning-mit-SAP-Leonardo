package ml.leonardobuch;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HumanDetection {

  /**
   * Die main Methode der HumanDetection-Klasse.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) {

    String fileName = Settings.PATH + "african-american-3548081_640.jpg";

    DataOutputStream dataOut = null;
    BufferedReader in = null;

    try {

      // API endpoint for API sandbox
      String url = "https://sandbox.api.sap.com/ml/api/v2alpha1/image/human-detection/format:image";

      URL urlObj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
      // setting request method
      connection.setRequestMethod("POST");

      // adding headers
      connection.setRequestProperty("content-type", 
          "multipart/form-data; boundary=---011000010111000001101001");
      // API Key for API Sandbox
      connection.setRequestProperty("APIKey", Settings.API_KEY);

      connection.setDoInput(true);

      // sending POST request
      connection.setDoOutput(true);
      dataOut = new DataOutputStream(connection.getOutputStream());

      dataOut.writeBytes("-----011000010111000001101001\r\n");
      dataOut.writeBytes("Content-Disposition: form-data; " + "name=\"file\"; filename=\"");
      // <file_name>
      dataOut.writeBytes(fileName);
      dataOut.writeBytes("\"\r\nContent-Type: ");
      // <file_type>
      dataOut.writeBytes("image/jpeg");
      dataOut.writeBytes("\r\n\r\n");
      // <file_contents>
      Path filePath = FileSystems.getDefault().getPath(fileName);
      Files.copy(filePath, connection.getOutputStream());
      dataOut.writeBytes("\r\n-----011000010111000001101001--");
      dataOut.flush();

      BufferedImage image = ImageIO.read(connection.getInputStream());

      JFrame frame = new JFrame();
      frame.add(new JLabel(new ImageIcon(image)));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      ImageIO.write(image, "png", new File(Settings.PATH + "human_out.png"));

    } catch (Exception e) {
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
        e.printStackTrace();
      }
    }
  }

}
