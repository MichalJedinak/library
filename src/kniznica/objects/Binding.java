package kniznica.objects;

public enum Binding {

      hard(1),soft(2);
      final int číslo; 
       Binding(int číslo ){
             this.číslo =číslo;
             
      }
      public int getPoradie() {
            return číslo;
      }
}
