package new_libraries;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import kniznica.objects.Binding;
import java.awt.event.ActionListener;

public class AddNewBookPanel   extends JFrame implements ActionListener {
public static String title;
public static String author;
public static double amout;
public static String shelfRow;
String genre ;
Binding binding = Binding.soft;
Border border = new LineBorder(Color.black,2);
Border fieldBorder = new LineBorder(Color.black,1);
//////////////////////////////////////////////////
JPanel fieldPanel = new JPanel();  ///   panel pre fieldy   layout null
JTextField titleTf = new JTextField(15)  ;
JTextField authorTf = new JTextField(15)  ;
JTextField genreTf = new JTextField(8)  ;
 JTextField amoutTf = new JTextField(5)  ;
 JTextField bindingTf = new JTextField(5)  ;
 //////////////////////////////////////////////////
 JPanel  labelPanel = new JPanel();   ///  panempere labels      widht 500/2     (height 445 - 50)max / 5
 JLabel titleLabel = new JLabel   ("   Title");
 JLabel authorLabel = new JLabel  ("   Author  Name");
 JLabel genreLabel = new JLabel   ("   Select Genre");
 JLabel amoutLabel = new JLabel   ("   Book  Amout");
 JLabel bindingLabel = new JLabel ("   Select Binding");
 ////////////////////////////////////////////////////
 String[] data_1 = {"soft","hard"};
 JComboBox<Object> cmb ;//genre
 JComboBox<Object> cmb_2 = new JComboBox<>(data_1);//binging
 JPanel column = new JPanel();
 JPanel column_1 = new JPanel();
 ///////////////////////////////////////////////////
 JButton button = new JButton("Create new Book"); 
 ///////////////////////////////////////////////////
ArrayList<JTextField> fielList= new ArrayList<>();
JScrollPane genrePanel = new JScrollPane();
ArrayList<String> dataList ;//  pre Jlist s database
JList<String> genreList;
 /**
 * 
 */
AddNewBookPanel(){  
      
      // genrePanel.setLayout(new ScrollPaneLayout());
      // genrePanel.setBounds(5, 130, 228, 28);
      // genreList= new JList<>();
      // showDataFromDatabaseToJlist();
      // genrePanel.add(genreList);
      // genreList.addMouseListener((MouseListener) this);
      // genreList.setVisibleRowCount(1);
      // genreList.setFont(new Font("Roboto", Font.BOLD, 15));
      // genrePanel.setViewportView(genreList);
       
       this.setBounds(125, 10, 525, 415);
       this.setBackground(new Color(100,149,237));
       this.setLayout(null);
       this.setLayout(null);  
       this.setTitle(" Adding new Book to Database "); 
       ImageIcon i = new ImageIcon(getClass().getClassLoader().getResource("new_libraries\\person\\resources\\books.png"));
       this.setIconImage(i.getImage());   
            ///////////   JPanel pre JTextFieds       

      fieldPanel.setBounds(262,5,245,290);
      fieldPanel.setLayout(null);
      fieldPanel.setBackground(new Color(173, 216 ,230));  
      
      labelPanel.setBounds(5,5,240,290);
      labelPanel.setLayout(null);
      labelPanel.setBackground(new Color(173, 216 ,230)); 
  
      ///////////////////// FIELDS /////////////////      
      titleTf.setBounds(5, 40, 230, 40);
  
      authorTf.setBounds(5, 85, 230, 40);           
      amoutTf.setBounds(5, 175, 80, 40);
      fielList.add(titleTf);fielList.add(authorTf);fielList.add(genreTf);
      fielList.add(amoutTf);fielList.add(bindingTf);
      for(JTextField field : fielList){
            field.setFont(new Font("Alurra",Font.PLAIN,20));
      }          
      fieldPanel.add(titleTf);            
      fieldPanel.add(authorTf);         
      genreTf.setBounds(5, 130, 125, 40);
      genreTf.addActionListener(this); 
      setComboBoxData();
      fieldPanel.add(genreTf) ;
      cmb.setBounds(131, 132, 105, 35);
      fieldPanel.add(cmb) ;
      cmb.addActionListener(this);
      // column.setBackground(Color.pink);
      // fieldPanel.add(column);    
      fieldPanel.add(amoutTf);  
      bindingTf.setBounds(5,220,80,40);
      cmb_2.setBounds(87,222,60,35);
      fieldPanel.add(bindingTf);fieldPanel.add(cmb_2);
      cmb_2.addActionListener(this); 
      column_1.setLayout(new GridLayout(0,2,5,0));
      column_1.setBounds(5, 130, 228, 40);
     // column_1.add(genreTf);
      //column_1.add(cmb);
      //column_1.add(genrePanel);
     // fieldPanel.add(genrePanel);  // panel s Jlistom na skušku
     // fieldPanel.add(column_1);
      
      titleTf.setBorder(fieldBorder);            
      authorTf.setBorder(fieldBorder);
      genreTf.setBorder(fieldBorder) ; genreTf.setEditable(false);         
      amoutTf.setBorder(fieldBorder);            
      bindingTf.setBorder(fieldBorder);bindingTf.setEditable(false);
      ////////// LABELS //////////////////////////////   
      ArrayList<JLabel> labelList = new ArrayList<>(); 
      labelList.add(titleLabel);labelList.add(authorLabel);labelList.add(genreLabel);
      labelList.add(amoutLabel);labelList.add(bindingLabel);  
      for(JLabel label :labelList){
            label.setFont(new Font("Roboro",Font.BOLD,22));
      }
      titleLabel.setBounds(5, 40, 230, 40);
      authorLabel.setBounds(5, 85, 230, 40);
      genreLabel.setBounds(5, 130, 230, 40);
      amoutLabel.setBounds(5, 175, 230, 40);
      bindingLabel.setBounds(5, 220, 230, 40);
      
      labelPanel.add(titleLabel);
      labelPanel.add(authorLabel);
      labelPanel.add(genreLabel);
      labelPanel.add(amoutLabel);
      labelPanel.add(bindingLabel);
      /////////   Button
      button.setBounds(2, 300, 500,45);
      button.setBackground(new Color(0,255 ,255));
      button.addActionListener(this);
      //this.setBorder(border);
      this.add(labelPanel);
      this.add(fieldPanel);
      //this.add(hlavny);
      this.add(button);
      this.setVisible(true);
}
@Override
      public void actionPerformed(ActionEvent e) {      
           
            if(e.getSource()==button){   
                  String title = titleTf.getText();  
                  String author= authorTf.getText();
                  String genre = genreTf.getText();
                  Double amout = Double.parseDouble(amoutTf.getText());
                  Binding binding = Binding.valueOf(bindingTf.getText());  
                  for(JTextField field :fielList){
                         if(field.getText()!=null || !field.getText().isEmpty()){
                               System.out.println(" toto tuto sa poslalo");
                               SqlFunctions.insertNewBookToDatabaseTableBooks(title, author, genre, amout, binding);
                         }if(field.getText()==null || field.getText().isEmpty()){
                               JOptionPane.showMessageDialog(null, "Zadajte požadované hodnoty !!", "title", JOptionPane.ERROR_MESSAGE);
                         }
                   }         
                  // if(bindingTf.getText().equals( String.valueOf( Binding.hard.getPoradie() ) )  ) {
                  //      System.out.println(" enum sa nezhoduje s hodnotami ktoré ste zadali");
                  //       JOptionPane.showMessageDialog(null, "Zadajte požadované hodnoty !!", "title", JOptionPane.ERROR_MESSAGE);
                  // }
                 refreshComboBox();

                  JOptionPane.showMessageDialog(null, "Kniha bola úspašne pridaná do databázy :)", "úspech :))", JOptionPane.INFORMATION_MESSAGE);

                 
            }
            for(JTextField foo :fielList){
                  if(e.getSource()==foo){
                       System.out.println("pisaut");
                  }
            }
            if(e.getSource()==cmb){
                  String selectedGender = String.valueOf(cmb.getSelectedItem());
                  genreTf.setText(selectedGender);
            }
            if(e.getSource()==cmb_2){
                  String selectedBinding = String.valueOf(cmb_2.getSelectedItem());
                  bindingTf.setText(selectedBinding);
            }


      }

          public void setComboBoxData(){
      try {
            String query = "SELECT genre FROM genres ;";            
            Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(query);          
                  cmb = new JComboBox<Object>() ;
                  while (resultSet.next()) {
                        String i = resultSet.getString(1);                    
                        cmb.addItem(i);                      
                  }
            System.out.println("Načítanie údajov prebehlo úspešne.");
            c.close();
         
            resultSet.close();
          
            } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Update is Not Corect !!!");
            }
      }
      public void refreshComboBox() {
            try {
                String query = "SELECT genre FROM genres ;";
                Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                java.sql.Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        
                ComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>();
                
                while (resultSet.next()) {
                    String addData = resultSet.getString(1);
                    ((DefaultComboBoxModel<Object>) comboBoxModel).addElement(addData);
                }
        
                c.close();
        
                cmb.setModel(comboBoxModel); // Nastaví nový model do JComboBox
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Refresh is Not Correct !!!");
            }
        }


         public void showDataFromDatabaseToJlist(){
            try {
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resulset = statement.executeQuery("SELECT genre FROM genres;");
                  dataList= new ArrayList<>();
                  DefaultListModel<String> defaultModel = new DefaultListModel<>(); 
                  while(resulset.next()){                     
                      String meno=    resulset.getString(1);                   
                      dataList.add(meno);                    
                  }
                  genreList.setModel(defaultModel);
                  
                  for(String string : dataList){
                        defaultModel.addElement(string);
                  }
                  connection.close();
                  statement.close();
                  resulset.close();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "CHYBA", JOptionPane.ERROR_MESSAGE);;
            }
      }

        
}

/*
 * AddNexBookPanel  pre zápis nonej knihy  do databazy 
 */
