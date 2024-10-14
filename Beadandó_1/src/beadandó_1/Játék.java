package beadandó_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Játék {
    private Pálya pálya;
    private ArrayList<Játékos> játékosok;
    private int játékosokSzáma;
    private int pályaHossz;
    private ArrayList<Integer> dobások;
    private Boolean vanKocka;
    public Boolean vanHiba;

    public Játék() {
        try{
            this.vanHiba = false;
            File file = new File("C:\\Users\\Andor\\Desktop\\Harmadik félév\\Progtek\\Beadandó_1\\src\\beadandó_1\\source1.txt");
            this.játékosok = new ArrayList<>();
            this.dobások = new ArrayList<>();
            this.pálya = new Pálya();
                Scanner scn = new Scanner(file);
                int ph = scn.nextInt();
                this.pályaHossz = ph;  
                String test = scn.nextLine();
                String[] splitted = test.split(" ");
                while(!isNumeric(splitted[0])){
                    if(null != splitted[0])
                        switch (splitted[0]) {
                        case "ingatlan" -> {
                            Ingatlan toAdd = new Ingatlan();
                            pálya.addMező(toAdd);
                            
                            }
                        case "szolgáltatás" -> {
                            Szolgáltatás toAdd = new Szolgáltatás(Integer.parseInt(splitted[1]));
                            pálya.addMező(toAdd);
                            }
                        case "szerencse" -> {
                            Szerencse toAdd = new Szerencse(Integer.parseInt(splitted[1]));
                            pálya.addMező(toAdd);
                            }
                        default -> {
                            //System.out.println("Hibás típúsú mező!");
                            //this.vanHiba = true;
                        }
                    }

                    test = scn.nextLine();
                    splitted = test.split(" ");
                }
                this.játékosokSzáma = Integer.parseInt(splitted[0]);

                test = scn.nextLine();
                splitted = test.split(" ");
                Boolean vanMég = true;
                while(!isNumeric(splitted[0]) && vanMég){
                    String name = splitted[0];
                    String type = splitted[1];
                    if(null != type)switch (type) {
                        case "Mohó" -> {
                            Mohó toAdd = new Mohó(name);
                            this.játékosok.add(toAdd);
                            }
                        case "Óvatos" -> {
                            Óvatos toAdd = new Óvatos(name);
                            this.játékosok.add(toAdd);
                            }
                        case "Taktikus" -> {
                            Taktikus toAdd = new Taktikus(name);
                            this.játékosok.add(toAdd);
                            }
                        default -> {
                            System.out.println("Hibás típusú játékos!");
                            this.vanHiba = true;
                        }
                    }
                    vanMég = scn.hasNext();
                    if(vanMég){
                        test = scn.nextLine();
                        splitted = test.split(" ");
                    }
                }
                if(isNumeric(splitted[0])){
                    this.dobások.add(Integer.parseInt(splitted[0]));
                }
                while(scn.hasNext()){
                    int nextDobás = Integer.parseInt(scn.nextLine());
                    this.dobások.add(nextDobás);
                }
                if(!this.getDobások().isEmpty()){
                    this.setVanKocka(true);
                }
                else{
                    this.setVanKocka(false);
                }
        }
        catch (FileNotFoundException e){
            System.out.println("Nem található a fájl!");
        }
    }
    
    public void játékFuttatása(){
        if(this.getVanKocka() == null || this.vanHiba){
            
        }else{
        ArrayList<Játékos> vesztesek = new ArrayList<>();
        int jelenlegiJátékos = 0;
        if(this.getVanKocka()){
            Boolean egyMaradt = false;
            int i = 0;
            while(!egyMaradt){
                int lépés;
                if(i == this.getDobások().size()){
                    i = 0;
                    lépés = this.getDobások().get(i);
                }
                else{
                    lépés = this.getDobások().get(i);   
                }
                System.out.println("\n\n"+ this.getJátékosok().get(jelenlegiJátékos).getNév() + " dobása: " + lépés);
                //Kocka dobása
                int holvan = this.getJátékosok().get(jelenlegiJátékos).getJelenlegiPozíció();
                int hova;
                if(holvan + lépés >= this.getPályaHossz()){
                    hova = (holvan + lépés) - this.getPályaHossz();
                    if("Óvatos".equals(this.getJátékosok().get(jelenlegiJátékos).getTaktika())){
                        Óvatos temp = (Óvatos)this.getJátékosok().get(jelenlegiJátékos);
                        temp.setMennyitKölthetAKörben(temp.getKassza() / 2);
                    }
                }
                else{
                    hova = holvan + lépés;
                }
                System.out.println(this.getJátékosok().get(jelenlegiJátékos).getNév() + " ide lép: " +
                                   hova + " (" + this.getPálya().getMezők().get(hova).getType() + ")");
                this.getJátékosok().get(jelenlegiJátékos).setJelenlegiPozíció(hova);
                Ingatlan tempMezőIngatlan = null;
                Szolgáltatás tempMezőSzolgáltatás = null;
                Szerencse tempMezőSzerencse = null;
                if(null != this.getPálya().getMezők().get(hova).getType())
                    switch (this.getPálya().getMezők().get(hova).getType()) {
                    case "Ingatlan" -> tempMezőIngatlan = (Ingatlan)this.getPálya().getMezők().get(hova);
                    case "Szolgáltatás" -> tempMezőSzolgáltatás = (Szolgáltatás)this.getPálya().getMezők().get(hova);
                    case "Szerencse" -> tempMezőSzerencse = (Szerencse)this.getPálya().getMezők().get(hova);
                    default -> {
                    }
                }
                if(null != this.getJátékosok().get(jelenlegiJátékos).getTaktika())
                    switch (this.getJátékosok().get(jelenlegiJátékos).getTaktika()) {
                    case "Mohó" -> {
                        Mohó temp = (Mohó)this.getJátékosok().get(jelenlegiJátékos);
                            switch (this.getPálya().getMezők().get(hova).getType()) {
                            case "Ingatlan" -> {
                                if(tempMezőIngatlan.getTulaj() == null){
                                    if(temp.getKassza() >= 1000){
                                        tempMezőIngatlan.setTulaj(temp);
                                        tempMezőIngatlan.setLépettMárRá(true);
                                        temp.ingatlantVesz(tempMezőIngatlan);
                                        System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                    }
                                }
                                else if(tempMezőIngatlan.getTulaj() == temp){
                                    if(tempMezőIngatlan.getVanHáz() == false){
                                        if(temp.getKassza() >= 4000){
                                            tempMezőIngatlan.setVanHáz(true);
                                            System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                        }
                                    }
                                    else{
                                        System.out.println("Saját, van már rajta ház.");
                                    }
                                }
                                else{
                                    if(tempMezőIngatlan.getVanHáz() == false){
                                        temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                        System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                                
                                    }
                                    else{
                                        temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                        System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                    }
                                }
                            }
                            case "Szolgáltatás" -> {
                                temp.költ(tempMezőSzolgáltatás.getBírság());
                                System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                            }
                            case "Szerencse" -> {
                                temp.nyer(tempMezőSzerencse.getNyeremény());
                                System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                            }
                        }
                    }

                    case "Óvatos" -> {
                        Óvatos temp = (Óvatos)this.getJátékosok().get(jelenlegiJátékos);
                        switch (this.getPálya().getMezők().get(hova).getType()) {
                        case "Ingatlan" -> {
                            if(tempMezőIngatlan.getTulaj() == null){
                                if(temp.getMennyitKölthetAKörben() >= 1000){
                                    tempMezőIngatlan.setTulaj(temp);
                                    tempMezőIngatlan.setLépettMárRá(true);
                                    temp.ingatlantVesz(tempMezőIngatlan);
                                    System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                }
                                System.out.println("a");
                            }
                            else if(tempMezőIngatlan.getTulaj() == temp){
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    if(tempMezőIngatlan.getLépettMárRá()) {
                                        if(temp.getMennyitKölthetAKörben() >= 4000){
                                            tempMezőIngatlan.setVanHáz(true);
                                            System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                        }
                                        else{
                                            System.out.println("Nem költhet ennyit!");
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Saját, van már rajta ház.");
                                }
                            }
                            else{
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                    System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                                else{
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                    System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                            }
                        }
                        case "Szolgáltatás" -> {
                            temp.költ(tempMezőSzolgáltatás.getBírság());
                            System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                        }
                        case "Szerencse" -> {
                            temp.nyer(tempMezőSzerencse.getNyeremény());
                            System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                        }
                    }         
                    }
                    case "Taktikus" -> {
                        Taktikus temp = (Taktikus)this.getJátékosok().get(jelenlegiJátékos);
                        if("Ingatlan".equals(this.getPálya().getMezők().get(hova).getType())){
                            if(tempMezőIngatlan.getTulaj() == null){
                                if(temp.getKassza() >= 1000){
                                    if(temp.getVehetE()){
                                        tempMezőIngatlan.setTulaj(temp);
                                        tempMezőIngatlan.setLépettMárRá(true);
                                        temp.ingatlantVesz(tempMezőIngatlan);
                                        System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                        temp.setVehetE(false);
                                    }
                                    else{
                                        temp.setVehetE(true);
                                        System.out.println("Nem vehette meg!");
                                    }
                                }
                            }
                            else if(tempMezőIngatlan.getTulaj() == temp && temp.getVehetE()){
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    if(temp.getKassza() >= 4000 && temp.getVehetE()){
                                        tempMezőIngatlan.setVanHáz(true);
                                        System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                    }
                                }
                                else{
                                    System.out.println("Saját, van már rajta ház.");
                                }
                            }
                            else{
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                    System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                                else{
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                    System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                            }
                        }
                        else if("Szolgáltatás".equals(this.getPálya().getMezők().get(hova).getType())){
                            temp.költ(tempMezőSzolgáltatás.getBírság());
                            System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                        }
                        else if("Szerencse".equals(this.getPálya().getMezők().get(hova).getType())){
                            temp.nyer(tempMezőSzerencse.getNyeremény());
                            System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                        }
                    }
                    default -> {}
                    
                    }
                if(this.getJátékosok().get(jelenlegiJátékos).getKassza() < 0){
                    for(int o = 0; o < this.getJátékosok().get(jelenlegiJátékos).getIngatlanok().size(); o++){
                        this.getJátékosok().get(jelenlegiJátékos).getIngatlanok().get(o).setTulaj(null);
                    }
                    vesztesek.add(this.getJátékosok().get(jelenlegiJátékos));
                    this.getJátékosok().remove(this.getJátékosok().get(jelenlegiJátékos));
                }
                if(this.getJátékosok().size() < 2){
                    egyMaradt = true;
                }
                if(jelenlegiJátékos + 1 >= this.getJátékosok().size()){
                    jelenlegiJátékos = 0;
                }
                else{
                    jelenlegiJátékos++;
                }
                i++;
            }
            System.out.println(this.getJátékosok().get(0).getNév());
            for(int j = vesztesek.size() - 1; j != -1; j--){
                System.out.println(vesztesek.get(j).getNév());
            }
            if(vesztesek.size() > 1){
                System.out.println("\n\n\n\n\n\n\n\n\n\nA játékos amely másodszorra kiesik a játékból: " + vesztesek.get(1).getNév() + "\n\n\n");
            }else{
                System.out.println("\n\n\n\n\n\n\n\n\n\nA játékos amely másodszorra kiesik a játékból: " + vesztesek.get(0).getNév() + "\n\n\n");
            }
            
        }else{
            Boolean egyMaradt = false;
            int i = 0;
            while(!egyMaradt){
                int lépés;
                if(i == this.getDobások().size()){
                    i = 0;
                    lépés = this.getDobások().get(i);
                }
                else{
                    lépés = this.getDobások().get(i);   
                }
                System.out.println("\n\n"+ this.getJátékosok().get(jelenlegiJátékos).getNév() + " dobása: " + lépés);
                //Kocka dobása
                int holvan = this.getJátékosok().get(jelenlegiJátékos).getJelenlegiPozíció();
                int hova;
                if(holvan + lépés >= this.getPályaHossz()){
                    hova = (holvan + lépés) - this.getPályaHossz();
                    if("Óvatos".equals(this.getJátékosok().get(jelenlegiJátékos).getTaktika())){
                        Óvatos temp = (Óvatos)this.getJátékosok().get(jelenlegiJátékos);
                        temp.setMennyitKölthetAKörben(temp.getKassza() / 2);
                    }
                }
                else{
                    hova = holvan + lépés;
                }
                System.out.println(this.getJátékosok().get(jelenlegiJátékos).getNév() + " ide lép: " +
                                   hova + " (" + this.getPálya().getMezők().get(hova).getType() + ")");
                this.getJátékosok().get(jelenlegiJátékos).setJelenlegiPozíció(hova);
                Ingatlan tempMezőIngatlan = null;
                Szolgáltatás tempMezőSzolgáltatás = null;
                Szerencse tempMezőSzerencse = null;
                if(null != this.getPálya().getMezők().get(hova).getType())
                    switch (this.getPálya().getMezők().get(hova).getType()) {
                    case "Ingatlan" -> tempMezőIngatlan = (Ingatlan)this.getPálya().getMezők().get(hova);
                    case "Szolgáltatás" -> tempMezőSzolgáltatás = (Szolgáltatás)this.getPálya().getMezők().get(hova);
                    case "Szerencse" -> tempMezőSzerencse = (Szerencse)this.getPálya().getMezők().get(hova);
                    default -> {
                    }
                }
                if(null != this.getJátékosok().get(jelenlegiJátékos).getTaktika())
                    switch (this.getJátékosok().get(jelenlegiJátékos).getTaktika()) {
                    case "Mohó" -> {
                        Mohó temp = (Mohó)this.getJátékosok().get(jelenlegiJátékos);
                            switch (this.getPálya().getMezők().get(hova).getType()) {
                            case "Ingatlan" -> {
                                if(tempMezőIngatlan.getTulaj() == null){
                                    if(temp.getKassza() >= 1000){
                                        tempMezőIngatlan.setTulaj(temp);
                                        tempMezőIngatlan.setLépettMárRá(true);
                                        temp.ingatlantVesz(tempMezőIngatlan);
                                        System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                    }
                                }
                                else if(tempMezőIngatlan.getTulaj() == temp){
                                    if(tempMezőIngatlan.getVanHáz() == false){
                                        if(temp.getKassza() >= 4000){
                                            tempMezőIngatlan.setVanHáz(true);
                                            System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                        }
                                    }
                                    else{
                                        System.out.println("Saját, van már rajta ház.");
                                    }
                                }
                                else{
                                    if(tempMezőIngatlan.getVanHáz() == false){
                                        temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                        System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                                
                                    }
                                    else{
                                        temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                        System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                    }
                                }
                            }
                            case "Szolgáltatás" -> {
                                temp.költ(tempMezőSzolgáltatás.getBírság());
                                System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                            }
                            case "Szerencse" -> {
                                temp.nyer(tempMezőSzerencse.getNyeremény());
                                System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                            }
                        }
                    }

                    case "Óvatos" -> {
                        Óvatos temp = (Óvatos)this.getJátékosok().get(jelenlegiJátékos);
                        switch (this.getPálya().getMezők().get(hova).getType()) {
                        case "Ingatlan" -> {
                            if(tempMezőIngatlan.getTulaj() == null){
                                if(temp.getMennyitKölthetAKörben() >= 1000){
                                    tempMezőIngatlan.setTulaj(temp);
                                    tempMezőIngatlan.setLépettMárRá(true);
                                    temp.ingatlantVesz(tempMezőIngatlan);
                                    System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                }
                                System.out.println("a");
                            }
                            else if(tempMezőIngatlan.getTulaj() == temp){
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    if(tempMezőIngatlan.getLépettMárRá()) {
                                        if(temp.getMennyitKölthetAKörben() >= 4000){
                                            tempMezőIngatlan.setVanHáz(true);
                                            System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                        }
                                        else{
                                            System.out.println("Nem költhet ennyit!");
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Saját, van már rajta ház.");
                                }
                            }
                            else{
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                    System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                                else{
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                    System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                            }
                        }
                        case "Szolgáltatás" -> {
                            temp.költ(tempMezőSzolgáltatás.getBírság());
                            System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                        }
                        case "Szerencse" -> {
                            temp.nyer(tempMezőSzerencse.getNyeremény());
                            System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                        }
                    }         
                    }
                    case "Taktikus" -> {
                        Taktikus temp = (Taktikus)this.getJátékosok().get(jelenlegiJátékos);
                        if("Ingatlan".equals(this.getPálya().getMezők().get(hova).getType())){
                            if(tempMezőIngatlan.getTulaj() == null){
                                if(temp.getKassza() >= 1000){
                                    if(temp.getVehetE()){
                                        tempMezőIngatlan.setTulaj(temp);
                                        tempMezőIngatlan.setLépettMárRá(true);
                                        temp.ingatlantVesz(tempMezőIngatlan);
                                        System.out.println(temp.getNév() + " megvette, kasszája ennyire csökkent: " + temp.getKassza());
                                        temp.setVehetE(false);
                                    }
                                    else{
                                        temp.setVehetE(true);
                                        System.out.println("Nem vehette meg!");
                                    }
                                }
                            }
                            else if(tempMezőIngatlan.getTulaj() == temp && temp.getVehetE()){
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    if(temp.getKassza() >= 4000 && temp.getVehetE()){
                                        tempMezőIngatlan.setVanHáz(true);
                                        System.out.println(temp.getNév() + " vett rá házat, kasszája ennyire csökkent: " + temp.getKassza());
                                    }
                                }
                                else{
                                    System.out.println("Saját, van már rajta ház.");
                                }
                            }
                            else{
                                if(tempMezőIngatlan.getVanHáz() == false){
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 500);
                                    System.out.println(temp.getNév() + " fizet 500 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                                else{
                                    temp.fizet(tempMezőIngatlan.getTulaj(), 2000);
                                    System.out.println(temp.getNév() + " fizet 2000 Petákot " + tempMezőIngatlan.getTulaj().getNév() + "-nak/nek. " + temp.getNév() + " kasszája ennyire csökkent: " + temp.getKassza() + "\n" + tempMezőIngatlan.getTulaj().getNév() + " kasszája ennyire nő: " + tempMezőIngatlan.getTulaj().getKassza());
                                }
                            }
                        }
                        else if("Szolgáltatás".equals(this.getPálya().getMezők().get(hova).getType())){
                            temp.költ(tempMezőSzolgáltatás.getBírság());
                            System.out.println(temp.getNév() + " fizet " + tempMezőSzolgáltatás.getBírság() + " Petákot Szolgáltatásért, " + "kasszája ennyire csökken: " + temp.getKassza());
                        }
                        else if("Szerencse".equals(this.getPálya().getMezők().get(hova).getType())){
                            temp.nyer(tempMezőSzerencse.getNyeremény());
                            System.out.println(temp.getNév() + " kap " + tempMezőSzerencse.getNyeremény() + " Petákot Szerencséért, " + "kasszája ennyire nő: " + temp.getKassza());
                        }
                    }
                    default -> {}
                    
                    }
                if(this.getJátékosok().get(jelenlegiJátékos).getKassza() < 0){
                    for(int o = 0; o < this.getJátékosok().get(jelenlegiJátékos).getIngatlanok().size(); o++){
                        this.getJátékosok().get(jelenlegiJátékos).getIngatlanok().get(o).setTulaj(null);
                    }
                    vesztesek.add(this.getJátékosok().get(jelenlegiJátékos));
                    this.getJátékosok().remove(this.getJátékosok().get(jelenlegiJátékos));
                }
                if(this.getJátékosok().size() < 2){
                    egyMaradt = true;
                }
                if(jelenlegiJátékos + 1 >= this.getJátékosok().size()){
                    jelenlegiJátékos = 0;
                }
                else{
                    jelenlegiJátékos++;
                }
                i++;
            }
            System.out.println(this.getJátékosok().get(0).getNév());
            for(int j = vesztesek.size() - 1; j != -1; j--){
                System.out.println(vesztesek.get(j).getNév());
            }
            
            System.out.println("\n\nA játékos amely másodszorra kiesik a játékból: " + vesztesek.get(1));
        }
        }
    }

    public Pálya getPálya() {
        return pálya;
    }

    public void getJátékosokString(){
        for(int i = 0; i < this.játékosokSzáma; i++){
            System.out.print(this.játékosok.get(i).getNév() + " - " + this.játékosok.get(i).getTaktika() + "\n");
        }
    }
    
    public ArrayList<Játékos> getJátékosok() {
        return játékosok;
    }

    public int getJátékosokSzáma() {
        return játékosokSzáma;
    }

    public int getPályaHossz() {
        return pályaHossz;
    }

    public ArrayList<Integer> getDobások() {
        return dobások;
    }

    public Boolean getVanKocka() {
        return vanKocka;
    }

    public void setVanKocka(Boolean vanKocka) {
        this.vanKocka = vanKocka;
    }
    
    public void setKockaNull(){
        this.vanKocka = null;
    }
    
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-+]?\\d*\\.?\\d+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
