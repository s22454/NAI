import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataReader {
    public static HashMap<ArrayList<String>,String> read(String path) throws IOException {

        Scanner scanner = new Scanner(new File(path));
        HashMap<ArrayList<String>,String> res = new HashMap<>();

        while (scanner.hasNextLine()){

            ArrayList<String> keyTmp = new ArrayList<>();
            String line = scanner.nextLine();
            String[] tmp = line.split("\\s");

            for (int i = 0; i < tmp.length - 1; i++){
                keyTmp.add(tmp[i]);
            }

            res.put(keyTmp, tmp[tmp.length - 1]);
        }

        scanner.close();
        return res;
    }
}
