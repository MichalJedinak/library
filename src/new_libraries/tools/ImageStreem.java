package new_libraries.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import kniznica.objects.Person;

public class ImageStreem {
      public static void main(String[] args) {
            ///////////  akýkoľvekk súbor txt  , png, etc...
            int bytes;
            FileInputStream input =null;
            FileOutputStream output =null;
            try {
                  input = new FileInputStream("src\\new_libraries\\person\\resources\\myšiak.gif");
                  output = new FileOutputStream("src\\new_libraries\\person\\resources\\img.gif");
                  while ( (bytes=input.read()) !=-1 ) {
                        output.write(bytes);
                  }
                  input.close();
                  output.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
            //////////////////   na čítanie po charakteroch
            int readBytes;
            FileReader reader = null;
            FileWriter writer = null;
            try {
                  reader = new FileReader("src\\new_libraries\\person\\resources\\byte.txt");
                  writer= new FileWriter("src\\new_libraries\\person\\resources\\empty.txt");
                  while ( (readBytes= reader.read() )!=-1) {
                        writer.write(readBytes);
                  }
                  reader.close();
                  writer.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
            //////////////  čítanie po riadkoch 
            String stringBytes;
            BufferedReader  bufferedReader = null;
            PrintWriter     printWriter    = null;
            try {
                  bufferedReader= new BufferedReader(new FileReader("   "));
                  printWriter= new PrintWriter(new FileWriter("    "));
                  while ( (stringBytes = bufferedReader.readLine() ) !=null) {
                        printWriter.println(stringBytes);
                  }
                  bufferedReader.close();
                  printWriter.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
            ///////////   object streem 
            ///   Objekt v tomto prípade Person musí byť imlements Serializable inak sa nebud edať s ním pracovať
            // vytrvoríme si nový objekt 
            Person person = new Person("Michal", "Jedinák",47);
            
            ObjectInputStream objectInputStream;
            ObjectOutputStream onObjectOutputStream;
            // určíme si cestu pomocou FileOutputSream ktorý použijeme v ObjectOutputStream 
            //  a následne  ho tam zapíšeme
            try {
                  FileOutputStream outputStream= new FileOutputStream("src\\new_libraries\\person\\\\resources\\\\empty.txt");
                  onObjectOutputStream = new ObjectOutputStream(outputStream);
                  onObjectOutputStream.writeObject(person);
                  outputStream.close();
                  onObjectOutputStream.close();
            } catch (Exception e) {
                  System.err.println(e);
            }
            ///  z súboru Objekt načízame pomocou FileInputStream cez ObjectInputStream a vypíšeme na konzolu alebo odšleme niekam
            try {
                  FileInputStream fis = new FileInputStream("src\\new_libraries\\person\\resources\\empty.txt");
                  objectInputStream = new ObjectInputStream(fis);
                  Person person_2 = (Person) objectInputStream.readObject();
                  System.out.println(person_2.getPerson_name());
                  System.out.println(person_2.getPerson_lastName());
                  System.out.println(person_2.getidentity_card());
                  
            } catch (Exception e) {
                 System.err.println(e);
            }
      }
}
