//  nový rám gui  pre výber databazy na upravu
package kniznica.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SelectPanel  extends JFrame implements ActionListener {

      JPanel selectObjectPanel = new JPanel();
      Border selectObjectPanelBorder = new LineBorder(Color.BLUE,2);
      JButton personsButton;
      JButton booksButton;
      final int PANEL_WIDTH=300;
      final int PANEL_HEIGHT=300;

      public SelectPanel(){
           
                  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  this.setSize(310,310);
                  this.setLayout(null);
                  //this.pack();
                  this.setLocationRelativeTo(null);
                  this.setVisible(true);
                 
                 selectObjectPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
                 selectObjectPanel.setBorder(selectObjectPanelBorder);
                 selectObjectPanel.setBounds(2,2,300,300);
                 selectObjectPanel.setLayout(new GridLayout(2,1));

                 personsButton= new JButton("PERSONS");
                 personsButton.setSize(298,149);
                 personsButton.setBounds(10,10,298,149);
                 personsButton.setBackground(new Color(176, 224 ,230));
                 personsButton.addActionListener(this);
                 
                 booksButton= new JButton("BOOKS");
                 booksButton.setSize(298,149);
                 booksButton.setBounds(10,149,298,149);
                 booksButton.setBackground(new Color(123 ,104 ,238));
                 booksButton.addActionListener(this);
                
                 selectObjectPanel.add(personsButton);
                 selectObjectPanel.add(booksButton);

                 this.add(selectObjectPanel);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource()== personsButton){
              new PersonDatabase();    
              System.out.println("personsbutton work");    
            }
          if(e.getSource()== booksButton){
            new BookDatabase();  
            System.out.println("book_button work");    
          }
            
      }
}
