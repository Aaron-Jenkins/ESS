import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends Application {

    private Stage window;
    private Scene login, mainMenu;
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("mainMenuMockup");

        // init grid
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setHgap(8);
        layout.setVgap(10);

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
        // Pass username and password to REST, if status comes back true login and display menu
        loginButton.setOnAction(event -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://104.248.168.181/REST/login/userLogin.php");
                conn = (HttpURLConnection) url.openConnection();
                String username = usernameIn.getText();
                String pw = passIn.getText();
                String postData = "username=" + username + "&password=" + pw;
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(postData.length());
                OutputStream out;
                out = conn.getOutputStream();
                out.write(postData.getBytes());
                if (conn.getResponseCode() == 200) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String all = "", line;
                    while ((line = br.readLine()) != null)
                        all += line;
                    JSONObject jsonObj = new JSONObject(all);
                    if (jsonObj.get("status").equals(true)) {
                        //login successful load main menu
                        window.setScene(Menu.getScene());
                    } else {
                        // alert box ideally
                        System.out.println("Login Failed Try again");
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        GridPane.setConstraints(loginButton, 0, 2);
        layout.getChildren().addAll(userLabel, usernameIn, passLabel, passIn, loginButton);
        login = new Scene(layout, 300, 200);
        window.setScene(login);
        window.show();

    }



}
