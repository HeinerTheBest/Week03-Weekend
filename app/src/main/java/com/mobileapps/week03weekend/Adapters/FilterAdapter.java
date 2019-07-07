package com.mobileapps.week03weekend.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.week03weekend.Models.Filter;
import com.mobileapps.week03weekend.R;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder>
{
    ArrayList<Filter> filters = new ArrayList<>();
    Context context;

    public FilterAdapter(ArrayList<Filter> filters, Context context) {
        this.filters = filters;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.filter_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final Filter filter = filters.get(position);

        holder.tvTitle.setText(filter.getTitle());

        int id = context.getResources().getIdentifier(filter.getPicture(), "drawable", context.getPackageName());
        holder.imgIcon.setImageResource(id);
        Log.d("Heiner","Setting the data in the adapter");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, filter.getOpc());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  tvTitle;
        ImageView imgIcon;
        Spinner   spinner;
        ImageView btnSearch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgIcon = itemView.findViewById(R.id.imgLogo);
            spinner = itemView.findViewById(R.id.spinnerOpc);
            btnSearch = itemView.findViewById(R.id.btnSearch);
        }
    }
}
