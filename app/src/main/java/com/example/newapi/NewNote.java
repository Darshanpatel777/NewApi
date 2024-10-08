package com.example.newapi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class NewNote extends AppCompatActivity {

    Button Save,Cancel;

  TextInputEditText txt;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_note);

        txt = findViewById(R.id.txt);
        Save = findViewById(R.id.Save);
        Cancel = findViewById(R.id.Cancel);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog = new Dialog(NewNote.this);
                dialog.setContentView(R.layout.dialogview_save);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                TextView  tex = dialog.findViewById(R.id.tex);
                Button yes = dialog.findViewById(R.id.yes);
                Button no = dialog.findViewById(R.id.no);

                tex.getText();

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(NewNote.this,MainActivity.class));
                        finish();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
            }
        });


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(NewNote.this,MainActivity.class));
                finish();

            }
        });


        RequestQueue que = Volley.newRequestQueue(NewNote.this);

        String url = "https://service.apikeeda.com/api/v1/notes";

        StringRequest post = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("+--+", "onResponse: " + response);

//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String status = jsonObject.getString("status");
//
//
//                } catch (JSONException e) {
//                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }

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