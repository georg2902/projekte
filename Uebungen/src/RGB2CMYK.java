import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Georg Rolof on 18.07.2017.
 */
public class RGB2CMYK {


    public static void main(String[] args) {
        System.out.println(new RGB2CMYK().getClass().getResource("C:/git/projekte/Uebungen/src/background.jpg").getPath());
        Mat src = Imgcodecs.imread(new RGB2CMYK().getClass().getResource("/background.jpg").getPath());
        ArrayList<Mat> dst = new ArrayList<>();
        new RGB2CMYK().r2c(src,dst);


    }
    public void r2c(Mat img, ArrayList<Mat> cmyk){
        for (int i = 0; i < 4; i++) {
            cmyk.add(new Mat(img.size(), CvType.CV_8UC1));
        }
        //get rgb
        ArrayList<Mat> rgb = new ArrayList<>();
        Core.split(img,rgb);

        //rgb to cmyk
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {
                float r = (int) rgb.get(0).get(i,j)[0] / 255;
                float g = (int) rgb.get(1).get(i,j)[1] / 255;
                float b = (int) rgb.get(2).get(i,j)[2] / 255;

                float k = Math.min(Math.min(1-r,1-g),1-b);

                cmyk.get(0).get(i,j)[0] = (1-r-k) / (1-k) * 255;
                cmyk.get(1).get(i,j)[1] = (1-g-k) / (1-k) * 255;
                cmyk.get(2).get(i,j)[2] = (1-b-k) / (1-k) * 255;
                cmyk.get(3).get(i,j)[3] = k*255;
            }
        }

    }
}
