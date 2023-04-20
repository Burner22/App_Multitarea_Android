package com.lospibescompany.tpanexoc;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MiUbicacionViewModel extends AndroidViewModel {
    private static Context context;

    private MutableLiveData<MiUbicacionViewModel.MapaActual> mapa;

    public MiUbicacionViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<MiUbicacionViewModel.MapaActual> getMapa(){
        if(mapa==null){
            mapa=new MutableLiveData<>();
        }
        return mapa;
    }
    public void construirMapa(){
        mapa.setValue(new MiUbicacionViewModel.MapaActual());
    }

    public static class MapaActual implements OnMapReadyCallback {
        private Marker currentMarker;
        private FusedLocationProviderClient fused;
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            LocationRequest request = LocationRequest.create();
            request.setInterval(5000);
            request.setFastestInterval(5000);
            request.setPriority(100);

            LocationCallback callback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    Log.d("Salida", "Leyendo....");
                    if (locationResult == null) {
                        return;
                    }
                    List<Location> lista = locationResult.getLocations();
                    for (Location ubicacion : lista) {
                        //Aca es donde borro la marca anterior para guardar la nueva
                        if (currentMarker != null) {
                            currentMarker.remove();
                        }
                        LatLng location = new LatLng(ubicacion.getLatitude(),ubicacion.getLongitude());
                        Log.d("Location", location+"");
                        currentMarker = googleMap.addMarker(new MarkerOptions().position(location).title("Ubicacion Actual"));
                        CameraPosition camPos = new CameraPosition.Builder()
                                .target(location)
                                .zoom(19)
                                .bearing(45)
                                .tilt(70)
                                .build();
                        CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
                        googleMap.animateCamera(camUpd);
                    }
                }
            };
            fused = LocationServices.getFusedLocationProviderClient(context);
            fused.requestLocationUpdates(request, callback, null);
        }
    }

}








