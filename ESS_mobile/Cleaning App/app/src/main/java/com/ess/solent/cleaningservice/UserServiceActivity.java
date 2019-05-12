package com.ess.solent.cleaningservice;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceActivity extends ListActivity {
    MyAdapter mAdapter;
    public String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = getIntent().getStringArrayExtra("cleaning-service-titles");
        //setContentView(R.layout.activity_user_service);
        //recyclerView = findViewById(R.id.my_recycler_view);
/*        String userId = getIntent().getStringExtra("cleaning-userid");
        System.out.println("User ID: " + userId);*/

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        /*recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        context = this;*/

        mAdapter = new MyAdapter();
        setListAdapter(mAdapter);

        //recyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter() {
            super(UserServiceActivity.this, android.R.layout.simple_list_item_1, titles);
        }

        public View getView(int index, View convertView, ViewGroup parent){
            titles = getIntent().getStringArrayExtra("cleaning-service-titles");
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_user_service, parent, false);
            TextView serviceTitle = (TextView) findViewById(R.id.service_title);
            serviceTitle.setText(titles[index]);
            return view;
        }

    }



}
