import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String firstFilePath;
        System.out.print("Podaj ścieżkę do pliku z danymi treningowymi:");
        firstFilePath = scanner.nextLine();
        File trainingFile = new File(firstFilePath);


        String secondFilePath;
        System.out.print("Podaj ścieżkę do pliku z danymi testowymi:");
        secondFilePath = scanner.nextLine();
        File testFile = new File(secondFilePath);


        String tmp;
        System.out.print("Podaj k:");
        tmp = scanner.nextLine();


        ArrayList<String> trainList = new ArrayList<>();
        ArrayList<String> testList  = new ArrayList<>();

        try {
            Scanner scannerTrain    = new Scanner(trainingFile);
            Scanner scannerTest     = new Scanner(testFile);

            while (scannerTrain.hasNext()){
                trainList.add(scannerTest.next());
            }

            while (scannerTest.hasNext()){
                testList.add(scannerTest.next());
            }

            AI ai = new AI(trainList);

            System.out.println("\nProcessing ...\n");
            ai.process(testFile);

            System.out.println("Results:");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
