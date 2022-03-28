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
            System.out.println("\n---------------------------------------------");
            System.out.println("ROZPOCZYNANIE TRENOWANIA PERCEPTRONU");
            System.out.println("---------------------------------------------");
            Perceptron p = new Perceptron(learningConstant, threshold);
            p.train(trainingPath,accuracy);

            //testing perceptron
            System.out.print("Podaj sciezke do pliku z danymi do testowania: ");
            String testPath = scanner.nextLine();
            System.out.println("---------------------------------------------");
            System.out.println("ROZPOCZYNANIE TESTOWANIA DANYCH");
            System.out.println("---------------------------------------------");
            p.test(testPath);


            //user input vectors
            System.out.println("---------------------------------------------");
            System.out.print("Czy chcesz podac wlasny wektor (y - tak, n - nie): ");
            String yesOrNo = scanner.nextLine();

            while(yesOrNo.equals("y")){
                System.out.print("Podaj wektor i jego oczekiwany wynik: ");
                String input = scanner.nextLine();
                String[] cells = input.split(",");
                Double[] tab = new Double[cells.length - 1];

                for (int i = 0; i < cells.length - 1; i++)
                    tab[i] = Double.parseDouble(cells[i]);

                int res = p.process(tab);

                System.out.println("Oczekiwany wynik: " + cells[cells.length - 1]);
                System.out.println("Otrzymany wynik: " + p.interpretation(res));
                System.out.println("Czy byl poprawny: " + ((p.interpretation(res).equals(cells[cells.length - 1])) ? "TAK" : "NIE"));
                System.out.println("---------------------------------------------");
                System.out.print("Czy chcesz podac wlasny wektor (y - tak, n - nie): ");
                yesOrNo = scanner.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Podano nieprawidlowe dane!");
        }

        System.out.println("\n---------------------------------------------");
        System.out.println("KONIEC PRACY!");
        System.out.println("---------------------------------------------");
        //"data_for_perceptron/iris_perceptron/training.txt"
    }
}
