package new_libraries.tools;

public class Z {
      
public void send(){

      String s = "S radosťou vám oznamujem , že sa uchádzam o vami ponúkané pracovné miesto .";
      for(int i = 0;i<s.length();i++){
            System.out.println(s);
      }
}
      
public static void main(String[] args) {
      new Z().send();
}
     
}
