package beadandó_1;

public class Szerencse extends Mező {
    private final int nyeremény;
    
    public Szerencse(int nyeremény) {
        super();
        this.nyeremény = nyeremény;
    }
    
    public int getNyeremény(){
        return this.nyeremény;
    }

    @Override
    public String getType(){
        return "Szerencse";
    }
}
