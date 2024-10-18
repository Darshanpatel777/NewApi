package com.example.newapi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewNote extends AppCompatActivity {

    Button Save, Cancel;
    TextInputEditText title, des;
    Calendar cr;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.title);
        des = findViewById(R.id.description);
        Save = findViewById(R.id.Save);
        Cancel = findViewById(R.id.Cancel);

        cr = Calendar.getInstance();


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue que = Volley.newRequestQueue(NewNote.this);

                String url = "https://service.apikeeda.com/api/v1/notes";

                StringRequest post = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("+--+", "onResponse: " + response);

                        startActivity(new Intent(NewNote.this, MainActivity.class));
                        finish();

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
                        header.put("x-apikeeda-key", "u1728652257324irb777494598xo");

                        return header;
                    }

                    //            Map<String,String> =
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> params = new HashMap<>();

                        params.put("title", title.getText().toString());
                        params.put("date", cr.getTime().toString());
                        params.put("description", des.getText().toString());

                        return params;
                    }
                };
                que.add(post);

            }

        });

    }

}