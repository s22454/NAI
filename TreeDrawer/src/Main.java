import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initialization scene
        TextField pathEntryTextField    = new TextField();
        Label pathEntryLabel            = new Label(" Path to the file: ");
        Button pathEntryButton          = new Button("Submit");

        pathEntryLabel.setFont(new Font(15));
        pathEntryTextField.setPrefColumnCount(30);
        pathEntryButton.setFont(new Font(12));

        GridPane pathEntryPane = new GridPane();
        pathEntryPane.addRow(0,pathEntryLabel,pathEntryTextField,pathEntryButton);
        Scene initScene = new Scene(pathEntryPane,520,30);
        //-----------------------

        
        //tree scene
        Scene treeScene = new Scene(new Label("lol"),800,800);
        //-----------------------


        //setting application to be shown
        primaryStage.setTitle("TreeDrawer");
        primaryStage.setScene(initScene);
        primaryStage.show();
        //-----------------------

        pathEntryButton.setOnAction(e -> primaryStage.setScene(treeScene));
    }
}
