package kniznica.objects;

public class Autor extends Person{  
      
      public String country;    

////////////////////////////////////// konštruktor tiredy 
      public Autor(int personid, String person_name, String person_lastName, int identity_card,String country) {
            super(personid, person_name, person_lastName, identity_card);
            this.country= country;
            
      }    

/////////////////////// meno preizvysko autora  
      public void getAutorComletName(){
            String complet_name = person_name +" "+person_lastName;
            System.out.println(complet_name+".");
      }
      
///////////  zdedené metody od person
      @Override
      public void getPerson() {            
            super.getPerson();
      }
//////////////////// geter seter pre country   
      /**
       * @return the country
       */
      public String getcountry() {
            return country;
      }
      
      /**
       * @param country the country to set
       */
      public void setcountry(String country) {
            this.country = country;
      }

      public static void main(String[] args) {
            Autor pokuston = new Autor(012, "Vladislav", "Kormalenko", 20,"Russia");
            pokuston.getPerson();
            pokuston.getAutorComletName();
            pokuston.setPerson_lastName("pičiak opičiak");
            System.out.println(pokuston.getPerson_lastName());
            

      }

    
}
