public class circle extends figure{
    double radius;

    public circle(double x, double y, double radius){
        super(x, y);
        this.radius = radius;
    }

    @Override
    public boolean is_point_in_it(double x, double y){
        double distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        return this.radius >= distance;
    }
}