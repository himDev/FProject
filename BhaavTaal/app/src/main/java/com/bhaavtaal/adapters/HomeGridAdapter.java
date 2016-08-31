package com.bhaavtaal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhaavtaal.R;
import com.bhaavtaal.models.Home_grid_list;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.MyViewHolder> {

    private List<Home_grid_list> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView img_item;

        public MyViewHolder(View view) {
            super(view);
            img_item = (ImageView) view.findViewById(R.id.img_item);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public HomeGridAdapter(Context context, List<Home_grid_list> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_grid_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Home_grid_list homeGrid = moviesList.get(position);

        holder.title.setText(homeGrid.item_name);
        holder.genre.setText(homeGrid.price);
        holder.year.setText(homeGrid.distance_from_current);
        Picasso.with(context).load(homeGrid.item_image).into(holder.img_item);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
