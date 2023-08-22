//* @author Camron Cisneros, 6187231, COP4534 U01
public interface GraphInterface
{
    public void addEdge(int v, int w, int weight);
    public void removeEdge(int v, int w);
    public int[] findAdjacencyVertices(int v);
    public String toString();
}