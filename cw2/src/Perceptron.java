import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Perceptron {
    HashMap<ArrayList<Double>,String> data;

    public Perceptron(String path) throws IOException {
        data = new HashMap<>();
        Scanner scanner = new Scanner(new File(path));

        while(scanner.hasNext()){
            String[] cells = scanner.next().split(",");
            ArrayList<Double> tmpList = new ArrayList<>();

            for (int i = 0; i < cells.length - 1; i++)
                tmpList.add(Double.parseDouble(cells[i]));

            data.put(tmpList, cells[cells.length - 1]);
        }

        for (String s : data.values())
            System.out.println(s);
    }
}
