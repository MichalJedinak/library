package new_libraries.tools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LabelIcon extends JFrame {
ImageIcon icon ;
JLabel label;
JPanel panel;
      LabelIcon(){
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(800, 600);
      icon = new ImageIcon("src\\new_libraries\\person\\resources\\myšiak.gif");
      Image img = icon.getImage();
      Image scaleImg_1 =img.getScaledInstance(200,200, Image.SCALE_FAST);
      ImageIcon scaleIcon = new ImageIcon(scaleImg_1);
     // label = new JLabel("", icon, 0);
      label = new JLabel();label.setIcon(scaleIcon);
      JTextArea area = new JTextArea("Ja som šikovný");
      area.setFont(new Font("Gotic", Font.BOLD, 15));
      area.setForeground(Color.BLACK);
      area.setBounds(35, 270,150, 18);
      area.setEditable(false);
      label.add(area);
      this.setLocationRelativeTo(null);
      this.add(label);
      this.setVisible(true);
      }
      public static void main(String[] args) {
            new LabelIcon();
      }
}
