package com.lospibescompany.tpanexoc;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.SupportMapFragment;
import com.lospibescompany.tpanexoc.databinding.FragmentLocationBinding;
import com.lospibescompany.tpanexoc.databinding.FragmentMiUbicacionBinding;


public class MiUbicacionFragment extends Fragment {

    FragmentMiUbicacionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = binding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Se utiliza ViewModelProvider SI O SI
        MiUbicacionViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(MiUbicacionViewModel.class);

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





