package com.lokiiichauhan.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    MySingleton queue;
    String url = "https://jsonplaceholder.typicode.com/todos";
    String getApiUrl = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = MySingleton.getInstance(this.getApplicationContext());

        TextView textView = findViewById(R.id.textview);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(getApiUrl, response -> {
            try {

                Log.d("JSONObj", "onCreate " + response.getString("title"));
                textView.setText(response.getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });

        JsonArrayRequest jsonArrayRequest = getJsonArrayRequest();

        queue.addToRequestQueue(jsonObjectRequest);

//        queue.add(jsonArrayRequest);
//
//        getString(queue);

    }

    private JsonArrayRequest getJsonArrayRequest() {
        return new JsonArrayRequest(url,
                response ->{

                    for (int i = 0; i < response.length(); i++) {

                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("Title", "onCreate " + jsonObject.getInt("userId"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    Log.d("JSON", "onCreate " + response.toString());


                    }, error -> {

            Log.d("JSON", "onCreate " + error.toString());

        });
    }

    private void getString(RequestQueue queue) {
        queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://www.google.com", response -> {

            Log.d("Main", "onCreate " + response.substring(0,500));

        }, error -> {
            Log.d("Main", "Failed");
        });

        queue.add(stringRequest);
    }
}