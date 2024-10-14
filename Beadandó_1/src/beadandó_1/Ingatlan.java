package beadandó_1;

public class Ingatlan extends Mező{
    
    public Ingatlan(){
        super();
        this.vanHáz = false;
        this.tulaj = null;
        this.lépettMárRá = false;
    }
    
    protected Boolean vanHáz;
    protected Játékos tulaj;
    protected Boolean lépettMárRá;
    
    public Boolean getLépettMárRá() {
        return lépettMárRá;
    }

    public void setLépettMárRá(Boolean lépettMárRá) {
        this.lépettMárRá = lépettMárRá;
    }

    public Boolean getVanHáz() {
        return vanHáz;
    }

    public Játékos getTulaj(){
        return this.tulaj;
    }
    
    public void setVanHáz(Boolean vanHáz) {
        this.vanHáz = vanHáz;
    }

    public void setTulaj(Játékos tulaj) {
        this.tulaj = tulaj;
    }
    
    public void megvesz(Játékos játékos){
        tulaj = játékos;
    }
    
    public void házatVesz(Játékos játékos){
        this.vanHáz = true;
    }
    
    @Override
    public String getType(){
        return "Ingatlan";
    }
}
