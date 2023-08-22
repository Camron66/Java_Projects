import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * Defines the panel the drawings will be made in.
 *
 * @author Camron Cisneros, 6187231, COP4534 U01 */
public class GraphDisplay extends JPanel
{
    ArrayList<Point> points;
    double localSearchCost;
    int[] localSearchRoute;

    public GraphDisplay(ArrayList<Point> points,
                        double localSearchCost,
                        int[] localSearchRoute)
    {
        this.points = points;
        this.localSearchCost = localSearchCost;
        this.localSearchRoute = localSearchRoute;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        // Draw the points
        g.setColor(Color.BLUE);
        for (Point point : points) {
            g.fillOval(point.x - 5, point.y - 5, 10, 10);
        }

        // Draw the edges of the shortest path
        g.setColor(Color.RED);
        for (int i = 0; i < localSearchRoute.length - 1; i++) {
            Point from = points.get(localSearchRoute[i]);
            Point to = points.get(localSearchRoute[i + 1]);
            g.drawLine(from.x, from.y, to.x, to.y);
        }

        // Draw the edge connecting the last and first point
        Point from = points.get(localSearchRoute[localSearchRoute.length - 1]);
        Point to = points.get(localSearchRoute[0]);
        g.drawLine(from.x, from.y, to.x, to.y);

        // Display the cost of the shortest path
        g.setColor(Color.BLACK);
        g.drawString("Shortest Path Cost: " + localSearchCost, 10, 20);
    }
}
