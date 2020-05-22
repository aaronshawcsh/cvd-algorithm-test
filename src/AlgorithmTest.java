import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class AlgorithmTest {
    public static void main(String[] args) {
        //step-1
        BufferedImage originalBufferedImage = null;
        try {
            originalBufferedImage = getOriginalImage();
        } catch(IOException e) {
            System.out.println("Error reading original image. Please make sure the file exists and has read/write permissions.");
            System.exit(1);
        }
        ImageRGB originalImageRGB = new ImageRGB(originalBufferedImage);

        //step-2 UNIMPLEMENTED
        //ImageLMS originalImageLMS = new ImageLMS(originalImageRGB);

        //step-3 UNIMPLEMENTED
        //ImageLMS cvdSimulatedImageLMS = ImageLMS.getCVDSimulatedImageLMS(originalImageLMS);
    }

    public static BufferedImage getOriginalImage() throws IOException {
        return ImageIO.read(new File("images\\dragon.jpg"));
    }
}
