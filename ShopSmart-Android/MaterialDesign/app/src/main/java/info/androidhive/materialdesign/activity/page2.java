package info.androidhive.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.Store.RecyclerTouchListener;
import info.androidhive.materialdesign.adapter.Store.Store;
import info.androidhive.materialdesign.adapter.StoreAdapter;


public class page2 extends AppCompatActivity {
    private List<Store> storeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoreAdapter mAdapter;

    //    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new StoreAdapter(storeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Store store = storeList.get(position);
                //Toast.makeText(getApplicationContext(), store.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(page2.this, MainActivity.class);
                intent.putExtra("key", store.getName());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareStoreData();
    }

    private void prepareStoreData() {
        Store store = new Store("Shoppers Stop", "Gurgaon");
        storeList.add(store);

        store = new Store("Nike", "Gurgaon");
        storeList.add(store);

        store = new Store("Big Bazaar", "Gurgaon");
        storeList.add(store);

        mAdapter.notifyDataSetChanged();
    }


}
