package com.lospibescompany.tpanexoc;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Closeable;

public class LocationViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<MapaActual> mapa;
    private static final LatLng walmart = new LatLng(-33.296339019478296,-66.30341321971963);
    private static final LatLng carrefour = new LatLng(-33.302695583984196,-66.33677539530545);

    public LocationViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<MapaActual> getMapa(){
        if(mapa==null){
            mapa=new MutableLiveData<>();
        }
        return mapa;
    }
    public void construirMapa(){
        mapa.setValue(new MapaActual());
    }

    public static class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.addMarker(new MarkerOptions().position(walmart).title("WalMart"));
            googleMap.addMarker(new MarkerOptions().position(carrefour).title("Carrefour"));
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(walmart)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(camUpd);
        }
    }
}