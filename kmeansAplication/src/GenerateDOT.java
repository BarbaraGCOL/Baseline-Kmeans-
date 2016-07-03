import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class to generate *.dot file from int[][]matrix
 * @author barbara.lopes
 *
 */
public class GenerateDOT {

    /**
     * Save matrix on DOT file (Graphviz)
     * @param matrix
     * @throws FileNotFoundException
     */
    public void generateDOT(int[][]matrix) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter("RGR.dot");
        writer.println("graph RGR");
        writer.println("{");
        for(int i=0;i<matrix.length;i++){
            writer.print(i);
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j] == 1){
                    writer.print("--");
                    writer.print(j);
                }
            }
            writer.println("");
        }
        writer.println("}");
        writer.close();
    }
}
