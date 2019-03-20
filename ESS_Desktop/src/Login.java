import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

    private Stage window;
    Menu menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("LOGIN_MOCKUP");
        // init grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(8);
        grid.setVgap(10);


        // Username
        Label userLabel = new Label("Username:");
        TextField usernameIn = new TextField("Aaron");
        GridPane.setConstraints(userLabel, 0, 0);
        GridPane.setConstraints(usernameIn, 1, 0);

        // Password
        Label passLabel = new Label("password");
        TextField passIn = new TextField("password");
        GridPane.setConstraints(passLabel, 0, 1);
        GridPane.setConstraints(passIn, 1, 1);

        Button loginButton = new Button("Log in!");
        GridPane.setConstraints(loginButton, 0, 2);

        grid.getChildren().addAll(userLabel, usernameIn, passLabel, passIn, loginButton);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();

    }



}
