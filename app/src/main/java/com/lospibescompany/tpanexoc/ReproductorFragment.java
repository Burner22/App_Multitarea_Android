package com.lospibescompany.tpanexoc;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lospibescompany.tpanexoc.databinding.FragmentLocationBinding;
import com.lospibescompany.tpanexoc.databinding.FragmentReproductorBinding;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ReproductorFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private ReproductorViewModel reproductorViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reproductor, container, false);

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.musica);
        reproductorViewModel = new ViewModelProvider(requireActivity()).get(ReproductorViewModel.class);

        Button botonReproducir = root.findViewById(R.id.btnReproducir);
        Button botonPausar = root.findViewById(R.id.btnPausar);

        botonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproductorViewModel.reproducir(mediaPlayer);
            }
        });

        botonPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproductorViewModel.pausar(mediaPlayer);
            }
        });

        reproductorViewModel.getIsPlaying().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isPlaying) {
                if (isPlaying) {
                    Toast.makeText(getContext(), "Reproduciendo canción", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Canción pausada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
