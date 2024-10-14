import java.io.*;
import java.util.List;

public class ShapeCounter {

    public static int countShapesContainingPoint(List<figure> shapes, double pointX, double pointY) {
        int count = 0;
        for (figure shape : shapes) {
            if (shape.is_point_in_it(pointX, pointY)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try {
            // A fájl neve
            String filename = "shapes.txt";

            // A rögzített pont
            double pointX = 2.0;
            double pointY = 3.0;

            // Síkidomok betöltése
            List<figure> shapes = ShapeLoader.loadShapesFromFile(filename);

            // Megszámoljuk, hány síkidom tartalmazza a pontot
            int count = countShapesContainingPoint(shapes, pointX, pointY);

            // Eredmény kiírása
            System.out.println("A pontot tartalmazó síkidomok száma: " + count);
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasása közben: " + e.getMessage());
        }
    }
}
