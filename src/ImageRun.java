import java.awt.*;
import java.io.IOException;

public class ImageRun {

    public static void main(String[] args) throws IOException {
        ImageBreakdown original = new ImageBreakdown(args[0]);
        ImageBreakdown grayscale = new ImageBreakdown(original);
        ImageBreakdown redscale = new ImageBreakdown(original);
        ImageProcessor.grayscale(grayscale);
        ImageProcessor.partitionPixels(grayscale, 4);
        ImageProcessor.redscale(redscale);

        //Waldo is found between here
        String filename = ImageRestore.restore(grayscale, "C:/Users/cod21/IdeaProjects/Waldo/RestoredWaldo/newWaldo1.png");
        EventQueue.invokeLater(() -> {
            ImageRunner ex = new ImageRunner(filename);
            ex.setVisible(true);
        });
    }
}
