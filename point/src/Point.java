/**
 * Клас Point — проста реалізація інтерфейсу Movable.
 */
public class Point implements Movable {
    private double x;
    private double y;

    /**
     * Конструктор за замовчуванням — точка у початку координат (0,0).
     */
    public Point() {
        this(0.0, 0.0);
    }

    /**
     * Конструктор з початковими координатами.
     *
     * @param x початкова X-координата
     * @param y початкова Y-координата
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point other = (Point) o;
        return Double.compare(other.x, x) == 0 &&
                Double.compare(other.y, y) == 0;
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(x);
        bits = 31 * bits + Double.doubleToLongBits(y);
        return (int) (bits ^ (bits >>> 32));
    }
}
