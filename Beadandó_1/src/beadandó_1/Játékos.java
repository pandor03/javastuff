package beadandó_1;

import java.util.ArrayList;

public abstract class Játékos {
    int jelenlegiPozíció;
    String név;
    int kassza;
    ArrayList<Ingatlan> ingatlanok = new ArrayList<>();

    public Játékos(String név) {
        this.jelenlegiPozíció = 0;
        this.név = név;
        this.kassza = 10000;
        this.ingatlanok = new ArrayList<>();
    }
    
    public int getKassza(){
        return this.kassza;
    }
    
    public void költ(int mennyit){
        this.kassza -= mennyit;
    }
    
    public void nyer(int mennyit){
        this.kassza += mennyit;
    }
    
    public void ingatlantVesz(Ingatlan ingatlan){
        this.ingatlanok.add(ingatlan);
        költ(1000);
    }

    public ArrayList<Ingatlan> getIngatlanok() {
        return this.ingatlanok;
    }
    
    public void fizet(Játékos kinek, int mennyit){
        kinek.nyer(mennyit);
        this.költ(mennyit);
    }

    public void setKassza(int kassza) {
        this.kassza = kassza;
    }
    
    public int getJelenlegiPozíció() {
        return jelenlegiPozíció;
    }

    public void setJelenlegiPozíció(int jelenlegiPozíció) {
        this.jelenlegiPozíció = jelenlegiPozíció;
    }
    
    public void házatÉpít(Ingatlan ingatlan){
        if(ingatlan.getTulaj() == this){
            ingatlan.házatVesz(this);
        }
    }

    public String getNév() {
        return név;
    }
    
    public abstract String getTaktika();
}
