/*
Name: Camron Cisneros
Section: U01
PID: 6187231
 */
import java.awt.*;
import javax.swing.*;

public class GraphDisplay extends JPanel {
    private final int VERTEX_RADIUS = 30;
   // private final int LABEL_OFFSET = 15;

    private Graph graph;
    private int[] route; // array of vertex indices representing the route

    public GraphDisplay(Graph g, int[] route) {
        graph = g;
        this.route = route;
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int distance = graph.shortestDistance(graph.s,graph.t);
        g.drawString("Path from " + graph.s + " to " + graph.t,10, 70);
        g.drawString("Distance = " + distance,10,80);
        g.drawString("Green = Visited", 10,40);
        g.drawString("Yellow = Unvisited", 10,50);
        // Draw vertices
        int n = graph.verticesNumber;
        double angle = 2 * Math.PI / n;
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        for (int i = 0; i < n; i++) {
            int x = (int) (cx + 200 * Math.cos(i * angle));
            int y = (int) (cy + 200 * Math.sin(i * angle));
            if (containsVertex(route, i)) {
                g.setColor(Color.GREEN); // highlight vertices in the route
            } else {
                g.setColor(Color.YELLOW);
            }
            g.fillOval(x - VERTEX_RADIUS, y - VERTEX_RADIUS, 2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);
            g.setColor(Color.BLACK);
            g.drawOval(x - VERTEX_RADIUS, y - VERTEX_RADIUS, 2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);
            g.drawString(Integer.toString(i), x , y );
        }

        // Draw edges
        for (int i = 0; i < n; i++) {
            for (int j : graph.findAdjacencyVertices(i)) {
                if (j > i) { // draw each edge only once
                    int xi = (int) (cx + 200 * Math.cos(i * angle));
                    int yi = (int) (cy + 200 * Math.sin(i * angle));
                    int xj = (int) (cx + 200 * Math.cos(j * angle));
                    int yj = (int) (cy + 200 * Math.sin(j * angle));
                    int weight = graph.matrix[i][j];
                    int xm = (xi + xj) / 2;
                    int ym = (yi + yj) / 2;
                    if (containsEdge(route, i, j)) {
                        g.setColor(Color.GREEN); // highlight edges in the route
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.drawLine(xi, yi, xj, yj);
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(weight), xm , ym );
                }
            }
        }
    }

    // Helper method to check if a vertex is in the given route
    private boolean containsVertex(int[] route, int v) {
        for (int i = 0; i < route.length; i++) {
            if (route[i] == v) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if an edge is in the given route
    private boolean containsEdge(int[] route, int u, int v) {
        for (int i = 0; i < route.length - 1; i++) {
            if (route[i] == u && route[i+1] == v || route[i] == v && route[i+1] == u) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Graph g = new Graph("input.txt");
        int s = g.s, t = g.t;
        int[] p = new int[g.verticesNumber];
        int[] d = new int[g.verticesNumber];
        g.allShortestPaths(p,d,s);
        int[] path = g.getPath(s,t,p);
        GraphDisplay display = new GraphDisplay(g,path);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
    }
}