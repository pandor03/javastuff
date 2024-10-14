package beadandó_1;

import java.util.ArrayList;

public class Pálya {
    private ArrayList<Mező> mezők;

    public Pálya() {
        this.mezők = new ArrayList<Mező>();
    }

    public ArrayList<Mező> getMezők() {
        return mezők;
    }
    
    public void addMező(Mező mező){
        this.mezők.add(mező);
    }
}
