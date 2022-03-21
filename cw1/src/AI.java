import java.util.ArrayList;

public class AI {

    ArrayList<Sasiad> list;

    public AI(ArrayList<String> fileTxt){

        String tmp[];

        for (String s : fileTxt){
            tmp = s.split(",");


            for (String s1 : tmp){
                if (s1.matches("[a-zA-Z].*"));
            }
        }
    }
}
