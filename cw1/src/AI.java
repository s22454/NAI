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

        for (String s : sasiedzi.keySet()){
            System.out.print(s + ": ");
            for (int i = 0; i < sasiedzi.get(s).size(); i++){
                for (Double d : sasiedzi.get(s).get(i))
                    System.out.print(d + " | ");
            }
            System.out.println();
        }
    }
}
