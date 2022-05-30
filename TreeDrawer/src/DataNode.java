import java.util.ArrayList;
import java.util.HashMap;

public class DataNode {

    private HashMap<String,DataNode> options;
    private String name;

    public DataNode(String name){
        this.name = name;
        options = new HashMap<>();
    }

    public void addOption(String optionName, DataNode optionResult){
        options.put(optionName,optionResult);
    }

    public String getName() { return name; }

    public HashMap<String, DataNode> getOptions() {
        return options;
    }

    public static DataNode createATree(String[][] data){

        DataNode first = new DataNode(data[0][0]);

        ArrayList<String> optionsInCategory = new ArrayList<>();
        for (int i = 1; i < data.length; i++){

        }
    }
}
