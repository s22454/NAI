import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            Perceptron p = new Perceptron("data_for_perceptron/iris_perceptron/test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
