package com.axr.sjoerd.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<com.axr.sjoerd.contactcard.DomainLayer.Person> personArrayList = new ArrayList<>();

    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<com.axr.sjoerd.contactcard.DomainLayer.Person> personArrayList) {
        this.personArrayList = personArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //responsible for inflation

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Log.d("Going out", "before bit");
        Glide.with(context).asBitmap().load(personArrayList.get(i).getProfileThumbnail()).into(viewHolder.circleImageView);
        Log.d("Going out", "after bit");

        viewHolder.textView.setText(personArrayList.get(i).getFirstName() + " " + personArrayList.get(i).getLastName());

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.axr.sjoerd.contactcard.DomainLayer.Person p = personArrayList.get(i);
                Toast.makeText(context, p.getFirstName(), Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
                intent.putExtra("Person", p);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("Going out: count: ", Integer.toString(personArrayList.size()));
        return personArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.image_name);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
