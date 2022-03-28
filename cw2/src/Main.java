import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){

            //init data
            Double learningConstant;
            System.out.print("Podaj stala uczenia sie: ");
            learningConstant = Double.parseDouble(scanner.nextLine());

            Double threshold;
            System.out.print("Podaj prog aktywacji: ");
            threshold = Double.parseDouble(scanner.nextLine());

            //learning data
            String trainingPath;
            System.out.print("Podaj sciezke do pliku z danymi treningowymi: ");
            trainingPath = scanner.nextLine();

            double accuracy;
            System.out.print("Podaj docelowa dokladnosc: ");
            accuracy = Double.parseDouble(scanner.nextLine());

            //creating and training perceptron
            System.out.println("\n");
            System.out.println("---------------------------------------------");
            System.out.println("ROZPOCZYNANIE TRENOWANIA PERCEPTRONU");
            System.out.println("---------------------------------------------");
            Perceptron p = new Perceptron(learningConstant, threshold);
            p.train(trainingPath,accuracy);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //"data_for_perceptron/iris_perceptron/training.txt"
    }
}
