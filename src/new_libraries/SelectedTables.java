package new_libraries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
public class SelectedTables extends JPanel {

private JTable table ;
String[][] data;
public SelectedTables(Object[][] data,Object[] columns) {
    // Create table and set size 
    DefaultTableModel model = new DefaultTableModel(data,columns);
    table = new JTable(model);
    JScrollPane scroll = new JScrollPane();
    scroll.setViewportView(table);
    scroll.setSize(350, 100);
    add(scroll);
    //  Add Action Listener for enter value from table
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    int selectedColumn = table.getSelectedColumn();
                    if (selectedRow >= 0 && selectedColumn >= 0) {
                        Object selectedValue = table.getValueAt(selectedRow, selectedColumn);                        
                        System.out.println(selectedValue);
                    }
                }
            }
        });
       
    }
public JTable getJTable(){
    return table;
}
// funkcia na prepnutie tabuliek pre osobu a knihu v BorrowedPanel
public void replaceTable(JTable newTable) {
    // Remove the current table from the panel
    remove(newTable);
    // Add the new table to the panel
    table = newTable;
    JScrollPane scroll = new JScrollPane(newTable);
    add(scroll);
    // Revalidate and repaint the panel to reflect the changes
    revalidate();
    repaint();
}

////////////////   na skúšku  ako by som prepínal  funkcie  medzi knižnicami????????????????????????????????????
public void showBorrowedBookInMartin_1(){
                  try {
            int count;
            int index = 6;// 6 columns v selekte      
                  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kniznica", "root", "show_pussy8squirrel~hairy");             
            java.sql.Statement personStatment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);           
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
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kniznica", "root", "show_pussy8squirrel~hairy");             
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
                            i++; System.out.println(i + " "+ resultSet.getString(1));
                      } 
                      resultSet.close();
                     connection.close(); 
                } catch (Exception e) {
                      e.printStackTrace();
                      System.out.println("stala sa nejaká chyba:)) !");
                }
          }

}
/*
 * Trieda SelectTables  obsahuje  JTable vytvorenú cez DefaultTableModel , 
 * je určná pre hodnoty  z Databázovej tabulky kniznica.persons z seletu
 * SELECT name,midle_name,last_name AS Meno 
 *        membership_number AS Preukaz 
 * FROM kniznica.persons;
 * a 
 * pre SELECT id, title FROM kniznica.books;
 * Obsahuje funkcie  na zavolanie triedy v inej triede  getJTable()
 * a funkciu repaceTable() na možnosť prepínania medzi tabulkami s rôznymi hodnotami
 * táto funkcia by mala byť použitá v tirede BorrowedPanel 
 * aby vymenila tabulky pri kliknutí na konkrétny JTextField (person or Bokk)
 * 
 * Na tabulku taktiež naviazyný ActionListeners  pre možnosť  vyberania hodnôt z tabulky 
 * a možnou ďalšou prácou s u
 * s údajmi s nej 
 */
