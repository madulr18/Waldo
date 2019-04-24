
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRestore {

    public static String restore(ImageBreakdown raw, String filename) throws IOException {
        BufferedImage newImage = new BufferedImage(raw.getWidth(), raw.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int row = 0; row < raw.getHeight(); row++){
            for(int col = 0; col < raw.getWidth(); col++){
                newImage.setRGB(col, row, raw.getPixel(row, col).getRGB());
            }
        }

        File output = new File(filename);
        ImageIO.write(newImage, "png", output);
        return filename;
    }
}
