package com.example.hp.framWorld;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LatLng [] location={new LatLng(12.6781433,78.5823081),new LatLng(12.7821177,78.685341),
            new LatLng(12.9274596,79.3497632),new LatLng(12.9001922,79.3010064)};
    String[] locationName = { "Vaniyambadi", "Ambur","Walajapet","Arcot"};


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        list = (ListView) findViewById(R.id.lstViewTractor);

        ListAdaptor listAdaptor=new ListAdaptor();
        list.setAdapter(listAdaptor);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);

        for (int i=0;i<location.length;i++){
            mMap.addMarker(new MarkerOptions().position(location[i]).title(locationName[i]));
        }
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location[0],8));
    }

    class ListAdaptor extends BaseAdapter{
        public ListAdaptor() {
        }

        @Override
        public int getCount() {
            return location.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            convertView=inflater.inflate(R.layout.list_tractor_map,null);
            TextView tv=(TextView)convertView.findViewById(R.id.location);
            tv.setText(locationName[position].toString());
            return convertView;
        }
    }
}
