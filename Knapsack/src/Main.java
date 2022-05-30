import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private int capacity;
    private ArrayList<int[]> items;

    private int[] bestVector;
    private int maxVal;

    public Main(String path){
        items = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))){

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
    }

    public int[] findBestVector(){
        int position = 0;
        bestVector = new int[items.size()];
        maxVal = 0;

        findBestVector(new int[items.size()], position);

        return bestVector;
    }

    public void findBestVector(int[] vector, int position){

        if (getVal(vector) > maxVal) {
            maxVal = getVal(vector);
            bestVector = vector.clone();
        }

        if (position == vector.length)
            return;

        findBestVector(vector.clone(), position + 1);

        vector[position] = 1;
        findBestVector(vector.clone(), position + 1);
    }

    public int getVal(int[] vector){
        int thisCapacity = 0;
        int totalValue = 0;

        for (int i = 0; i < items.size(); i++){
            thisCapacity += items.get(i)[0] * vector[i];
            totalValue += items.get(i)[1] * vector[i];

            if (thisCapacity > capacity)
                return -1;
        }

        return totalValue;
    }

    public static String msToTime(long ms){
        StringBuilder stringBuilder = new StringBuilder();
        int min;
        int sec;

        stringBuilder.append("Wykonanie zajęło: ");

        if (ms > 60_000){
            min = Math.round(ms / 60_000);
            ms -= 60_000 * min;
            stringBuilder.append(min + "min ");
        }

        if (ms > 1_000){
            sec = Math.round(ms / 1_000);
            ms -= 1_000 * sec;
            stringBuilder.append(sec + "sec ");
        }

        stringBuilder.append(ms + "ms");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Main main = new Main("data/tmp.txt");

        long start = System.currentTimeMillis();
        int[] tmp = main.findBestVector();
        long end = System.currentTimeMillis();

        System.out.println(main.msToTime(end - start));
        System.out.print("Najlepszy wektor: ");

        for (int i : tmp)
            System.out.print(i);
    }
}
