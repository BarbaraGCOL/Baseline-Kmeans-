import java.io.IOException;

/**
 * Class to represent Graph (adjacency matrix)
 * @author barbara.lopes
 *
 */
public class Graph
{
    private int[][] adjMatrix;
    private int vertices;
    private int edges;                   

    /**
     *  Constructs a graph with n vertices and no edges.
     */
    public Graph(int n) {
        vertices = n;
        edges = 0;
        adjMatrix = new int[n][n];
    }

    /**
     *  Returns the number of vertices.
     *  @return this graph's vertex count.
     */
    public int getNumVertices() {
        return vertices;
    }

    /**
     *  Returns the number of edges.
     *  @return this graph's edge count.
     */
    public int getNumEdges() {
        return edges;
    }

    public int[][]getAdjcMatrix(){
        return adjMatrix;
    }
    
    /**
     *  Returns true if v is a valid vertex number; false otherwise.
     *  @param v the vertex.
     *  @return boolean indicating existence of vertex number v.
     */
    public boolean validVertex(int v) {
        return (v >= 0) && (v < vertices);
    }

    /**
     *  Returns true if edge (origin, destination) exists; false otherwise.
     *  @param origin the origin vertex.
     *  @param destination the destination vertex.
     *  @return boolean indicating the presence of edge (origin, destination).
     */
    public int hasEdge(int origin, int destination) {
        if (validVertex(origin) && validVertex(destination)) {
            return adjMatrix[origin][destination];
        } else {
            return 0;
        }
    }

    /**
     *  Creates the edge (origin, destination).  If the edge did not already
     *    exists, increments the edge count.
     *  @param origin the origin vertex.
     *  @param edstination the destination vertex.
     */
    public void addEdge(int origin, int destination) {
        if (validVertex(origin) && validVertex(destination)) {
            adjMatrix[origin][destination] = 1;
            edges++;
        }
    }
    
    /**
     *  Deletes the edge (origin, destination).  If the edge existed, decrements
     *    the edge count.
     *  @param origin the origin vertex.
     *  @param destination the destination vertex.
     */
    public void removeEdge(int origin, int destination) {
        if (validVertex(origin) && validVertex(destination)) {
            if (adjMatrix[origin][destination] == 1) {
                adjMatrix[origin][destination] = 0;
                edges--;
            }
        }        
    }

    /**
     * Save Graph output on file *.csv
     * @param path - output file path
     * @param graph - Graph Class instance
     * @throws IOException
     */
    public void saveGraphOut(String path) throws IOException{

        String dir = System.getProperty("user.dir");
        
        String pathOut = dir+"\\"+path+".csv";
        
        GenerateCsv csvGenerator = new GenerateCsv();
        csvGenerator.generateCsvFileMatrix(pathOut, adjMatrix);
        System.out.println("Arquivo "+path+" salvo com sucesso!!!");
    }
}