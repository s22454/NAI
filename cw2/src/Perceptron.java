import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    private HashMap<Double[],String> data;
    private String[]    resOptions;
    private double[]    wieghts;
    private double      learningConstant;
    private double      threshold;

    public Perceptron(double learningConstant, double threshold) throws IOException {
        this.learningConstant   = learningConstant;
        this.threshold          = threshold;
    }

    public String interpretation(int i) { return resOptions[i]; }

    public int dataInit(String path){
        int vectorLength = 0;

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

                if (vectorLength == 0)
                    vectorLength = tmpArr.length;

                //result options tab init
                if (resOptions[0] == null)
                    resOptions[0] = tmp[tmp.length - 1];
                else if (resOptions[1] == null && !tmp[tmp.length - 1].equals(resOptions[0]))
                    resOptions[1] = tmp[tmp.length - 1];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return vectorLength;
    }

    public void train(String path, double accuracyGoal) throws ArrayIndexOutOfBoundsException{
        double accuracy = 0.0;
        double goodAnswares;

        //initialize data
        int vectorLength = dataInit(path);

        //wieghts init
        wieghts = new double[vectorLength];
        Random r = new Random();
        for (int i = 0; i < wieghts.length; i++)
            wieghts[i] = r.nextDouble();

        //learning
        int i = 1;
        while(accuracy < accuracyGoal){
            if (i > 1000000)
                throw new IllegalStateException();
            goodAnswares = train();
            accuracy = goodAnswares / (double) data.size();
            System.out.println("Przejscie " + i + " dokladnosc: " + (Math.round(accuracy * 10000.0) / 100.0) + "%");
            i++;
        }

        //show perceptron data on console
        System.out.println("\n---------------------------------------------");
        System.out.println("UDALO SIE WYTRENOWAC PERCEPTRON");
        System.out.println("---------------------------------------------");
        System.out.println("Stala uczenia sie: " + learningConstant);
        System.out.println("Prog aktywacji: " + threshold);
        System.out.println("Wartosci wag: ");
        for (int j = 0; j < wieghts.length; j++)
            System.out.println("W" + (j + 1) + " - " + wieghts[j]);
        System.out.println("Finalna dokladnosc: " + (Math.round(accuracy * 10000.0) / 100.0) + "%");
        System.out.println("Ilosc przejsc potrzebna do nauczenia: " + (i - 1));
        System.out.println("---------------------------------------------");
    }

    private int train(){
        int goodAnswares = 0;

        for (Double[] tab : data.keySet()){
            int res             = process(tab);
            int expectedRes     = (data.get(tab).equals(resOptions[0])) ? 0 : 1;

            if (res == expectedRes)
                goodAnswares++;

            for (int i = 0; i < wieghts.length; i++){
                wieghts[i] += ((expectedRes - res) * learningConstant * tab[i]);
            }
        }

        return goodAnswares;
    }

    public int process(Double[] data) throws NullPointerException{
        double sum = 0;

        if (data.length != wieghts.length)
            throw new NullPointerException();

        for (int i = 0; i < wieghts.length; i++)
            sum += wieghts[i] * data[i];

        if (sum > threshold)
            return 1;
        else
            return 0;
    }

    public void test(String path) throws NullPointerException{
        double goodAnswares = 0.0;

        int newDataVectorLength = dataInit(path);

        if (newDataVectorLength != wieghts.length)
                    throw new NullPointerException();

        for (Double[] tab : data.keySet()){
            int ans = process(tab);
            if (resOptions[ans].equals(data.get(tab))) goodAnswares++;

            System.out.print("Testowanie: ");
            for (Double d : tab)
                System.out.print(d + " ");
            System.out.print(data.get(tab));
            System.out.println("\nOdpowiedz poprawna: " + data.get(tab));
            System.out.println("Odpowiedz perceptronu: " + resOptions[ans]);
            System.out.println("Czy byla poprawna: " + ((resOptions[ans].equals(data.get(tab))) ? "TAK" : "NIE"));
            System.out.println("---------------------------------------------");
        }

        System.out.println("Finalna dokladnosc: " + (Math.round((goodAnswares / (double) data.size()) * 10000.0) / 100.0) + "%");
        System.out.println("---------------------------------------------");
    }
}
