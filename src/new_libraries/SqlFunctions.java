package new_libraries;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import kniznica.objects.Binding;
// import kniznica.objects.Gender;
import new_libraries.person.AddNewPesronPanel;

public class SqlFunctions {

public static String url = "jdbc:mysql://localhost:3306/kniznica";
public static String username = "root";
public static String password = "show_pussy8squirrel~hairy";
public static String bookSelect ="SELECT * FROM books";  
public static String insertBookQuery = "INSERT INTO books (item_number,id,title,autor,genre,shelf_row,amout,binding) VALUES(?,?,?,?,?,?,?,?);";
public static String personSelect = "SELECT * FROM persons";
public static String insertPersonQuery = "INSERT INTO persons (id,name,midle_name,last_name,membership_number,gender,street,city,post_code,foto) VALUES(?,?,?,?,?,?,?,?,?,?);";
public static String borrowedSelect ="SELECT * FROM books";
public static String insertBorrowedQuery = "INSERT INTO borrowed_books(day_of_borrowed,book_id,person_id,membership_cards,amout,return_date) VALUE(NOW(),?,?,?,?,DATE_ADD(NOW(), INTERVAL 2 MONTH) )";
//// 
public static String insertItemQuery = "INSERT INTO items (id,info) SELECT MAX(id) + 1 FROM items " ;
public static String shelfRowIdSelect = "";
static Connection connection ;
public static String nameToSearch;
public static ResultSet resultSet;
public static ArrayList<String> x ;

    ///////////////////// Metod    Insert new Book to database connection
    static void insertNewBookToDatabaseTableBooks(String title, String author, String genre,double amout, Binding binding){
      try {
            // Create Connection
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false); //  zanutie manuálneho ovládania príkazu / tranzakcie nech môžne vykonať viac statmentov súčasne
            System.out.println("Connection id correct !!");// control sysout
            System.out.println(connection.getMetaData());
            //Set Statment 
            Statement statement = connection.createStatement();
            /// Get  ResultSet
            ResultSet resultSet = statement.executeQuery(bookSelect);
            /*
            Set VALUE for Prepered Sattment 
            * item_number int 
            id int PK            
            title varchar(150) 
            autor varchar(50) 
            genre varchar(30) 
            shelf_row char(10) 
            amout decimal(5,2) 
            binding enum('hard','soft')
            */
            
            ////  Prvý SQL príkaz - Get the next available item_id from the items table and insert a new row with the new ID
            
            // pri vkladaní novej knihy musíme zároveň vytvoriť nový záznam v v tabulke item 
            // a toto id vložiť ako item_id  do tabulky books
            ResultSet itemIdResultSet = statement.executeQuery("SELECT max(id) FROM item");
            PreparedStatement itemStatment = connection.prepareStatement("SELECT max(id) + 1 FROM item");  
            int itemId =  itemIdResultSet.next() ? itemIdResultSet.getInt(1)+1  : 1 ;      
            
            //druhý Sql príkaz : Vložte nový záznam do tabuľky items s použitím získaného id
            String insertItemQuery = "INSERT INTO item (id, info) VALUES (?, ?)";
            PreparedStatement itemStatement = connection.prepareStatement(insertItemQuery);
            itemStatement.setInt(1, itemId);
            itemStatement.setString(2, "book");
            itemStatement.executeUpdate();
         
            // tretí SQL príkazz  Get the next available id from the items table
            //statement.executeUpdate(insertItemQuery);
            ResultSet itemResultSet = statement.executeQuery("SELECT max(item.id) FROM item \r\n" + 
             "             LEFT JOIN books  ON books.item_number = item.id  \r\n" + 
            "             LEFT join DVD  ON DVD.item_number = item.id\r\n" + 
            "            LEFT JOIN magazine ON magazine.item_number = item .id\r\n" + 
            "            WHERE DVD.item_number IS NULL and magazine.item_number IS NULL");
            int nextItemId = itemResultSet.next() ? itemResultSet.getInt(1)  : 1;

            ResultSet shelfRowIdResultSet = statement.executeQuery("SELECT shelf_row.id FROM shelf_row \r\n"+
                                                                "   LEFT JOIN DVD ON DVD.shelf_row = shelf_row.id \r\n"+
                                                                 "  JOIN magazine ON magazine.shelf_row = shelf_row.id \r\n"+
                                                                 " WHERE DVD.shelf_row IS NULL AND magazine.shelf_row IS NULL");
            String newShelfRoewId = shelfRowIdResultSet.next() ? shelfRowIdResultSet.getString(1) +1  : "A01";

            // štvrtý  SQL príkaz  Get the next available ID  from the books table
            ResultSet bookIdResultSet = statement.executeQuery("SELECT MAX(id) FROM books");
            int nextBookId = bookIdResultSet.next() ? bookIdResultSet.getInt(1) + 1 : 1;

            PreparedStatement newBookStatment = connection.prepareStatement(insertBookQuery);
            newBookStatment.setInt(1, nextItemId); //  item id z tabulky items                 
            newBookStatment.setInt(2,nextBookId); // nastaviť na nasledujúce id z tabulky books
            newBookStatment.setString(3,title); // nový titul
            newBookStatment.setString(4, author);  // nový autor
            newBookStatment.setString(5, genre);   // novy žaner zhodný s tabulkou genres
            newBookStatment.setString(6,newShelfRoewId); // nastaviť na shelfRow id            
            newBookStatment.setDouble(7,amout);  // nová cena
            newBookStatment.setString(8,binding.name());// nový binding =  enum value

            newBookStatment.executeUpdate();
            System.out.println("adding a new book went well");
            // while (resultSet.next()) {
            //       System.out.println(
            //             resultSet.getInt(1)+ " "
            //           + resultSet.getInt(2) + " "
            //           + resultSet.getInt(3) + " "
            //           + resultSet.getString(4) + " "
            //           + resultSet.getString(5) + " "
            //           + resultSet.getString(6) + " "
            //           + resultSet.getDouble(7) + " "
            //           + resultSet.getString(8) + " "
            //           + resultSet.getString(9) 
            //       );
            //   }     
              //  uzatvoriť všetky  príkazy 
            itemIdResultSet.close();
            resultSet.close();
            statement.close();
            itemStatment.close();
            bookIdResultSet.close();
            newBookStatment.close();
            connection.commit();         
            connection.close();
      } catch (SQLException e) {
            if (connection != null) {
                  try {
                        connection.rollback();
                  } catch (SQLException ex) {
                        ex.printStackTrace();
                  }
            }
            e.printStackTrace(); 
      } 
    }
//////////////////////////////////////   vloženie novej Osoby  do taulky persons /////////////////////////////////////úú
    public static void insertNewPersonToDatabaseTablePersons(String name , String midleName,String lastName,String gender, String adrres,String city,String postCode,byte[] byteImg){
      try {
            File file =null;
            if(AddNewPesronPanel.usSelImg){
                  file=new File("src\\new_libraries\\person\\resources\\img.jpg");
            }
            FileInputStream fis = null;
            if(file != null){
                  try {
                        fis = new FileInputStream(file);
                  } catch (FileNotFoundException e) {                       
                        e.printStackTrace();
                  }
            }
            Connection connection = DriverManager.getConnection(url, username, password);
            // connection.setAutoCommit(false); 
            Statement statement = connection.createStatement();
         //   ResultSet resultSet = statement.executeQuery(personSelect);

            ResultSet memberCard = statement.executeQuery("SELECT id FROM membership_cards WHERE member_name IS NULL LIMIT 1;");
            int countId = memberCard.next() ? memberCard.getInt(1)  : 0;
            /// nastvenie  Id  
            ResultSet perosnsResultSet = statement.executeQuery("SELECT max(id) FROM persons");
            int personId =  perosnsResultSet.next() ? perosnsResultSet.getInt(1)+1  : 1 ;    

            PreparedStatement newPersonStatment= connection.prepareStatement(insertPersonQuery);
            newPersonStatment.setInt(1, personId);
            newPersonStatment.setString(2, name);
            newPersonStatment.setString(3, midleName);
            newPersonStatment.setString(4, lastName);
            newPersonStatment.setInt(5,  countId);
            newPersonStatment.setString(6, gender);  
            newPersonStatment.setString(7, adrres);  
            newPersonStatment.setString(8, city);
            newPersonStatment.setString(9, postCode);

            if(fis !=null){
                  newPersonStatment.setBinaryStream(10,fis,file.length());    
            }else{
                  AddNewPesronPanel.usSelImg=false;
                  newPersonStatment.setNull(10,java.sql.Types.BLOB);
            }
            
            newPersonStatment.executeUpdate();      
          //  System.out.println("newPersonStatment  :"+newPersonStatment);
           // System.out.println(personId+" "+name+" "+lastName+" \n"+countId+" \n"+byteImg+" Contol Data!!! ");
            JOptionPane.showMessageDialog(null, personId+" "+name+" "+lastName+" "+countId+" Contol Data!!! ", "OZNÁMENIE O VLOŽENÍ ÚDAJOV .", JOptionPane.INFORMATION_MESSAGE);
            Statement membershipStatement =connection.createStatement();
            membershipStatement.executeUpdate("UPDATE membership_cards SET member_name = (SELECT CONCAT(name,\" \", last_name) FROM persons WHERE  membership_number = membership_cards.id) , release_date = NOW() , validity_to = DATE_ADD(release_date, INTERVAL 2 YEAR) WHERE id ="+countId+";");
            System.out.println("Adding a new Person  is correct :)"); 
            memberCard.close(); 
            perosnsResultSet.close(); 
            connection.close();     
      } catch (SQLException ex) {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(null, ex, "UPOZORNENIE", JOptionPane.ERROR_MESSAGE);
      }
    }
    
 ///////////////////////////////////////// Nová výpožička z knižnice        //////////////////////   
    static void inserNewBorrowedToDatabaseTableBorrowedBooks(int bookId, int personId, int card,double cena){          
          try {
                Connection connection = DriverManager.getConnection(url, username, password);             
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM borrowed_books";
                ResultSet resultSet = statement.executeQuery(query);

                //ResultSet dayOfBorowedResultSet= statement.executeQuery("UPDATE borrowed_books SET day_of_borrowed = NOW()");

            //     ResultSet idBookResultSet= statement.executeQuery("SELECT books.id FROM books ");
            //     int bookIdFromSelect = idBookResultSet.next() ? idBookResultSet.getInt(1) : 1;

            //     ResultSet idPersonResultSet = statement.executeQuery("SELECT persons.id FROM persons") ;
            //     int personIdFromSelect = idPersonResultSet.next() ? idPersonResultSet.getInt(1) :1;

            //     ResultSet numbrerpersonComleteName= statement.executeQuery("SELECT membership_number FROM persons WHERE persons.id ="+personIdFromSelect);
            //     String memberShipCard = numbrerpersonComleteName.next() ? numbrerpersonComleteName.getString(1) :"DEFAULT_CARD";

            //     ResultSet amoutResultSet = statement.executeQuery("SELECT amout FROM books WHERE books.id ="+bookIdFromSelect);
            //     double amout = amoutResultSet.next() ? amoutResultSet.getDouble(1) : 0.00;


                PreparedStatement newBorrowedStatment = connection.prepareStatement(insertBorrowedQuery);
                //newBorrowedStatment.setDate(1, null, null);  potrbujem aby bol nastavený na NOW()
                newBorrowedStatment.setInt(1, bookId);
                newBorrowedStatment.setInt(2,personId);
                newBorrowedStatment.setInt(3, card);
                newBorrowedStatment.setDouble(4, cena);
                
                newBorrowedStatment.executeUpdate();
                // Tu získaj výsledky a zobraz ich
                  while (resultSet.next()) {                      
                        System.out.println("borrowed boooks");                        
                  }
                  // Uvoľnenie zdrojov
                
                  connection.close();
                  resultSet.close();
                  // idBookResultSet.close();
                  // idPersonResultSet.close();
                  // numbrerpersonComleteName.close();
                  // amoutResultSet.close();
            } catch (SQLException ex) {
                  ex.printStackTrace();
                  JOptionPane.showMessageDialog(null, "Niečo sa nám pojebalo","Chyba v programe!!!", JOptionPane.ERROR_MESSAGE);
            }
      }
      /////////////////// Search name fromm database table if letter equal frist writed letter in field
      public static void showNameFromDatabaseTablePersons() {
            
              nameToSearch = AddNewPesronPanel.nameField.getText();//  nastaviť na string z fieldu
              if (!nameToSearch.isEmpty()) {                    
                    try {
                    Connection connection = DriverManager.getConnection(url, username, password);             
                    Statement statement = connection.createStatement();
                    String query = "SELECT meno FROM zoznam_mien WHERE meno LIKE '" + nameToSearch +"%'";
                   // String query = "SELECT meno FROM zoznam_mien ;";
                     resultSet = statement.executeQuery(query);
                   x = new ArrayList<>();
                    while (resultSet.next()) {                     
                          String meno = resultSet.getString("meno");  
                          x.add(meno);
                          // System.out.println(meno);                                  
                        }
                    resultSet.close();
                    statement.close();
                    connection.close();
                    } catch (SQLException ex) {
                    ex.printStackTrace();
                    }
              }
        }

        public static void refreschDatabase(){
            try {
                  Connection c = DriverManager.getConnection(url, username, password);
                  Statement s = c.createStatement();
                  ResultSet rp = s.executeQuery("SELECT * FROM persons;");
                  while (rp.next()) {
                        rp.getInt(1);
                        rp.getString(2);
                        rp.getString(3);
                        rp.getString(4);
                      //  System.out.println(rp.getString(2));
                  }
                 // System.out.println("knižnica bola načítaná");
                  rp.close();
                  c.close();
            } catch (Exception e) {
                  System.out.println(e);
                  System.out.println("údaje v knižnici sa neaktualizovali !!!");
            }
        }

      public void showBorrowedBookInMartinSever(){
            
      }
      
}

/*
 * V triede SqlFunctions  sú metódy pre jednotlivé pripojenia do SQL databazy 
 * - showNameFromDatabaseTablePersons()   na vyhladanie mena z tabulky persons z počiatočného písmena
 * - na výber údajov do JTable z databazy tabulky persons a tabulky books
 * - na vytvorenie noej výpožičky v tabulke borrowed_books 
 * - na vloženie novej osoby do tabulky persons
 * - na vloženie novej knihy do tabulky books
 * - na výpis všetkých kníh z knižnice ktoré momentálne  sú požičané
 * - na výpis kníh ktoré sa momentálne nachádzajú v danej kižnici 
 * 
 */

