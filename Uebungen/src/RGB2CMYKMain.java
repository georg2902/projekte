import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Georg Rolof on 18.07.2017.
 */
public class RGB2CMYKMain {
    public static void main(String[] args) {
        RGB2CMYK test = new RGB2CMYK();
        File x = new File("src/background.jpg");
        System.out.println("x.getAbsolutePath() = " + x.getAbsolutePath());
        System.out.println("x.exists() = " + x.exists());
        Mat src = Imgcodecs.imread(x.getPath());
        ArrayList<Mat> dst = new ArrayList<>();
        test.r2c(src,dst);
    }
}
