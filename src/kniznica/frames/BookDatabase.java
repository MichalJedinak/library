package kniznica.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import kniznica.JdbcMySQL;
import kniznica.frames.frame_componets.AddToDatabasePanel;
import kniznica.frames.frame_componets.InfolabelPanel;
import kniznica.frames.frame_componets.TextFieldPanel;
import kniznica.objects.Book;
import kniznica.objects.Genre;

public class BookDatabase implements ActionListener {

      int bounsY = 60;
      AddToDatabasePanel newAdd_panel;
      Border titleBorder = new LineBorder(Color.black,3);
      Border database_PanelBorder = new LineBorder(Color.black,3);
      JFrame frame; 
      InfolabelPanel info_label_Panel;
      TextFieldPanel text_field_Panel;
      JTextField book_id_Field;
      JTextField book_title_Field;
      JTextField book_autor_Field  ;
      JTextField book_genre_Field;

      JPanel genre_panel;
      JMenuBar ganre_bar      ;
      JMenuItem genre_menu;
      JMenuItem ROMANCE;
      JMenuItem HOROR;
      JMenuItem POETRY;
      JMenuItem FANTASY;
      JMenuItem FARYTAIL;
      JMenuItem EDUKA;
      JTextField book_stock_Field;
      JButton set_field_Button;
      JButton reset_field_Button;
      
      JPanel database_Panel;
      JPanel title_database_Panel;
      JLabel label;
      JLabel title;    
      
      JPanel add_to_database_Panel;
      
      
      String book_id_String;
      String book_title_String;
      String book_autor_String;
      String book_genre_String;
      String book_stock_String;
      JLabel add_book_id_Label;
      JLabel add_book_title_Label;
      JLabel add_autor_Label;
      JLabel add_genre_Label;
      JLabel add_stock_Label;
      
      JPanel button_Panel;
      JButton save_Button;
      JButton crl_Button;
      JButton reset_Button;
      
      public BookDatabase() {     

            frame= new JFrame();
            frame.setSize(900, 600); 
            frame.setBackground(Color.MAGENTA);
            frame.setLayout(null);
            frame.setVisible(true); 
            //  label for image icon only  for fun and clic button control 
            label = new JLabel();
            label.setBounds(((900/2)-(300/2)),200,50,50);
            ImageIcon icon = new ImageIcon("javaKniznicaUloha2\\src\\kniznica\\img.png");
            Image     img  = icon.getImage();
            Image imgScale = 
            img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon smallIcon = new ImageIcon(imgScale);
            label.setIcon(smallIcon);

            ////  informacny panel nad text field panelom len popis pre zadavane hodnoty
            info_label_Panel = new InfolabelPanel();
            info_label_Panel.setBounds(0, 0, 900, 50);
           ////// Panel pre zapisovanie udajov na prepis do databazy 
           text_field_Panel = new TextFieldPanel();
            text_field_Panel.setSize(900,60);
           text_field_Panel.setLayout(null);
            text_field_Panel.setBackground(Color.LIGHT_GRAY);
           text_field_Panel.setBounds(1,51,900,60); 

            book_id_Field = new JTextField();            
            book_id_Field.setBounds(0,5,80,40);
            book_id_Field.setForeground(Color.BLACK);
            
            book_title_Field = new JTextField();
            book_title_Field.setBounds(82,5,100,40);
            book_title_Field.setForeground(Color.BLACK);
           
           book_autor_Field = new JTextField();
           book_autor_Field.setBounds(184,5,100,40);
           book_autor_Field.setForeground(Color.BLACK);


           genre_panel = new JPanel();
           genre_panel.setSize(100,40);
           genre_panel.setBounds(286,5,100,40);   

            ganre_bar = new JMenuBar();  
            ganre_bar.setBounds(0, 0, 100, 40);
            genre_panel.add(genre_menu);
           genre_menu = new JMenu();
           genre_menu.setForeground(Color.BLACK);
           
           ROMANCE = new JMenuItem("ROMANCE");
           genre_menu.add(ROMANCE);
           ROMANCE.addActionListener(this);
           ROMANCE.setMnemonic(KeyEvent.VK_R);

           HOROR = new JMenuItem("HOROR");
           genre_menu.add(HOROR);
           HOROR.addActionListener(this);
           HOROR.setMnemonic(KeyEvent.VK_H);

           ganre_bar.add(genre_menu);
          

      //      book_genre_Field = new JTextField();
      //      book_genre_Field.setBounds(286,5,100,40);
      //      book_genre_Field.setForeground(Color.BLACK);
           
           book_stock_Field = new JTextField();
           book_stock_Field.setBounds(388,5,100,40);
           book_stock_Field.setForeground(Color.BLUE);        

           //// Buttony  pre text field po potvrdeny prepise hodnoty do database panel -database lebal          
           set_field_Button = new JButton("SET");
           set_field_Button.setBounds(610,7,70,35);
           set_field_Button.setFocusable(false);
           set_field_Button.setBackground(Color.green);
           set_field_Button.addActionListener(this);

           reset_field_Button= new JButton("Reset");// nastavy text na ""
           reset_field_Button.setBounds(681,7,70,35);
           reset_field_Button.setFocusable(false);
           reset_field_Button.setBackground(new Color(255, 250 ,205));
           reset_field_Button.addActionListener(this);
           
           text_field_Panel.add(book_id_Field);
           text_field_Panel.add(book_title_Field);
           text_field_Panel.add(book_autor_Field);
           text_field_Panel.add(genre_panel);
          // text_field_Panel.add(book_genre_Field);
           text_field_Panel.add(set_field_Button);
           text_field_Panel.add(reset_field_Button); 

    //// DATABASE PANEL / TITLE_PANEL - title_label/ ADD_TO_DTABASE_PANEL - (id,neame,lastnema,card LABEL)/BUTTON_PANEL
            database_Panel= new JPanel();
            database_Panel.setBounds(0, 110, 900, 500);
            database_Panel.setBackground(new Color (133 ,204 ,250));
            database_Panel.setBorder(database_PanelBorder);
            database_Panel.setLayout(null);

            title_database_Panel= new JPanel(); // TITLE PANEL
            title_database_Panel.setBounds(0,0,900,50);
            title_database_Panel.setBackground(new Color(60 ,105, 225));
            title_database_Panel.setBorder(titleBorder);
            
            title = new JLabel();//TITLE LABEL
            title.setText("BOOK DATABASE");
            title.setPreferredSize(new Dimension(100, 40));
            title.setBounds(350,3,100,40);
            title.setForeground(Color.black);
            
            title_database_Panel.add(title);            
            database_Panel.add(title_database_Panel);
            title_database_Panel.add(label);
            
            Border addBorder= new LineBorder(Color.black, 2);
            Border labelsBorder = new LineBorder(Color.black,1);
            //////////// ADD PANEL - LABELS(id,name----) 
            add_to_database_Panel = new JPanel();
            add_to_database_Panel.setBounds(0,52,900,48);
            add_to_database_Panel.setBackground(Color.lightGray);
            add_to_database_Panel.setForeground(Color.white);
            add_to_database_Panel.setBorder(addBorder);
            add_to_database_Panel.setLayout(null);

            database_Panel.add(add_to_database_Panel);
            
            add_book_id_Label = new JLabel("");            
            add_book_id_Label.setText("");
            add_book_id_Label.setPreferredSize(new Dimension(80,40));
            add_book_id_Label.setBounds(0, 3, 80, 40);
            add_book_id_Label.setBorder(labelsBorder);
            add_book_id_Label.setForeground(Color.black);
            
            add_book_title_Label = new JLabel();
            add_book_title_Label.setText("");
            add_book_title_Label.setPreferredSize(new Dimension(100,40));
            add_book_title_Label.setBounds(81, 3, 100, 40);
            add_book_title_Label.setBorder(labelsBorder);
            
            add_autor_Label = new JLabel();
            add_autor_Label.setText("");
            add_autor_Label.setPreferredSize(new Dimension(100,40));
            add_autor_Label.setBounds(182, 3, 100, 40);
            add_autor_Label.setBorder(labelsBorder);
            add_autor_Label.setForeground(Color.black);
            
            add_genre_Label = new JLabel();
            add_genre_Label.setText("");
            add_genre_Label.setPreferredSize(new Dimension(100,40));
            add_genre_Label.setBounds(283, 3, 100, 40);
            add_genre_Label.setBorder(labelsBorder);
            add_genre_Label.setForeground(Color.black);   

            add_stock_Label = new JLabel();
            add_stock_Label.setText("");
            add_stock_Label.setPreferredSize(new Dimension(100,40));
            add_stock_Label.setBounds(384, 3, 100, 40);
            add_stock_Label.setBorder(labelsBorder);
            add_stock_Label.setForeground(Color.black);

            ///////////Buttons panel  for  clear send reset   database   /////
            button_Panel = new JPanel();
            button_Panel.setBounds(640,5,235,42);
            button_Panel.setBackground(Color.lightGray);

            crl_Button= new JButton("Clear");
            crl_Button.addActionListener(this);
            crl_Button.setSize(59, 40);
            crl_Button.setBounds(1, 5, 59, 39);
            crl_Button.setBackground(Color.red);

            save_Button= new JButton("Save");
            save_Button.addActionListener(this);
            save_Button.setSize(59, 40);
            save_Button.setBounds(70, 5, 59, 39);
            save_Button.setBackground(Color.green);
            
            reset_Button= new JButton("Reset");
            reset_Button.addActionListener(this);
            reset_Button.setSize(59, 40);
            reset_Button.setBounds(135, 5, 59, 39);
            reset_Button.setBackground(Color.lightGray);

            button_Panel.add(save_Button);
            button_Panel.add(reset_Button);
            button_Panel.add(crl_Button);
            
            add_to_database_Panel.add(add_book_id_Label) ;          
            add_to_database_Panel.add(add_book_title_Label);
            add_to_database_Panel.add(add_autor_Label);
            add_to_database_Panel.add(add_genre_Label);
            add_to_database_Panel.add(add_stock_Label); 
            add_to_database_Panel.add(button_Panel) ;       
            add_to_database_Panel.setVisible(true);

            frame.add(info_label_Panel);
            frame.add(text_field_Panel);
            frame.add(database_Panel);   

          
      }
      //////////////////// Action Listener metod
      @Override
      public void actionPerformed(ActionEvent e) {
            ArrayList<Object> objList= new ArrayList<Object>();

            book_id_String = book_id_Field.getText();
            book_title_String = book_title_Field.getText(); 
            book_autor_String = book_autor_Field.getText();
            book_genre_String = book_genre_Field.getText();
            book_stock_String =book_stock_Field.getText();

      if (e.getSource() == set_field_Button) {
             database_Panel.add(add_to_database_Panel);
             label.setVisible(false);// image  for fan

             add_book_id_Label.setText(book_id_String);   

             add_book_title_Label.setText(book_title_String);   

             add_autor_Label.setText(book_autor_String);   

             add_genre_Label.setText(book_genre_String);   

             add_stock_Label.setText(book_stock_String);
            
             frame.repaint();
             System.out.println(book_title_String+" "+book_autor_String+" "+book_genre_String+" "+book_stock_String);        
      }  
      ////////////////set field BUTTON//////////////////   
      if(e.getSource()==reset_field_Button) {
            book_id_Field.setText("");
             book_title_Field.setText("");
              book_autor_Field.setText("");
               book_genre_Field.setText("");
                book_stock_Field.setText("");

                frame.repaint();
      }

      ////////////////////////// SAVE BUTTON////////////////////
      int i = 60; 
      if( i <= bounsY )    {
            bounsY = i + bounsY;
            bounsY++;
            if (e.getSource() == save_Button) {  
                  //String add_String_id = add_book_id_Label.getText();
                  //int add_id =Integer.parseInt(add_String_id);
                  String add_string_title= add_book_title_Label.getText();
                  String add_string_autor = add_autor_Label.getText();
                  Genre add_string_genre = Genre.valueOf(add_genre_Label.getText());

                  Book book = new Book(add_string_title, add_string_autor, add_string_genre);
                  JdbcMySQL boo_creatko = new JdbcMySQL();
                        try {
                              boo_creatko.createBook(book);System.out.println("book vos created !!");
                        } catch (ClassNotFoundException e1) {                             
                              e1.printStackTrace();
                        }

                  System.out.println("Svae Button work !!"); 
                  database_Panel.revalidate();                   
                  database_Panel.add(add_to_database_Panel)  ;           
                  database_Panel.repaint();
                 add_to_database_Panel.setBounds(0,bounsY,900,60);
                  frame.repaint();                   
                  System.out.println(bounsY);            
                        
                  } 
      }  
      if(e.getSource()==reset_Button){
            add_book_id_Label.setText("");               
             add_book_title_Label.setText("");   
             add_autor_Label.setText("");            
             add_genre_Label.setText("");              
             add_stock_Label.setText("");

             frame.repaint();
      }   
      if(e.getSource()==crl_Button){
            objList.remove(add_to_database_Panel);
            database_Panel.remove(add_to_database_Panel); 
            frame.repaint();
      }
}
      public static void main(String[] args) {
            new BookDatabase();
      }
}