package com.lospibescompany.tpanexoc;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel
{
    private MutableLiveData<Boolean> correcto = new MutableLiveData<>();
    private MutableLiveData<Boolean> incorrecto = new MutableLiveData<>();
    private Context context;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        this.context=application;
    }
    public LiveData<Boolean> getCorrecto(){
        if(correcto==null){
            correcto=new MutableLiveData<>();
        }
        return correcto;
    }
    public LiveData<Boolean> getIncorrecto(){
        if(incorrecto==null){
            incorrecto=new MutableLiveData<>();
        }
        return incorrecto;
    }
    public void login(String username, String password) {
        if(username==null||password==null){
            incorrecto.setValue(false);
        } else if(username.equals("usuario")&& password.equals("qwerty123")){
            correcto.setValue(true);
        } else{
            incorrecto.setValue(false);
        }
    }
}
