package kniznica.frames.frame_componets;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextFieldPanel extends JPanel {
      JTextField book_id_Field;
      JTextField book_title_Field;
      JTextField book_autor_Field  ;
      JTextField book_genre_Field;
      JTextField book_stock_Field;
      
      public TextFieldPanel(){
            new JPanel();
             
            this.setSize(900,60);
            this.setLayout(null);
            this.setBackground(Color.LIGHT_GRAY);
            this.setVisible(true);
            /////Fields
            book_id_Field = new JTextField();            
            book_id_Field.setBounds(0,5,80,40);
            book_id_Field.setForeground(Color.BLACK);
            
            book_title_Field = new JTextField();
            book_title_Field.setBounds(82,5,100,40);
            book_title_Field.setForeground(Color.black);
           
           book_autor_Field = new JTextField();
           book_autor_Field.setBounds(184,5,100,40);
           book_autor_Field.setForeground(Color.black);
//////////////////////////////////////////////////////////////////////  tu spraviť chcekbox???
            // book_genre_Field= new JTextField();
            // book_genre_Field.add(new Checkbox("ROMANCE"));
            // book_genre_Field.setBounds(286,5,100,40);
            // book_genre_Field.setForeground(Color.black);

           book_genre_Field = new JTextField();
           book_genre_Field.setBounds(286,5,100,40);
           book_genre_Field.setForeground(Color.black);
           
           book_stock_Field = new JTextField();
           book_stock_Field.setBounds(388,5,100,40);
           book_stock_Field.setForeground(Color.black);        

           this.add(book_id_Field);
           this.add(book_title_Field);
           this.add(book_autor_Field);
           this.add(book_genre_Field);
      }
      
}
