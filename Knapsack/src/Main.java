import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int capacity;
        ArrayList<int[]> items = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("data/tmp.txt"))){

            capacity = Integer.parseInt(scanner.nextLine());

            while (scanner.hasNext()){
                String[] tmp = scanner.nextLine().split("\\s");

                int[] tmpTab = new int[2];
                tmpTab[0] = Integer.parseInt(tmp[0]);
                tmpTab[1] = Integer.parseInt(tmp[1]);

                items.add(tmpTab);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        for (int[] c : items)
            System.out.println(c[0] + " " + c[1]);
    }
}
