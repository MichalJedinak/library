package new_libraries;

public class Book {
      public static int id ;
      public static String author;
      public static double amout;
     /**
       * @return the id
       */
      public static int getId() {
            return id;
      }

      /**
       * @param id the id to set
       */
      public void setId(int id) {
            Book.id = id;
      }
public  static String title;

        // public Book(String title, String author) {
        //     this.title = title;
        //     this.author = author;
        // }

        /**
       * @return the amout
       */
      public static double getAmout() {
            return amout;
      }

      /**
       * @param amout the amout to set
       */
      public void setAmout(double amout) {
            Book.amout = amout;
      }

      public static String getTitle() {
            return title;
        }

        public static String getAuthor() {
            return author;
        }
      public void setTitle(String title) {
            Book.title = title;
      }
      public void setAuthor(String author) {
            Book.author = author;
      }
}
