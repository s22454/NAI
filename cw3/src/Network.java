import java.io.File;

public class Network {

    private Perceptron[] perceptrons;

    public Network(File trainingPath){
        File[] fileList = trainingPath.listFiles();
        perceptrons     = new Perceptron[fileList.length];
        System.out.println("Wykryte jezyki: " + fileList.length);

        for (int i = 0; i < perceptrons.length; i++){
            perceptrons[i] = new Perceptron(2,50,fileList[i].getName());

            for (int j = 0; j < fileList.length; j++){
                File[] samples = fileList[j].listFiles();

                for (File f : samples){
                    perceptrons[i].dataInit(f.getPath());
                    perceptrons[i].train(j == i);
                }
            }

            perceptrons[i].show();
        }
    }

    public String test (String testFile){
        double max  = 0;
        int iMax    = 0;

        for (int i = 0; i < perceptrons.length; i++) {
            if (perceptrons[i].test(testFile) > max){
                max     = perceptrons[i].test(testFile);
                iMax    = i;
            }
        }

        return "Jezyk tekstu to: " + perceptrons[iMax].getLanguage();
    }

    public String userTest (String line){
        double max = 0;
        int iMax = 0;

        for (int i = 0; i < perceptrons.length; i++){
            if (perceptrons[i].testUserInput(line) > max){
                max = perceptrons[i].testUserInput(line);
                iMax = i;
            }
        }

        return "Jezyk tekstu to: " + perceptrons[iMax].getLanguage();
    }
}
