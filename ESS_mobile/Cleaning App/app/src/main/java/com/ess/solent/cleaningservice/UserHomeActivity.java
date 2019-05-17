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
    JSONArray serviceArray, jobsArray;
    String[] id;
    String[] titles;
    String[] desc;
    String[] cost;
    String[] si;
    String[] sd;
    String[] ed;
    String[] ei;
    RequestQueue requestQueue;
    Context context;
    String userId;

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

    //check what button the user clicked and display the correct page
    @Override
    public void onClick(View v) {
        Bundle extras = getIntent().getExtras();
        userId = extras.getString("cleaning-userid");
        context = this;
        try {
            if (v.getId() == R.id.button1) {
                getServices();
            }
            if (v.getId() == R.id.button2) {
                getSchedule();
                Intent intent = new Intent(this, UserScheduleActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button3) {
                getServiceHistory();
                Intent intent = new Intent(this, UserServiceHistoryActivity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Log.d("JAMES DEBUG", e.getMessage());

        }
    }
    //load the schedule page and get all the services available from the REST.
    private void getSchedule() {
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
                                id = new String[serviceArray.length()];
                                titles = new String[serviceArray.length()];
                                desc = new String[serviceArray.length()];
                                cost = new String[serviceArray.length()];
                                for (int i = 0; i < serviceArray.length(); i++) {
                                    JSONObject s = (JSONObject) serviceArray.get(i);
                                    String di = s.getString("id");
                                    String t = s.getString("title");
                                    String d = s.getString("description");
                                    String c = s.getString("cost");
                                    id[i] = di;
                                    titles[i] = t;
                                    desc[i] = d;
                                    cost[i] = c;
                                }
                                Intent serviceIntent = new Intent(context, UserServiceActivity.class);
                                serviceIntent.putExtra("cleaning-service-id", id);
                                serviceIntent.putExtra("cleaning-service-titles", titles);
                                serviceIntent.putExtra("cleaning-service-description", desc);
                                serviceIntent.putExtra("cleaning-service-cost", cost);
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
    //get all services from the REST API
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
                                id = new String[serviceArray.length()];
                                titles = new String[serviceArray.length()];
                                desc = new String[serviceArray.length()];
                                cost = new String[serviceArray.length()];
                                for (int i = 0; i < serviceArray.length(); i++) {
                                    JSONObject s = (JSONObject) serviceArray.get(i);
                                    String di = s.getString("id");
                                    String t = s.getString("title");
                                    String d = s.getString("description");
                                    String c = s.getString("cost");
                                    id[i] = di;
                                    titles[i] = t;
                                    desc[i] = d;
                                    cost[i] = c;
                                }
                                Intent serviceIntent = new Intent(context, UserServiceActivity.class);
                                serviceIntent.putExtra("cleaning-service-userid", userId);
                                serviceIntent.putExtra("cleaning-service-id", id);
                                serviceIntent.putExtra("cleaning-service-titles", titles);
                                serviceIntent.putExtra("cleaning-service-description", desc);
                                serviceIntent.putExtra("cleaning-service-cost", cost);
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
    //Get service history for users from the REST API
    private void getServiceHistory() {
        // get the data set
        String url = "http://104.248.168.181/REST/read/readServiceHistory.php?userId=" + userId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // specify an adapter (see also next example)
                        System.out.println(response);

                        try {
                            jsonObject = new JSONObject(response);

                            if(jsonObject.has("service")){
                                jobsArray = (JSONArray) jsonObject.get("service");
                                id = new String[jobsArray.length()];
                                si = new String[jobsArray.length()];
                                sd = new String[jobsArray.length()];
                                ed = new String[jobsArray.length()];
                                ei = new String[jobsArray.length()];
                                for (int i = 0; i < jobsArray.length(); i++) {
                                    JSONObject s = (JSONObject) jobsArray.get(i);
                                    String di = s.getString("id");
                                    String sid = s.getString("serviceId");
                                    String sdate = s.getString("startDate");
                                    String edate = s.getString("endDate");
                                    String eid = s.getString("empId");
                                    id[i] = di;
                                    si[i] = sid;
                                    sd[i] = sdate;
                                    ed[i] = edate;
                                    ei[i] = eid;
                                }
                                Intent serviceIntent = new Intent(context, UserServiceHistoryActivity.class);
                                serviceIntent.putExtra("cleaning-service-userid", userId);
                                serviceIntent.putExtra("cleaning-service-id-history", id);
                                serviceIntent.putExtra("cleaning-service-start-history", sd);
                                serviceIntent.putExtra("cleaning-service-end-history", ed);
                                serviceIntent.putExtra("cleaning-service-empid-history", ei);
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
