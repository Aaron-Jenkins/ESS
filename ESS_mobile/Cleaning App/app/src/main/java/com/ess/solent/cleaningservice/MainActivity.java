package com.ess.solent.cleaningservice;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button guestLogin;
    private Button userLogin;
    private EditText editUsername;
    private EditText editPassword;
    protected String username;
    protected String password;
    protected JSONObject jsonObject;
    private String userId;
    RequestQueue requestQueue;
    String url;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button guestLogin = (Button) findViewById(R.id.guestLogin);
        guestLogin.setOnClickListener(this);
        Button userLogin = (Button) findViewById(R.id.userLogin);
        userLogin.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);

    }


    @Override
    public void onClick(View v) {
        boolean loggedIn = false;
        switch (v.getId()) {
            case R.id.userLogin:
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                loginUser();
                context = this;
                /*if(user.isEmpty()) {

                }*/
                break;
            case R.id.guestLogin:
                System.out.println("guestLogin");
                //TODO login guest
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    public void loginUser() {
        Boolean res = false;
        url = "http://104.248.168.181/REST/login/userLogin.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            jsonObject = new JSONObject(response);
                            if(!jsonObject.isNull("status")){
                                Intent intent = new Intent(context, UserHomeActivity.class);
                                Bundle bundle=new Bundle();
                                bundle.putBoolean("cleaning-loggedIn", Boolean.parseBoolean(jsonObject.getString("status")));
                                bundle.putString("cleaning-userid", String.valueOf(jsonObject.getJSONObject("user").getString("id")));
                                bundle.putString("cleaning-firstname", String.valueOf(jsonObject.getJSONObject("user").getString("firstName")));
                                intent.putExtras(bundle);
                                setResult(RESULT_OK,intent);
                                startActivity(intent);
                            }else{
                                System.out.println("LINE 116 => " + jsonObject.getString("status"));
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
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                System.out.println(username + " -> " + password);
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}
