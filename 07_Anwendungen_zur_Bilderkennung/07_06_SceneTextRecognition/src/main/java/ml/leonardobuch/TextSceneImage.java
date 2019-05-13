package ml.leonardobuch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextSceneImage {

  String backgroundImage = Settings.PATH + "stickies-2324090_640.jpg";

  static final  int X = 165;
  static final  int Y = 153;
  int degree = -6;

  boolean isAntialiasing = true;
  String fontName = "Arial"; // "Apple Chancery";
  int fontStyle = Font.PLAIN;
  int fontSize = 18;
  Color textColor = Color.DARK_GRAY;

  String texts = "Äpfel;Bananen;Grapefruits";
  int[] strikethroughs = { 1 };
  int stroke = 1;

  boolean cut = true;

  /**
   * Erstellt einen "Einkaufszettel".
   * 
   * @return ein "Einkaufszettel" Bild
   * @throws IOException IO Fehler
   */
  public BufferedImage generate() throws IOException {
    BufferedImage img = ImageIO.read(new File(backgroundImage));
    Graphics2D graph = img.createGraphics();

    graph.rotate(Math.toRadians(degree), X, Y);

    if (isAntialiasing) {
      graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    graph.setFont(new Font(fontName, fontStyle, fontSize));
    graph.setColor(textColor);

    int pos = 0;
    String[] ts = texts.split(";");
    for (String text : ts) {
      graph.drawString("- " + text, X, Y + fontSize * pos);
      pos += 1;
    }

    graph.setStroke(new BasicStroke(stroke));
    for (int strikethrough : strikethroughs) {
      int textlen = ts[strikethrough].length();
      double x1 = X;
      double y1 = Y + fontSize * (0.8 + strikethrough - 1);
      double x2 = X + fontSize * 0.6 * (textlen + 2);
      double y2 = Y + fontSize * (0.8 + strikethrough - 1);
      graph.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    ImageIO.write(img, "png", new File(Settings.PATH + "SceneText.png"));
    return img;
  }

}
