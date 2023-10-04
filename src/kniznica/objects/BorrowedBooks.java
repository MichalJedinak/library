package kniznica.objects;

import java.math.BigDecimal;

public class BorrowedBooks {

      int borrowed_id;
      int book_id;
      int person_id;
      String day_of_borrowed;
      BigDecimal amount;      
   
      BorrowedBooks(int borrowed_id ,int book_id, int person_id, String day_of_borrowed, BigDecimal amount) {
            this.borrowed_id= borrowed_id;
            this.book_id= book_id;
            this.person_id =person_id;
            this.day_of_borrowed= day_of_borrowed;
            this.amount=amount;          
      }
     
      /**
       * @return the borrowed_id
       */
      public int getBorrowed_id() {
            return borrowed_id;
      }


      /**
       * @param borrowed_id the borrowed_id to set
       */
      public void setBorrowed_id(int borrowed_id) {
            this.borrowed_id = borrowed_id;
      }


      /**
       * @return the book_id
       */
      public int getBook_id() {
            return book_id;
      }


      /**
       * @param book_id the book_id to set
       */
      public void setBook_id(int book_id) {
            this.book_id = book_id;
      }


      /**
       * @return the person_id
       */
      public int getPerson_id() {
            return person_id;
      }


      /**
       * @param person_id the person_id to set
       */
      public void setPerson_id(int person_id) {
            this.person_id = person_id;
      }


      /**
       * @return the day_of_borrowed
       */
      public String getDay_of_borrowed() {
            return day_of_borrowed;
      }


      /**
       * @param day_of_borrowed the day_of_borrowed to set
       */
      public void setDay_of_borrowed(String day_of_borrowed) {
            this.day_of_borrowed = day_of_borrowed;
      }


      /**
       * @return the amount
       */
      public BigDecimal getAmount() {
            return amount;
      }


      /**
       * @param amount the amount to set
       */
      public void setAmount(BigDecimal amount) {
            this.amount = amount;
      }
      
}
