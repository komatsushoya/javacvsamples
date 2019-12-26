import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class grabber {
    public static void main(String[] args) {
        new grabber().slice(new File("./photo/movie2.mp4"), new File("./photo/jpg2"));
    }
    /**
     *  動画ファイルをフレームごとに切り出し静止画に変換する
     */
    public void slice(final File srcMoviePath, final File destJpegPath) {
        try {
            FFmpegFrameGrabber frameGrabber= new FFmpegFrameGrabber(srcMoviePath);
            Java2DFrameConverter frameConverter = new Java2DFrameConverter();

            frameGrabber.start();

            // FFmpegFrameGrabber#startを実行した後から、getFrameRateやgetLengthInFramesなどのプロパティで値が取得できるようになる
            System.out.println("format:" + frameGrabber.getFormat() + ", size:" + frameGrabber.getImageWidth() + "x"
                    + frameGrabber.getImageHeight() + ", frame-rate:" + frameGrabber.getFrameRate()
                    + ", length-in-frame:" + frameGrabber.getLengthInFrames());

            int count = 0;

            // 現在のフレーム番号が動画ファイルの総フレーム数に到達するまでループ
            while (frameGrabber.getFrameNumber() < frameGrabber.getLengthInFrames()) {
                // フレームを取りだして
                BufferedImage img = frameConverter.convert(frameGrabber.grab());
                if (img == null) {
                    // BufferedImageが取得できないことがあるので、そのときはスキップ
                    continue;
                }
                /*Frame to Mat
                OpenCVFrameConverter.ToMat toMat = new OpenCVFrameConverter.ToMat();
                toMat.convert(frame);
                //Mat to Frame
                OpenCVFrameConverter.ToMat toMat = new OpenCVFrameConverter.ToMat();
                toMat.convert(frame);
                // 静止画で出力
                //フレーム番号が重複することがある
                */
                ImageIO.write(img, "jpg", new File(destJpegPath, "frame-" + frameGrabber.getFrameNumber() + "-" + count + ".jpg"));
                }
            frameGrabber.stop();
            frameGrabber.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();;
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
//http://www.ria-lab.com/archives/1164