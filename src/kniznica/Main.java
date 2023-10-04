package kniznica;

import kniznica.frames.LoginPage;


public class Main {
      public static void main(String[] args) throws ClassNotFoundException {          
            // Prihlásime sa do App cez GUI 
            IdAndPasswords idAndPasswords = new IdAndPasswords();
            LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo());
            System.out.println(loginPage);                  
      } 
}

