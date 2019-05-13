import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        loginButton.setOnAction(event -> {
            HttpURLConnection conn = null;
            try {
                URL url =  new URL ("http://104.248.168.181/REST/login/userLogin.php");
                conn = (HttpURLConnection) url.openConnection();
                String username = usernameIn.getText();
                String pw = passIn.getText();
                String postData = "username="+username + "&password="+pw;
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(postData.length());
                OutputStream out = null;
                out = conn.getOutputStream();
                out.write(postData.getBytes());
                if (conn.getResponseCode() == 200) {
                    System.out.println("true");
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String all = "", line;
                    while((line = br.readLine()) !=null)
                        all += line;
                    JSONObject jsonObj = new JSONObject (json);
                    System.out.println(all);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        GridPane.setConstraints(loginButton, 0, 2);

        grid.getChildren().addAll(userLabel, usernameIn, passLabel, passIn, loginButton);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();

    }



}
