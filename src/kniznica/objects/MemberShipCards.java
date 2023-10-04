package kniznica.objects;

public class MemberShipCards {
      public static  String cardNumber;
      public static int   intCardNumber;
      MemberShipCards(){

      }
      /**
       * @return the cardNumber
       */
      public String getCardNumber() {
            return cardNumber;
      }
      /**
       * @param cardNumber the cardNumber to set
       */
      public void setCardNumber(String cardNumber) {
           MemberShipCards.cardNumber = cardNumber;
      }
      /**
       * @return the intCardNumber
       */
      public int getIntCardNumber() {
            return intCardNumber;
      }
      /**
       * @param intCardNumber the intCardNumber to set
       */
      public void setIntCardNumber(int intCardNumber) {
            MemberShipCards.intCardNumber = intCardNumber;
      }
}

