package com.example.newapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.PixelCopy;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SearchView Search;
    FloatingActionButton add, pop;
    RecyclerView recycle;

    ArrayList<Modalclass>  alldata = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Search = findViewById(R.id.Search);
        add = findViewById(R.id.add);
        pop = findViewById(R.id.pop);
        recycle = findViewById(R.id.recycle);


        // Object  ->  {"":"" , "":""}
        // Array  ->  ["","",""]

        RequestQueue que = Volley.newRequestQueue(MainActivity.this);

        String url = "https://service.apikeeda.com/api/v1/notes";

        StringRequest post = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("+--+", "onResponse:  get" + response);

                alldata.clear();
                try {
                    JSONObject alldate = new JSONObject(response);
                    JSONArray data = alldate.getJSONArray("data");

                    for (int i = 0; i<data.length(); i++)
                    {
                        JSONObject one = data.getJSONObject(i);

                        String id = one.getString("_id");
                        String Title = one.getString("title");
                        String date = one.getString("date");
                        String description  = one.getString("description");

                        Modalclass modalclass = new Modalclass(id,Title,date,description);
                        Log.d("+-+-+-", "onResponse: "+modalclass);
                        MainActivity.this.alldata.add(modalclass);

                    }


                } catch (JSONException e) {

                    Log.d("=======", "onResponse: "+e);
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }



                MyAdapter adpter = new MyAdapter(MainActivity.this, alldata);
                recycle.setAdapter(adpter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("+--+", "onResponse: not " + error);
            }
        }) {
            //            Map<String,String> = heder
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("x-apikeeda-key", "u1728652257324irb777494598xo");

                return header;
            }

        };
        que.add(post);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,NewNote.class));
                finish();

            }
        });

    }

}