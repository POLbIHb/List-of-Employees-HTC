package com.gmail.illobikol.htcemployerslist;

import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String COMPANY = "company";
    private static final String EMPLOYEES = "employees";
    private static final String SKILLS = "skills";
    private static final String NAME = "name";
    private static final String PHONE = "phone_number";
    private static final String DEFAULT_NAME = "имя не указано";
    private static final String DEFAULT_PHONE = "телефон не указан";
    private static final String DEFAULT_SKILLS = "умения отсутствуют";

    private static String[] employerDefaultInfo = new String[3];

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ArrayList<Employer> employers = new ArrayList<Employer>();
    private static final String JSON_URL = "https://www.mocky.io/v2/5ddcd3673400005800eae483";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EmployerAdapter adapter = new EmployerAdapter(employers);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,//подключение к сайту
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject companyEmployersResponse = new JSONObject(response);
                            JSONObject company = companyEmployersResponse.getJSONObject(COMPANY);
                            JSONArray employees = company.getJSONArray(EMPLOYEES);
                            System.out.println(employees);
                            System.out.println(employees.getJSONObject(9));
                            for (int i = 0; i < employees.length(); i++) {
                                JSONObject employer = employees.getJSONObject(i);

                                employerDefaultInfo[0] = DEFAULT_NAME;
                                employerDefaultInfo[1] = DEFAULT_PHONE;
                                employerDefaultInfo[2] = DEFAULT_SKILLS;

                                List<String> skillsList = new ArrayList<String>();

                                if (employer.has(NAME)) {
                                    employerDefaultInfo[0] = employer.getString(NAME);
                                }
                                if (employer.has(PHONE)) {
                                    employerDefaultInfo[1] = employer.getString(PHONE);
                                }
                                if (employer.has(SKILLS)) {
                                    JSONArray skills = employer.getJSONArray(SKILLS);
                                        for (int j=0;j<skills.length();j++){
                                            skillsList.add(skills.getString(j));
                                        }
                                    employerDefaultInfo[2] = skillsList.toString();
                                }

                                employers.add(new Employer(employerDefaultInfo[0], employerDefaultInfo[1], employerDefaultInfo[2]));

                            }
                        } catch (JSONException exeption) {
                            Log.d(exeption.toString(),exeption.getMessage());
                        }
                        Collections.sort(employers, new Comparator<Employer>() { //сортировка по алфавиту
                            @Override
                            public int compare(Employer o1, Employer o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });
                        adapter.setEmployer(employers);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {//вывод ошибки при подключении к сайту
                        Toast.makeText(MainActivity.this, "Невозможно загрузить список сотрудников", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
