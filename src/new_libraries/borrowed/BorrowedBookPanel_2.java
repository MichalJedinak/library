package new_libraries.borrowed;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import new_libraries.SqlFunctions;
public class BorrowedBookPanel_2 extends JFrame implements ActionListener{
JFrame panel = new JFrame();
//JPanel panel = new JPanel();
JPanel labelPanel = new JPanel();
JLabel label = new JLabel();
JTable table = new JTable();
JPanel tbPanel = new JPanel();
JScrollPane pane = new JScrollPane(table);                     
      public BorrowedBookPanel_2(){
            try {
                  DefaultTableModel model =(DefaultTableModel) table.getModel();
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);             
                  java.sql.Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                  String query = "SELECT libraries.library_name AS nazov_kniznice,\r\n" + 
          "       rooms.id AS roomId,\r\n" + 
          "       shelf.id AS shelfId,\r\n" + 
          "       shelf_row.id AS shelfRoeId,\r\n" + 
                  "      CONCAT( books.id,+\" \",+ books.title )AS bookName,\r\n" + 
                  "       CONCAT(persons.id,+\" \",+persons.last_name) AS personName     \r\n" + 
                  "FROM borrowed_books\r\n" + 
                  "JOIN books ON borrowed_books.book_id = books.id\r\n" + 
                  "JOIN persons ON borrowed_books.person_id = persons.id\r\n" + 
                  "JOIN shelf_row ON books.shelf_row = shelf_row.id\r\n" + 
                  "JOIN shelf ON shelf_row.regal = shelf.id\r\n" + 
                  "JOIN rooms ON shelf.rooms = rooms.id                              \r\n" + 
                  "LEFT JOIN libraries ON rooms.library = libraries.library_name\r\n" + 
                  "where libraries.library_name = \"Martin Sever\";";
                  ResultSet rs = st.executeQuery(query);
                  java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                  
                  int cols = rsmd.getColumnCount();
                  System.out.println(cols);
                  String[] colName = new String[cols];
                  for(int i = 0; i<cols;i++)
                  colName[i]=rsmd.getColumnName(i+1);
                  model.setColumnIdentifiers(colName);
                  
                  String nazov_kniznice,roomId,shelfId,shelfRoeId,bookName,personName;
                  while (rs.next()) {
                        nazov_kniznice = rs.getString(1);
                        roomId=rs.getString(2);
                        shelfId=rs.getString(3);
                        shelfRoeId=rs.getString(4);
                        bookName=rs.getString(5);
                        personName=rs.getString(6);
                        String[]row ={nazov_kniznice,roomId,shelfId,shelfRoeId,bookName,personName};
                        model.addRow(row);
                        System.out.println(nazov_kniznice+" "+roomId+" "+shelfId+" "+shelfRoeId+" "+bookName+" "+personName);
                  }
                  connection.close();
                  st.close();
                  
            } catch (Exception ea) {
                  ea.printStackTrace();                       
            }
            panel.setSize(630,257);
            //panel.setPreferredSize(new Dimension(630, 400));
          //  panel.setBounds(75, 80, 630, 400);
            panel.setLayout(new FlowLayout());
            labelPanel.setPreferredSize(new Dimension(600, 50));
            labelPanel.setBounds(0, 0, 600, 50);
            labelPanel.setLayout(null);
            labelPanel.setBackground(new Color(35,35,95));        
            label.setPreferredSize(new Dimension(500, 40));
            label.setBounds(50, 4,500 , 40);
            label.setText("Všetky požičané knihy pobočka Martin Sever");
            label.setFont(new Font("Consolas",Font.ITALIC,19));
            label.setForeground(Color.white);
            labelPanel.add(label);
            panel.add(labelPanel);
           // pane.setPreferredSize(new Dimension(600, 250));
            panel.setVisible(true);
            tbPanel.setBounds(0, 150, 600, 150);
            pane.setPreferredSize(new Dimension(600, 150));
            tbPanel.add(pane);
            panel.add(tbPanel);
            panel.setVisible(true);                
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
      }
      public static void main(String[] args) {
            new BorrowedBookPanel_2();
      }
}
