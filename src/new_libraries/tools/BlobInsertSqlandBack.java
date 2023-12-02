package new_libraries.tools;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;
import new_libraries.person.AddNewPesronPanel;

public class BlobInsertSqlandBack extends JFrame{

      JButton b = new JButton("Select new foto )) ");
      JButton send = new JButton("Send foto to database");
      JComboBox<String> combo = new JComboBox<>();
      ImageIcon icon ;
      JLabel imgL = new JLabel();
      
      BlobInsertSqlandBack(){
            
            icon= new ImageIcon("src\\new_libraries\\person\\resources\\libraries.png");
            Image i = icon.getImage();
            Image scI = i.getScaledInstance(500, 300, Image.SCALE_FAST);
            ImageIcon sI= new ImageIcon(scI);
            imgL.setPreferredSize(new Dimension(700, 300));
            imgL.setIcon(sI);
            b.setSize(100, 50);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(700, 400);
            this.setLayout(new MigLayout());
            this.add(imgL,"wrap,span 3");
            this.add(b,"span 2");
            setComboBoxDataFromFoto();
            this.add(combo,"span 2");
            this.add(send,"wrap");
            this.setVisible(true);

            b.addActionListener(new ActionListener(){

                  @Override
                  public void actionPerformed(ActionEvent e) {
                        String userHome = System.getProperty("user.home");                 
                  File picturesDirectory = new File(userHome, "Pictures");
                  JFileChooser fileChooser = new JFileChooser();
                  fileChooser.setCurrentDirectory(picturesDirectory);
                  int respons = fileChooser.showSaveDialog(null);

                  if(respons == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        ImageIcon fileIcon = new ImageIcon(file.getPath());
                        Image img = fileIcon.getImage();
                        Image scaleImg_1 =img.getScaledInstance(120, 120, Image.SCALE_FAST);
                        icon = new ImageIcon(scaleImg_1);
                        imgL.setIcon(icon);
                        String inPutPath = file.getPath();   
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
                  w.close() ;           
                  } catch (Exception ew) {
                  System.out.println(ew);
            }
                  }

                  }

            });

            send.addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent e) {
                        insetFotoToDtatabaseBLOP();
                        refComboBoxDataFromFoto();
                  }
                  
            });

            combo.addActionListener(new ActionListener() {
                  // String q = (String) combo.getSelectedItem();
                  // String[] qV= q.split(" ");
                  // String id = qV[0];

                  @Override
                  public void actionPerformed(ActionEvent e) {
                        showBlobFromDatabase();  
                       
             }
            });

      }

      public void setComboBoxDataFromFoto(){
            try {
                  Connection con = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = con.createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM BLOP;");

                  while(resultSet.next()){
                        resultSet.getInt(1);
                        int i = resultSet.getInt(1);
                        String inValue = String.valueOf(i);
                       // String text = resultSet.getString("popis");
                        combo.addItem(inValue);
                  }
            } catch (Exception e) {
                 e.printStackTrace();
            }
      }

      public void refComboBoxDataFromFoto(){
            try {
                  Connection con = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = con.createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM BLOP;");
                  DefaultComboBoxModel<String> dfcmb= new DefaultComboBoxModel<>();
                  while(resultSet.next()){
                        resultSet.getInt(1);
                        int i = resultSet.getInt(1);
                        String inValue = String.valueOf(i);
                        dfcmb.addElement(inValue);
                  }
                  combo.setModel(dfcmb);
            } catch (Exception e) {
                 e.printStackTrace();
            }
      }

      public void insetFotoToDtatabaseBLOP(){
            File f= new File(getName());
            File file = new File("src\\new_libraries\\person\\resources\\obrazok.jpg");
            try {
                  f= file;
                  FileInputStream fis = new FileInputStream(f);

                  String query = "INSERT INTO BLOP (foto) VALUE(?);";
                  Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  PreparedStatement pr = c.prepareStatement(query);
                  pr.setBinaryStream(1, fis, (int) f.length());
                  pr.executeUpdate();

                  JOptionPane.showMessageDialog(null, "OBRAZOK SA PRIDAL DO DATABAZY");

            } catch (Exception e) {
                 System.err.println(e);
                 JOptionPane.showMessageDialog(null, e, "UPOZORNENIE", JOptionPane.ERROR_MESSAGE);
            }
      }

      public void showBlobFromDatabase(){
                  try {  
                              Connection conn = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                              String sql = "SELECT * FROM BLOP WHERE id ="+ combo.getSelectedItem()+";";
                              Statement stmt = conn.createStatement();
                              ResultSet rs = stmt.executeQuery(sql);
                              
                        if (rs.next()) {
                              java.sql.Blob blob = rs.getBlob("foto");
                              byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                              InputStream inputStream = new ByteArrayInputStream(imageBytes);
                              BufferedImage image = ImageIO.read(inputStream);
                              ImageIcon icon = new ImageIcon(image);
                              Image ii = icon.getImage();
                              Image scaleImg = ii.getScaledInstance(imgL.getWidth(), imgL.getHeight(), Image.SCALE_FAST);
                              ImageIcon sclIcon = new ImageIcon(scaleImg);
                              imgL.setIcon(sclIcon);                    
                        }                  
                        rs.close();
                        stmt.close();
                        conn.close();
                  } catch (Exception exeption) {
                        exeption.printStackTrace();
                  } 
      }

      public static void main(String[] args) {
            new BlobInsertSqlandBack();   
            
      }
      
}
