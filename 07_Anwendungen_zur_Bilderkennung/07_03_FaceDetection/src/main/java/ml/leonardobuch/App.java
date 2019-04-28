package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.face_detection_api.api.FaceDetectionApi;
import com.sap.apibhub.sdk.face_detection_api.model.BoundaryBox;
import com.sap.apibhub.sdk.face_detection_api.model.ResponseOk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Face Detection Application.
 *
 */
public class App {

  public static final Boolean PIXEL_IMAGE = false;

  /**
   * main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws IOException, ApiException {

    final String fileName = Settings.PATH + "man-3365368_640.jpg";

    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    FaceDetectionApi apiInstance = new FaceDetectionApi();
    apiInstance.setApiClient(apiClient);

    File files = new File(fileName);
    ResponseOk result = apiInstance.pOSTInferenceSync(files);
    System.out.println(result);

    // draw bounding boxes
    BufferedImage image = ImageIO.read(files);
    Graphics2D graph = image.createGraphics();

    for (BoundaryBox boundaryBox : result.getPredictions().get(0).getFaces()) {

      if (PIXEL_IMAGE) {
        pixel(image, graph, boundaryBox);
      } else {
        box(image, graph, boundaryBox);
      }

    }

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);

    ImageIO.write(image, "png", new File(Settings.PATH + "face_out.png"));
  }

  private static void box(BufferedImage image, Graphics2D graph, BoundaryBox boundaryBox) {
    graph.setColor(Color.ORANGE);
    graph.setStroke(new BasicStroke(3));

    int x = boundaryBox.getLeft().intValue();
    int y = boundaryBox.getTop().intValue();
    int width = boundaryBox.getRight().subtract(boundaryBox.getLeft()).intValue();
    int height = boundaryBox.getBottom().subtract(boundaryBox.getTop()).intValue();
    graph.drawRect(x, y, width, height);

    graph.setColor(Color.BLACK);
    graph.setStroke(new BasicStroke(1));
    graph.drawRect(x, y, width, height);
  }

  private static void pixel(BufferedImage image, Graphics2D graph, BoundaryBox boundaryBox) {
    int x0 = boundaryBox.getLeft().intValue();
    int y0 = boundaryBox.getTop().intValue();
    int x1 = boundaryBox.getRight().intValue();
    int y1 = boundaryBox.getBottom().intValue();

    int pixel = Math.max((x1 - x0), (y1 - y0)) / 10;

    for (int x = x0; x <= x1; x += pixel) {
      for (int y = y0; y <= y1; y += pixel) {
        int color = image.getRGB(x, y);
        graph.setColor(new Color(color));
        graph.fillRect(x, y, pixel, pixel);
      }
    }
  }

}