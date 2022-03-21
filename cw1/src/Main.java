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


        ArrayList<String> list = new ArrayList<>();

        try {
            Scanner scanner1 = new Scanner(trainingFile);

            while (scanner1.hasNext()){
                list.add(scanner1.next());
            }

            AI ai = new AI(list);
            ai.process();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
