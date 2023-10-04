package new_libraries.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class PopMenu  extends JPanel implements ActionListener{
      JPopupMenu popupMenu = new JPopupMenu();
 JMenu   pjm= new JMenu("select genre");
 JMenuItem FANTAZIA = new JMenuItem("FANTAZIA");
 JMenuItem FILOZOFIA = new JMenuItem("FILOZOFIA");
 JMenuItem HOROR = new JMenuItem("HOROR");

 PopMenu(){
      this.setSize(80, 80);
      this.setVisible(true);
   
      this.add(popupMenu);
      popupMenu.add(FANTAZIA);
      popupMenu.add(FILOZOFIA);
      popupMenu.add(HOROR);
      popupMenu.show(this,0,0);
      FANTAZIA.addActionListener(this);
      FILOZOFIA.addActionListener(this);
      HOROR.addActionListener(this);
 }

@Override
public void actionPerformed(ActionEvent e) {
     

}
}
