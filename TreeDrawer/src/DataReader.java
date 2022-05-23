import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataReader {
    public static ArrayList<ArrayList<String>> read(String path) throws IOException {

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<String>> res = new ArrayList<>();

        if (!file.getName().endsWith(".txt"))
            throw new IOException();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tmp = line.split("\\s");
            ArrayList<String> valTmp = new ArrayList<>(Arrays.asList(tmp));
            res.add(valTmp);
        }

        scanner.close();
        return res;
    }
}
