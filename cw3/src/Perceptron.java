import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    private double[]        data;
    private int             charCount;
    private int             generations;
    private final double[]  weights;
    private final double    learningConstant;
    private final String    language;

    public Perceptron(double learningConstant, double threshold, String language){
        this.learningConstant   = learningConstant;
        this.language           = language;
        this.generations        = 0;

        //weights init
        weights = new double[27];
        weights[weights.length - 1] = threshold;
        Random r = new Random();
        for (int i = 0; i < weights.length - 1; i++)
            weights[i] = r.nextDouble();

    }

    public void show(){
        System.out.println("---------------------------------------------");
        System.out.println("UDALO SIE WYTRENOWAC PERCEPTRON");
        System.out.println("---------------------------------------------");
        System.out.println("Jezyk: " + language);
        System.out.println("Stala uczenia sie: " + learningConstant);
        System.out.println("Prog aktywacji: " + weights[weights.length - 1]);
        System.out.println("Wartosci wag: ");
        for (int j = 0; j < weights.length - 1; j++)
            System.out.println((char) (j + 97) + " - " + weights[j]);
        System.out.println("Ilosc przejsc potrzebna do nauczenia: " + generations);

    }

    public void dataInit(String path){

        //data tab init
        data        = new double[27];
        charCount   = 0;
        data[26]    = -1;
        for (int i = 0; i < 26; i++)
            data[i] = 0;


        //process file
        try (Scanner scanner = new Scanner(new File(path))){
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine().toLowerCase(Locale.ROOT);

                for (char c : line.toCharArray())
                    if (c > 96 && c < 123) {
                        data[c - 97]++;
                        charCount++;
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //changing data from counting table to percentage table
        for (int i = 0; i < 26; i++)
            data[i] /= charCount;
    }

    public void userDataInit(String line){
        //data tab init
        data        = new double[27];
        charCount   = 0;
        data[26]    = -1;
        for (int i = 0; i < 26; i++)
            data[i] = 0;

        //process line
        line = line.toLowerCase(Locale.ROOT);

        for (char c : line.toCharArray())
            if (c > 96 && c < 123) {
                data[c - 97]++;
                charCount++;
            }

        //changing data from counting table to percentage table
        for (int i = 0; i < 26; i++)
            data[i] /= charCount;
    }

    public void train(boolean correct) throws ArrayIndexOutOfBoundsException{

        //learning
        int i = 0;
        int goodAnsware = 0;
        while(goodAnsware != 1){

            //call an exception if it has repeted more ten 1000000 times
            if (i > 1000000)
                throw new IllegalStateException();

            //modify goodansware if res was good
            goodAnsware = (train2(correct)) ? 1 : 0;

            i++;
            generations++;
        }
    }

    private boolean train2(boolean correct){
        int res = (process() > weights[weights.length - 1]) ? 1 : 0;

        for (int i = 0; i < weights.length; i++)
            weights[i] += ((((correct) ? 1 : 0) - res) * learningConstant * data[i]);


        return res == ((correct) ? 1 : 0);
    }

    public double process() throws NullPointerException{
        double sum = 0;

        for (int i = 0; i < 26; i++)
            sum += weights[i] * data[i];

        return sum;
    }

    public double test(String path) throws NullPointerException{
        dataInit(path);
        return process();
    }

    public double testUserInput(String line){
        userDataInit(line);
        return process();
    }

    public String getLanguage(){ return language; }
}
