import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class to generate *.csv file from int[][]matrix
 * @author barbara.lopes
 *
 */
public class GenerateCsv
{
    
    public void generateCsvMap(String sFileName, HashMap<Integer, List<Integer>> mapa) 
            throws IOException{
        FileWriter writer = new FileWriter(sFileName);
        for(List<Integer> genes: mapa.values()){
            for(int i: genes){
                writer.append(i+"");
                writer.append(';');
            }
            writer.append("\n");
            writer.flush();
        }
        writer.close();
    }
    
    /**
     * Save matrix on csv file
     * @param sFileName
     * @param matrix
     */
    public void generateCsvFileMatrix(String sFileName, int[][] matrix)
    {
        try
        {
            int i = 0;

            FileWriter writer = new FileWriter(sFileName);

            writer.append(';');
            for(i=0; i < matrix.length - 1; i++){
                writer.append(i+"");
                writer.append(';');
            }

            writer.append(i+"");
            writer.append('\n');

            int j = 0;

            for(i = 0; i < matrix.length; i++)
            {
                writer.append(i+"");
                writer.append(';');
                for (j=0; j<matrix.length - 1; j++)
                {
                    writer.append(String.valueOf(matrix[i][j]));
                    writer.append(';');
                }
                writer.append(String.valueOf(matrix[i][j]));
                writer.append('\n');
                writer.flush();
            }
            writer.close();
        }        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}