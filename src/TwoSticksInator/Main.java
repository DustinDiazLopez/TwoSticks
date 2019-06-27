package TwoSticksInator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    static String THEME = "/css/dark-theme.css";
    private static int WIDTH;
    private static int HEIGHT;
    private static int minWIDTH = 1080;
    private static int minHEIGHT = 605;

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Close Application", "Are you sure you want to quit? :(");

        if (answer) {
            System.exit(0);
        }
    }

    private static int scaleBetween(int unscaledNum, int minAllowed, int maxAllowed, int min, int max) {
        return (maxAllowed - minAllowed) * (unscaledNum - min) / (max - min) + minAllowed;
    }

    private static void setScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int maxRange = Math.max(minWIDTH, screenSize.width / 2);

        int w = screenSize.width;
        int h = screenSize.height;

        if (w >= 3440) {
            w /= 3;
        } else if (w >= 2560) {
            w -= minWIDTH;
            w -= (2560 - minWIDTH) * 2;
        } else if (w >= 1920) {
            w -= (1920 - minWIDTH);
        } else {
            w = minWIDTH;
        }

        WIDTH = w;
        HEIGHT = minHEIGHT;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Two Sticks");
        setScreen();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(minWIDTH);
        primaryStage.setMinHeight(minHEIGHT);

        primaryStage.getIcons().add(new Image("TwoSticksInator/what.png"));

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
