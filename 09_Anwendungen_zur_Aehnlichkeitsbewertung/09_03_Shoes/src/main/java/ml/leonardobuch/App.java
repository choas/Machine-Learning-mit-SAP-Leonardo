package ml.leonardobuch;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.similarity_scoring_api.model.ResponseOk;

/**
 * Shoes.
 *
 */
public class App {
	private static final int SIZE_X = 150;
	private static final int SIZE_Y = 100;

	public static void main(String[] args) throws IOException, ApiException {

		// TODO segmentor call
		// Hinweis: Der Multi-Instance Image Segmentation Service wurde deaktiviert.
		// Aus diesem Grund wird das "Segmentor" Bild direkt geladen.
		File file = new File(Settings.PATH + "segmentor.png");
		BufferedImage segmentorImage = ImageIO.read(file);
		List<Image> mainImages = new ArrayList<Image>();
		Image imgScaled = segmentorImage.getScaledInstance(SIZE_X, SIZE_Y, Image.SCALE_SMOOTH);
		mainImages.add(imgScaled);

		// TODO segmentor image feature extraction
		List<List<BigDecimal>> features0 = ImageFeatureExtraction.imageFeatureExtraction(file);

		// TODO shoe list image feature extraction
		List<String> shoeList = new ArrayList<String>(
				Arrays.asList(
						"hiking-shoes-3074971_640.png", 
						"chucks-153310_640.png",
						//"childrens-shoes-3075082_640.png", 
						"high-heeled-shoes-2781084_640.png", 
						"keychain-1106242_640.png", 
						"shoe-1433925_640.jpg",
						// "shoe-2764123_640.png",
						"shoe-2769336_640.png", 
						"shoes-2732693_640.png", 
						"shoes-2743420_640.png",
						//"shoes-2765458_640.png", 
						"sneakers-2768263_640.png", 
						"sneakers-2798332_640.png", 
						"winter-boots-2788671_640.png"));
		List<Image> testImages = new ArrayList<Image>();
		List<List<BigDecimal>> features1 = new ArrayList<List<BigDecimal>>();

		for (String shoe : shoeList) {
			File fileShoe = new File(Settings.PATH + "shoes/" + shoe);

			List<List<BigDecimal>> features = ImageFeatureExtraction.imageFeatureExtraction(fileShoe);
			features1.addAll(features);

			BufferedImage image = ImageIO.read(fileShoe);
			Image imgScaledShoe = image.getScaledInstance(SIZE_X, SIZE_Y, Image.SCALE_SMOOTH);

			testImages.add(imgScaledShoe);
		}

		// TODO similarity scoring
		ResponseOk scores = SimilarityScoring.similarityScoring(features0, features1);
		System.out.println(scores);

		// TODO grid
		JFrame frame = new JFrame();
		frame.add(new Grid(mainImages, testImages, scores, SIZE_X, SIZE_Y, Color.WHITE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
