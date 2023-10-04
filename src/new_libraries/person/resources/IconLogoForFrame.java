package new_libraries.person.resources;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IconLogoForFrame  extends JFrame{

     public IconLogoForFrame(){
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setSize(300,300);
          this.setLayout(new FlowLayout());
          JLabel label = new JLabel("AKo dať logo do hlavičky rámu ?");
          label.setSize(250, 200);
          this.add(label);
          ImageIcon frameLogoImg = new ImageIcon(getClass().getClassLoader().getResource("new_libraries\\person\\resources\\strasiak.png"));
         this.setIconImage(frameLogoImg.getImage());
          this.setVisible(true);
     }
     public static void main(String[] args) {
      new IconLogoForFrame();
     }
      
}
