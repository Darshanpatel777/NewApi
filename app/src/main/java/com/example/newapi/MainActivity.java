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

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SearchView Search;
    ListView tex;
    FloatingActionButton add, pop;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Search = findViewById(R.id.Search);
        tex = findViewById(R.id.tex);
        add = findViewById(R.id.add);
        pop = findViewById(R.id.pop);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu pmenu = new PopupMenu(MainActivity.this, pop);

                pmenu.inflate(R.menu.menu);
                pmenu.show();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this,NewNote.class));
            }
        });




        RequestQueue que = Volley.newRequestQueue(MainActivity.this);

        String url = "https://service.apikeeda.com/api/v1/notes";

        StringRequest post = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("+--+", "onResponse: " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("+--+", "onResponse: " + error);
            }
        }) {
            //            Map<String,String> = heder
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {


                HashMap<String, String> header = new HashMap<>();
                header.put("x-apikeeda-key", "p1727874311879vqw342375297yx");

                return header;
            }

            //            Map<String,String> =
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("title", " new cdmi");
                params.put("date", "2024-02-29T11:22:15.945Z");
                params.put("description", " new institute");

                return params;
            }
        };
        que.add(post);

    }

}