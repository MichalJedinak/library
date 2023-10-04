/* package sqlservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kniznica.objects.Person;

public class PersonServlet  extends HttpServlet{
      private static final long serialVersionUID = 1L ;

      /// create new SQL_DAO Object (PersonSQL)

      protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
            response.getWriter().append("Served at: ").append(request.getContextPath());
      }
      protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
            String person_name     = request.getParameter("person_name");
            String person_lastName = request.getParameter("person_lastName");
            String identity_card   = request.getParameter(null);
            int idCard = Integer.parseInt(identity_card);
            Person svrlPerson = new Person(person_name, person_lastName, idCard);
                   //person.setidentity_card(null);

            // try {
            //       JdbcMySQL jdbc = new JdbcMySQL();
            //        jdbc.getJdbcMySQL();
            //        Statement stt = jdbc.getJdbcMySQL().createStatement();
            //        ResultSet resSet = stt.executeQuery("SELECT * FROM persons");
            //        PreparedStatement prd = jdbc.getJdbcMySQL().prepareStatement("INSERT INTO persons(person_name,person_lastName) VALUES(?,?);");
            //                          prd.setString(1, person_lastName);
            //                          prd.setString(2, person_lastName);
            // } catch (ClassNotFoundException e) {
                 
            //       e.printStackTrace();
            // } catch (Exception e) {
                  
            //       e.printStackTrace();
            // }      

            
            String url = "jdbc:mysql://localhost:3306/kniznica";
            String user_name = "root";
            String password = "show_pussy8squirrel~hairy";
           
            try{
                  //Class.forName("com.mysql.jdbc.Driver");
                  Connection conn = DriverManager.getConnection(url,user_name,password);
                  PreparedStatement prst
                  = conn.prepareStatement("inser into users(user_name,password,checkbox) value(?,?,?)" );
                        prst.setString(1,person_name);
                        prst.setString(2,password);                      
                        int x = prst.executeUpdate();  
                        if(x>0){
                              System.out.println("Registration done successfully...");
                        }else{
                              System.out.println("registration failed......EROR!! ");
                        }  
                  
                  }catch(Exception e){
                        
                  }
            response.sendRedirect(url);
      }
            public static void main(String[] args) throws ServletException, IOException {
                  PersonServlet npsvl = new PersonServlet();
                  npsvl.doPost(null, null);
            }
}
 */