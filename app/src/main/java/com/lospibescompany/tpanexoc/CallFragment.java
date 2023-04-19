package com.lospibescompany.tpanexoc;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallFragment extends androidx.fragment.app.Fragment {
    private CallViewModel mCallViewModel;
    private EditText mNumeroEditText;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        mCallViewModel = new CallViewModel();
        mNumeroEditText = view.findViewById(R.id.numero);
        Button llamarButton = view.findViewById(R.id.btnLlamar);

        // Agregar un observador para el objeto MutableLiveData de error
        mCallViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                // Actualizar la interfaz de usuario con el mensaje de error
                // (por ejemplo, mostrar un mensaje de error en un TextView)
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


        llamarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = mNumeroEditText.getText().toString();
                mCallViewModel.setNumero(numero);
                mCallViewModel.llamar(requireContext());
            }
        });

        return view;
    }






}