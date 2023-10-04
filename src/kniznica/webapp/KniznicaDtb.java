package kniznica.webapp;


import java.io.IOException;
import java.io.PrintWriter;
//import javax.persistence.Entity;
//import javax.persistence.Tablename;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kniznica.entity;

@WebServlet("/register")
@entity
// @tablename(name= "kniha",schema= "kniznica")

public class KniznicaDtb extends HttpServlet   {  ///    príprava  na html connection

  public static void main(String[] args) throws ServletException, IOException {
    KniznicaDtb connet = new KniznicaDtb();
    connet.doPost(null, null);
  }
     
      int book_id;   
      
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          // get printwriter
          PrintWriter prw = res.getWriter();
          //set content type
          res.setContentType("http://localhost:8080/phpmyadmin/index.php?route=/database/structure&db=movies");
          // read the form values
          String username = req.getParameter("root");
          String pasword = req.getParameter("");

            System.out.println("UserName"+username+"\n Pasword"+pasword);
          // close printwriter
           prw.close();
          
      }

      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doGet(req, resp);
         
      }
}
