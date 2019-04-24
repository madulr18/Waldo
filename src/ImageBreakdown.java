import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

public class ImageBreakdown {
    Color[][] image;
    int height;
    int width;

    public ImageBreakdown(ImageBreakdown ib){
        height = ib.getHeight();
        width = ib.getWidth();
        image = new Color[height][width];
        for(int row = 0; row < height; row ++){
            for(int col = 0; col < width; col++){
                image[row][col] = ib.getPixel(row,col);
            }
        }
    }

    public ImageBreakdown(String filename) throws IOException {
        File f = new File(filename);
        BufferedImage img = ImageIO.read(f);

        height = img.getHeight();
        width = img.getWidth();

        image = new Color[height][width];
        storeImage(img);
    }

    private void storeImage(BufferedImage img){
        for(int row = 0; row < height; row ++){
            for(int col = 0; col < width; col++){
                image[row][col] = new Color(img.getRGB(col,row));
            }
        }
    }

    public Color[][] getArray(){
        return image;
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public Color getPixel(int row, int col){
        return image[row][col];
    }

    public void setPixel(int row, int col, Color color){
        image[row][col] = color;
    }

    public boolean isInBounds(int row, int col){
        return row < height && col < width;
    }
}
