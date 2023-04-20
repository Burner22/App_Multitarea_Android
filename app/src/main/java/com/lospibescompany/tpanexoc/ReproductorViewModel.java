package com.lospibescompany.tpanexoc;

import android.media.MediaPlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.media.MediaPlayer;
public class ReproductorViewModel extends ViewModel {

        private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>(false);

        public void reproducir(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
            isPlaying.setValue(true);
        }

        public void pausar(MediaPlayer mediaPlayer) {
            mediaPlayer.pause();
            isPlaying.setValue(false);
        }

        public MutableLiveData<Boolean> getIsPlaying() {
            return isPlaying;
        }



}

