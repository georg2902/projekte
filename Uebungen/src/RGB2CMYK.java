import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Georg Rolof on 18.07.2017.
 */
public class RGB2CMYK {


    public static void main(String[] args) {
        System.out.println(new RGB2CMYK().getClass().getResource("C:/git/projekte/Uebungen/src/background.jpg").getPath());
        Mat src = Imgcodecs.imread(new RGB2CMYK().getClass().getResource("/background.jpg").getPath());
        ArrayList<Mat> dst = new ArrayList<>();
        new RGB2CMYK().r2c(src, dst);


    }

    public void r2c(Mat img, ArrayList<Mat> cmyk) {
        for (int i = 0; i < 4; i++) {
            cmyk.add(new Mat(img.size(), CvType.CV_8UC1));
        }
        //get rgb
        ArrayList<Mat> rgb = new ArrayList<>();
        ArrayList<Double> averageC = new ArrayList<>();
        ArrayList<Double> averageM = new ArrayList<>();
        ArrayList<Double> averageY = new ArrayList<>();
        ArrayList<Double> averageK = new ArrayList<>();
        Core.split(img, rgb);

        //rgb to cmyk

        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {

                float r = (int) rgb.get(0).get(i, j)[0] / 255.0F;
                // System.out.println(r);
                float g = (int) rgb.get(1).get(i, j)[0] / 255.0F;
                //System.out.println(g);
                float b = (int) rgb.get(2).get(i, j)[0] / 255.0F;
                //System.out.println(b);
                float k = Math.min(Math.min(1 - r, 1 - g), 1 - b);
                //System.out.println(k);
                double cValue = (1 - r - k) / (1 - k);
                double mValue = (1 - g - k) / (1 - k);
                double yValue = (1 - b - k) / (1 - k);
                double kValue = k;
                // System.out.println("________");
                //System.out.println("C: "+cValue);
                //System.out.println("M: "+mValue);
                // System.out.println("Y: "+yValue);
                //System.out.println("K: "+kValue);
                averageC.add(cValue);
                averageM.add(mValue);
                averageY.add(yValue);
                averageK.add(kValue);
                cmyk.get(0).put(i, j, yValue * 255.0F);
                cmyk.get(1).put(i, j, mValue * 255.0F);
                cmyk.get(2).put(i, j, cValue * 255.0F);
                cmyk.get(3).put(i, j, kValue * 255.0F);
                //cmyk.get(0).get(i, j)[0] = (1 - r - k) / (1 - k) * 255.0F;
                //cmyk.get(1).get(i, j)[0] = (1 - g - k) / (1 - k) * 255.0F;
                //cmyk.get(2).get(i, j)[0] = (1 - b - k) / (1 - k) * 255.0F;
                //cmyk.get(3).get(i, j)[0] = k * 255.0F;
            }
        }

        double sumC = averageC.stream().mapToDouble(Double::doubleValue).sum();
        double sumM = averageM.stream().mapToDouble(Double::doubleValue).sum();
        double sumY = averageY.stream().mapToDouble(Double::doubleValue).sum();
        double sumK = averageK.stream().mapToDouble(Double::doubleValue).sum();

        double averageCValue = sumC / averageC.size();
        double averageMValue = sumM / averageM.size();
        double averageYValue = sumY / averageY.size();
        double averageKValue = sumK / averageK.size();

        System.out.println("C: " + averageYValue);
        System.out.println("M: " + averageMValue);
        System.out.println("Y: " + averageCValue);
        System.out.println("K: " + averageKValue);
    }
}
