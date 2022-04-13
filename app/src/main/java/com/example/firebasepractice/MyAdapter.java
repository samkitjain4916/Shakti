package com.example.firebasepractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;
    ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_data_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.userName.setText(user.userName);
        holder.userSkill.setText(user.userSkill);
        holder.userAge.setText(user.userAge);
        holder.userNumber.setText(user.userNumber);
        holder.userEmail.setText(user.userEmail);
        holder.userAddress.setText(user.userAddress);
        holder.userRequest.setText(user.userRequest);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName,userSkill,userEmail,userAge,userNumber,userAddress,userRequest;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userName = itemView.findViewById(R.id.tvName);
            userSkill = itemView.findViewById(R.id.tvSkill);
            userAge = itemView.findViewById(R.id.tvAge);
            userNumber = itemView.findViewById(R.id.tvPhone);
            userEmail = itemView.findViewById(R.id.tvEmail);
            userAddress = itemView.findViewById(R.id.tvAddress);
            userRequest = itemView.findViewById(R.id.tvRequest);



        }
    }
}
