package new_libraries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class NovaVypozicka extends JFrame implements ActionListener{

      JLabel title = new JLabel("Výpožičky kníh z knižnice.");
      JPanel left = new JPanel();
      JLabel leftInfo = new JLabel("Vyber čitateľa s zoznamu !");
      JLabel tableTitle = new JLabel("Zoznam vypožičaných kníh");
      static JComboBox<String> comboBox;
      JTable table = new JTable(); // tabulka pre zoznam kníh z knižnice
      JScrollPane pane = new JScrollPane(table);
      JPanel right = new JPanel();
      JPanel addPanel =new  JPanel();
      JLabel label_1 = new JLabel("Kniha");// pre názov vybratej knihy
      JTextField field_1 = new JTextField(90);// pre zapísanie vybratej knihy
      JTable table_1 = new JTable();
      JScrollPane pane_1 = new JScrollPane();
      JButton addButton = new JButton("add");
      
      JPanel dropPanel = new JPanel();
      JList<String> dropList = new JList<>();
      JScrollPane dropPane = new JScrollPane();
      JButton dropButton = new JButton("drop");
      
      ImageIcon icon = new ImageIcon("src\\new_libraries\\person\\resources\\person.png");
      Image img = icon.getImage();
      JButton vratenie = new JButton("Vrátiť",icon);
      JButton vypozicaj = new JButton("vypžičať");
      NovaVypozicka(){
            Image i = img.getScaledInstance(45, 20, Image.SCALE_SMOOTH);
            ImageIcon scI= new ImageIcon(i);
            vratenie.setIcon(scI);

            comboBox = new JComboBox<String>();
            selectPersonFromDatabase();
            comboBox.setPreferredSize(new Dimension(300,30));
            pane.setPreferredSize(new Dimension(300, 300));
            pane.setLayout(new ScrollPaneLayout());
            pane.setViewportView(table);
            left.setLayout(new MigLayout());
           // left.setBackground(Color.red);
           comboBox.addActionListener(this);
           left.add(leftInfo,"wrap,center");
           left.add(comboBox,"wrap, gap bottom 10");
           left.add(tableTitle,"wrap");
           left.add(pane,"wrap");


           vypozicaj.addActionListener(this);
            vratenie.addActionListener(this);
            pane_1.setPreferredSize(new Dimension(300, 150));
            pane_1.setLayout(new ScrollPaneLayout());
            addPanel.setPreferredSize(new Dimension(300, 200));
            addPanel.setLayout(new MigLayout());
            addPanel.add(label_1);
            addPanel.add(field_1,"wrap");
            addPanel.add(pane_1,"span 3,wrap");
            addPanel.add(addButton,"wrap");

            dropPane.setPreferredSize(new Dimension(300, 150));
            dropPane.add(dropList);dropPane.setViewportView(dropList);
            dropList.setVisibleRowCount(9);
            dropPanel.setPreferredSize(new Dimension(300, 200));
            dropPanel.setLayout(new MigLayout());
            dropPanel.add(dropPane,"wrap");
            dropPanel.add(dropButton," wrap");

            right.setPreferredSize(new Dimension(300, 300));
           // right.setBackground(Color.black);
            right.setLayout(new MigLayout());

            title.setFont(new Font("Allura", Font.ITALIC, 40));
            title.setForeground(new Color(106,90,205));

            this.setSize(650, 450);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // this.setLayout(new BorderLayout());
            // this.add(title,BorderLayout.NORTH);
            // this.add(left,BorderLayout.WEST);
            // this.add(right,BorderLayout.EAST);
            this.setLayout(new MigLayout());
            this.add(vratenie,"align left");         
            this.add(vypozicaj,"left 10,wrap");
            this.add(title,"gap left 100 ,wrap,span 5");
            this.add(left);
            this.add(right,"wrap");
            this.setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource()==vypozicaj){
                  right.remove(dropPanel);
                  right.add(addPanel,"wrap");
                  this.repaint();this.revalidate();
            }
            if(e.getSource()==vratenie){
                  right.remove(addPanel);
                  right.add(dropPanel,"wrap");
                  this.repaint();this.revalidate();
            }
            if(e.getSource()==comboBox){
                  showAllBorrowedBooksOfaPerson();
                  this.repaint();this.revalidate();
            }
      }

      public static void selectPersonFromDatabase(){
            String query = " SELECT * FROM persons;";
            Connection connection = null;

            try {
                  connection=DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);
                  DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                  while (resultSet.next()) {
                        int i        = resultSet.getInt(1);
                        String id    = String.valueOf(i);
                        String name  = resultSet.getString(2);
                        String midle = resultSet.getString(3);
                        String last  = resultSet.getString(4);
                        int j        = resultSet.getInt(5);
                        String card  = String.valueOf(j);
                        model.addElement(id+" "+name+" "+midle+" "+last+" "+card);
                  }
                  comboBox.setModel(model);
                  connection.close();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "SQL EROR",JOptionPane.INFORMATION_MESSAGE);
            }
      }

      public void showAllBorrowedBooksOfaPerson(){
            String selectedValues =(String) comboBox.getSelectedItem();
            String[] selectedValue =  selectedValues.split(" ");
            String firstValue = selectedValue[0];
            int id = Integer.parseInt(firstValue);
            String query = "select books.id,title,autor from books \n"+
                         "left join borrowed_books on books.id = borrowed_books.book_id \n"+ 
                         "left join persons on persons.id = borrowed_books.person_id\n"+ 
                         "where persons.id ="+id+";";
            Connection connection = null;
            try {
                  connection= DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);
                  DefaultTableModel model = new DefaultTableModel();
                  java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
                  int columns = rsmd.getColumnCount();// zistíme počet stĺpcou
                  String[] columnsName = new String[columns];// vytvoríme pole stringov pre názvy columns 
                  for(int i = 0; i<columns;i++)
                  columnsName[i]=rsmd.getColumnName(i+1);// použijeme get column Name s resulset meta data
                  model.setColumnIdentifiers(columnsName);//  default modelu nastavíme identifikované stĺpce
                  DefaultTableModel emptyModel = new DefaultTableModel();
                  emptyModel.setColumnIdentifiers(columnsName);
                  if(!resultSet.next()){                    
                        String[] emptyData={null};
                        emptyModel.addRow(emptyData);
                        table.setModel(emptyModel);
                       JOptionPane.showMessageDialog(null, "Osoba nemá žiadne knihy požičané.", "Info", JOptionPane.INFORMATION_MESSAGE);
                  }else{
                        do{
                              int i =    resultSet.getInt(1);
                              String selectId = String.valueOf(i);
                              String title = resultSet.getString(2); 
                              String autor = resultSet.getString(3)   ;
                              String[]  data ={selectId,title,autor};                      
                              model.addRow(data);
                              table.setModel(model);
                        } while (resultSet.next()) ;
                  }
                 
                  connection.close();     
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "SQL EROR",JOptionPane.INFORMATION_MESSAGE);

            }
            
      }
      public void booksFromDatabase(){
            String query = "SELECT * FROM books";
            Connection connection = null;
            try {
                  connection= DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);
                  DefaultTableModel emptyModel = new DefaultTableModel();
                  DefaultTableModel model = new DefaultTableModel();
                  java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
                  int columns = rsmd.getColumnCount();// zistíme počet stĺpcou
                  String[] columnsName = new String[columns];// vytvoríme pole stringov pre názvy columns 
                  for(int i = 0; i<columns;i++)
                  columnsName[i]=rsmd.getColumnName(i+1);// použijeme get column Name s resulset meta data
                  model.setColumnIdentifiers(columnsName);//  default modelu nastavíme identifikované stĺpce
                  emptyModel.setColumnIdentifiers(columnsName);
                  while (resultSet.next()) {
                    int i =    resultSet.getInt(1);
                    String selectId = String.valueOf(i);
                    String title = resultSet.getString(2); 
                    String autor = resultSet.getString(3);
                    String[]  data ={selectId,title,autor};                      
             
                  model.addRow(data);
                  table.setModel(model);
                  
                  }
                  connection.close();     
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "SQL EROR",JOptionPane.INFORMATION_MESSAGE);

            }
      }

      public static void main(String[] args) {
            new NovaVypozicka();
      }
}
