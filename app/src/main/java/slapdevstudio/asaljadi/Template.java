package slapdevstudio.asaljadi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

/**
 * Created by User on 12/07/2015.
 */
public class Template extends Fragment {
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.template,null);
        ListView listView = (ListView) v.findViewById(R.id.listView);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.attachToListView(listView);
        listView.setEmptyView(v.findViewById(R.id.empty));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddTemplate.class);
                startActivity(i);
            }
        });
        return v;
    }
}
