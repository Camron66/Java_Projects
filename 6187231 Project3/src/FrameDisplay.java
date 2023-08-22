import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Defines a frame to which a panel with drawings will be added.
 *
 * @author Camron Cisneros, 6187231, COP4534 U01
 */
public class FrameDisplay extends JFrame
{
    int WIDTH = 450;
    int HEIGHT = 450;

    public FrameDisplay(ArrayList<Point> points,
                        double localSearchCost,
                        int[] localSearchRoute)
    {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay graphDisplay = new GraphDisplay(points, localSearchCost, localSearchRoute);
        JPanel costDisplay = new JPanel();
        costDisplay.add(new JLabel("Shortest Path Cost: " + localSearchCost));

        Container contentPane = getContentPane();
        contentPane.add(graphDisplay, BorderLayout.CENTER);
        contentPane.add(costDisplay, BorderLayout.SOUTH);
    }
}
