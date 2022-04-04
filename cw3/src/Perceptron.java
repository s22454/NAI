import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    private double[]        data;
    private int             charCount;
    private double[]        wieghts;
    private int             generations;
    private final double    learningConstant;
    private final double    threshold;
    private final String    language;

    public Perceptron(double learningConstant, double threshold, String language){
        this.learningConstant   = learningConstant;
        this.threshold          = threshold;
        this.language           = language;
        this.generations        = 0;
    }

    public void show(){
        System.out.println("---------------------------------------------");
        System.out.println("UDALO SIE WYTRENOWAC PERCEPTRON");
        System.out.println("---------------------------------------------");
        System.out.println("Jezyk: " + language);
        System.out.println("Stala uczenia sie: " + learningConstant);
        System.out.println("Prog aktywacji: " + threshold);
        System.out.println("Wartosci wag: ");
        for (int j = 0; j < wieghts.length; j++)
            System.out.println((char) (j + 97) + " - " + wieghts[j]);
        System.out.println("Ilosc przejsc potrzebna do nauczenia: " + generations);
        System.out.println("---------------------------------------------");
    }

    public void dataInit(String path){

        //data tab init
        data        = new double[26];
        charCount   = 0;
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
        data        = new double[26];
        charCount   = 0;
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

    public void train() throws ArrayIndexOutOfBoundsException{

        //wieghts init
        wieghts = new double[26];
        Random r = new Random();
        for (int i = 0; i < wieghts.length; i++)
            wieghts[i] = r.nextDouble();

        //learning
        int i = 0;
        int goodAnsware = 0;
        while(goodAnsware != 1){

            //call an exception if it has repeted more ten 1000000 times
            if (i > 1000000)
                throw new IllegalStateException();

            //modify goodansware if res was good
            goodAnsware = (train2()) ? 1 : 0;

//            System.out.println("Przejscie " + (i + 1) + " ilosc poprawnych pod rzad: " + goodAnsware);
            i++;
            generations++;
        }
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

    public boolean testUserInput(String line){
        userDataInit(line);
        return process() == 1;
    }

    public String getLanguage(){ return language; }
}
