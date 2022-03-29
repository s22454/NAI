import java.util.ArrayList;
import java.util.HashMap;

public class AI {

    HashMap<ArrayList<Double>,String> sasiedzi;

    public AI(ArrayList<String> fileTxt){
        this.sasiedzi = new HashMap<>();

        for (String s : fileTxt){
            ArrayList<Double> tmp = new ArrayList<>();
            String[] sSplited = s.split(",");

            for (int i = 0; i < sSplited.length - 1; i++)
                tmp.add(Double.parseDouble(sSplited[i]));

            sasiedzi.put(new ArrayList<>(tmp), sSplited[sSplited.length - 1]);
        }
    }

    public void process(ArrayList<String> testList, int k){
        double goodAnswer  = 0;
        double badAnswer   = 0;

        for (int i = 0; i < testList.size(); i++) {

            //v1 init
            ArrayList<Double> v1 = new ArrayList<>();
            String[] tmp = testList.get(i).split(",");
            for (int j = 0; j < tmp.length - 1; j++){
                v1.add(Double.parseDouble(tmp[j]));
            }

            //finding k closest points
            HashMap<ArrayList<Double>,String>   resMap    = new HashMap<>();
            HashMap<ArrayList<Double>,String>   tmpMap    = new HashMap<>(sasiedzi);
            for (int j = 0 ; j < k; j++){
               ArrayList<Double> minDistance                   = new ArrayList<>();
               double minD                                     = distance(v1,(ArrayList<Double>) tmpMap.keySet().toArray()[0]);

               for (ArrayList<Double> l : tmpMap.keySet()){
                   if (distance(v1,l) < minD){
                       minD = distance(v1,l);
                       minDistance = l;
                   }
               }

               resMap.put(new ArrayList<>(minDistance), tmpMap.get(minDistance));
               tmpMap.remove(minDistance);
            }

            //show on console
            System.out.print("\nFor point [ " );
            for (int j = 0; j < tmp.length - 1; j++)
                System.out.print(tmp[j] + ", ");
            System.out.println("] closest " + k + " points are:");

            for (int j = 0; j < resMap.size(); j++){
                System.out.print((j + 1) + " - [ ");
                for (Double d : (ArrayList<Double>) resMap.keySet().toArray()[j])
                    System.out.print(d + ", ");
                System.out.print("] - " + resMap.get(resMap.keySet().toArray()[j]) + "\n");
            }

            HashMap<String, Integer> res = new HashMap<>();
            for (String s : resMap.values()){
                if (res.containsKey(s))
                    res.put(s, res.get(s) + 1);
                else
                    res.put(s, 1);
            }

            int maxVal = 0;
            String maxKey = "";
            for (String s : res.keySet()){
                if (res.get(s) > maxVal){
                    maxVal = res.get(s);
                    maxKey = s;
                }
            }

            System.out.println("Expected answer: " + tmp[tmp.length - 1]);
            System.out.println("Resault is: " + maxKey);
            System.out.println("Is this correct: " + ((maxKey.equals(tmp[tmp.length - 1])) ? "YES" : "NO"));
            if ((maxKey.equals(tmp[tmp.length - 1]))) {
                goodAnswer++;
            } else {
                badAnswer++;
            }
        }

        System.out.println("\nGood answers: " + (int) goodAnswer);
        System.out.println("Bad answers: " + (int) badAnswer);
        System.out.println("Accuracy: " + ((double) (Math.round((goodAnswer / testList.size()) * 10000))) / 100 + "%");
    }

    private Double distance(ArrayList<Double> v1, ArrayList<Double> v2){
        Double res = 0.0;

        for (int i = 0; i < v1.size(); i++)
            res += Math.pow(v1.get(i) - v2.get(i), 2);


        return Math.sqrt(res);
    }
}