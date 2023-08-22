/*
Name: Camron Cisneros
Section: U01
PID: 6187231
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Graph implements GraphInterface {
    public int verticesNumber;
    public int[][] matrix; //adjacency matrix
    public int s, t; //start and target

    public Graph() {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(int n) {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Instantiates a graph and initializes it with info from a text file.
     *
     * @param filename text file with graph info
     */
    public Graph(String filename) {
        File input = new File(filename);

        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println("" + e);
            System.out.println();
            System.exit(0);
        }

        while (in.hasNextLine()) {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];

            for (int i = 0; i < verticesNumber; i++) {
                for (int j = 0; j < verticesNumber; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
            s = in.nextInt();
            t = in.nextInt();
        }
        in.close();
    }

    /**
     * Finds vertices adjacent to a given vertex.
     *
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array;
     * size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v) {
        int[] vert = new int[verticesNumber];
        int total = 0;

        for (int i = 0; i < verticesNumber; i++) {
            if (matrix[v][i] != 0) {
                vert[total] = i;
                total++;
            }
        }

        return Arrays.copyOf(vert, total);
    }
    /**
     Returns smallest element in given array d, out of those that
     have not been visited
     Uses Dijkstra's Algorithm
     @param visited visited elements
     @param distance array of distances
     @return index of smallest element in d
     */

    private int minDistance(boolean[] visited, int[] distance){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verticesNumber; i++){
            if(!visited[i]){
                if(distance[i] <= min){
                    min = distance[i];
                    index = i;
                }
            }
        }
        return index;
    }
    /**
     Calculates the shortest paths from a given vertex to all vertices.
     Uses Dijkstra's Algorithm
     @param p paths (p[i] contains previous vertex in the shortest path from v)
     @param d distances (d[i] contains the shortest distance from v)
     @param v given vertex
     */
    public void allShortestPaths(int[] p, int[] d, int v){
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++){
            visited[i] = false;            // Not Yet Visited
            p[i] = -1;                     // Previous vertex UNK
            d[i] = Integer.MAX_VALUE;      // p[i] = INFINITY
        }
        d[v] = 0;
        for(int i = 0; i < verticesNumber-1; i++){
            int w = minDistance(visited, d);
            visited[w] = true;

            int[] adj = findAdjacencyVertices(w);
            for( int u : adj){
                if(!visited[u]){
                    if(d[w] + matrix[w][u] < d[u]){
                        d[u] = d[w] + matrix[w][u];
                        p[u] = w;
                    }
                }
            }
        }
    }
    /**
     * Calculates distance of shortest path between two vertices using Dijkstra's algorithm.
     *
     * @param s source vertex
     * @param t target vertex
     * @return distance of shortest path between s and t
     */
    public int shortestDistance(int s, int t){
        int[] distance = new int[verticesNumber]; //array of distances from source to each vertex
        boolean[] visited = new boolean[verticesNumber]; //visited[i] = true if vertex i is visited
        int[] prev = new int[verticesNumber]; //prev[i] = previous vertex in the shortest path from s to i

        //initialize distance array and visited array
        for(int i = 0; i < verticesNumber; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        //distance to source vertex is 0
        distance[s] = 0;

        //calculate shortest path
        for(int i = 0; i < verticesNumber-1; i++){
            int u = minDistance(visited, distance);
            visited[u] = true;
            int[] adj = findAdjacencyVertices(u);
            for(int v: adj){
                int alt = distance[u] + matrix[u][v];
                if(alt < distance[v]){
                    distance[v] = alt;
                    prev[v] = u;
                }
            }
        }

        return distance[t];
    }
    /**
     * Returns shortest path between given source and target vertices
     *
     * @param s source vertex
     * @param t target vertex
     * @param p paths (p[total] contains previous vertex in the shortest
     *        path from source vertex)
     * @return shortest path stored in array; s is the first and t the last
     */
    public int[] getPath(int s, int t, int[] p){
        int[] shortestPath = new int[p.length];

        int current = t;
        int total = 0;
        while(current != s){
            shortestPath[total] = current;
            current = p[current];
            total++;
        }
        shortestPath[total++] = s;
        shortestPath = Arrays.copyOf(shortestPath,total);

        //reverses array
        for( int i = 0; i< total/2; i++){
            int temp = shortestPath[i];
            shortestPath[i] = shortestPath[total - 1 - i];
            shortestPath[total - 1 - i] = temp;
        }
        return shortestPath;
    }
    public static void main(String[] args) {
        //Read input file
        String filename = "input.txt";
        Graph graph = new Graph(filename);
        int s = graph.s, t = graph.t;
        //Calculate shortest path Distance
        int distance = graph.shortestDistance(s, t);
        System.out.println("Shortest distance from  " + s + " to " + t + ": " + distance);

        //Get shortest path
        int[] p = new int[graph.verticesNumber];
        int[] d = new int[graph.verticesNumber];
        graph.allShortestPaths(p,d,s);
        int[] path = graph.getPath(s,t,p);
        System.out.println(Arrays.toString(path));
        System.out.print("Shortest path: ");
        for(int i = 0; i < path.length - 1; i++){
            System.out.print(path[i] + " -> ");
        }
        System.out.println(path[path.length-1]);


    }
}
