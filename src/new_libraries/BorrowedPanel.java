package new_libraries;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class BorrowedPanel extends JPanel implements ActionListener{
    public static String url = "jdbc:mysql://localhost:3306/kniznica";
    public static String username = "root";
    public static String password = "show_pussy8squirrel~hairy";
// 1), panel pre zápis  požičaných kníh do databázy 
// 2), bude obsahovať panel s lablom Názov - Nové výpožičky  
// 3), panel s lablom osoba  a lablom kniha 
// 4),Panel pre  textové pole na vyhladávanie knihy a osoby ktoré bude prepojené s databázov 
// 4a), po zadaní prvého písmena mena nech sa zobrazý nejaké item menu s menami z databazy
//  tj. začne sa spojenie s databazovou taulko v ktorej po kliknutí na meno nastavíme konketnu osobu????
// pravdepodobne nejaké pole s buttomi v kotrých bude meno ????
//  možno cez tabulku  JTable ??,  zistím
// 5), button na potvrdenie výberu 
// 6), prepojiť zapis s textoveho pola do databazy  zobrať text z tfiledu a porovnať ho s databazou
// 7), pridať listener na button 
// 8),  vytvoriť class s connection do datbazy ktoré naviažem na button click
// 9). doplniť textové polia potrebujeme vlastne 4 údaje na zápis do databazy id osoby, číslo členskej karty id knihy a cenu knihy 
//  dá sa to ošetriť aj v SQL príkaze  ale tito údaje aj tak budem mať vybrané v tabulke
Border border = new LineBorder(Color.black,2);
Border titleBorder = new LineBorder(Color.black,1);
Border labelBorder = new LineBorder(Color.black,1);

JPanel title  = new JPanel(); //2),

JLabel titleLabel = new JLabel("Nová výpožička"); //2),
JPanel lablePanel = new JPanel(); //3),
JLabel person = new JLabel("        PERSON"); //3),
JLabel book   = new JLabel("        BOOK"); //3),

JPanel fieldPanel = new JPanel(); //4),
JTextField personField = new JTextField(); // 4),
JTextField bookField  = new JTextField(); //4),
JTextField cardField = new JTextField(); // 4),
JTextField amoutField  = new JTextField(); //4),

JPanel selectedPanel = new JPanel();
JPanel selectedValuePanel = new JPanel();
JLabel slectBookValue = new JLabel("Vybraná hodnota Id:");
JLabel slectPersonValue = new JLabel("Vybraná hodnota Id:");

Object selectedValuePersonId;
Object slectedValuePersonCard;
Object slectedValueBookId;
Object slectedValueBookAmout;

String [] columns = new String[] {"id","name","membership_number"};
String [][] data;
String [] booksColumn = new String[] { "id","title","amout"};
String [][] bookData ;
DefaultTableModel personsModel = new DefaultTableModel(data,columns); 
DefaultTableModel booksModel = new DefaultTableModel(bookData,booksColumn) ;
JTable bookDataTable = new JTable(booksModel);
JTable personDataTable = new JTable(personsModel);
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    JButton button = new JButton("VYTVOR"); // 5),
    BorrowedPanel(){        
        try {
            int personRowCount; 
            int bookRowCount ;
            int columnIndex = 3;
            Connection connection = DriverManager.getConnection(url, username, password);             
            java.sql.Statement personStatment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);           
            ResultSet resultSet = personStatment.executeQuery("SELECT id, CONCAT(last_name) AS name,membership_number FROM persons");
            
            if (resultSet.last()) {
                    personRowCount = resultSet.getRow();
                    resultSet.beforeFirst();
                    System.out.println(personRowCount);             
                } 
                java.sql.Statement statment = connection.createStatement();           
                ResultSet countRowResultSet= statment.executeQuery("SELECT COUNT(id) FROM persons");
                personRowCount = countRowResultSet.next() ? countRowResultSet.getInt(1): 1;
                countRowResultSet.close();
                List<String[][]> personListData = new ArrayList<>();
                data = new String[personRowCount][columnIndex];System.out.println(personRowCount);
                int i = 0;
                while (resultSet.next()) {  
                    //for selectedTables  set data 
                    data[i][0] = resultSet.getString(1);
                    data[i][1] = resultSet.getString(2);
                    data[i][2] = resultSet.getString(3); 
                    i++;     
                    // for table scrol panel 
                    String[]row = {resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)};
                    personsModel.addRow(row);
                    System.out.println(resultSet.getString(1 )+" "+resultSet.getString(2)+" "+resultSet.getString(3));                
                    System.out.println("počet row : "+i);
                }  
                resultSet.close();
                
                java.sql.Statement bookStatement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet bookResultSet = bookStatement.executeQuery("SELECT id, title,amout FROM books");
                
                List<String[][]> booksColumn = new ArrayList<>();
                
                if (bookResultSet.last()) {
                    bookRowCount = bookResultSet.getRow();
                    bookResultSet.beforeFirst();
                    bookData = new String[bookRowCount][columnIndex];
                    System.out.println(bookRowCount);
                }
                int bookRow = 0;
                while (bookResultSet.next()) {   
                    // for Select table set data        
                bookData[ bookRow ][0] = bookResultSet.getString(1);
                bookData[ bookRow ][1] = bookResultSet.getString(2);
                bookData[ bookRow ][2] = bookResultSet.getString(3);  
                bookRow++;
                System.out.println(bookResultSet.getString(1 )+" "+bookResultSet.getString(2)+" "+bookResultSet.getString(3));                
                
                System.out.println( "number row : "+ bookRow );
            }
            bookResultSet.close();
            for (String [][] columns : personListData) {
            personsModel.addRow(data);
             System.out.println(columns);
          }      
          
           for(String[][] columns :booksColumn ){
                booksModel.addColumn(bookData);
                System.out.println(columns);
           }
           
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane selectedTablePesron = new JScrollPane(personDataTable);
   // SelectedTables selectedTablePesron  = new SelectedTables(data, columns);
    SelectedTables selectedTableBooks   = new SelectedTables(bookData, booksColumn);
            // 1),
            this.setSize(375,345);
            this.setBounds(180, 25, 375, 345);
            this.setBackground(new Color(100,149,237));
            this.setLayout(null);

            title.setBounds(0,0,375, 30);
            title.setLayout(new FlowLayout());
            title.setBackground(new Color(100,149,237));
            //title.setBorder(titleBorder);
            
            titleLabel.setBounds(75, 0, 250, 30);
            titleLabel.setFont(new Font("Roboto",Font.BOLD, 24));
            titleLabel.setForeground(Color.WHITE);
            title.add(titleLabel);

            lablePanel.setBounds(0, 30,375, 30);
            lablePanel.setBackground(new Color(100,149,237));
            
            person.setBounds(5, 0,182 , 30);
            person.setFont(new Font("Roboto",Font.BOLD, 20));
            person.setForeground(Color.BLACK);
            book.setBounds(187, 0, 182, 30);
            book.setFont(new Font("Roboto",Font.BOLD, 20));
            book.setForeground(Color.BLACK);
            lablePanel.add(person);
            lablePanel.add(book);
            book.setBorder(labelBorder);
            person.setBorder(labelBorder);

            fieldPanel.setBounds(0, 65, 375, 45);
            fieldPanel.setBackground(new Color(100,149,237));
            fieldPanel.setLayout(null);
            personField.setBounds(5, 0, 91, 43);
            cardField.setBounds(96, 0, 91, 43);
            bookField.setBounds(187,0, 91, 43);
            amoutField.setBounds(278, 0, 91, 43);           
            ////  
            fieldPanel.add(personField);
            fieldPanel.add(cardField);
            fieldPanel.add(bookField);
            fieldPanel.add(amoutField);
            
            selectedPanel.setBounds(5,90, 365, 150);
            selectedPanel.setBackground(new Color(100,149,237));
            selectedPanel.setLayout(new BorderLayout()); 

            selectedValuePanel.setBounds(0, 1, 365, 45); 
            slectPersonValue.setBounds(0, 0, 180, 45);
            selectedValuePanel.setLayout(new GridLayout(0,2,0,20));
            selectedValuePanel.setBackground(Color.green);
            slectBookValue.setFont(new Font("Roboto",Font.PLAIN, 15));
            slectPersonValue.setForeground(Color.WHITE);
            slectBookValue.setForeground(Color.WHITE);
            slectBookValue.setBounds(181, 0, 180, 45);
            slectPersonValue.setFont(new Font("Roboto",Font.PLAIN, 15));
            selectedValuePanel.add(slectPersonValue);
            selectedValuePanel.add(slectBookValue);
            
            selectedPanel.add(selectedValuePanel,BorderLayout.NORTH) ;             
            
            selectedTablePesron.setBounds(5,51,370,100);
            selectedTablePesron.setVisible(true);
           // selectedTablePesron.add(personDataTable);
            selectedTableBooks.setBounds(5,51,370,100);
            selectedTableBooks.setVisible(true);
            //selectedTableBooks.add(bookDataTable);
          
            ////////////////////////////// Person table valueChenged  and  Book table  valuechenged
            // selectedTablePesron.getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //     @Override
            //     public void valueChanged(ListSelectionEvent e) {   
            //         int selectedRow = selectedTablePesron.getJTable().getSelectedRow();
            //         int selectedColumn = selectedTablePesron.getJTable().getSelectedColumn();
            //         selectedValuePersonId = selectedTablePesron.getJTable().getValueAt(selectedRow, selectedColumn); 
            //         slectedValuePersonCard =   selectedTablePesron.getJTable().getValueAt(selectedRow, selectedColumn); 
            //         // personField.setText(selectedValuePersonId.toString());  
            //         // cardField.setText(selectedValuePersonId.toString());
            //     }
            // } );           
            selectedTableBooks.getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) { 

                    int selectedRow = selectedTableBooks.getJTable().getSelectedRow();
                    int selectedColumn = selectedTableBooks.getJTable().getSelectedColumn();
                   slectedValueBookId = selectedTableBooks.getJTable().getValueAt(selectedRow, selectedColumn); 
                   slectedValueBookAmout = selectedTableBooks.getJTable().getValueAt(selectedRow, selectedColumn);
                    bookField.setText(selectedValuePersonId.toString()); 
                    amoutField.setText(slectedValueBookAmout.toString());
                }
            } );   
               personDataTable.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {    
                  int row = personDataTable.getSelectedRow();
                    DefaultTableModel d = (DefaultTableModel) personDataTable.getModel();
                    personField.setText(d.getValueAt(row, 0).toString());
                    cardField.setText(d.getValueAt(row, 2).toString());
                    System.out.println(personField+" "+ cardField);              
                }
            });
           //  pre selected tables  nie scrol pane
            //   personField.addFocusListener(new FocusAdapter() {
            //     @Override
            //     public void focusLost(FocusEvent e) {                
            //         String selectedValueId = 
            //         selectedTablePesron.getJTable().getValueAt( selectedTablePesron.getJTable().getSelectedRow(),
            //          selectedTablePesron.getJTable().getSelectedColumn() ).toString();
            //         personField.setText(selectedValueId);
            //     }
            // });   
            // cardField.addFocusListener(new FocusAdapter() {
            //     @Override
            //     public void focusLost(FocusEvent e) {
            //         String selectedValueC = 
            //         selectedTablePesron.getJTable().getValueAt( selectedTablePesron.getJTable().getSelectedRow(),
            //          selectedTablePesron.getJTable().getSelectedColumn() ).toString();
            //        cardField.setText(selectedValueC);
            //     }
            // });   
            bookField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String selectedValueBiD = 
                    selectedTableBooks.getJTable().getValueAt(selectedTableBooks.getJTable().getSelectedRow(), 
                    selectedTableBooks.getJTable().getSelectedColumn()).toString();
                    bookField.setText(selectedValueBiD);
                }
            });   
            amoutField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String selectedValueA =
                     selectedTableBooks.getJTable().getValueAt (selectedTableBooks.getJTable().getSelectedRow(),
                      selectedTableBooks.getJTable().getSelectedColumn() ).toString();
                    amoutField.setText(selectedValueA);
                }
            });   
            ///////////////////////////////////////      book / person field  listeners  replace table
            personField.addMouseListener(new MouseAdapter(){                
                @Override
                public void mouseClicked(MouseEvent e) {   
                    selectedPanel.add(selectedTablePesron, BorderLayout.CENTER); 
                    selectedPanel.remove(selectedTableBooks)   ;   
                    System.out.println("click work");           
                }
            });       
            bookField.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {    
                    selectedPanel.add(selectedTableBooks,BorderLayout.CENTER) ; 
                    selectedPanel.remove(selectedTablePesron);
                     System.out.println("click work");                 
                }
            });
            button.setBounds(10, 285, 355, 50);
            button.setBackground(new Color(0,255,127));
            button.setFont(new Font("Roboto",Font.BOLD, 20));
            button.setForeground(Color.WHITE);
            button.addActionListener(this);
            
            //this.setBorder( border);
            this.add(title);
            this.add(fieldPanel); 
            this.add(lablePanel);
            this.add(selectedPanel);  
            this.add(button);     
            this.setVisible(true);
            this.repaint();
           // loadTableDataFromDatabase();
           for(int row = 0;row<100;row++)
           personDataTable.setRowHeight(row,26);
           personDataTable.setEnabled(true);
      }
      @Override
      public void actionPerformed(ActionEvent e) {         
            if(e.getSource()== button){  
                ///   nastviť hodnoty  do funkcie z book a person cez fields 
                ArrayList<JTextField>fieldList= new ArrayList<>() ; 
                fieldList.add(bookField);fieldList.add(personField);fieldList.add(cardField);fieldList.add(amoutField);
                int addBookId = Integer.parseInt(bookField.getText());  
                double addAmout = Double.parseDouble(amoutField.getText());
             
                int addPersonId = Integer.parseInt(personField.getText());
                int addCard = Integer.parseInt(cardField.getText());
               
                System.out.println("Hodnoty na zápis  do databázy  : ID osoby - " 
                + personField.getText()+" Číslo Karty - "+cardField.getText()+
                "  ID Knihy - "+bookField.getText()+"  Cena Knihy - "+amoutField.getText()); // kontrolný sysout  
                SqlFunctions.inserNewBorrowedToDatabaseTableBorrowedBooks(addBookId,addPersonId,addCard,addAmout);
                System.out.println(addBookId+" "+addPersonId+" "+addCard+" "+addAmout); 
                JOptionPane.showMessageDialog(null,"Hurá ty pako podarilo sa ti to pridať", "TOOL_TIP_TEXT_KEY",JOptionPane.INFORMATION_MESSAGE);  
                   for(JTextField fields : fieldList){
                    fields.setText(" ");
                   }            
            }          

         
      }
      ///  skuska  ako dostať súčasne po kliknutí na riadok udaje do oboch fields
       public void perDatabEvent(java.awt.event.MouseEvent e){
                int row = personDataTable.getSelectedRow();
                    DefaultTableModel d = (DefaultTableModel) personDataTable.getModel();
                    personField.setText(d.getValueAt(row, 0).toString());
                    cardField.setText(d.getValueAt(row, 2).toString());
                    System.out.println(personField+" "+ cardField);
            }
        
      
}

/*
 * Trieda BOrrowedPanel  má výtvoriť GUI JPanel  ktorý sa následne použije v triede MyFrame 
 * ako panel ktorý sa pridá do JFrame po výbere z Menu cez ItemMenu.
 * Trieda obsahuje 
 * - JPanel pre popis (nadpis, vyberove možnosti).
 * - Jpanel s dvomi JTextFiel  (osoba,kniha).
 * - JPanel pre JScollPanel  z  Triedy SelectedTable . 
 * _JButton na ktorý je naviazaný ActionListener,
 *  pre zavolanie funkcie s Triedy SqlFunctions showNameFromDatabaseTablePersons();
 * INSERT INTO borrowed_books (book_id,person_id,amout,membership_cards) VALUES(?,?,?);

 */