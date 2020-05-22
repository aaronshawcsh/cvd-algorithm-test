import java.awt.image.*;

public class ImageRGB {
    private PixelRGB[][] image;
    private int height, width;

    public ImageRGB(BufferedImage image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.image = new PixelRGB[this.height][this.width];

        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                this.image[i][j] = new PixelRGB(image.getRGB(i, j));
            }
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public PixelRGB getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        return this.image[x][y];
    }

    public PixelRGB[][] getImageAsPixelArray() {
        return this.image;
    }
}
