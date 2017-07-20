import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by Georg Rolof on 18.07.2017.
 */
public class RGB2CMYKMain {
    public static void main(String[] args) {
        RGB2CMYK test = new RGB2CMYK();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("src/blut1hcropped.jpg");
        ArrayList<Mat> dst = new ArrayList<>();
        test.r2c(src, dst);
        BufferedImage img1 = Mat2BufferedImage(dst.get(0));
        BufferedImage img2 = Mat2BufferedImage(dst.get(1));
        BufferedImage img3 = Mat2BufferedImage(dst.get(2));
        BufferedImage img4 = Mat2BufferedImage(dst.get(3));
        BufferedImage sourceImg = Mat2BufferedImage(src);
        displayImage(sourceImg, "source");
        displayImage(img1, "C");
        displayImage(img2,"M");
        displayImage(img3,"Y");
        displayImage(img4,"K");


    }

    public static BufferedImage Mat2BufferedImage(Mat m) {
// source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
// Fastest code
// The output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    public static void displayImage(Image img2, String title)
    {
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        ImageIcon icon=new ImageIcon(img2);
        JFrame frame=new JFrame();
        frame.setTitle(title);
        frame.setLayout(new FlowLayout());
        frame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
