import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;

/**
 * Class for Clustering
 * @author barbara.lopes
 *
 */
public class Cluster {

    /**
     * Read file
     * @param filename
     * @return
     */
    public static BufferedReader readDataFile(String filename) {
        BufferedReader datafile = null;

        try {
            datafile = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }

        return datafile;
    }

    /**
     * Class for Clustering using K-Means
     * @param pathArff
     * @throws Exception
     */
    public static void runKMeans(String pathArff) throws Exception {
        BufferedReader datafile = null;

        datafile = readDataFile(pathArff);


        SimpleKMeans kmeans = new SimpleKMeans();

        kmeans.setSeed(7);

        //important parameter to set: preserver order, number of cluster.
        kmeans.setPreserveInstancesOrder(true);
        kmeans.setNumClusters(10);

        Instances data = new Instances(datafile);
        DistanceFunction m_DistanceFunction = new EuclideanDistance();//new ManhattanDistance();

        kmeans.setDistanceFunction(m_DistanceFunction);

        // kmeans.setMaxIterations(data.size()*10);

        kmeans.buildClusterer(data);

        // This array returns the cluster number (starting with 0) for each instance
        // The array has as many elements as the number of instances
        int[] assignments = kmeans.getAssignments();

        HashMap<Integer, List<Integer>> mapa = new HashMap<Integer, List<Integer>>();

        int i=0;
        for(int clusterNum : assignments) {
            if(mapa.containsKey(clusterNum)){
                mapa.get(clusterNum).add(i);
            }
            else{
                List<Integer>values = new ArrayList<Integer>();
                values.add(i);
                mapa.put(clusterNum, values);
            }
            System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
            i++;
        }

        Graph graph = new Graph(data.size());

        /**
         * Salva clusters como grafo
         */
        for(List<Integer> values: mapa.values()){
            for(int value: values){
                for(int value2: values){
                    if(value2 != value){
                        graph.addEdge(value, value2);
                    }
                }
            }
        }

        graph.saveGraphOut("out2");
        // GenerateDOT generator = new GenerateDOT();
        // generator.generateDOT(graph.getAdjcMatrix());
        
        /**
         * Salva clusters gerados
         */
        saveClusters(mapa);

    }

    public static void saveClusters(HashMap<Integer, List<Integer>> mapa) throws IOException{

        String dir = System.getProperty("user.dir");
        
        String pathOut = dir+"\\clusters2.csv";
        
        GenerateCsv csvGenerator = new GenerateCsv();
        csvGenerator.generateCsvMap(pathOut, mapa);
        System.out.println("Arquivo "+pathOut+" salvo com sucesso!!!");
    }
    
    public static void main(String[] args) throws Exception {
    	long tempoInicial = System.currentTimeMillis();
        /** 
         * Descomentar se não for executar via .ssh
         */
        args = new String[1];
        args[0] = "dataset1.arff";
        
        String dir = System.getProperty("user.dir");
        Scanner s = new Scanner(System.in);

        if(args.length == 1){
            if(!args[0].contains(".arff")){
                System.out.println("Deve ser informado um arquivo *.arff");
            }
            else{
                String pathIn = dir+"\\"+args[0];
                Cluster.runKMeans(pathIn);
                s.close();
                long tempoFinal = System.currentTimeMillis();
                System.out.println( tempoFinal - tempoInicial );
            }

        }
        else{
            System.out.println("Parâmetros Incorretos!");
        }
    }
}