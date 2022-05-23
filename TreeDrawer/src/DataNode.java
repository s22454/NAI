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
}
