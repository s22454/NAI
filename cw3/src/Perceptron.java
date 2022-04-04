import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    private int[]       data;
    private double[]    wieghts;
    private double      learningConstant;
    private double      threshold;

    public Perceptron(double learningConstant, double threshold) throws IOException {
        this.learningConstant   = learningConstant;
        this.threshold          = threshold;
    }

    public void dataInit(String path){

        //data tab init
        data = new int[26];
        for (int i = 0; i < 26; i++)
            data[i] = 0;

        //process file
        try (Scanner scanner = new Scanner(new File(path))){
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine().toLowerCase(Locale.ROOT);

                for (char c : line.toCharArray())
                    if (c > 96 && c < 123)
                        data[c - 97]++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void train() throws ArrayIndexOutOfBoundsException{

        //wieghts init
        wieghts = new double[26];
        Random r = new Random();
        for (int i = 0; i < wieghts.length; i++)
            wieghts[i] = r.nextDouble();

        //learning
        int i = 0;
        int goodAnsware = 0;
        while(goodAnsware < 10){ //10 times in a row it has to get a good answare

            //call an exception if it has repeted more ten 1000000 times
            if (i > 1000000)
                throw new IllegalStateException();

            //modify goodansware if res was good or bring it to 0 if not
            goodAnsware = (train2()) ? goodAnsware + 1 : 0;

            System.out.println("Przejscie " + i + " ilosc poprawnych pod rzad: " + goodAnsware);
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
            System.out.println((char) (j + 97) + " - " + wieghts[j]);
        System.out.println("Ilosc przejsc potrzebna do nauczenia: " + (i - 1));
        System.out.println("---------------------------------------------");
    }

    private boolean train2(){
        int res = process();

        for (int i = 0; i < wieghts.length; i++){
            wieghts[i] += ((1 - res) * learningConstant * data[i]);
        }

        return res == 1;
    }

    public int process() throws NullPointerException{
        double sum = 0;

        for (int i = 0; i < 26; i++)
            sum += wieghts[i] * data[i];

        if (sum > threshold)
            return 1;
        else
            return 0;
    }

    public boolean test(String path) throws NullPointerException{
        dataInit(path);
        return process() == 1;
    }
}
