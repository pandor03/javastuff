package beadandó_1;

public class Szolgáltatás extends Mező {
    private final int bírság;
    
    public Szolgáltatás(int bírság) {
        super();
        this.bírság = bírság;
    }
    
    public int getBírság(){
        return this.bírság;
    }
    
    @Override
    public String getType(){
        return "Szolgáltatás";
    }
}