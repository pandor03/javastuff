public class hexagon extends figure{
    double side;

    public hexagon(double x, double y, double side){
        super(x, y);
        this.side = side;
    }

    @Override
    public boolean is_point_in_it(double x, double y) {
        double radius = this.side; // A hatszög oldala megegyezik a sugarával
        double distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));

        if (distance > radius) {
            return false;
        }

        double angle = Math.atan2(y - this.y, x - this.x);
        double normalizedAngle = Math.abs(angle % (Math.PI / 3));

        return (distance * Math.cos(normalizedAngle)) <= this.side;
    }
}