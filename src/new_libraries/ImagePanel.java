package new_libraries;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel  extends JPanel {      
      
    static public ImageIcon icon = new ImageIcon("src\\n" +  "ew_libraries\\strasiak.png");
    //             Image     iconImage = icon.getImage();
    //             Image     scaleImg_1 = iconImage.getScaledInstance(labelIcon.getWidth(), labelIcon.getHeight(), Image.SCALE_SMOOTH);
    //             ImageIcon scaleImgIcon = new ImageIcon(scaleImg_1);
//      static public  JLabel labelIcon = new JLabel();      
      
//       void ImageIcon(){                      
//             labelIcon.setIcon(scaleImgIcon);
//       }
private Image img;

public ImagePanel(String img) {
  this(new ImageIcon(img).getImage());
}

public ImagePanel(Image img) {
  this.img = img;
  Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
  setPreferredSize(size);
  setMinimumSize(size);
  setMaximumSize(size);
  setSize(size);
  setLayout(null);
}

public void paintComponent(Graphics g) {
  g.drawImage(img, 0, 0, null);
}


public static void main(String[] args) {
      ImagePanel panel = new ImagePanel(
        new ImageIcon("src\\n" + //
                    "ew_libraries\\strasiak.png").getImage());
      JFrame f= new JFrame();
      f.getContentPane().add(panel);
    f.pack();
    f.setVisible(true);
}
}
