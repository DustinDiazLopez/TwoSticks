package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Close Application", "Are you sure you want to quit? :(");

        if (answer) {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Two Sticks");

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setScene(new Scene(root, 1080, 605));

        primaryStage.getIcons().add(new Image("sample/what.png"));

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
