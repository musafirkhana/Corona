package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.baf.bafcoronainfo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private Context mContext;

    private GoogleMap map;
    List<Marker> liveMarker = new ArrayList<>();
    private double cameraZoomLat=23.8434;
    private double cameraZoomLong=90.4029;
    private double latarray[]={23.7797806,23.8350313,24.2575976,22.2604611,21.4455484};
    private double longarray[]={90.3890075,90.3886198,90.1542055,91.8210827,91.9589299};

    private int height[]={150,140,110,90,60};
    private int width[]={150,140,110,90,60};
    List<Double> lat_list = new ArrayList<Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        mContext=this;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        drawliveMarker();


    }
    private void drawliveMarker() {
        liveMarker.clear();

        map.animateCamera(CameraUpdateFactory.zoomIn());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(cameraZoomLat,cameraZoomLong)) // Sets the center of the map
                .tilt(20) // Sets the tilt of the camera to 20 degrees
                .zoom(7) // Sets the zoom
                .bearing(0) // Sets the orientation of the camera to east
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Adding marker on the Google Map
        for(int i =0; i<latarray.length; i++)  {



            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(latarray[i],longarray[i]))
                    .title("234").icon(BitmapDescriptorFactory.fromBitmap(
                            resizeMapIcons("ic_corona_map",height[i],width[i]))));

            liveMarker.add(marker);
            marker.showInfoWindow();


        }
    }
    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void BACK(View v) {
        this.finish();

    }


}
