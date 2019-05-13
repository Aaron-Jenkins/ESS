import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage .*;
import javafx.scene .*;
import javafx.scene.layout .*;
import javafx.scene.control .*;
import javafx.geometry .*;

public class Allocate {

    public static void display(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Allocate");

        //Create two buttons
        Button allocate = new Button("Allocate");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(0);
        layout.setHgap(10);

        //Add buttons
        layout.getChildren().addAll(label, allocate);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
