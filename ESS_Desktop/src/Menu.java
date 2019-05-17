import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Menu {

    public static Scene getScene() {

        GridPane menu = new GridPane();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setVgap(0);
        menu.setHgap(10);
        Label label1 = new Label("Staff Controls");
        GridPane.setConstraints(label1, 0, 0);
        //Button1
        Button button1 = new Button();
        GridPane.setConstraints(button1, 1, 0);
        button1.setText("Staff Controls");
        button1.setOnAction(event -> {
            System.out.println("something");
        });

        //Button2
        Label label2 = new Label("Allocate");
        GridPane.setConstraints(label2, 0, 1);
        Button button2 = new Button();
        GridPane.setConstraints(button2, 1, 1);
        button2.setText("Allocate");
        button2.setOnAction(event -> {
            Allocate.display();
        });

        menu.getChildren().addAll(label1, button1, label2, button2);
        Scene mainMenu = new Scene(menu, 800, 600);
        return mainMenu;

    }


}
