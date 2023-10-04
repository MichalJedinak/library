package new_libraries.borrowed;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import new_libraries.SelectedTables;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class BorrowedBooksPanel extends JFrame implements ActionListener{
     Border border = new LineBorder(Color.black,3); 
     public static String url = "jdbc:mysql://localhost:3306/kniznica";
      public static String username = "root";
      public static String password = "show_pussy8squirrel~hairy";
      String [] columns = new String[] {"nazov_kniznice","nazov_miestnosti","nazov_regalu","cislo_police","nazov_knihy","meno_osoby"};
      String [][] data;      
      DefaultTableModel model = new DefaultTableModel(data,columns);              
      JTable jTable = new JTable(model);
      JPanel labelPanel = new JPanel();
      JLabel label = new JLabel();
      JPanel tablePanel = new JPanel();
      SelectedTables selectTable;
     JScrollPane scroll = new JScrollPane(jTable);
      public BorrowedBooksPanel(){
            this.setSize(630, 330);
            //this.setPreferredSize(new Dimension(630, 330));         
            this.setLayout(new FlowLayout());         
            this.setVisible(true);
           // this.setBorder(border);
            labelPanel.setPreferredSize(new Dimension(600, 50));
            labelPanel.setBounds(15, 0, 600, 50);
            labelPanel.setLayout(null);
            labelPanel.setBackground(new Color(35,35,95));        
            label.setPreferredSize(new Dimension(500, 40));
            label.setBounds(50, 4,500 , 40);
            label.setText("Všetky požičané knihy knižnica_ pobočka Martin 1");
            label.setFont(new Font("Consolas",Font.ITALIC,19));
            label.setForeground(Color.white);
            labelPanel.add(label);
            tablePanel.setPreferredSize(new Dimension(600, 250));
            tablePanel.setBounds(15,55, 600, 250);
            tablePanel.setBackground(new Color(100,149,237));            
            this.add(tablePanel);

           showBorrowedBookInMartin_1();
           //showBorrowedBookInMartinSever();          
           //selectTable.getSize();
           selectTable = new SelectedTables(data,columns); 
           //scroll.setPreferredSize(new Dimension(600, 150));
           //selectTable.add(scroll);       
           selectTable.setBounds(0,0, 600, 250);
           selectTable.setPreferredSize(new Dimension(600, 250));
          // selectTable.setBackground(new Color(100,149,237));          
           selectTable.setVisible(true);                  
         //jTable.setPreferredSize(new Dimension(600,200));
           tablePanel.add(selectTable);
           this.add(labelPanel);
           this.add(tablePanel);

      }

      // BorrowedBooksPanel(SelectedTables table){
      //       SelectedTables st = new SelectedTables(data, columns);
      //        st.showBorrowedBookInMartinSever();
      // }
public void showBorrowedBookInMartin_1(){
                  try {
            int count;
            int index = 6;// 6 columns v selekte      
                  Connection connection = DriverManager.getConnection(url, username, password);             
            java.sql.Statement personStatment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);           
            ResultSet resultSet = personStatment.executeQuery("SELECT libraries.library_name AS nazov_kniznice,\r\n" + 
                        "       rooms.id AS nazov_miestnosti,\r\n" + 
                        "       shelf.id AS nazov_regalu,\r\n" + 
                        "       shelf_row.id AS cislo_police,\r\n" + 
                        "      CONCAT( books.id,+\" \",+ books.title )AS nazov_knihy,\r\n" + 
                        "       CONCAT(persons.id,+\" \",+persons.last_name) AS meno_osoby     \r\n" + 
                        "FROM borrowed_books\r\n" + 
                        "JOIN books ON borrowed_books.book_id = books.id\r\n" + 
                        "JOIN persons ON borrowed_books.person_id = persons.id\r\n" + 
                        "JOIN shelf_row ON books.shelf_row = shelf_row.id\r\n" + 
                        "JOIN shelf ON shelf_row.regal = shelf.id\r\n" + 
                        "JOIN rooms ON shelf.rooms = rooms.id                              \r\n" + 
                        "LEFT JOIN libraries ON rooms.library = libraries.library_name\r\n" + 
                        "where libraries.library_name = \"Martin 1\";");

                        if (resultSet.last()) {
                              count = resultSet.getRow();
                              resultSet.beforeFirst();
                              System.out.println(count);             
                              data = new String[count][index];System.out.println(count);
                        } 

                        int i = 0;
                        while (resultSet.next()) {                               
                              data[i][0]=  resultSet.getString(1);
                              data[i][1]=  resultSet.getString(2);
                              data[i][2]=  resultSet.getString(3);
                              data[i][3]=  resultSet.getString(4);
                              data[i][4]=  resultSet.getString(5);
                              data[i][5]=  resultSet.getString(6);
                              i++; System.out.println(i + " "+ resultSet.getString(1));
                        } 
                        resultSet.close();
                       connection.close(); 
                  } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("stala sa nejaká chyba:)) !");
                  }
            }

            public void showBorrowedBookInMartinSever(){
                  try {
            int count;
            int index = 6;// 6 columns v selekte      
                  Connection connection = DriverManager.getConnection(url, username, password);             
            java.sql.Statement personStatment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);           
            ResultSet resultSet = personStatment.executeQuery("SELECT libraries.library_name AS nazov_kniznice,\r\n" + 
                        "       rooms.id AS nazov_miestnosti,\r\n" + 
                        "       shelf.id AS nazov_regalu,\r\n" + 
                        "       shelf_row.id AS cislo_police,\r\n" + 
                        "      CONCAT( books.id,+\" \",+ books.title )AS nazov_knihy,\r\n" + 
                        "       CONCAT(persons.id,+\" \",+persons.last_name) AS meno_osoby     \r\n" + 
                        "FROM borrowed_books\r\n" + 
                        "JOIN books ON borrowed_books.book_id = books.id\r\n" + 
                        "JOIN persons ON borrowed_books.person_id = persons.id\r\n" + 
                        "JOIN shelf_row ON books.shelf_row = shelf_row.id\r\n" + 
                        "JOIN shelf ON shelf_row.regal = shelf.id\r\n" + 
                        "JOIN rooms ON shelf.rooms = rooms.id                              \r\n" + 
                        "LEFT JOIN libraries ON rooms.library = libraries.library_name\r\n" + 
                        "where libraries.library_name = \"Martin Sever\";");

                        if (resultSet.last()) {
                              count = resultSet.getRow();
                              resultSet.beforeFirst();
                              System.out.println(count);             
                              data = new String[count][index];System.out.println(count);
                        } 

                        int i = 0;
                        while (resultSet.next()) {                               
                              data[i][0]=  resultSet.getString(1);
                              data[i][1]=  resultSet.getString(2);
                              data[i][2]=  resultSet.getString(3);
                              data[i][3]=  resultSet.getString(4);
                              data[i][4]=  resultSet.getString(5);
                              data[i][5]=  resultSet.getString(6);
                              i++;
                        }
                  }catch(Exception e){
                        e.printStackTrace();
                  }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                  
            }
      }
