package com.example.newapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<Myrecycle> {

    public MyAdapter(MainActivity mainActivity, ArrayList<Modalclass> alldata) {
        this.mainActivity = mainActivity;
        this.alldata = alldata;
    }

    MainActivity mainActivity;
    ArrayList<Modalclass> alldata;

    @NonNull
    @Override
    public Myrecycle onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View vv = LayoutInflater.from(mainActivity).inflate(R.layout.detail, parent, false);
        Myrecycle myrecycle = new Myrecycle(vv);

        return myrecycle;
    }

    @Override
    public void onBindViewHolder(@NonNull Myrecycle holder, int position) {

        Modalclass  modal = alldata.get(position);

//        holder.id.setText(modal.getId());
        holder.date.setText(modal.getDate());
        holder.tit.setText(modal.getTitle());
        holder.des.setText(modal.getDescription());

    }

    @Override
    public int getItemCount() {
        return alldata.size();
    }
}

class Myrecycle extends RecyclerView.ViewHolder {


//    TextView id;
    TextView date;
    TextView tit;
    TextView des;


    public Myrecycle(@NonNull View itemView) {
        super(itemView);


//        id = itemView.findViewById(R.id.id);
        date = itemView.findViewById(R.id.date);
        tit = itemView.findViewById(R.id.Tit);
        des = itemView.findViewById(R.id.des);
    }
}