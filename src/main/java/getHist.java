
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class getHist {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {

        List<Double> histList = new ArrayList<Double>();
        for (int i = 268; i < 390; i = i+2) {
            String filename1 = "./photo/jpg2/frame-" + i + "-0.jpg";
            String filename2 = "./photo/jpg2/frame-" + (i + 2) + "-0.jpg";
            File file1 = new File(filename1);
            File file2 = new File(filename2);
            try {
                BufferedImage img = ImageIO.read(file1);
            } catch (IOException e) {
                System.out.println(e);
            }
            if (!(file1.exists() && file2.exists())) {
                continue;
            }
            // ------------------------------------------------------------------
            // ヒストグラム
            // ------------------------------------------------------------------
            Mat img1 = Imgcodecs.imread(filename1);
            Mat img2 = Imgcodecs.imread(filename2);

            List<Mat> src1 = new ArrayList<Mat>();
            src1.add(img1);
            List<Mat> src2 = new ArrayList<Mat>();
            src2.add(img2);

            Mat hist1_0 = new Mat();
            Imgproc.calcHist(src1, new MatOfInt(0), new Mat(), hist1_0, new MatOfInt(256), new MatOfFloat(0, 256));
            Mat hist1_1 = new Mat();
            Imgproc.calcHist(src1, new MatOfInt(1), new Mat(), hist1_1, new MatOfInt(256), new MatOfFloat(0, 256));
            Mat hist1_2 = new Mat();
            Imgproc.calcHist(src1, new MatOfInt(2), new Mat(), hist1_2, new MatOfInt(256), new MatOfFloat(0, 256));
            Mat hist2_0 = new Mat();
            Imgproc.calcHist(src2, new MatOfInt(0), new Mat(), hist2_0, new MatOfInt(256), new MatOfFloat(0, 256));
            Mat hist2_1 = new Mat();
            Imgproc.calcHist(src2, new MatOfInt(1), new Mat(), hist2_1, new MatOfInt(256), new MatOfFloat(0, 256));
            Mat hist2_2 = new Mat();
            Imgproc.calcHist(src2, new MatOfInt(2), new Mat(), hist2_2, new MatOfInt(256), new MatOfFloat(0, 256));
            //正規化してみると少し類似度が上がる
            Core.normalize(hist1_0,hist1_0);
            Core.normalize(hist1_1,hist1_1);
            Core.normalize(hist1_2,hist1_2);
            Core.normalize(hist2_0,hist2_0);
            Core.normalize(hist2_1,hist2_1);
            Core.normalize(hist2_2,hist2_2);
            /*
            histList.add(Imgproc.compareHist(hist1_0, hist2_0,0));
            histList.add(Imgproc.compareHist(hist1_1, hist2_1, 0));
            histList.add(Imgproc.compareHist(hist1_2, hist2_2, 0));
            */
            double a = Imgproc.compareHist(hist1_0, hist2_0, 3);
            if(true) {
                System.out.println(a);
                System.out.println(Imgproc.compareHist(hist1_1, hist2_1, 3));
                System.out.println(Imgproc.compareHist(hist1_2, hist2_2, 3));
            }
        }
    }
}