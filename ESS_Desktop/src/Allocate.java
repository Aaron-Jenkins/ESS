import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
import java.util.Objects;

public class Allocate {

    static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Allocator");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Allocate");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(0);
        layout.setHgap(10);

        //Id column
        TableColumn<pendingService, Integer> IdColumn = new TableColumn<>("Id");
        IdColumn.setMinWidth(100);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        //userId column
        TableColumn<pendingService, Integer> userIdColumn = new TableColumn<>("userId");
        userIdColumn.setMinWidth(100);
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //serviceId column
        TableColumn<pendingService, Integer> serviceIdColumn = new TableColumn<>("serviceId");
        serviceIdColumn.setMinWidth(100);
        serviceIdColumn.setCellValueFactory(new PropertyValueFactory<>("serviceId"));

        //startDate column
        TableColumn<pendingService, String> startDateColumn = new TableColumn<>("startDate");
        startDateColumn.setMinWidth(100);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        //endDate column
        TableColumn<pendingService, String> endDateColumn = new TableColumn<>("endDate");
        endDateColumn.setMinWidth(100);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        //empId column
        TableColumn<pendingService, Integer> empIdColumn = new TableColumn<>("empId");
        empIdColumn.setMinWidth(100);
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("empId"));

        TableView<pendingService> table = new TableView<>();
        table.setItems(jsonToList(Objects.requireNonNull(getPending())));
        table.getColumns().addAll(IdColumn, userIdColumn, serviceIdColumn, startDateColumn, endDateColumn, empIdColumn);

        /*
        // create text fields
        final TextField addId = new TextField();
        addId.setPromptText("booking id");
        addId.setMaxWidth(IdColumn.getPrefWidth());

        final TextField addEmpId = new TextField();
        addEmpId.setPromptText("employee id");
        addEmpId.setMaxWidth(empIdColumn.getPrefWidth());

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

         */

        //Add controls to layout
        layout.getChildren().addAll(label, table);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }



    // Function to grab jsonObj from rest
    public static JSONObject getPending() {
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
                //jsonToList(jsonObj);
                return jsonObj;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // Loop through pending servies and convert to observable array list 
    public static ObservableList<pendingService> jsonToList(JSONObject jsonObj){
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
        return services;
    }
}