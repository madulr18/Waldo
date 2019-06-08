import java.awt.*;

public class ImageProcessor {
    static PixelMarker markedGrid;
    static PixelMarker hairSpots;

    public static void findWaldo(ImageBreakdown image, ImageBreakdown grayscale){
        markedGrid = new PixelMarker(image.getHeight(), image.getWidth());
        hairSpots = new PixelMarker(image.getHeight(), image.getWidth());
        /*
        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++) {

                drawSquare(image, row, col, Color.BLUE, 4);
            }
        }
        */

        triangleSearch(image, grayscale);

        /*
        for (int i =  0; i < 100; i++){
            int row = (int)(Math.random()*image.getHeight());
            int col = (int)(Math.random()*image.getWidth());
            drawSquare(image,row,col,Color.BLUE,4);
        }
        */


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
        int squareRadius = ((image.getHeight() + image.getWidth())/(2*30));
        int rowTop = row - squareRadius;
        int rowBottom = row + squareRadius;
        int colLeft = col - squareRadius;
        int colRight = col + squareRadius;
        if (!OverlappingSquare(rowTop,rowBottom,colLeft,colRight)) {
            markedGrid.markArea(row, col, squareRadius);
            for (int i = 0; i < thickness; i++) {
                drawSingleSquare(image, rowTop + i, rowBottom - i, colLeft + i, colRight - i, color);
            }
        }

    }
    private static void drawSingleSquare(ImageBreakdown image, int rowTop, int rowBottom, int colLeft, int colRight, Color color){

        drawVerticalLine(image, rowTop, rowBottom, colLeft, color);
        drawVerticalLine(image, rowTop, rowBottom, colRight, color);

        drawHorizontalLine(image, colLeft, colRight, rowTop, color);
        drawHorizontalLine(image, colLeft, colRight, rowBottom, color);
    }


    //Needs to behave only when the square overlaps the center pixel
    private static boolean OverlappingSquare(int rowTop, int rowBottom, int colLeft, int colRight){
        return markedGrid.isMarked(rowTop, colLeft) ||
                markedGrid.isMarked(rowTop, colRight) ||
                markedGrid.isMarked(rowBottom, colLeft) ||
                markedGrid.isMarked(rowBottom, colRight);
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

    private static void triangleSearch(ImageBreakdown image, ImageBreakdown grayscale){
        for(int row = 0; row < grayscale.getHeight(); row++){
            for (int col = 0; col < grayscale.getWidth(); col++) {
                if(singleTriangleTest(grayscale, row, col)){
                    hairSpots.mark(row, col);
                    drawSquare(image, row, col, Color.BLUE, 4);
                }
            }
        }
    }

    private static boolean singleTriangleTest(ImageBreakdown grayscale, int row, int col){
        for(int i = 0; i<7; i++){
            if(grayscale.isInBounds(row+2, col+i)){
                if(!grayscale.getPixel(row+2, col+i).equals(Color.BLACK)){
                    return false;
                }
            }
        }
        for(int i = 2; i<7; i++){
            if(grayscale.isInBounds(row+1, col+i)){
                if(!grayscale.getPixel(row+1, col+i).equals(Color.BLACK)){
                    return false;
                }
            }
        }
        for(int i = 4; i<7; i++){
            if(grayscale.isInBounds(row, col+i)){
                if(!grayscale.getPixel(row, col+i).equals(Color.BLACK)){
                    return false;
                }
            }
        }
        return true;
    }
}
