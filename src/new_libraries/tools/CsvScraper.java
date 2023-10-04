package new_libraries.tools;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvScraper {
      
      CsvScraper(){
            try {
                  File file = new  File("src\\new_libraries\\tools\\obce_postCode.txt");
                  Scanner scaner = new Scanner(file);
                  while(scaner.hasNextLine()){
                    String s = scaner.nextLine();
                    String[] pole= s.split(";");
                    // pre vyber konkretnehoho ps vyber z akého mesta podla txt suboru 
                    if(pole[2].contains("Martin") || s.contains("Turčianske Teplice")){
                        if(!pole[3].isEmpty()){
                              ArrayList<String> postCodes = new ArrayList<>();   
                              postCodes.add("('"+pole[3] +"' , \"" +pole[1]+"\"),") ;   ///  post code a nazov obce
                             // postCodes.add("('"+pole[3] +"'),");         // post code         
                               // System.out.println(pole[3] +" , "+pole[1] +" || "+pole[2]);
                               System.out.println(postCodes.get(0));
                        }
                    }
                  }
                  scaner.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
    }
    /*
     * Z zoznamu smerových čísel uložených s xmsl súboru do csv súboru 
     * vyhladaj a vypíš všetky smerové čísla z Martina a Turčianskych Teplíc 
    */
    public static void main(String[] args) {
      new CsvScraper();
    }
}
