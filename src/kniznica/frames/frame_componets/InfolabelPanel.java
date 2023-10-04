package kniznica.frames.frame_componets;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfolabelPanel extends JPanel {    
      
      public InfolabelPanel(){
            
             new JPanel();
            this.setSize(900,50 );
            this.setLayout(null);
            this.setBackground(Color.DARK_GRAY);

            JLabel bookIdLabel = new JLabel("book_id");
            bookIdLabel.setBounds(20,5,80,40);
            bookIdLabel.setPreferredSize(new Dimension(80,40));
            bookIdLabel.setForeground(Color.white);
            //bookIdLabel.setOpaque(true);

            JLabel bookTitleLabel = new JLabel("book_title");
            bookTitleLabel.setBounds(102,5,100,40);
            bookTitleLabel.setPreferredSize(new Dimension(100,40));

            bookTitleLabel.setForeground(Color.white);

            JLabel bookAutorLabel = new JLabel("book_autor");
            bookAutorLabel.setBounds(204,5,100,40);
            bookAutorLabel.setPreferredSize(new Dimension(100,40));
            bookAutorLabel.setForeground(Color.white);

            JLabel bookGenreLabel = new JLabel("book_genre" );
            bookGenreLabel.setBounds(306,5, 100, 40);
            bookGenreLabel.setPreferredSize(new Dimension(100,40));
            bookGenreLabel.setForeground(Color.white);

            JLabel book_stock_Label = new JLabel("book_stock" );
            book_stock_Label.setBounds(408,5, 100, 40);
            book_stock_Label.setPreferredSize(new Dimension(100,40));
            book_stock_Label.setForeground(Color.white);

            this.add(bookIdLabel);
            this.add(bookTitleLabel);
            this.add(bookAutorLabel);
            this.add(bookGenreLabel);
            this.add(book_stock_Label);
      }
      
}
