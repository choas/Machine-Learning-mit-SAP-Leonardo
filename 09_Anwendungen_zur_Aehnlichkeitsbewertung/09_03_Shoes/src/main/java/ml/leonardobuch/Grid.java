package ml.leonardobuch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;
import com.sap.apibhub.sdk.similarity_scoring_api.model.TopSimilarVectors;

public class Grid extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6648857767924077743L;

	public Grid(List<Image> mainImages, List<Image> testImages, ResponseOk scores, int sizeX, int sizeY,
			Color bgColor) {

		setLayout(new GridLayout(mainImages.size(), testImages.size() + 1, 5, 5));
		setBackground(bgColor);

		for (int m = 0; m < mainImages.size(); m++) {

			BufferedImage image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graph = (Graphics2D) image.getGraphics();
			Image faceImageM = mainImages.get(m);
			graph.drawImage(faceImageM, 0, 0, null);

			add(new JLabel(new ImageIcon(image)));

			System.out.print(m + ": ");
			TopSimilarVectors vectors = scores.getPredictions().get(m).getSimilarVectors();

			for (int t = 0; t < testImages.size(); t++) {
				image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
				graph = (Graphics2D) image.getGraphics();
				Integer index = Integer.valueOf(vectors.get(t).getId());
				Image faceImageT = testImages.get(index);
				graph.drawImage(faceImageT, 0, 0, null);

				graph.setColor(Color.DARK_GRAY);
				graph.fillRect(0, sizeY - 10, sizeX, 5);

				double score = vectors.get(t).getScore().doubleValue();
				System.out.print((int) (score * 100) + "." + (int) ((score * 10000) % 100) + "% ");
				graph.setColor(Color.ORANGE);
				graph.fillRect(0, sizeY - 10, (int) (sizeX * score), 5);

				add(new JLabel(new ImageIcon(image)));
			}
			System.out.println("");
		}
	}

}