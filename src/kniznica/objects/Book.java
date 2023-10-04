package kniznica.objects;


public class Book extends Autors {   
      
      public static String book_title;
      public static String book_autor;
      public static Genre genre;
      
      String book_gennre;
  
      /**
       * @return the book_autor
       */
      public String getBook_autor() {
            return book_autor;
      }
      /**
       * @param book_autor the book_autor to set
       */
      public void setBook_autor(String book_autor) {
        
            Book.book_autor = book_autor;
      }

      /**
       * @return the book_gennre
       */
      public String getBook_gennre() {
            return book_gennre;
      }
      /**
       * @param book_gennre the book_gennre to set
       */
      public void setBook_gennre(String book_gennre) {
            this.book_gennre = book_gennre;
      }  
      /**
       * @return the genre
       */
      public Genre getGenre() {
            return genre;
      }
      /**
       * @param genre the genre to set
       */
      public void setGenre(Genre genre) {
            Book.genre = genre;
      }

      public String getBook_title() {
            return book_title;
      }

      /**
       * @param book_title the book_title to set
       */
      public void setBook_title(String book_title) {
            Book.book_title = book_title;
      }

      public Book(String book_title,String book_autor, Genre genre){
           Book.book_title= book_title;
            Book.book_autor=book_autor;
            Book.genre =genre;
      }
      public void setBookGenre(Genre genre){
            Book.genre=genre;
      }
public Book(){
     
  
      Genre.selectGenre(genre);
      System.out.println();
         for (Genre select_genre : Genre.values()){
            System.out.println(select_genre);
         }
       if(genre == Genre.EDUKA){
            Genre.selectGenre(genre);
       }
        if(genre== Genre.ROMANCE){
            Genre.selectGenre(genre);
       }
}

public static void main(String[] args) {

    

}

}