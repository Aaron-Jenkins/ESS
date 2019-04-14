import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


public class MainApp{
    public static void main(String[] args) throws ProtocolException {
        MainApp mainApp = new MainApp();
        Scanner scanner = new Scanner(System.in);
        String artist = null;
        if(scanner.hasNextLine()){
            artist = scanner.nextLine();
        }
        URL url = null;
        try {
            url = new URL("http://www.free-map.org.uk/course/ws/hits.php?artist=" + artist + "&format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.setRequestMethod("GET");
        int status = 0;
        try {
            status = con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(status == 200){
            BufferedReader in = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = null;
            StringBuffer content = new StringBuffer();
            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content.append(inputLine);
                System.out.println(inputLine);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        con.disconnect();


    }

}
