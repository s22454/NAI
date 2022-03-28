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
            System.out.print("\nPodaj prog aktywacji: ");
            threshold = Double.parseDouble(scanner.nextLine());

            //learning data
            String trainingPath;
            System.out.print("\nPodaj sciezke do pliku z danymi treningowymi: ");
            trainingPath = scanner.nextLine();

            double accuracy;
            System.out.print("\nPodaj docelowa dokladnosc: ");
            accuracy = Double.parseDouble(scanner.nextLine());

            //creating and training perceptron
            Perceptron p = new Perceptron(learningConstant, threshold);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //"data_for_perceptron/iris_perceptron/test.txt"
    }
}
