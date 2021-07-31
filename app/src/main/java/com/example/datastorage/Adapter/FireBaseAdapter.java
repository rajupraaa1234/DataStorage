package com.example.datastorage.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datastorage.FireBaseServices.Person;
import com.example.datastorage.R;

import java.util.ArrayList;

public class FireBaseAdapter extends RecyclerView.Adapter<FireBaseAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<Person> coursesArrayList;
    private Context context;

    // creating constructor for our adapter class
    public FireBaseAdapter(ArrayList<Person> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FireBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        Person person = coursesArrayList.get(position);
        holder.stuName.setText(person.getName());
        holder.stuLast.setText(person.getLastname());
        holder.stuDob.setText(person.getDob());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return coursesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private TextView stuName;
        private TextView stuLast;
        private TextView stuDob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            stuName = itemView.findViewById(R.id.itname);
            stuLast = itemView.findViewById(R.id.itlast);
            stuDob = itemView.findViewById(R.id.itdob);
        }
    }
}