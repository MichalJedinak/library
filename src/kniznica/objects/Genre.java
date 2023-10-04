package kniznica.objects;

public enum Genre {

    ROMANCE(1),
    HOROR(2),
    FANTASY(3),
    FARYTAIL(4),
    POETRY(5),
    EDUKA(6);

    final int poradie; 

    public int getPoradie() {
        return poradie;
    }


    Genre(int poradie ){
        this.poradie =poradie;
     
    }
    static void selectGenre(Genre genre){      
        switch (genre) {
            case ROMANCE:
            String romance = ROMANCE.toString();
                System.out.println(romance+ "pohoda");
                break;
            case HOROR :
            System.out.println(HOROR);
                break;
            case FANTASY :
                System.out.println(FANTASY);
                break;
            case FARYTAIL :
                System.out.println(FARYTAIL);
                break;
            case POETRY :
                System.out.println(POETRY);
                break;
            case EDUKA :
            System.out.println(EDUKA);
                break;
            default:
                break;
        }
    }

     public static void main(String[] args) {
     
        Genre genre = Genre.ROMANCE;
        System.out.println();
        Genre.selectGenre(genre);
         for (Genre select_genres : Genre.values()){
            System.out.println(select_genres);
         }

         System.out.println(Genre.POETRY.poradie+" ");
     } 
      
}
