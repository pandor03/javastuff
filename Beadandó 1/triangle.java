public class triangle extends figure{
    double side;

    public triangle(double x, double y, double side){
        super(x, y);
        this.side = side;
    }

    @Override
    public boolean is_point_in_it(double x, double y) {
        double height = Math.sqrt(3) / 2 * side;
        
        double x1 = this.x - this.side / 2;
        double x2 = this.x + this.side / 2;
        double y1 = this.y - height / 3;
        double y2 = this.y - height / 3;
        double x3 = this.x;
        double y3 = this.y + 2 * height / 3;

        double denominator = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);
        double a = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / denominator;
        double b = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / denominator;
        double c = 1 - a - b;

        return a >= 0 && b >= 0 && c >= 0;
    }
}