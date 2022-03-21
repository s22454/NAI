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


        String k;
        System.out.print("Podaj k:");
        k = scanner.nextLine();



        try {
            Scanner scannerTrain        = new Scanner(trainingFile);
            ArrayList<String> trainList = new ArrayList<>();
            while (scannerTrain.hasNext()){
                trainList.add(scannerTrain.next());
            }

            Scanner scannerTest         = new Scanner(testFile);
            ArrayList<String> testList  = new ArrayList<>();
            while (scannerTest.hasNext()){
                testList.add(scannerTest.next());
            }

            //training AI
            AI ai = new AI(trainList);

            System.out.println("\nProcessing ...");
            ai.process(testList, Integer.parseInt(k));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
