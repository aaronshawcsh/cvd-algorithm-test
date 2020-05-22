import java.util.InputMismatchException;

public class PixelRGB {
    private int[] rgb;

    public PixelRGB(int rgb) {
        initialize();

        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        this.rgb[0] = red;
        this.rgb[1] = green;
        this.rgb[2] = blue;
    }

    public PixelRGB() {
        initialize();
    }

    public void initialize() {
        this.rgb = new int[3];
    }

    public void setRGB(int[] rgb) throws InputMismatchException {
        if(rgb.length != 3) throw new InputMismatchException();
        this.rgb[0] = rgb[0];
        this.rgb[1] = rgb[1];
        this.rgb[2] = rgb[2];
    }

    public int[] getRGB() {
        return this.rgb;
    }

    /*
    code to reform int RGB from individual components:
    int rgb = red;
    rgb = (rgb << 8) + green;
    rgb = (rgb << 8) + blue;
     */
}
