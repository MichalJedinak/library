package new_libraries.tools;

public class Loop {
      static  String[] charList = {"A","B","C"}; ///  hodnoty v id char(10)
      static int count = 1;
      static String curreString = "";
      static int charIndex= 0;
      static int j=0;
      static String id;
      static int counter= 258;
      
      static int startId = 55;  // set value from lasted number of item.id   
      public static int getStartId() {
            return startId;
      }  
      public static void setStartId(int startId) {
            Loop.startId = startId;
      }

public void loop(){
      for ( int i =0;i < counter ; i++ ){
            curreString = charList[charIndex];
            String format = String.format("%02d", count++);
            id = curreString+ "-"+ format;
           
            if(count%10==0 ){  
                  charIndex++;
            }
            if(i%5==0){
                  j++;
                  }
            System.out.println(id  +" "+ " 250"+"cm " + j );               
      }

}       
public static void itemIdLoop(){
      for( int i = 0 ; i < counter ; i++ ){
            startId++;
            System.out.println(i+ "     -  "+ startId);
      }
}

// lumpo slúži na zápis viacerich hornôt do databazy naraz 
public static void main(String[] args) {
      System.out.println("poradie"+"-"+" id ");
      System.out.println("_________________");
      Loop l = new Loop();
      Loop.itemIdLoop();System.out.println(l);
}


}