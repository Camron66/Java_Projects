import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Project template to be used as a framework for your solution.
 *
 * @author Camron Cisneros, 6187231, COP4534 U01
 * */
public class Project3
{
    public static void main(String[] args)
    {
        new Project3();
    }

    /**
     * Reads input file with points, instantiates graph, obtain shortest route,
     * and graphs it.
     */
    public Project3()
    {
        // read filename
        Scanner in = new Scanner(System.in);
        System.out.print("Name of file: ");
        String filename = in.nextLine();
        in.close();
        // get points from file
        ArrayList<Point> points = getPointsFromFile(filename);

        if (points.isEmpty()) {
            System.out.println("No points were found in the input file.");
            return;
        }

        // obtain adjacency matrix and define graph with it
        int[][] matrix = generateMatrix(points);
        Graph g = new Graph(matrix);

        // compute solution to problem
        int[] localSearchRoute = new int[points.size()];
        double localSearchCost = g.TSP_localSearch(localSearchRoute);

        FrameDisplay frame = new FrameDisplay(points, localSearchCost, localSearchRoute);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Reads points from given input file and returns them in an array list.
     *
     * @param filename name of input file
     * @return array list of points
     */
    public ArrayList<Point> getPointsFromFile(String filename)
    {
        ArrayList<Point> points = new ArrayList<Point>();
        try {
            Scanner input = new Scanner(new File(filename));
            int n = input.nextInt();
            while(input.hasNext()){
                for (int i = 0; i < n; i++) {
                    int x = input.nextInt();
                    int y = input.nextInt();
                    points.add(new Point(x, y));
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return points;    }

    /**
     * Generates a square matrix from the given array list of points.
     * Cell [i][j] of the matrix will contain the distance between points[i] and
     * points[j].
     *
     * @param points array list of points
     * @return matrix of pairwise distances
     */
    public int[][] generateMatrix(ArrayList<Point> points)
    {
        int[][] matrix = new int[points.size()][points.size()];
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                matrix[i][j] = (int) Math.round(points.get(i).distanceTo(points.get(j)));
            }
        }
        return matrix;
    }
}