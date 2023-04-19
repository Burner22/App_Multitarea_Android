package com.lospibescompany.tpanexoc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationViewModel extends ViewModel {
    private Fragment mFragment;
    private static final LatLng walmart = new LatLng(-33.296339019478296,-66.30341321971963);
    private static final LatLng carrefour = new LatLng(-33.302695583984196,-66.33677539530545);
    private GoogleMap map;

    //Aca creo un constructor que recibe un fragment, para que en Location fragment pueda
    //pasarselo por parametro al SupportMapFragment
    public LocationViewModel(Fragment fragment) {
        mFragment = fragment;
    }

    public void mostrarMapa() {
        SupportMapFragment mapFragment = (SupportMapFragment) mFragment.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new MapaActual());
    }

    private class MapaActual implements OnMapReadyCallback {
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