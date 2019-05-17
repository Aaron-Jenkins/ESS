package com.ess.solent.cleaningservice;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class UserServiceHistoryActivity extends ListActivity {

    String userId;
    String[] serviceId;
    String[] serviceStart;
    String[] serviceEnd;
    String[] serviceEmpId;
    Context context;
    RequestQueue requestQueue;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // use a recycled view to make a list and get all data from the intent
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("cleaning-service-userid");
        serviceId = getIntent().getStringArrayExtra("cleaning-service-id-history");
        serviceStart = getIntent().getStringArrayExtra("cleaning-service-start-history");
        serviceEnd = getIntent().getStringArrayExtra("cleaning-service-end-history");
        serviceEmpId = getIntent().getStringArrayExtra("cleaning-service-empid-history");
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        adapter = new UserServiceHistoryActivity.MyAdapter();
        setListAdapter(adapter);
    }


    //add onclick listener to each element in the list of services
    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        TextView x = v.findViewById(R.id.service_id_history);
        System.out.println(x);
    }



    //My adapter will generate the list view by reusing the layout activity_user_service this will
    // loop for however many results are returned from the API request from the Service list and
    // display them all on the page
    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter() {
            super(UserServiceHistoryActivity.this, android.R.layout.simple_list_item_1, serviceId);
        }

        public View getView(int index, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_user_service_history, parent, false);
            TextView serviceIdHistory = (TextView)view.findViewById(R.id.service_id_history);
            TextView startHistory = (TextView)view.findViewById(R.id.service_start_history);
            TextView endHistory = (TextView)view.findViewById(R.id.service_end_history);
            TextView empIdHistory = (TextView)view.findViewById(R.id.service_emp_id);
            serviceIdHistory.setText(serviceId[index]);
            startHistory.setText(serviceStart[index]);
            endHistory.setText(serviceEnd[index]);
            String ct = serviceEmpId[index];
            empIdHistory.setText(ct);
            return view;
        }

    }



}
