import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

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
        pathEntryTextField.setText("Data/data.txt");
        pathEntryButton.setFont(new Font(12));

        GridPane pathEntryPane = new GridPane();
        pathEntryPane.addRow(0,pathEntryLabel,pathEntryTextField,pathEntryButton);
        Scene initScene = new Scene(pathEntryPane,520,30);
        //-----------------------


        //error popup
        Button closePopup = new Button("Close");
        Label popupMessage = new Label("Wrong path to data file! ");
        popupMessage.setFont(new Font(20));

        GridPane errorPane = new GridPane();
        errorPane.addRow(0,popupMessage,closePopup);

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-color: red");
        hBox.getChildren().add(errorPane);

        Popup errorPopup = new Popup();
        errorPopup.getContent().add(hBox);
        errorPopup.hide();
        //-----------------------


        //tree scene
        Scene treeScene = new Scene(new Label("lol"),800,800);
        //-----------------------


        //setting application to be shown
        primaryStage.setTitle("TreeDrawer");
        primaryStage.setScene(initScene);
        primaryStage.show();
        //-----------------------

        pathEntryButton.setOnAction(e -> {

            boolean doesEverythingWentRight = true;

            try {
                DataReader.read(pathEntryTextField.getText());
            } catch (IOException ex) {
                doesEverythingWentRight = false;
            }

            if (doesEverythingWentRight) {
                primaryStage.setScene(treeScene);
                primaryStage.centerOnScreen();
            }else{
                errorPopup.show(primaryStage);
                closePopup.setOnAction(event -> errorPopup.hide());
            }
        });


    }
}
