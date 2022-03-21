import java.util.ArrayList;
import java.util.HashMap;

public class AI {

    HashMap<String, ArrayList<ArrayList<Double>>> sasiedzi;

    public AI(ArrayList<String> fileTxt){
        this.sasiedzi = new HashMap<>();

        for (String s : fileTxt){
            String[] tmp = s.split(",");
            ArrayList<Double> tmp2 = new ArrayList<>();
            
            for (int i = 0; i < tmp.length - 1; i++){
                tmp2.add(Double.parseDouble(tmp[i]));
            }

            if (sasiedzi.containsKey(tmp[tmp.length - 1])){
                sasiedzi.get(tmp[tmp.length - 1]).add(tmp2);
            }else {
                ArrayList<ArrayList<Double>> tmp3 = new ArrayList<>();
                tmp3.add(tmp2);
                sasiedzi.put(tmp[tmp.length - 1], tmp3);
            }
        }

//        for (String s : sasiedzi.keySet()){
//            System.out.print(s + ": ");
//            for (int i = 0; i < sasiedzi.get(s).size(); i++){
//                for (Double d : sasiedzi.get(s).get(i))
//                    System.out.print(d + " | ");
//            }
//            System.out.println();
//        }
    }

    public void process(File file){
        HashMap<String, Double[]> maxMap = new HashMap<>();
        HashMap<String, Double[]> minMap = new HashMap<>();

        //init
        for (String s : sasiedzi.keySet()){
            Double[] max = new Double[sasiedzi.get(s).get(0).size()];
            Double[] min = new Double[sasiedzi.get(s).get(0).size()];

            for (int i = 0; i < max.length; i++)
                max[i] = sasiedzi.get(s).get(0).get(i);

            for (int i = 0; i < min.length; i++)
                min[i] = sasiedzi.get(s).get(0).get(i);

            System.out.println(s + ":");
            for (int i = 0; i < sasiedzi.get(s).size(); i++){
                for (int j = 0; j < sasiedzi.get(s).get(i).size(); j++){
                    if (sasiedzi.get(s).get(i).get(j) > max[j])
                        max[j] = sasiedzi.get(s).get(i).get(j);

                    if (sasiedzi.get(s).get(i).get(j) < min[j])
                        min[j] = sasiedzi.get(s).get(i).get(j);
                }
            }

            System.out.print("max: ");
            for (Double d : max)
                System.out.print(d + " | ");

            System.out.print("\nmin: ");
            for (Double d : min)
                System.out.print(d + " | ");

            System.out.println();

            maxMap.put(s,max);
            minMap.put(s,min);
        }

        //testing test.txt file

    }
}
