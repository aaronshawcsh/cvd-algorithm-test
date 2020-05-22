import java.util.InputMismatchException;

public class ImageLMS {
    private PixelLMS[][] image;
    private enum CVD_TYPE { PROTANOPIA, DEUTARANOPIA, TRITANOPIA };

    public ImageLMS(ImageRGB image) {
        initializePixels(image);
        //C-LMS = T * C-RGB, where the elements of T are the LMS tristimulus values of the CRT primaries
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

    public void setPixel(int x, int y, int[] lms) {
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

    public static int[] getLMSDifference(ImageLMS C, ImageLMS c, int x, int y) {
        int[] lmsDiff = new int[3];
        PixelLMS CPixel = C.getPixel(x, y), cPixel = c.getPixel(x, y);
        int[] CPixelLMS = CPixel.getLMS(), cPixelLMS = cPixel.getLMS();

        lmsDiff[0] = CPixelLMS[0] - cPixelLMS[0];
        lmsDiff[1] = CPixelLMS[1] - cPixelLMS[1];
        lmsDiff[2] = CPixelLMS[2] - cPixelLMS[2];

        return lmsDiff;
    }
}
