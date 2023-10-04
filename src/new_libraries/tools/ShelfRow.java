package new_libraries.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShelfRow {
      
      public static  String[] charList = {"A","B","C","D","E","F","G","I","J","K"};
      public static  int count = 0;
      public static String id;
      public static  String currenString = "";
      public static  int charIndex= 0;
      public static  int shelfLenght = 250;
      public static  String unit = "cm";
      public static  int shelf = 0; 
      public static char  z = ',';
                    int maxRecords = 450;

      ///  udaje potrebné k zápisu do drivermanager
      String url = "jdbc:mysql://localhost:3306/kniznica";
      String username = "root";
      String password = "show_pussy8squirrel~hairy";
      String select ="SELECT * FROM polica";   
      //static String values = "(?,?,?,?);" ;
      static String insertShelfRow ="INSERT INTO polica(id,lenght,unit,shelf) VALUES(?,?,?,?)"; 
      final Connection conn = null;
      static int start = 0 ; // pre nastavenie pokračovania id 

      /// spojenie s databazov 
      public Connection shelfRowConnection(ShelfRow shelfRow,int startCount) throws ClassNotFoundException{
          try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the Database is correct :)"); /// hlasenie pri uspešnom pripojení
            System.out.println(conn.getMetaData());
            start= startCount;

            ///// loop na opakonie zápisu 
                for (int i =0; i < maxRecords; i++ ){                 
                  currenString = charList[charIndex];
                  String formattedNumber = String.format("%02d", count++); // Zabezpečí, aby čísla mali dve miesta (01, 02, ..., 10)
                  id =  currenString + "-" + formattedNumber;  
                //  int number = i % 50 == 0 ? 50 : i % 50;
                  if(count % 50 == 0 ){                                             
                        charIndex++; }                  
                  if(i % 5 == 0){  // Zabespečí aby sa id regálu zmenilo po každej piatej policy                     
                        shelf++;  }
                  
                  if(i % 15 == 0){  //  Zabespečí zmenu dĺžky police v určitých úsekoch loopu 
                        shelfLenght= 200 ;
                  }
                  if(i % 35 == 0){
                        shelfLenght= 150;  }
                  if(i % 50 == 0){
                        shelfLenght=250;   }
                  if(i == maxRecords-1){
                        z=';'; }
            System.out.println(id +z+ shelfLenght+z+unit+z+shelf+")"+z); /// kontrolny výpisnastavených hodnôt

            ///// nastavenie paramertov do pripravovaného zápisu do databazovej tabulky
             PreparedStatement prst = conn.prepareStatement(insertShelfRow);
                prst.setString(1, id);
                prst.setInt(2, shelfLenght);
                prst.setString(3, unit);
                prst.setInt(4, shelf);
                
               
                int result = prst.executeUpdate();
                if (result > 0) {
                    System.out.println("Záznam s ID " + id + " bol úspešne vložený do databázy."); }
            }
        
            java.sql.Statement st = conn.createStatement();
            ResultSet set = st.executeQuery(select);

            while (set.next()) {
                System.out.println(
                    set.getString(1) + " "
                    + set.getInt(2) + " "
                    + set.getString(3) + " "
                    + set.getInt(4)
                );
            }     
      } catch (Exception e) {
        System.out.println(e);
      }
      return conn;
          
      } 

      
      public static void main(String[] args) {
            ShelfRow shelfRow= new ShelfRow();
            try {
                  shelfRow.shelfRowConnection(shelfRow,0);
            } catch (ClassNotFoundException e) {                 
                  e.printStackTrace();
            }            
            
      }

}
