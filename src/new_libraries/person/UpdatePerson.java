package new_libraries.person;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;

public class UpdatePerson extends JPanel implements ActionListener {
     
      JPanel p = new JPanel(new MigLayout());
      JPanel l = new JPanel(new MigLayout());
      static JComboBox<String> combo;
      JLabel label = new JLabel("Select data for Editing !");// info label for combo data
      JLabel label_1 = new JLabel("  Name");
      static JTextField field_1 = new JTextField(15);// update name
      JLabel label_2 = new JLabel("  Midle Name");
      static JTextField field_2 = new JTextField(15);// update midle name
      JLabel label_3 = new JLabel("  Last Name");
      static JTextField field_3 = new JTextField(15);// update last name
      JLabel label_4 = new JLabel("  Gender");
      JTextField field_4 = new JTextField(15);
      JLabel label_5 = new JLabel("  Street");
      JTextField field_5 = new JTextField(15);
      JLabel label_6 = new JLabel("  City");
      JTextField field_6 = new JTextField(15);
      JLabel label_7 = new JLabel("  Post Code");
      JTextField field_7 = new JTextField(15);
      JLabel label_8 = new JLabel("  Card Number");
      JTextField field_8 = new JTextField(15);
      JLabel label_9 = new JLabel("  Byte[] BLOP");
      static JTextField field_9 = new JTextField(15);
      //JLabel info = new JLabel("foto");
      JLabel imgLabel = new JLabel(); //  pre zobrazenie fotky a jej zmenu
      static JTextArea imgArea = new JTextArea();//  len na čítanie blop s databazy  do img
      JButton selectImg = new JButton("Zmeň icon ");
      JButton deleteImg = new JButton("Odober icon");

      JButton back = new JButton("Back");
      JButton update = new JButton("Update Person", null);
      int x = 50;
      int y = 10;
      int panelWidht = 650;
      int panelHeight = 520;
      int labelWidht = 100;
      int labelHeight = 50;
      int fieldWidht = 100;
      int fielHeight = 40;
      int fontSize = 20;
      Color color = new Color(99,98,97);
      Color white = Color.white;
      Font labelFont = new Font("Roboto",Font.BOLD, fontSize);
      Font fieldFont = new Font("Roboto",Font.ITALIC, fontSize+2);
      ArrayList<JTextField> fieldList = new ArrayList<>();
      static String id; // id v databaze 
      static String name; // meno
      static String midle ; // stredne meno
      static String last ; //  priezvysko
      static String gender;  // pohlavie
      static String street; // ulica
      static String city; // mesto
      static String postCode; // smerové číslo
      static int number ; //  pre konvert z int na string s členskej karty
      static String card;  // členská karta
      static byte[] byteImg;  ///  pre obrazok 
      static boolean userSelectedImage;
      String empty = "";
      ResultSet resultSet ; // pre  setComboBoxData()
       String selectedValue;// pre combo retazec 
      String[] comboValues;// spetny retazec s combo
      ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");

      public      UpdatePerson(){
            ArrayList<JLabel> labelList = new ArrayList<>();
            labelList.add(label);
            labelList.add(label_1);
            labelList.add(label_2);
            labelList.add(label_3);
            labelList.add(label_4);
            labelList.add(label_5);
            labelList.add(label_6);
            labelList.add(label_7);
            labelList.add(label_8);
            labelList.add(label_9);
            //labelList.add(info);
            for(JLabel label : labelList){
                  label.setFont(labelFont);
                  label.setForeground(white);
                  label.setSize(labelWidht, labelHeight);                  
            }
            //info.setPreferredSize(new Dimension(120, 120));
            imgLabel.setPreferredSize(new Dimension(120, 120));
            l.setBounds(450, 150, 120, 200);
            Image img = icon.getImage();
            Image smallImg = img.getScaledInstance(120, 120, Image.SCALE_FAST);
            icon = new ImageIcon(smallImg);
            imgLabel.setIcon(icon);
            labelList.get(0).setBounds(50, 45, 450, 70);
            labelList.get(0).setBackground(white);
            labelList.get(0).setForeground(Color.BLACK);
            
            fieldList.add(field_1);
            fieldList.add(field_2);
            fieldList.add(field_3);
            fieldList.add(field_4);
            fieldList.add(field_5);
            fieldList.add(field_6);
            fieldList.add(field_7);
            fieldList.add(field_8);
            fieldList.add(field_9);
            for(JTextField field : fieldList){                 
                  field.setEditable(true);
                  field.addActionListener(this);                  
                  field.setSize(fieldWidht, fielHeight);
                  field.setFont(fieldFont);
            }
            field_8.setEditable(false);field_8.setBackground(Color.pink);;
            field_9.setEditable(false);field_9.setBackground(Color.yellow);
            ArrayList<JButton> buttonList = new ArrayList<>();
            buttonList.add(back);
            buttonList.add(update);
            for(JButton button : buttonList){
                  button.addActionListener(this);
                  if(button==buttonList.get(0)){
                        button.setBackground(white);
                        button.setForeground(new Color(178,34,34));
                        button.setFont(new Font("",Font.BOLD,15));
                  }else{
                        button.setBackground(Color.green);
                  }
            }
            ////   samostatne pre img seelctor pod imgLabelom
            selectImg.addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent e) {
                  String userHome = System.getProperty("user.home");                 
                  File picturesDirectory = new File(userHome, "Pictures");
                  JFileChooser fileChooser = new JFileChooser();
                  fileChooser.setCurrentDirectory(picturesDirectory);
                  int respons = fileChooser.showSaveDialog(null);

                  if(respons == JFileChooser.APPROVE_OPTION){                        
                       // JTextArea area = new JTextArea("");                     
                        File file = fileChooser.getSelectedFile();
                        ImageIcon fileIcon = new ImageIcon(file.getPath());
                        // String fileString = fileChooser.getSelectedFile().toString();
                        // String fileExtention = fileString.substring(fileString.lastIndexOf(".") + 1);
                        Image img = fileIcon.getImage();
                        Image scaleImg_1 =img.getScaledInstance(120, 120, Image.SCALE_FAST);
                        icon = new ImageIcon(scaleImg_1);
                        imgLabel.setIcon(icon);  
                        userSelectedImage=true;
                       
                  /////////////   skuska  najprv prebytovat s path a až potom obrazkovať
                   String inPutPath = file.getPath();     //  inPutPath  je String
                   System.out.println(inPutPath+"  inPutPath s area textu "+ "or inputpath = "+file.getPath());
                   String outPutPath ="src\\new_libraries\\person\\resources\\outPutImgUpdate.txt";
                   String savePath ="src\\new_libraries\\person\\resources\\obrazok.jpg";
                   try {
                         AddNewPesronPanel.imgByteEncode(inPutPath, outPutPath);
                   } catch (Exception e1) {
                         
                         e1.printStackTrace();
                   }
                   try {
                         AddNewPesronPanel.imgByteDecode(outPutPath, savePath);
                   } catch (Exception e1) {
                         
                         e1.printStackTrace();
                   }      
                        
                       // AddNewPesronPanel.imageTransferToBlop();
            try {
                  String filePath = "src\\new_libraries\\person\\resources\\obrazok.jpg";
                  File files = new File(filePath); //  použijeme cestu z filechooser selected
                  FileInputStream fis = new FileInputStream(files);// aby sme foto prepísali na byte[]
                  BufferedImage bi = ImageIO.read(fis);
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  ImageIO.write(bi, "png,jpg,jpeg,gif", baos);
                  byte[] byteImg;
                  byteImg = baos.toByteArray();
                  System.out.println(byteImg+" ////////////////");
                  FileWriter w= new FileWriter("src\\new_libraries\\person\\resources\\byteUpdate.txt");
                  String s = byteImg.toString();                
                  w.write(s);
                  field_9.setText(s);
                  w.close();                  
                  } catch (Exception ew) {
                  System.out.println(ew);
            }
                    
            }
                  if(respons == JFileChooser.SAVE_DIALOG){
                        userSelectedImage=true;
                  System.out.println("ja neviem či vôbec fungujem !!!!§"+ userSelectedImage+" = tue");                  
                  }                    
              }
            });

            ///////////////////////////delete  icon from label
            deleteImg.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                  ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
                  Image ii = icon.getImage();
                  Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                  icon = new ImageIcon(iscc);
                  imgLabel.setIcon(icon);
                  userSelectedImage = false;
                  }
                  
            });
                  
         
            label.setBounds(50, 0, 400, 40);
            combo = new JComboBox<String>();
            setComboBoxData();        
            combo.setFont(fieldFont);
            combo.setPreferredSize(new Dimension(500, 40));
            //combo.setEditable(true);
            combo.setBounds(25, 41, 550, 40); 
            combo.addActionListener(this);
            p.setBounds(5, 100, 400, 400);

            ArrayList<JPanel> panelL= new ArrayList<>();
            panelL.add(l);panelL.add(p);panelL.add(this);
            for(JPanel p :panelL){
                  p.setBackground(color);
            }
            selectImg.setPreferredSize(new Dimension(120, 39));
            deleteImg.setPreferredSize(new Dimension(120, 39));
            l.add(imgLabel,"wrap");
            l.add(selectImg,"wrap,center");
            l.add(deleteImg,"center");
            p.add(label_1);
            p.add(field_1,"span 3,wrap");
            p.add(label_2);
            p.add(field_2,"span 3,wrap");
            p.add(label_3);
            p.add(field_3,"wrap");
            p.add(label_4);
            p.add(field_4,"wrap");
            p.add(label_5);
            p.add(field_5,"wrap");
            p.add(label_6);
            p.add(field_6,"wrap");
            p.add(label_7);
            p.add(field_7,"wrap");
            p.add(label_8);
            p.add(field_8,"wrap");
            p.add(label_9);
            p.add(field_9,"wrap");
            //this.add(info,"span 2,wrap");
            p.add(back,"align right,gaptop 8");
            p.add(update,"gaptop 8,align right ,wrap");
            
            this.setBounds(x, y, panelWidht, panelHeight);
            this.setLayout(null);
            this.add(label);
            this.add(combo);
            this.add(l);
            this.add(p);
            this.setVisible(true);
            this.repaint();
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource()==combo){
                  selectedValue = (String) combo.getSelectedItem();// Rozdelím hodnoty na String reťazec
                  comboValues = selectedValue.split(" "); // Rozdelí reťazec na časti podľa medzier
                  selectedValueFromComboBox();this.repaint(); //  pomocou id s combovalue zadame do field text z kombo
                  // showFotoInLabel();this.repaint();
            
            }
            if(e.getSource() == back){
                  for(JTextField field : fieldList){  field.setText(empty);   }
                  //info.setText(empty);  
                  ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
                  Image ii = icon.getImage();
                  Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                  icon = new ImageIcon(iscc);
                  imgLabel.setIcon(icon);                  
                  refreshComboBoxData();  
            }
            if(e.getSource() == update){
                  updatePersonInDatabaseTable();
                  System.out.println("Person is Edited , the update corect!!"); 
               
                  for(JTextField field : fieldList){  field.setText(empty);   }                  
                 // JOptionPane.showConfirmDialog(null,"Update údajov sa podaril","Update",JOptionPane.PLAIN_MESSAGE);
                  ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
                  Image ii = icon.getImage();
                  Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                  icon = new ImageIcon(iscc);
                  imgLabel.setIcon(icon); 
                   refreshComboBoxData();
            }
            
      }
      public void setComboBoxData(){
      try {
                  String query ="SELECT * FROM persons;";
                  Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                   java.sql.Statement statement = c.createStatement();
                   resultSet = statement.executeQuery(query);
                   //  ArrayList<String> dataFromDatabase= new ArrayList<>();
                   // combo = new JComboBox<String>() ;
                  while (resultSet.next()) {
                        int i = resultSet.getInt(1);
                        id= String.valueOf(i);  
                        name = resultSet.getString(2);
                        midle = resultSet.getString(3);
                        last = resultSet.getString(4);
                        number = resultSet.getInt(5);
                        card = String.valueOf(number);
                        gender = resultSet.getString(6);                        
                        street= resultSet.getString(7);
                        city = resultSet.getString(8);
                        postCode = resultSet.getString(9);
                        byteImg = resultSet.getBytes(10);
                        // dataFromDatabase.add(id+" "+name+" "+midle+" "+last+" "+card+" "+gender+" "+street+" "+city+" "+postCode+" "+byteImg);
                        // for (Iterator<String> iterator = dataFromDatabase.iterator(); iterator.hasNext(); ) {
                        //       String hodnota = iterator.next();
                        //       if (hodnota == null) {
                        //             iterator.remove();
                        //       }
                        // }
                         // combo= new JComboBox<>(dataFromDatabase.toArray(new String[1]));
                        combo.addItem(id+" "+name+" "+midle+" "+last+" "+card+" "+gender+" "+street+" "+city+" "+postCode+" "+byteImg);
                        // System.out.println(id+" "+name+" "+midle+" "+last+" "+card+" "+gender+" "+street+" "+city+" "+postCode+" "+byteImg);
                      
                  }
                 // System.out.println(id);  //  len kontrolny vypis poctu id v persons
                  c.close();
            } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Update is Not Corect !!!");
            }
      }
      public static void refreshComboBoxData() {
            try {
                String query = "SELECT * FROM persons;";
                Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username,SqlFunctions.password);
                java.sql.Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
                while (resultSet.next()) {
                    int i = resultSet.getInt(1);
                        id= String.valueOf(i);  
                        name = resultSet.getString(2);
                        midle = resultSet.getString(3);
                        last = resultSet.getString(4);
                        number = resultSet.getInt(5);
                        card = String.valueOf(number);
                        gender = resultSet.getString(6);
                        street = resultSet.getString(7);
                        city = resultSet.getString(8);
                        postCode = resultSet.getString(9);
                        byteImg = resultSet.getBytes(10);
                        comboBoxModel.addElement(id+" "+name+" "+midle+" "+last+" "+card+" "+gender+" "+street+" "+city+" "+postCode+" "+byteImg);
                }
                c.close();
                combo.setModel(comboBoxModel); // Nastaví nový model do JComboBox
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Refresh is Not Correct !!!");
            }
        }
      public void updatePersonInDatabaseTable(){
               //  readByteFromTxt();
            try {
                  //byte[] pb = imgArea.getText().getBytes();
                  File fi = null;
                  if(userSelectedImage){
                        fi = new File("src\\new_libraries\\person\\resources\\obrazok.jpg");
                  }
                  FileInputStream fis = null;
                  if(fi != null){
                        fis = new FileInputStream(fi);
                  }
                  // System.out.println(fis+"  výstup s file in stre");fis.close();
                  String selectId = comboValues[0];
                  String query ="UPDATE persons SET name = ? ,midle_name=? ,last_name=?, gender=?,street=?,city=?,post_code=?,foto=? WHERE id ="+selectId;
                  Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, "show_pussy8squirrel~hairy");
                   java.sql.Statement statement = c.createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM persons;");   
                  PreparedStatement ubdateStatement = c.prepareStatement(query); 
                  ubdateStatement.setString(1, field_1.getText()); //meno
                  ubdateStatement.setString(2, field_2.getText()); // stredne meno
                  ubdateStatement.setString(3, field_3.getText()); //priezvysko
                  ubdateStatement.setString(4, field_4.getText());  //pohlavie
                  ubdateStatement.setString(5, field_5.getText());  // ulica
                  ubdateStatement.setString(6, field_6.getText()); //mesto
                  ubdateStatement.setString(7, field_7.getText()); //smerove č
                  //ubdateStatement.setBytes(8, field_9.getText().getBytes());  // fotka  
                  
                  if(fis != null){
                        ubdateStatement.setBinaryStream(8, fis, fi.length());
                  }else{
                        ubdateStatement.setNull(8,java.sql.Types.BLOB);
                      }
                // System.out.println(imgArea.getText()+" .................IMG AREA prevzala tento tuto");
                  ubdateStatement.executeUpdate();
              
                 JOptionPane.showConfirmDialog(null,"Update údajov sa podaril","Update is corect !!!",JOptionPane.PLAIN_MESSAGE);
                  resultSet.close();
                  c.close();
            } catch (Exception e) {
               e.printStackTrace();
               JOptionPane.showConfirmDialog(null,e,"Update colaps !!",JOptionPane.ERROR_MESSAGE);

               System.out.println("Update is Not Corect !!!");
            }
      }

      ////    výber údajov z kombo boxu do fields cez prvú hodnotu s setComboboxu listu
      public void selectedValueFromComboBox(){
            
            try {
                  String selectId = comboValues[0];                 
                  String query ="SELECT *  FROM persons WHERE id ="+selectId+";";
                  Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kniznica", "root", "show_pussy8squirrel~hairy");
                   java.sql.Statement statement = c.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);

                  if (resultSet.next()) {
                        field_1.setText(resultSet.getString(2));
                        field_2.setText(resultSet.getString(3));
                        field_3.setText(resultSet.getString(4));
                        field_4.setText(resultSet.getString(6));
                        field_5.setText(resultSet.getString(7));
                        field_6.setText(resultSet.getString(8));
                        field_7.setText(resultSet.getString(9));
                        field_8.setText(resultSet.getString(5));

                        java.sql.Blob blob = resultSet.getBlob("foto");
                        if(blob!=null){
                              userSelectedImage=true;
                              field_9.setText(resultSet.getBytes("foto").toString());
                              byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                              InputStream inputStream = new ByteArrayInputStream(imageBytes);
                              BufferedImage image = ImageIO.read(inputStream);
                              ImageIcon icon = new ImageIcon(image);
                              Image ii = icon.getImage();
                              Image scaleImg = ii.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_FAST);
                              ImageIcon sclIcon = new ImageIcon(scaleImg);
                              imgLabel.setIcon(sclIcon);     
                        }else{  
                              userSelectedImage= false;                               
                        ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
                        Image ii = icon.getImage();
                        Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                        icon = new ImageIcon(iscc);
                        System.out.println("  Default img ");
                        imgLabel.setIcon(icon);
                        }
                        // field_9.setText(resultSet.getString("foto"));    
                       
                  // byte[] b = resultSet.getBytes("foto"); 
                  // String filePath = "src\\new_libraries\\person\\resources\\obrazok.jpg";
                  // System.out.println(" selkected B value byte[] is : "+b);
                  // ByteArrayInputStream bis = new ByteArrayInputStream(b);
                  // BufferedImage bfi = ImageIO.read(bis);
                  // ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  // ImageIO.write(bfi, "png,jpg,jpeg,gif", baos);
                  // FileOutputStream fos = new FileOutputStream(filePath);
                  // fos.write(b);
                  // fos.close();  
                  
                  
                  // if(b!=null ){     
                  //       field_9.setText(resultSet.getBytes("foto").toString()); 
                  //       ImageIcon i = new ImageIcon(filePath);
                  //       Image o = i.getImage();
                  //       Image si= o.getScaledInstance(120, 120, Image.SCALE_FAST);
                  //       ImageIcon sico = new ImageIcon(si);
                  //       imgLabel.setIcon(sico);
                  //       //imgLabel.setIcon(i);
                  //       System.out.println(" Database IMG "+ b);
                  // }
                  // else {
                  //       filePath="src\\new_libraries\\person\\resources\\person.png";
                  //       field_9.setText("");
                  //       imgArea.setText("");                             
                  //       ImageIcon icon = new ImageIcon(filePath);
                  //       Image ii = icon.getImage();
                  //       Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                  //       icon = new ImageIcon(iscc);
                  //       System.out.println("  Default img ");
                  //       imgLabel.setIcon(icon);
                  // }
                  
            }
                  c.close();
                  resultSet.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
      }

      ///////   ked nešlo zobraziť obrazok tak jedna s ciest ako som to skušal nastaviť do icon
      public BufferedImage loadImageFromBytes(byte[] byteData) {
            try {
                  if (byteData != null && byteData.length > 0) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(byteData);
                        return ImageIO.read(bis);
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return null;
      }

      public static void readByteFromTxt() {
            String empty = " ";
            try {
                  File f = new File("src/new_libraries/person/resources/byteUpdate.txt");
                  Scanner scaner = new Scanner(f);
                  while (scaner.hasNext()) {
                        String s = scaner.next();  
                        byte[] b = s.getBytes();                  
                        if(b!=null && b.length>0 ){
                             // imgArea.setText(b.toString()); 
                              field_9.setText(b.toString());                             
                        }  else{
                             // imgArea.setText(empty); 
                              field_9.setText(empty);
                        }
                      System.out.println(b.toString()+" |||| "+field_9.getText()+" |||| "+ b);
                  }scaner.close();
                  
            } catch (FileNotFoundException e) {
                  e.printStackTrace();
            }
        }

            public void showFotoInLabel(){
            try {  
                String  selectedValue = comboValues[0];
                        Connection conn = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                        String sql = "SELECT foto FROM persons WHERE id ="+ selectedValue+";";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);                        
                        java.sql.Blob blob = rs.getBlob("foto");
                  if (rs.next()) {                       
                              byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                              InputStream inputStream = new ByteArrayInputStream(imageBytes);
                              BufferedImage image = ImageIO.read(inputStream);
                              ImageIcon icon = new ImageIcon(image);
                              Image ii = icon.getImage();
                              Image scaleImg = ii.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_FAST);
                              ImageIcon sclIcon = new ImageIcon(scaleImg);
                              imgLabel.setIcon(sclIcon);
                       
                        }                  
                        // else{
                        //       ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
                        //       Image ii = icon.getImage();
                        //       Image iscc= ii.getScaledInstance(120, 120, Image.SCALE_FAST);
                        //       icon = new ImageIcon(iscc);
                        //       System.out.println("  Default img ");
                        //       imgLabel.setIcon(icon);
                        // }                 
                  rs.close();
                  stmt.close();
                  conn.close();
            } catch (Exception exeption) {
                  exeption.printStackTrace();
            } 
      }
      
}
