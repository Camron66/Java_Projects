
/**
 * Represents an (x, y) point.
 *
 * @author Camron Cisneros, 6187231, COP4534 U01 */
public class Point
{
    public int x;
    public int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point point) {
        int dx = x - point.x;
        int dy = y - point.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
