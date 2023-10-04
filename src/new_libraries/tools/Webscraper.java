package new_libraries.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// do lib  pridať jsoup.jar subor na prepojenie 
public class Webscraper {

    public static void main(String[] args) {
        // List<Book> books = databaseBooks("https://www.databazeknih.cz/knihy");
        // for (Book book : books) {
        //     System.out.print( book.getAuthor()+ " "+book.getTitle());
          
        //     System.out.println();
        // }

        // List<Names> names = databaseNames("https://kalendar.aktuality.sk/meniny/");
        // for(Names name : names){
        //      String z = "( \" "+ name.getMeno()+"\" )"+",";
        //     System.out.println(z);
        // }

        // List<City> citys = databaseCity("https://sk.wikipedia.org/wiki/Zoznam_miest_na_Slovensku");
        // for(City city : citys){
        //      String z = "( \" "+ city.getCity()+"\" )"+",";
        //     System.out.println(z);
        // }

        /*
        Začína   na A   a končí na Z  
        http://www.mojslovnik.sk/zoznam-psc-Slovensko/A
        http://www.mojslovnik.sk/zoznam-psc-Slovensko/Z

        Ale nietoré písmená majú aj postránky s rôznym počtom napríklad pod B je ich 14 
        http://www.mojslovnik.sk/zoznam-psc-Slovensko/B
        http://www.mojslovnik.sk/zoznam-psc-Slovensko/B?page=2
        http://www.mojslovnik.sk/zoznam-psc-Slovensko/B?page=14
        
        Ako upravím kód cez for loop v tomto prípade aby prešiel cez A po Z  a zároveň skontroloval prípadný počet 
        postránok pre jednotlivé písmená?*/

        // List<PostCode> postCode = databasePostCode();
        // for (PostCode p : postCode){
        //     String s = p.getPostCode();System.out.println(s);
        // }

        String baseUrl = "http://www.mojslovnik.sk/zoznam-psc-Slovensko/";

        // Cyklus pre písmená od A po Z
        char a ;
        char z = 'Z';
        int page = 2;
       
        for (a='A';a <= z;a++) {
            String letterUrl = baseUrl + a;
          // System.out.println(letterUrl);
            // Vnútorný cyklus pre stránky pre jedno písmeno
            try {                
                    //Document pcDoc = Jsoup.connect(letterUrl).get();
                    String ooo= letterUrl+"?page="+page;
                    Document pcDoc= Jsoup.connect(ooo).get();                             
                    Elements postCodesElements = pcDoc.select("table.mslist tr"); 
                            System.out.println(ooo);                              
                       page++;     
                    if(postCodesElements.isEmpty()){
                        break;
                    }  

                    for (Element postCodeElement : postCodesElements) {
                        Element okresElement = postCodeElement.select("td:contains(Martin)").first();
                       // Element okresy = postCodeElement.select("td:eq(3)").first();

                        if (okresElement != null) {
                            Element firstTdElement = postCodeElement.select("td:eq(0)").first();
                            Element secondElement = postCodeElement.select("td:eq(1)").first();
                            String code = firstTdElement.text().trim();
                            String obec = secondElement.text().trim();
                            System.out.println(code+" "+obec);
                            
                        }
                    }
                } catch (Exception e) {
                   e.printStackTrace();
                }

            // while (true) {
            //       try {
            //           String pageUrl = letterUrl + "?page=" + page;
            //           //System.out.println(pageUrl);
            //           Document pcDoc = Jsoup.connect(pageUrl).get();
            //           Elements postCodesElements = pcDoc.select("table.mslist tr");
  
            //           if (postCodesElements.isEmpty()) {
            //               // Ak nie sú žiadne ďalšie stránky, ukonči vnútorný cyklus
            //               break;
            //           }
  
                    
            //           for (Element postCodeElement : postCodesElements) {
            //               Element okresElement = postCodeElement.select("td:contains(Martin)").first();
                  
            //               if (okresElement != null) {
            //                   Element firstTdElement = postCodeElement.select("td:eq(0)").first();
            //                   String code = firstTdElement.text().trim(); 
            //                   System.out.println(code + "  "+ okresElement.text());                    
            //               }
            //           }
            //          // page++;
            //       } catch (IOException e) {
            //           e.printStackTrace();
            //       }
            //   }
          
        }

    }

/// 1- vytvoriť list pre knihy z stiahnuté z stránky databazeknih.cz/knihy
// 2-  pokúsiť sa vytvoriť dokument s url adresy  s konkrétnych elementov s inspect s web stranky  
// <div class="box_with_image_and_text odright_pet"><img class="kniha_img" src="https://www.databazeknih.cz/img/books/50_/509372/les-v-dome-6479c873c40b8.jpg" title="Les v domě" alt="Les v domě"><div class="clear"></div>
// <div class="title">Les v domě</div>        text Les v Domě
// <div class="desc">Alena Mornštajnová       text   Alena M...  etc 
// </div>
// </div> 
// 3- vytvoriť knihu objekt ako class s hodnotami title a author  s polu s getermi a konštruktorom knihy
// 4 - dúfať že to pôjde :))  výsledok by mal byť zoznam kníh z prvej stranky  zoznamu 

    public static List<Names> databaseNames(String url){
        List<Names> names = new ArrayList<>();        
        Document nDoc;
        try {
            nDoc = Jsoup.connect(url).get();
            Elements namesElements = nDoc.select("table.t_zoznam");
            for(Element nameElement : namesElements){
              String[] mena = nameElement.select("td.value").text().trim().split("\\s*,\\s*|\\s+");
              for(String meno : mena){
                  names.add(new Names(meno));                 
              }
            }
        } catch (IOException e) {      
            e.printStackTrace();
        }
    
        return names;
    }

    public static List<Book> databaseBooks(String url) {
        List<Book> books = new ArrayList<>();
            try {
                  Document doc = Jsoup.connect(url).get();
                  Elements bookElements = doc.select("div.box_with_image_and_text.odright_pet");
                  for (Element bookElement : bookElements) {
                  String title = bookElement.select("div.title").text().trim();
                  String author = bookElement.select("div.desc").text().trim();
                  books.add(new Book(title, author));
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }

            return books;
      }

      public static List<City> databaseCity(String url){
        List<City> citys = new ArrayList<>();       
        try {
            Document cDoc = Jsoup.connect(url).get();
            Elements citysElements = cDoc.select("tbody");
            for(Element cityElement : citysElements){
                String[] mesta = cityElement.select("b").text().trim().split("\\s+"); 
                for(String mesto : mesta){
                  citys.add(new City(mesto));            
              }            
            }
        } catch (IOException e) {      
            e.printStackTrace();
        }    
        return citys;
    }
     
    public static List<PostCode> databasePostCode() {
        List<PostCode> postCodes = new ArrayList<>();
       String  url = "http://www.mojslovnik.sk/zoznam-psc-Slovensko/";
        for(char letter ='B';letter <= 'Z';letter++ ){
            String urlPlusLetter = url + letter;            
            int page =1;
            while (true) {
                try {
                    String pageUrl = urlPlusLetter+ "?page="+ page;
                    Document pcDoc = Jsoup.connect(pageUrl).get();
                    Elements postCodesElements = pcDoc.select("table.mslist tr"); 

                    if(postCodesElements.isEmpty()){
                        break;
                    }  

                    for (Element postCodeElement : postCodesElements) {
                        Element okresElement = postCodeElement.select("td:contains(Martin)").first();
                
                        if (okresElement != null) {
                            Element firstTdElement = postCodeElement.select("td:eq(0)").first();
                            String code = firstTdElement.text().trim();
                            postCodes.add(new PostCode(code));                          
                        }
                    }
                    page++;
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
      
        }
        return postCodes;

    }

       public static String mergeCityName(String cityName) {
        Pattern pattern = Pattern.compile("(.*)(\\b(?:nad|pod)\\b)(.*)");
        Matcher matcher = pattern.matcher(cityName);
        
        if (matcher.matches()) {
            String part1 = matcher.group(1).trim();
            String part2 = matcher.group(2).trim();
            String part3 = matcher.group(3).trim();
            
            // Spojíme mien s "nad" alebo "pod" tak, aby sa zobrazovali ako celok
            return part1 + " " + part2 + part3;
        } else {
            return cityName;
        }
    }
      /////////////////////////////////////// objekty kniha / meno  / mesto
    static class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }
    static class Names{
        private String meno ;

        public Names(String meno){
            this.meno= meno;
        }
        public String getMeno() {
            return meno;
        }

        public void setMeno(String meno) {
            this.meno = meno;
        }
    }

    static class City{
        private String city;

        public City(String city){
            this.city=city;
        }
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    static class PostCode{
       private String postCode;

        public PostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }
    }
}
//*[@id="left_less"]/a[1]/div