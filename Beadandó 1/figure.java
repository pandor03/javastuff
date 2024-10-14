public abstract class figure {
    protected double x;
    protected double y;

    public figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean is_point_in_it(double x, double y);
}