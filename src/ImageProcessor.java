import java.awt.*;

public class ImageProcessor {
    public static void findWaldo(ImageBreakdown image){
        /*
        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++) {
                Color pixel = image.getPixel(row,col);
                int b = pixel.getBlue();
                int r = pixel.getRed();
                int g = pixel.getGreen();
                Color newPixel = new Color(r, g, b);
                image.setPixel(row, col, newPixel);
            }
        }
        */
        drawSquare(image,300,300, Color.BLUE, 4);
    }

    public static void grayscale(ImageBreakdown image){
        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++) {
                Color pixel = image.getPixel(row,col);

                int red = (int)(pixel.getRed() * 0.299);
                int green = (int)(pixel.getGreen() * 0.587);
                int blue = (int)(pixel.getBlue() *0.114);
                int sum = red+green+blue;
                Color newPixel = new Color(sum,sum,sum);

                image.setPixel(row,col, newPixel);
            }
        }
    }

    public static void redscale(ImageBreakdown image){
        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++) {
                Color pixel = image.getPixel(row,col);
                int oldRed = pixel.getRed();
                int oldblue = pixel.getBlue();
                int oldGreen = pixel.getGreen();

                Color newPixel = new Color(255,255,255);
                if(oldRed > 160 && oldblue <100 && oldGreen <100){
                    oldRed = 255;
                    newPixel = new Color(oldRed,0,0);
                }

                image.setPixel(row,col, newPixel);
            }
        }
    }

    public static void partitionPixels(ImageBreakdown image,int n){
        int[] partitions = new int[n];
        int interval = 256/n;
        for(int i = 0; i<n; i++){
            partitions[i] = i*(interval);
        }

        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++) {
                Color pixel = image.getPixel(row,col);
                int gray = pixel.getBlue();
                int newgray = partitions[gray/interval];
                Color newPixel = new Color(newgray,newgray,newgray);
                image.setPixel(row,col, newPixel);

            }
        }
    }

    private static void drawSquare(ImageBreakdown image, int row, int col, Color color, int thickness){
        for(int i = 0; i < thickness; i++){
            drawSingleSquare(image, row, col, color, i);
        }

    }
    private static void drawSingleSquare(ImageBreakdown image, int row, int col, Color color, int iteration){
        int squareRadius = ((image.getHeight() + image.getWidth())/(2*30))-iteration;
        int rowTop = row - squareRadius;
        int rowBottom = row + squareRadius;
        int colLeft = col - squareRadius;
        int colRight = col + squareRadius;

        drawVerticalLine(image, rowTop, rowBottom, colLeft, color);
        drawVerticalLine(image, rowTop, rowBottom, colRight, color);

        drawHorizontalLine(image, colLeft, colRight, rowTop, color);
        drawHorizontalLine(image, colLeft, colRight, rowBottom, color);
    }

    private static void drawVerticalLine(ImageBreakdown image, int top, int bottom, int col, Color color){
        for(int i = top; i<= bottom; i++){
            if(image.isInBounds(i,col)){
                image.setPixel(i, col, color);
            }
        }
    }

    private static void drawHorizontalLine(ImageBreakdown image, int left, int right, int row, Color color){
        for(int i = left; i<= right; i++){
            if(image.isInBounds(row,i)){
                image.setPixel(row, i, color);
            }
        }
    }
}
