package com.ess.solent.cleaningservice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class UserServiceActivity extends ListActivity {
    MyAdapter mAdapter;
    String[] id;
    String[] titles;
    String[] desc;
    String[] cost;
    String userId;
    String serviceId;
    Context context;
    String serviceDate;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String resDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // use a recycled view to make a list and get all data from the intent
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("cleaning-service-userid");
        id = getIntent().getStringArrayExtra("cleaning-service-id");
        titles = getIntent().getStringArrayExtra("cleaning-service-titles");
        desc = getIntent().getStringArrayExtra("cleaning-service-description");
        cost = getIntent().getStringArrayExtra("cleaning-service-cost");
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        mAdapter = new MyAdapter();
        setListAdapter(mAdapter);



    }

    //add onclick listener to each element in the list of services
    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        TextView x = v.findViewById(R.id.service_id);
        serviceId = (String) x.getText();
        showDialog(999);
    }

    //create the date picker dialog for the user to enter a date when requesting a service
    int year, month, day, hour, min;
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog datePicker = new DatePickerDialog(this, myDateListener, year, month, day);
            datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
            return datePicker;
        }
        if (id == 998) {
            return new TimePickerDialog(this, myTimeListener, hour, min, false);
        }


        return null;
    }

    //get the date from the dialog when the user click ok
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            String month = "";
            String day = "";
            //format the date with leading 0's so that the format is correct for the MySQL database
            if(arg2 < 10){
                month = "0" + arg2;
            }else{
                month = String.valueOf(arg2);
            }
            if(arg3 < 10){
                day = "0" + arg3;
            }else{
                day = String.valueOf(arg3);
            }
            resDate = arg1 + "-" + month + "-" + day;
            showDialog(998);
        }
    };

    //allow user to enter a time for the service to take place
    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //add trailing 0's to the time then concatenate the date and time into the correct format.
            System.out.println(resDate + " " + hourOfDay+":"+minute+":00");
            String time = hourOfDay + ":"+minute;
            serviceDate = resDate + " " + time + ":00";
            System.out.println(serviceDate);

            //send the request
            requestBooking();
        }
    };

    //Send a request to the rest service to add a job
    public void requestBooking(){
        Boolean res = false;
        String url = "http://104.248.168.181/REST/create/createJob.php";
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("status").equals(true)){
                                System.out.println("Job Added");
                                // if the job is added finish the activity and return to the main menu
                                finish();
                            }else{
                                System.out.println("ERROR!=> " + jsonObject.getString("status"));
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
            //define the parameters to add the job and place then in the header
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", userId);
                params.put("serviceId", serviceId);
                params.put("startDate", String.valueOf(serviceDate));
                params.put("endDate", String.valueOf(serviceDate));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    //My adapter will generate the list view by reusing the layout activity_user_service this will
    // loop for however many results are returned from the API request from the Service list and
    // display them all on the page
    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter() {
            super(UserServiceActivity.this, android.R.layout.simple_list_item_1, titles);
        }

        public View getView(int index, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_user_service, parent, false);
            TextView serviceId = (TextView)view.findViewById(R.id.service_id);
            TextView serviceTitle = (TextView)view.findViewById(R.id.service_title);
            TextView serviceDesc = (TextView)view.findViewById(R.id.service_description);
            TextView serviceCost = (TextView)view.findViewById(R.id.service_cost);
            serviceId.setText(id[index]);
            serviceTitle.setText(titles[index]);
            serviceDesc.setText(desc[index]);
            String ct = "£" + cost[index];
            serviceCost.setText(ct);
            return view;
        }

    }



}
