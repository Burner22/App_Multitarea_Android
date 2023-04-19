package com.lospibescompany.tpanexoc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CallViewModel extends ViewModel {

    private MutableLiveData<String> mNumero = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();

    public MutableLiveData<String> getNumero() {
        return mNumero;
    }
    public void setNumero(String numero) {
        mNumero.setValue(numero);
    }

    //Paso por parametro un objeto de tipo context para que en el fragment tenga la referencia una vez ejecute llamar()
    public void llamar(Context context) {
        String number = mNumero.getValue();
        if (number == null || number.isEmpty()) {
            mError.setValue("Ingrese un número de teléfono válido.");
        } else {
            // Lógica para hacer la llamada al número
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+number));
            context.startActivity(callIntent);
        }
    }

    public LiveData<String> getError() {
        return mError;
    }
}
