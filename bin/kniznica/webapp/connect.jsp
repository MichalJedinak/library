<%@ page import = "java.sql.*"%>
<%
String user_name = request.getParameter(user_name);
String password = request.getParameter(password);
String checkbox = request.getParameter(checkbox);

try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/test,"root","");
      PreparedStatement prst
       = connection.prepareStatement(inser into users(user_name,password,checkbox) value(?,?,?) );
            prst.setSrting(1,user_name);
            prst.setString(2,password);
            prst.setString(3,checkbox);
            int x = prst.executeUpdate();  
            if(x>0){
                  System.out.println("Registration done successfully...");
            }else{
                  System.out.println("registration failed......EROR!! ");
            }  
           
}catch(Exception e){
    out.println(e);
}

%>
C:\Users\BigON\Documents\javaKniznicaUloha2\javaKniznicaUloha2\lib\mysql-connector-j-8.0.33.jar