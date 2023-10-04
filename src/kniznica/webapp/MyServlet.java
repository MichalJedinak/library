package kniznica.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import new_libraries.SqlFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/my-url")

public class MyServlet extends HttpServlet implements ActionListener{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  PrintWriter out = response.getWriter();
                  out.println("<h2> Myservlet<h2>");
                 
                  // Tu môžeš spracovať GET požiadavku od frontendu
                  // a prípadne poslať nejaké dáta späť na frontend.
            }
            
            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  String button = request.getParameter("submit");

            if("submit".equals(button)){
                  System.out.println("das is gut");
                        try {
                              
                        String name = request.getParameter("user_name");
                        String password = request.getParameter("password");
                        
                        Connection conn = DriverManager.getConnection(SqlFunctions.url,SqlFunctions.username,SqlFunctions.password);
                        System.out.println("Connection id correct !!");// control sysout
                        System.out.println(conn.getMetaData());
                        Statement statement = conn.createStatement();
            
                        String query = "SELECT * FROM login WHERE userName ='"+name+"' and password = '"+password+"';";
                        ResultSet resulset = statement.executeQuery(query);
                        
                        if(resulset.next()) {
                              //response.sendRedirect("C:\\Users\\BigON\\Documents\\HTML\\REZOLUCIA\\rezolucia-new.html");                     
                              //response.sendRedirect(request.getContextPath() + "/pages/rezolucia-new.html");
                              response.sendRedirect("https://www.beliweb.sk");
                              System.out.println("pokus je ok ale niiie");
                        }else{
                           System.out.println("zle je to ");
                        }
            
                        conn.close();
                  } catch (SQLException e) {
                        e.printStackTrace();
                  }
            }else{
                  System.out.println("nespojilo to ???Lkonzola som ja");
            }
        // Tu môžeš spracovať POST požiadavku od frontendu
        // a prípadne poslať nejaké dáta späť na frontend.
        doPost(request, response);
    }
      
@Override
public void actionPerformed(ActionEvent e) {
     
}
public static void main(String[] args) {
      new MyServlet();
}
}