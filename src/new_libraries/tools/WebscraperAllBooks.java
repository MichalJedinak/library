package new_libraries.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebscraperAllBooks {

      static String FANTAZIA = "FANTAZIA";
      static String HOROR = "HOROR";
      static String POEZIA = "POEZIA";
      static String ROMÁN = "ROMÁN";
      static String ROZPRAVKA = "ROZPRÁVKA";
      static String VZDELANIE = "VZDELANIE";
      static String author;
      public static int serchResult;

    public static void main(String[] args) {
        List<Book>
            // allBooks=  databaseAllBooks("https://www.databazeknih.cz/zanry/romany-12");  // ROMÁN žáner
             //allBooks = databaseAllBooks("https://www.databazeknih.cz/zanry/fantasy-21");
                  //  databaseAllBooks("https://www.databazeknih.cz/knihy") ; // All books 
                  //  databaseAllBooks("https://www.databazeknih.cz/zanry/sci-fi-19");  // SCIFI žáner
                   // allBooks=  databaseAllBooks("https://www.databazeknih.cz/zanry//poezie-23");  // POEZIA žáner
                 // allBooks=  databaseAllBooks("https://www.databazeknih.cz/zanry/horory-6");  // HOROR žáner
                  //  databaseAllBooks("https://www.databazeknih.cz/zanry/fantasy-21") ; // FANTASY žáner
                //  WebscraperAllBooks.databaseAllBooks("https://www.databazeknih.cz/zanry/fantasy-21");   
                 //  allBooks=  databaseAllBooks("https://www.databazeknih.cz/zanry/pohadky-77");//rozpravky 
                    books=  databaseAllBooks("https://www.databazeknih.cz/zanry/erotiky-40");//enciklopedis

    }

//     WebscraperAllBooks(){
//       databaseAllBooks("https://www.databazeknih.cz/zanry/fantasy-21");
//     }
    //// 5 - pre všetky knihy s každej podstránky pridať k adrese ?orderBy=&pageNumber=30  tak aby sa pridavala posledné číslo od 1 do 30
    public static List<Book> databaseAllBooks(String baseUrl) {
        List<Book> allBooks = new ArrayList<>();
        
        int maxPageNumber = 30; // max Počet stránok, ktoré chceme prejsť a získaž zápais 
     /////  ?orderBy=&pageNumber=pageNumber tj. url + string ?orderBy=&pageNumber= + pageNumber ++
        try {
            for (int pageNumber = 1; pageNumber <= maxPageNumber; pageNumber++) {
               // String url = baseUrl + "?orderBy=&pageNumber=" + pageNumber; // string kde definujeme 
                String url = baseUrl + "?orderBy=&pageNumber=" + pageNumber;
                List<Book> booksOnPage = scrapeBooks(url);
                allBooks.addAll(booksOnPage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Book book : allBooks) {
            serchResult=allBooks.size()  ;
            System.out.println( book.getAuthor()+"  " +book.getTitle()+ "  " + VZDELANIE);
           // System.out.println( serchResult);
        }
        return allBooks;
    }

    public static List<Book> scrapeBooks(String url) throws IOException {
        List<Book> books = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements bookElements = doc.select("div.box_with_image_and_text.odright_pet");
                           
        for (Element bookElement : bookElements) {
            String title = bookElement.select("div.title").text().trim();
            String author = bookElement.select("div.desc").text().trim();
            books.add(new Book(title, author));
        }

        return books;
    }

    public static class Book {
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
}

