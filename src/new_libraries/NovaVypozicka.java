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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class NovaVypozicka extends JFrame implements ActionListener{

      JLabel title = new JLabel("Výpožičky kníh z knižnice.");
      JPanel left = new JPanel();
      JLabel leftInfo = new JLabel("Vyber čitateľa s zoznamu !");
      JLabel tableTitle = new JLabel("Zoznam vypožičaných kníh");
      static JComboBox<String> comboBox;
      JTable table = new JTable(); // tabulka pre zoznam požičaných kníh podla osoby z knižnice
      JScrollPane pane = new JScrollPane(table);
      JPanel right = new JPanel();
      JPanel addPanel =new  JPanel();
      JLabel label_1 = new JLabel("Kniha");// pre názov vybratej knihy
      JTextField field_1 = new JTextField(90);// pre zapísanie vybratej knihy
      JTable table_1 = new JTable();//  tabulka pre knihy čo sa dajú požičať
      JScrollPane pane_1 = new JScrollPane(table_1);
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
            pane_1.setViewportView(table_1);
            pane_1.setLayout(new ScrollPaneLayout());
            addPanel.setPreferredSize(new Dimension(300, 200));
            addPanel.setLayout(new MigLayout());
            addPanel.add(label_1);
            addPanel.add(field_1,"wrap");
            field_1.addActionListener(this);
            addPanel.add(pane_1,"span 3,wrap");
            addPanel.add(addButton,"wrap");
            addButton.addActionListener(this);

            dropPane.setPreferredSize(new Dimension(300, 150));
            dropPane.setLayout(new ScrollPaneLayout());
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
                  booksFromDatabase();
                  right.remove(dropPanel);
                  right.add(addPanel,"wrap");
                  DefaultListModel<String> empryModel= new DefaultListModel<>();
                  empryModel.addElement("");
                  dropList.setModel(empryModel);       
                  this.repaint();this.revalidate();
            }
            if(e.getSource()==vratenie){
                  right.remove(addPanel);
                  right.add(dropPanel,"wrap");
                  field_1.setText("");
                  this.repaint();this.revalidate();
            }
            if(e.getSource()==comboBox){
                  showAllBorrowedBooksOfaPerson();
                  this.repaint();this.revalidate();
            }
            if(e.getSource()==addButton){      
            pridajNovuVypozicanuKnihu();
            selectPersonFromDatabase();     
            showAllBorrowedBooksOfaPerson();
            booksFromDatabase();  
            field_1.setText("");  
            this.repaint();  this.revalidate();   
            }

            table.addMouseListener(new MouseAdapter() {
                                    
                  @Override
                  public void mouseClicked(MouseEvent e) {
                        int listItems = table.getSelectedRow();
                        for(int i = 0;i<table.getRowCount();i++){
                              DefaultListModel<String> model = new DefaultListModel<String>();
                              model.addElement(table.getModel().getValueAt(listItems,1).toString()+"\n");
                              dropList.setModel(model);                              
                        }

                        // int listItems = table.getSelectedRow();
                        // DefaultListModel<String> model = (DefaultListModel<String>) dropList.getModel(); // Získajte existujúci model JList
                        // model.addElement(table.getModel().getValueAt(listItems, 1).toString() + "\n");

                        // int listItems = table.getSelectedRow();
                        // DefaultListModel<String> model = new DefaultListModel<>(); // Vytvorte nový model
                        // model.addElement(table.getModel().getValueAt(listItems, 1).toString() + "\n");

                        // // Pridajte nový model k existujúcemu JList (ak neexistuje, tak ho vytvorte)
                        // if (dropList.getModel() == null) {
                        // dropList.setModel(model);
                        // } else {
                        // for (int i = 0; i < model.getSize(); i++) {
                        //       ((DefaultListModel<String>) dropList.getModel()).addElement(model.get(i));
                        // }
                        // }

                        // int listItems = table.getSelectedRow();
                        // String selectedValue = (table.getModel().getValueAt(listItems, 1).toString()+"\n" );
                        // ListModel<String> currentModel = dropList.getModel();
                        // DefaultListModel<String> newModel = new DefaultListModel<>();
                        // newModel.addElement(table.getModel().getValueAt(listItems, 1).toString()+"\n" );
                        // if (!newModel.contains(selectedValue)) {
                        //       newModel.addElement(selectedValue);
                        //       dropList.setModel(newModel);
                        //   }
                        // Pridajte položky z existujúceho modelu do nového modelu (ak existuje)
                        // for (int i = 0; i < currentModel.getSize(); i++) {
                        // newModel.addElement(currentModel.getElementAt(i));
                        // }
                        //dropList.setModel(newModel); // Nastavte nový model do JList



                        this.repaint();                                  
                         System.out.println(listItems);
                     }
   
                     private void repaint() {
               }

                     @Override
                     public void mousePressed(MouseEvent e) {
                     }
   
                     @Override
                     public void mouseReleased(MouseEvent e) {
                     }
   
                     @Override
                     public void mouseEntered(MouseEvent e) {
                     }
   
                     @Override
                     public void mouseExited(MouseEvent e) {
                     }
               });

               table_1.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                        int tableRow = table_1.getSelectedRow();                          
                        field_1.setText(table_1.getModel().getValueAt(tableRow,1).toString());
                     }
               });
               field_1.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {                                                
                        field_1.setText("");
                     }
               });
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
            String query = "select books.id,title,autor from books   left join borrowed_books on books.id = borrowed_books.book_id WHERE  day_of_borrowed is null  or day_of_borrowed is not null and borrowed = false && returned_when is not null ;;";
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
                  while (resultSet.next()) {
                    int i =    resultSet.getInt(1);
                    String selectId = String.valueOf(i);
                    String title = resultSet.getString(2); 
                    String autor = resultSet.getString(3);
                    String[]  data ={selectId,title,autor};                      
             
                  model.addRow(data);
                  table_1.setModel(model);
                  
                  }
                  connection.close();     
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "SQL EROR",JOptionPane.INFORMATION_MESSAGE);

            }
      }
      public void pridajNovuVypozicanuKnihu(){
            String query="INSERT INTO borrowed_books(day_of_borrowed,book_id,borrowed,person_id,membership_cards,amout,return_date) VALUE(NOW(),?,true,?,?,?,DATE_ADD(NOW(), INTERVAL 2 MONTH) )";
            int row = table_1.getSelectedRow();
            String column = table_1.getModel().getValueAt(row,0).toString();
            int book_id = Integer.parseInt(column);
            String comboBoxItems = comboBox.getSelectedItem().toString();
            String[ ]splitItems = comboBoxItems.split(" "); 
            String item_0 = splitItems[0];
            String item_4 = splitItems[4];
            int person_id = Integer.parseInt(item_0);
            int membership_card = Integer.parseInt(item_4);
            System.out.println(membership_card+" "+person_id+" "+book_id);

            String cenaQuery = "SELECT amout FROM books WHERE books.id ="+book_id+";";
            try {
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);             
                  Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(cenaQuery);
                  if (resultSet.next()) {
                        resultSet.getDouble("amout");
                        System.out.println();
                  }
                  Double cena = resultSet.getDouble("amout");
                  PreparedStatement preparedStatement = connection.prepareStatement(query);
                  preparedStatement.setInt(1, book_id);
                  preparedStatement.setInt(2,person_id);
                  preparedStatement.setInt(3, membership_card);
                  preparedStatement.setDouble(4, cena);
                  preparedStatement.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Kniha s ID : "+book_id+" Bola pridaná osobe s ID : "+person_id, "RESULT SQL INFO",JOptionPane.INFORMATION_MESSAGE);
                 resultSet.close();
                 statement.close();
                 connection.close();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "SQL EROR",JOptionPane.INFORMATION_MESSAGE);

            }
      }

      public static void main(String[] args) {
            new NovaVypozicka();
      }
}
