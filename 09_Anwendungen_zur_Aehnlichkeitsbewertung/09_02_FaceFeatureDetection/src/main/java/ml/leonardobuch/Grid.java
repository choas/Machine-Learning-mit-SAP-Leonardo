package ml.leonardobuch;

import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;
import com.sap.apibhub.sdk.similarity_scoring_api.model.TopSimilarVectors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Grid Klasse.
 *
 */
public class Grid extends JPanel {

  private static final long serialVersionUID = 6648857767924077743L;

  /**
   * Grid Konstruktor.
   * 
   * @param mainImages main images
   * @param testImages test images
   * @param scores scores
   * @param width width
   * @param height height
   * @param bgColor bgColor
   */
  public Grid(
      List<Image> mainImages, List<Image> testImages, 
      ResponseOk scores, int width, 
      int height, Color bgColor) {

    // TODO layout and mainImages

    setLayout(new GridLayout(mainImages.size(), testImages.size() + 1, 5, 5));
    setBackground(bgColor);

    for (int m = 0; m < mainImages.size(); m++) {

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D graph = (Graphics2D) image.getGraphics();
      Image faceImageM = mainImages.get(m);
      graph.drawImage(faceImageM, 0, 0, null);
      add(new JLabel(new ImageIcon(image)));

      System.out.print(m + ": ");

      // TODO testImages
      // TODO testImages
      TopSimilarVectors vectors = scores.getPredictions().get(m).getSimilarVectors();

      for (int t = 0; t < testImages.size(); t++) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graph = (Graphics2D) image.getGraphics();
        Integer index = Integer.valueOf(vectors.get(t).getId());
        Image faceImageT = testImages.get(index);
        graph.drawImage(faceImageT, 0, 0, null);

        graph.setColor(Color.DARK_GRAY);
        graph.fillRect(0, height - 10, width, 5);
        double score = vectors.get(t).getScore().doubleValue();
        graph.setColor(Color.ORANGE);
        graph.fillRect(0, height - 10, (int) (width * score), 5);

        add(new JLabel(new ImageIcon(image)));

        System.out.print(String.format("%.2f%% ", score * 100));
      }
      System.out.println("");
    }
  }

}