package info.androidhive.materialdesign.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import info.androidhive.materialdesign.R;
/**
 * Created by deepak on 6/5/17.
 */

public class StoreListFrag extends Fragment {

    ListView storeListView;
    ArrayAdapter adapter;
    String[] stores;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.storelistfragment, container, false);
        storeListView = (ListView) view.findViewById(R.id.list_view);

        stores = getResources().getStringArray(R.array.stores);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.listviewlayout, R.id.rowItem, stores);

        storeListView.setAdapter(adapter);
        storeListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println("calling main intent");
                // TODO Auto-generated method stub
                Bundle inBundle = getActivity().getIntent().getExtras();
                String name = inBundle.get("name").toString();
                String surname = inBundle.get("surname").toString();
                String imageUrl = inBundle.get("imageUrl").toString();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("storeName",stores[position]);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                intent.putExtra("imageUrl", imageUrl);
                startActivity(intent);

            }
        });
        return view;
    }
}
