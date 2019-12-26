/*
package example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ReadImageUserInterface extends JFrame implements ActionListener {//[1]
    ReadImageComponent component = null;//[2]

    ReadImageUserInterface() {//[3]
        setTitle("Java 画像サンプル1");//[4]
        setSize(320, 160);//[5]
        setLayout(new BorderLayout());//[6]
        Container contentPane = getContentPane();//[7]
        component = new ReadImageComponent();//[8]
        contentPane.add(component, BorderLayout.CENTER);//[9]
    }

    public void actionPerformed(ActionEvent e){//[20]
    }

    public static void main(String[] args) {//[30]
        ImageIO.write(img, "jpg", new File("./photo/jpeg", "Histgram.jpg"));
        ReadImageUserInterface readImageUserInterface = new ReadImageUserInterface();//[31]
        WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };//[32]
        readImageUserInterface.addWindowListener(windowAdapter);//[33]
        readImageUserInterface.pack();//[34]
        readImageUserInterface.setVisible(true);//[35]
    }

    class ReadImageComponent extends Component {//[100]
        BufferedImage bufferedImage1 = null;//[101]
        BufferedImage bufferedImage2 = null;//[101]

        ReadImageComponent() {//[102]
            String imageFilename1 = "./photo/jpeg/frame-64-65.jpg";//[103]
            String imageFilename2 = "./photo/jpeg/frame-65-64.jpg";//[103]
            try {//[104]
                bufferedImage1 = ImageIO.read(new File(imageFilename1));//[105]
                bufferedImage2 = ImageIO.read(new File(imageFilename1));//[105]
            } catch (IOException e) {//[106]
                System.out.println("image file not found. [" + imageFilename1 + "]");//[107]
                System.out.println("image file not found. [" + imageFilename2 + "]");//[107]
            }
        }

        public void paint(Graphics graphics) {//[110]
            graphics.drawImage(bufferedImage1, 0, 0, null);//[111]
            graphics.drawImage(bufferedImage2, 0, 0, null);//[111]
        }

        public Dimension getPreferredSize() {//[120]
            int width = 100;//[123]
            int height = 100;//[134]
            if (bufferedImage1 != null) {//[125]
                width = bufferedImage1.getWidth(null);//[126]
                height = bufferedImage1.getHeight(null);//[127]
            }
            if (bufferedImage2 != null) {//[125]
                width = bufferedImage2.getWidth(null);//[126]
                height = bufferedImage2.getHeight(null);//[127]
            }
            return new Dimension(width, height);//[128]
        }

    }
}
*/