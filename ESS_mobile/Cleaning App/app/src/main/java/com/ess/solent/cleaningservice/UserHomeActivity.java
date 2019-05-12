package com.ess.solent.cleaningservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener {
    JSONObject jsonObject;
    JSONArray serviceArray;
    String[] titles;
    RequestQueue requestQueue;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_home_user);
        Button service = (Button)findViewById(R.id.button1);
                service.setOnClickListener(this);
        Button schedule = (Button)findViewById(R.id.button2);
        schedule.setOnClickListener(this);
        Button history = (Button)findViewById(R.id.button3);
        history.setOnClickListener(this);

        //Set user name
        TextView welcomeUser = findViewById(R.id.welcomeUser);
        String userName = "Welcome back " + getIntent().getStringExtra("cleaning-firstname");
        welcomeUser.setText(userName);
    }

    @Override
    public void onClick(View v) {
        Bundle extras = getIntent().getExtras();
        String userId = extras.getString("cleaning-userid");
        try {
            if (v.getId() == R.id.button1) {
                context = this;
                getServices();
            }
            if (v.getId() == R.id.button2) {
                Intent intent = new Intent(this, UserScheduleActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button3) {
                Intent intent = new Intent(this, UserServiceHistoryActivity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Log.d("JAMES DEBUG", e.getMessage());

        }
    }
    public void getServices(){
        // get the data set
        String url = "http://104.248.168.181/REST/read/readAllServices.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // specify an adapter (see also next example)
                        System.out.println(response);

                        try {
                            jsonObject = new JSONObject(response);

                            if(jsonObject.has("service")){
                                System.out.println("HAS SERVICES");
                                serviceArray = (JSONArray) jsonObject.get("service");
                                titles = new String[serviceArray.length()];
                                for (int i = 0; i < serviceArray.length(); i++) {
                                    JSONObject s = (JSONObject) serviceArray.get(i);
                                    String t = s.getString("title");
                                    titles[i] = t;
                                }
                                Intent serviceIntent = new Intent(context, UserServiceActivity.class);
                                serviceIntent.putExtra("cleaning-service-titles", titles);
                                setResult(RESULT_OK, serviceIntent);
                                startActivity(serviceIntent);
                            }else{
                                System.out.println("MO SERVICES!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        requestQueue.add(stringRequest);
    }
}
