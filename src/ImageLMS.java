import java.util.Arrays;
import java.util.InputMismatchException;

public class ImageLMS {
    private PixelLMS[][] image;
    private enum CVD_TYPE { PROTANOPIA, DEUTARANOPIA, TRITANOPIA };

    public ImageLMS(ImageRGB image) {
        initializePixels(image);
        writeLMS(image);
    }

    public ImageLMS(int height, int width) {
        initializePixels(height, width);
    }

    public void initializePixels(ImageRGB image) {
        int height = image.getHeight(), width = image.getWidth();
        initializePixels(height, width);
    }

    public void initializePixels(int height, int width) {
        this.image = new PixelLMS[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                this.image[i][j] = new PixelLMS();
            }
        }
    }

    public void writeLMS(ImageRGB image) {
        //using LMS tristimulus values
        final double[][] T = { {0.1992, 0.4112, 0.0742},
                               {0.0353, 0.2226, 0.0574},
                               {0.0185, 0.1231, 1.3550} };

        //C-LMS = T * C-RGB, where the elements of T are the LMS tristimulus values of the CRT primaries
        for(int i = 0; i < this.image.length; i++) {
            for(int j = 0; j < this.image[0].length; j++) {
                PixelRGB pixelRGB = image.getPixel(i, j); //imageRGB treats it as rows and columns :D
                int[] rgb = pixelRGB.getRGB();
                double[] lms = new double[3];

                lms[0] = (T[0][0] * rgb[0]) + (T[0][1] * rgb[1]) + (T[0][2] * rgb[2]);

                lms[1] = (T[1][0] * rgb[0]) + (T[1][1] * rgb[1]) + (T[1][2] * rgb[2]);

                lms[2] = (T[2][0] * rgb[0]) + (T[2][1] * rgb[1]) + (T[2][2] * rgb[2]);

                this.image[i][j].setLMS(lms);
                System.out.println(Arrays.toString(this.image[i][j].getLMS()));
            }
        }
    }

    public void setPixel(int x, int y, double[] lms) {
        try {
            this.image[x][y].setLMS(lms);
        } catch(InputMismatchException e) {
            System.out.println("Error parsing PixelRGB data, make sure parameter int[] rgb has 3 elements.");
            System.exit(1);
        }
    }

    public PixelLMS getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        return this.image[x][y];
    }

    public PixelLMS[][] getImageAsPixelArray() {
        return this.image;
    }

    public int getHeight() {
        return this.image.length;
    }

    public int getWidth() {
        return this.image[0].length;
    }

    //UNIMPLEMENTED
    /**
     * this method computes the CVD simulation algorithm on a provided LMS image
     * @param image image to be simulated
     * @param cvd_type the type of CVD to be simulated
     * @return cvd-simulated image
     */
    /*public static ImageLMS getCVDSimulatedImageLMS(ImageLMS image, CVD_TYPE cvd_type) {

    }*/

    /**
     * this method calculates the difference between two LMS images
     * @param C image to subtract other image from
     * @param c image to be subtracted from the other
     * @return the difference between two LMS image matrices as an ImageLMS
     */
    public static ImageLMS getDifference(ImageLMS C, ImageLMS c) throws InputMismatchException {
        int height = C.getHeight(), width = C.getWidth();
        if(height != c.getHeight() || width != c.getWidth()) throw new InputMismatchException();

        ImageLMS dC = new ImageLMS(height, width);
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                dC.setPixel(i, j, getLMSDifference(C, c, i, j));
            }
        }

        return dC;
    }

    public static double[] getLMSDifference(ImageLMS C, ImageLMS c, int x, int y) {
        double[] lmsDiff = new double[3];
        PixelLMS CPixel = C.getPixel(x, y), cPixel = c.getPixel(x, y);
        double[] CPixelLMS = CPixel.getLMS(), cPixelLMS = cPixel.getLMS();

        lmsDiff[0] = CPixelLMS[0] - cPixelLMS[0];
        lmsDiff[1] = CPixelLMS[1] - cPixelLMS[1];
        lmsDiff[2] = CPixelLMS[2] - cPixelLMS[2];

        return lmsDiff;
    }
}
