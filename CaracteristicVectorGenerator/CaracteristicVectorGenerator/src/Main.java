import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> vector = new ArrayList<>();
        ArrayList<int[]> item = new ArrayList<>();

        item.add(new int[2]);
        item.add(new int[2]);
        item.add(new int[2]);

        item.get(0)[0] = 15;
        item.get(0)[1] = 20;
        item.get(1)[0] = 20;
        item.get(1)[1] = 8;
        item.get(2)[0] = 8;
        item.get(2)[1] = 14;


        for (int j = 0; j < 2; j++){
            for (int k =0; k < 2; k++){
                for (int z = 0; z < 2; z++){
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(j);
                    tmp.add(k);
                    tmp.add(z);
                    vector.add(tmp);
                }
            }
        }

        int cap = 30;

        int maxVal = 0;
        int maxIndex = -1;

        for (int i = 0; i < vector.size(); i++){
            if (item.get(0)[1] * vector.get(i).get(0) + item.get(1)[1] * vector.get(i).get(1) + item.get(2)[1] * vector.get(i).get(2) > maxVal
                    && item.get(0)[0] * vector.get(i).get(0) + item.get(1)[0] * vector.get(i).get(1) + item.get(2)[0] * vector.get(i).get(2) < cap) {
                maxVal = item.get(0)[1] * vector.get(i).get(0) + item.get(1)[1] * vector.get(i).get(1) + item.get(2)[1] * vector.get(i).get(2);
                maxIndex = i;
            }
        }

        System.out.println(maxIndex);
    }
}
