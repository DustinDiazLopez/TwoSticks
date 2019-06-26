package TwoSticksInator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class DialogBox {
    static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(200);
        window.setMinWidth(350);
        window.getIcons().add(new Image("TwoSticksInator/what.png"));

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Close");

        yesButton.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                window.close();
            }
        });

        yesButton.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        HBox layButton = new HBox(10);
        layButton.getChildren().addAll(yesButton);
        layButton.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, layButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Main.THEME);
        window.setScene(scene);
        window.showAndWait();
    }
}
