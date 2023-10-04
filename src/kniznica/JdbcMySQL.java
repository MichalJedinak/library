package kniznica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import kniznica.objects.Person;
import kniznica.objects.Book;
/* Trieda na spojenie sa sql databazou. pre persons books and other
 *  metody selectPerson()  - SQL SELECT * FROM persons;
 *         createPerson()  - INSERT INTO persons VALUES(?,?,?,?);
 *         deletePerson()  - DELETE FROM persons WHERE person_id = ? ;
 */

public class JdbcMySQL {

      String url = "jdbc:mysql://localhost:3306/kniznica";
      String username = "root";
      String password = "show_pussy8squirrel~hairy";
      String select_persons =
             "SELECT * FROM persons";  
      String select_books =
            "SELECT * FROM books;";
      String INSERT_PERSON_SQL =
             "INSERT INTO persons(person_id,person_name,person_lastName,identity_card) VALUES(?,?,?,?);"; 
      String INSERT_BOOK_SQL =
      "INSERT INTO books(book_title,book_autor) VALUES(?,?);"; 
      String DELETEPERSON_SQL = "DELETE FROM persons WHERE person_id = ?";
      final Connection conn = null;

      public Connection selectPerson() throws ClassNotFoundException{
           
            try {
             Connection conn = DriverManager.getConnection(url, username, password);
                  java.sql.Statement st= conn.createStatement();
                  java.sql.ResultSet set = st.executeQuery(select_persons);                
               
                
                  while (set.next()) {
                        System.out.println(set.getInt(1)+" "+ 
                        set.getString(2)+" "
                        +set.getString(3)+" "+set.getInt(4));                      
                  }
                  
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return conn;
      }

      // metoda na pridanie osoby do tabulky
      public   Connection createPerson(Person add_person) throws ClassNotFoundException {
            // show_pussy8squirrel~hairy  heslo mySQL 
            // Vytvoríme si premenne pre spojenie sa z databazov 
            // skopirujeme si path na databazu          
            
            try {
                  Connection conn = DriverManager.getConnection(url, username, password);                
                  System.out.println("Connected to the Database is corect :)");
                  System.out.println(conn.getMetaData());           

                  java.sql.Statement st= conn.createStatement();
                  java.sql.ResultSet set = st.executeQuery(select_persons);
                  java.sql.PreparedStatement prst = conn.prepareStatement(INSERT_PERSON_SQL);
                  int result = 0;
                  prst.setInt(1, Person.person_id);
                  prst.setString(2,Person.person_name);
                  prst.setString(3,Person.person_lastName);
                 prst.setInt(4,Person.identity_card);
                  /////////////////////// step 3  execute query or update query
                 
                  result = prst.executeUpdate();
                  if(result > 0){
                        System.out.println("Person is vos created  ..!)(:?LK)...");
                  }
                  System.out.println(prst);
                  while (set.next()) {
                        System.out.println(set.getInt(1)+" "+ 
                        set.getString(2)+" "
                        +set.getString(3)+" "+set.getInt(4));
                  }
                  
            } catch (SQLException e) {  
                  System.out.println("Oops Error !!");                
                  e.printStackTrace();
            }
            return conn;
      }
      


      public   Connection deletePerson(Person person) throws ClassNotFoundException {
            // show_pussy8squirrel~hairy  heslo mySQL 
            // Vytvoríme si premenne pre spojenie sa z databazov 
            // skopirujeme si path na databazu          
            
            try {
                  Connection conn = DriverManager.getConnection(url, username, password);                
                  System.out.println("Connected to the Database is corect :)");
                  System.out.println(conn.getMetaData());           

                  java.sql.Statement st= conn.createStatement();
                  java.sql.ResultSet set = st.executeQuery(select_persons);
                  java.sql.PreparedStatement prst = conn.prepareStatement(DELETEPERSON_SQL);
                  int result = 0;
                  prst.setInt(1, Person.person_id);               
                  result = prst.executeUpdate();
                  if(result > 0){
                        System.out.println("Person is vos deleted ...........skapala");
                  }
                  System.out.println(prst);
                  while (set.next()) {
                        System.out.println(set.getInt(1)+
                        "person_id :"+Person.person_id +
                        " VOS DELETED ." );
                  }
                  
            } catch (SQLException e) {  
                  System.out.println("Oops Error !!");                
                  e.printStackTrace();
            }
            return conn;
      }

      //////////////////////////ú BOOO KS////////////////////////

       public   Connection createBook(Book book) throws ClassNotFoundException {
            // show_pussy8squirrel~hairy  heslo mySQL 
            // Vytvoríme si premenne pre spojenie sa z databazov 
            // skopirujeme si path na databazu          
            
            try {
                  Connection conn = DriverManager.getConnection(url, username, password);                
                  System.out.println("Connected to the Database is corect :)");
                  System.out.println(conn.getMetaData());           

                  java.sql.Statement st= conn.createStatement();
                  java.sql.ResultSet set = st.executeQuery(select_books);
                  java.sql.PreparedStatement prst = conn.prepareStatement(INSERT_BOOK_SQL);
                  int result = 0;                 
                  prst.setString(1,Book.book_title);
                  prst.setString(2,Book.book_autor);
                 // prst.setString(3,Book.book_genre);
                  /////////////////////// step 3  execute query or update query
                 
                  result = prst.executeUpdate();
                  if(result > 0){
                        System.out.println("Person is vos created  ..!)(:?LK)...");
                  }
                  System.out.println(prst);
                  while (set.next()) {
                        System.out.println(set.getString(1)+" "+ 
                        set.getString(2)+" "
                        +set.getString(3)+" "+set.getInt(4));
                  }
                  
            } catch (SQLException e) {  
                  System.out.println("Oops Error !!");                
                  e.printStackTrace();
            }
            return conn;
      }
}