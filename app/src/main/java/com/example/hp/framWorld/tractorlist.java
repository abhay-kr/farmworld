package com.example.hp.framWorld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.framWorld.modal.Tractor;
import com.google.android.gms.maps.model.LatLng;

public class tractorlist extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractorlist);
        ListView list = (ListView) findViewById(R.id.listView);
        customadapter ca = new customadapter();
        list.setAdapter(ca);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tracName= tractorName[position];
                LatLng locLng=location[position];
                String locName=locationName[position];

                Tractor tractor=new Tractor(tracName,locLng,locName);


                startActivity(new Intent(
                        tractorlist.this,MapsActivity.class));
            }
        });

    }

    class customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub

            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertview, ViewGroup arg2) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = getLayoutInflater();
            convertview = inflater.inflate(R.layout.custom, null);
            TextView tv = (TextView) convertview.findViewById(R.id.textView12);
            TextView tv1 = (TextView) convertview.findViewById(R.id.textView13);
            ImageView image = (ImageView) convertview
                    .findViewById(R.id.imageView2);
            tv.setText(tractorName[position]);
            tv1.setText(locationName[position]);
            image.setImageResource(images[position]);

            return convertview;
        }

    }

    String[] tractorName = { "name1", "name2", "name3", "name4", "name5", "name4" };
    LatLng [] location={new LatLng(12.6781433,78.5823081),new LatLng(13.6022611,79.3670168),
            new LatLng(12.6781433,78.5823081),new LatLng(13.6022611,79.3670168),
            new LatLng(12.6781433,78.5823081),new LatLng(13.6022611,79.3670168),};

    String[] locationName = { "Vaniyambadi", "Ambur", "Vaniyambadi", "Ambur",
            "Vaniyambadi", "Ambur"};
    int[] images = { R.drawable.force, R.drawable.masseyfergusion,R.drawable.force, R.drawable.masseyfergusion, R.drawable.mahindra,
            R.drawable.tafe, R.drawable.johndeere };
}
