import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    private HashMap<Double[],String>   data;
    private String[]    resOptions;
    private double[]    wieghts;
    private double      learningConstant;
    private double      threshold;

    public Perceptron(double learningConstant, double threshold) throws IOException {
        this.learningConstant   = learningConstant;
        this.threshold          = threshold;
    }

    public void train(String path, double accuracyGoal){
        double accuracy = 0.0;
        int goodAnswares;

        try (Scanner scanner = new Scanner(new File(path))){
            data        = new HashMap<>();
            resOptions  = new String[2];
            String[] tmp;

            //importing data
            while(scanner.hasNext()){
                tmp = scanner.nextLine().split(",");
                Double[] tmpArr = new Double[tmp.length - 1];

                for (int i = 0; i < tmp.length - 1; i++)
                    tmpArr[i] = Double.parseDouble(tmp[i]);

                data.put(tmpArr, tmp[tmp.length - 1]);

                //result options tab init
                if (resOptions[0] == null)
                    resOptions[0] = tmp[tmp.length - 1];
                else if (resOptions[1] == null && !tmp[tmp.length - 1].equals(resOptions[0]))
                    resOptions[1] = tmp[tmp.length - 1];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //wieghts init
        wieghts = new double[data.get(data.keySet().toArray()[0]).length()];
        Random r = new Random();
        for (int i = 0; i < wieghts.length; i++)
            wieghts[i] = r.nextDouble();

        //learning
        int i = 1;
        while(accuracy != accuracyGoal){
            goodAnswares = learn();
            accuracy = goodAnswares / (double) data.size();
            System.out.println("Przejscie " + i + " dokladnosc - " + (double) (Math.round(accuracy * 10000) / 100) + "%");
        }
    }

    private int learn(){
        int goodAnswares = 0;

        for (Double[] tab : data.keySet()){
            int res             = process(tab);
            int expectedRes     = (data.get(tab).equals(resOptions[0])) ? 0 : 1;

            if (res == expectedRes)
                goodAnswares++;

            for (int i = 0; i < wieghts.length; i++){
                wieghts[i] += (res - expectedRes) * learningConstant * tab[i];
            }
        }

        return goodAnswares;
    }

    private int process(Double[] data){
        double sum = 0;

        for (int i = 0; i < wieghts.length; i++)
            sum += wieghts[i] * data[i];

        if (sum > threshold)
            return 1;
        else
            return 0;
    }
}
