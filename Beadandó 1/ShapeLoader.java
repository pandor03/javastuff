import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeLoader {

    public static List<figure> loadShapesFromFile(String filename) throws IOException {
        List<figure> shapes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int numberOfShapes = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfShapes; i++) {
            String[] parts = reader.readLine().split(" ");
            String type = parts[0];
            double centerX = Double.parseDouble(parts[1]);
            double centerY = Double.parseDouble(parts[2]);
            double size = Double.parseDouble(parts[3]);

            switch (type) {
                case "CIRCLE":
                    shapes.add(new circle(centerX, centerY, size));
                    break;
                case "SQUARE":
                    shapes.add(new square(centerX, centerY, size));
                    break;
                case "TRIANGLE":
                    shapes.add(new triangle(centerX, centerY, size));
                    break;
                case "HEXAGON":
                    shapes.add(new hexagon(centerX, centerY, size));
                    break;
            }
        }

        reader.close();
        return shapes;
    }
}
