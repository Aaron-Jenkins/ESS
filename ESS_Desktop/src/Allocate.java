import com.sun.xml.internal.bind.v2.TODO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Allocate {

    static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Allocator");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Allocate");

        //Create  buttons

        // http://104.248.168.181/REST/read/readAllPendingJobs.php
        Button allocate = new Button("Allocate");
        allocate.setOnAction(e ->{
            //TODO FIX ME
            // GET PENDING FUNCTION CALL HERE
            getPending();

        }
        );


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



    // Function to grab jsonObj
    public static String getPending() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://104.248.168.181/REST/read/readAllPendingJobs.php");
            conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String all = "", line;
                while ((line = br.readLine()) != null)
                    all += line;
                JSONObject jsonObj = new JSONObject(all);
                //System.out.println(jsonObj);
                getProduct(jsonObj);
                return jsonObj.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return e.toString();
        }
        return "error";
    }

    // Loop through pending servies and convert to observable array list 
    public static ObservableList<pendingService> getProduct(JSONObject jsonObj){
        ObservableList<pendingService> services = FXCollections.observableArrayList();
        JSONArray ja = jsonObj.getJSONArray("service");
        for (int i = 0; i < ja.length(); i++) {
            int id = ja.getJSONObject(i).getInt("id");
            int userId = ja.getJSONObject(i).getInt("userId");
            int serviceId = ja.getJSONObject(i).getInt("serviceId");
            String startDate = ja.getJSONObject(i).getString("startDate");
            String endDate = ja.getJSONObject(i).getString("endDate");
            pendingService ps = new pendingService(id, userId, serviceId, startDate, endDate);
            services.add(ps);
        }
        System.out.println(services);
        /*
        services.add(new pendingService("Laptop", 859.00, 20));
        services.add(new pendingService("Bouncy Ball", 2.49, 198));
        services.add(new pendingService("Toilet", 99.00, 74));
        services.add(new pendingService("The Notebook DVD", 19.99, 12));
        services.add(new pendingService("Corn", 1.49, 856));
        */
        return services;
    }
}