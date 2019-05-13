package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiClient;
import com.sap.apibhub.sdk.client.Configuration;
import com.sap.apibhub.sdk.client.auth.ApiKeyAuth;
import com.sap.apibhub.sdk.client.auth.Authentication;
import com.sap.apibhub.sdk.client.auth.OAuth;
import com.sap.apibhub.sdk.instance_segmentor_api.api.InstanceSegmentorApi;
import com.sap.apibhub.sdk.instance_segmentor_api.model.ResponseOk;
import com.sap.apibhub.sdk.instance_segmentor_api.model.SegmentationDetails;
import com.sap.apibhub.sdk.instance_segmentor_api.model.SegmentationDetailsBbox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Multi-Instance Image Segmentation.
 *
 */
public class App {

  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws Exception {

    final String fileName = Settings.PATH + "juice-1271881_640.jpg";

    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.setBasePath("https://sandbox.api.sap.com/ml/api/v2alpha1/image/");
    apiClient.addDefaultHeader("APIKey", Settings.API_KEY);

    Map<String, Authentication> auths = apiClient.getAuthentications();
    auths.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));
    auths.put("Oauth2_ClientCredentials", new OAuth());

    InstanceSegmentorApi apiInstance = new InstanceSegmentorApi();
    apiInstance.setApiClient(apiClient);

    File files = new File(fileName);
    ResponseOk result = apiInstance.pOSTInferenceSync(files);

    for (SegmentationDetails r : result.getPredictions().get(0).getResults()) {
      System.out.println(r.getClassName() + " " + r.getScore() + " " + r.getBbox().getX1());
    }

    BufferedImage image = ImageIO.read(files);
    Graphics2D graph = image.createGraphics();

    SegmentationDetails segmentationDetails = result.getPredictions().get(0).getResults().get(0);
    String mask = segmentationDetails.getMask();
    byte[] maskDecoded = Base64.getDecoder().decode(mask);

    SegmentationDetailsBbox bbox = segmentationDetails.getBbox();
    int x1 = bbox.getX1().intValue();
    int y1 = bbox.getY1().intValue();
    int x2 = bbox.getX2().intValue();
    int y2 = bbox.getY2().intValue();
    int width = x2 - x1;
    int height = y2 - y1;

    for (int x = x1; x < x2; x += 1) {
      for (int y = y1; y < y2; y += 1) {

        int index = (x - x1) + (y - y1) * width;
        int m = maskDecoded[index];
        if (m >= 0) {
          graph.setColor(new Color(255, 255, 255, 60));
          graph.drawRect(x, y, 1, 1);
        }
      }
    }
    graph.dispose();

    JFrame frame = new JFrame();
    Image segmentationImage = image.getSubimage(x1, y1, width, height);
    frame.add(new JLabel(new ImageIcon(segmentationImage)));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    BufferedImage bufferedImage = new BufferedImage(
        segmentationImage.getWidth(null), 
        segmentationImage.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);
    graph = bufferedImage.createGraphics();
    graph.drawImage(segmentationImage, 0, 0, null);

    ImageIO.write(bufferedImage, "png", new File(Settings.PATH + "segmentationImage.png"));

  }

}
