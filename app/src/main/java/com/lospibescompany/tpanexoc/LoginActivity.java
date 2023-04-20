package com.lospibescompany.tpanexoc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lospibescompany.tpanexoc.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel =new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginActivityViewModel.class);

        EditText editTextEmail = findViewById(R.id.edit_text_email);
        EditText editTextPassword = findViewById(R.id.edit_text_password);
        Button buttonLogin = findViewById(R.id.button_login);

        loginViewModel.getCorrecto().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loginResult) {
                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        loginViewModel.getIncorrecto().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loginResult) {
                Toast.makeText(LoginActivity.this,"Usuarios y/o contraseña incorrecto",Toast.LENGTH_LONG).show();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginViewModel.login(editTextEmail.getText().toString(),
                        editTextPassword.getText().toString());
            }
        });
    }
}