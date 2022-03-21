import java.util.ArrayList;

public class Sasiad {

    String name;
    ArrayList<Integer> dim;

    Sasiad (String name, ArrayList<Integer> dim){
        this.name = name;
        this.dim = dim;
    }

    public ArrayList<Integer> getDim() {
        return dim;
    }

    public String getName() {
        return name;
    }
}
