import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("mainMenuMockup");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(0);
        layout.setHgap(10);
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
        Label label2 = new Label("Customer Controls");
        GridPane.setConstraints(label2, 0, 1);
        Button button2 = new Button();
        GridPane.setConstraints(button2, 1, 1);
        button2.setText("Customer Controls");
        button2.setOnAction(event -> {
            System.out.println("lalala");
        });



        layout.getChildren().addAll(label1, button1, label2, button2);
        Scene scene1 = new Scene(layout, 800, 600);

        primaryStage.setScene(scene1);
        primaryStage.show();

    }



}
