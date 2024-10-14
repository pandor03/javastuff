public class square extends figure{
    double side;

    public square(double x, double y, double side){
        super(x, y);
        this.side = side;
    }

    @Override
    public boolean is_point_in_it(double x, double y) {
        double halfSide = this.side / 2;
        return x >= this.x - halfSide && x <= this.x + halfSide &&
               y >= this.y - halfSide && y <= this.y + halfSide;
    }
}