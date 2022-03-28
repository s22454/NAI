import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Perceptron {

    private HashMap<ArrayList<Double>,String> data;
    private double learningConstant;
    private double threshold;

    public Perceptron(double learningConstant, double threshold) throws IOException {
        this.learningConstant   = learningConstant;
        this.threshold          = threshold;
        data                    = new HashMap<>();
    }


}
