package beadandó_1;

public class Óvatos extends Játékos {
    private int mennyitKölthetAKörben;
    
    public Óvatos(String név){
        super(név);
        this.mennyitKölthetAKörben = 5000;
    }

    public int getMennyitKölthetAKörben() {
        return mennyitKölthetAKörben;
    }

    public void setMennyitKölthetAKörben(int mennyitKölthetAKörben) {
        this.mennyitKölthetAKörben = mennyitKölthetAKörben;
    }
    
    @Override
    public String getTaktika(){
        return "Óvatos";
    }
}