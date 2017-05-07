package info.androidhive.materialdesign.adapter;

/**
 * Created by deepak on 7/5/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.androidhive.materialdesign.R;

import java.util.List;

import info.androidhive.materialdesign.activity.page2;
import info.androidhive.materialdesign.adapter.Store.Store;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private List<Store> storeList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            //year = (TextView) view.findViewById(R.id.year);
        }
    }


    public StoreAdapter(List<Store> storeList) {
        this.storeList = storeList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Store movie = storeList.get(position);
        holder.title.setText(movie.getName());
        holder.genre.setText(movie.getLocation());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}