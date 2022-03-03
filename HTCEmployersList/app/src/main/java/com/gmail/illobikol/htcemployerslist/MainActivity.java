package com.gmail.illobikol.htcemployerslist;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Employer> employers = new ArrayList<Employer>();

    private static final String JSON_URL = "https://www.mocky.io/v2/5ddcd3673400005800eae483";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(recyclerView.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,//подключение к сайту
                new Response.Listener< String>(){
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject company = object.getJSONObject("company");
                            JSONArray employees=company.getJSONArray("employees");

                            for (int i=0;i< employees.length();i++){

                                JSONObject userData = employees.getJSONObject(i);

                                String name_temp = "имя не указано";
                                String phone_number_temp = "телефон не указан";
                                String skills_temp = "умений нет";

                                if(userData.has("name")) {name_temp = userData.getString("name");}
                                if(userData.has("phone_number")){ phone_number_temp = userData.getString("phone_number");}
                                if(userData.has("skills")){ skills_temp = userData.getString("skills");}

                                employers.add(new Employer (name_temp, phone_number_temp, skills_temp));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        Collections.sort(employers, new Comparator<Employer>() { //сортировка по алфавиту
                            @Override
                            public int compare(Employer o1, Employer o2) {
                                return o1.name.compareTo(o2.name);
                            }
                        });
                        EmployerAdapter adapter = new EmployerAdapter(MainActivity.this, employers);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {//вывод ошибки при подключении к сайту
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}