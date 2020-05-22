import java.util.InputMismatchException;

public class PixelLMS {
    private int[] lms;

    public PixelLMS(int[] lms) {
        initialize();
        setLMS(lms);
    }

    public PixelLMS() {
        initialize();
    }

    public void initialize() {
        this.lms = new int[3];
    }

    public void setLMS(int[] lms) throws InputMismatchException {
        if(lms.length != 3) throw new InputMismatchException();
        this.lms[0] = lms[0];
        this.lms[1] = lms[1];
        this.lms[2] = lms[2];
    }

    public int[] getLMS() {
        return this.lms;
    }
}
