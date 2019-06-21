package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ConfirmBox {

    private static boolean answer;

    static boolean display(String title, String message) {


        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(200);
        window.setMinWidth(350);
        window.getIcons().add(new Image("sample/what.png"));

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton  = new Button("No");

        yesButton.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                answer = true;
                window.close();
            }
        });

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                answer = false;
                window.close();
            }
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        HBox layButton = new HBox(10);
        layButton.getChildren().addAll(yesButton, noButton);
        layButton.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, layButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Main.THEME);
        window.setScene(scene);
        window.showAndWait();


        return answer;
    }
}
