package beadandó_1;

public class Taktikus extends Játékos {
    private Boolean vehetE;
    
    public Taktikus(String név){
        super(név);
        this.vehetE = true;
    }

    public Boolean getVehetE() {
        return vehetE;
    }

    public void setVehetE(Boolean vehetE) {
        this.vehetE = vehetE;
    }

    @Override
    public String getTaktika(){
        return "Taktikus";
    }
}