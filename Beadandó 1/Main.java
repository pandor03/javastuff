import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // A fájl neve
            String filename = "figures.txt";

            // A rögzített pont
            double pointX = 2.0;
            double pointY = 3.0;

            // Síkidomok betöltése
            List<figure> shapes = ShapeLoader.loadShapesFromFile(filename);

            // Megszámoljuk, hány síkidom tartalmazza a pontot
            int count = ShapeCounter.countShapesContainingPoint(shapes, pointX, pointY);

            // Eredmény kiírása
            System.out.println("A pontot tartalmazo sikidomok szama: " + count);
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasása közben: " + e.getMessage());
        }
    }
}
