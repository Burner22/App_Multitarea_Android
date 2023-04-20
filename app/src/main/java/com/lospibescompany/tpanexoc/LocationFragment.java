package com.lospibescompany.tpanexoc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.lospibescompany.tpanexoc.databinding.FragmentLocationBinding;


public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Se utiliza ViewModelProvider SI O SI
        LocationViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);

        //Aca el observer que me permite utilizar en el fragment SupportMapFragment
        homeViewModel.getMapa().observe(getViewLifecycleOwner(), mapaActual -> {
            SupportMapFragment smf = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
            smf.getMapAsync(mapaActual);
        });
        homeViewModel.construirMapa();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}