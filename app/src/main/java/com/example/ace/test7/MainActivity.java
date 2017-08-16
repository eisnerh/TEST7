package com.example.ace.test7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname, age;
    Button insert, show;
    RequestQueue requestQueue;
    String insertUrl = "https://tryliquidaciones.000webhostapp.com/testing/insertProducto.php";
    String showUrl = "https://tryliquidaciones.000webhostapp.com/testing/showProductos.php";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.etName);
        lastname = (EditText) findViewById(R.id.etPrecio);
        age = (EditText) findViewById(R.id.etDescripcion);
        insert = (Button) findViewById(R.id.Insert);
        show = (Button) findViewById(R.id.show);
        result = (TextView) findViewById(R.id.textView);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("ww");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray students = response.getJSONArray("products");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);

                                String firstname = student.getString("name");
                                String lastname = student.getString("price");
                                String age = student.getString("description");

                                result.append(firstname + " " + lastname + " " + age + " \n");
                            }
                            result.append("===\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();
                        parameters.put("name",firstname.getText().toString());
                        parameters.put("price",lastname.getText().toString());
                        parameters.put("description",age.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });


    }
}
