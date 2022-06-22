package com.tsdl.practices.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.tsdl.practices.repository.MusicRepository;

public class MusicViewModel extends ViewModel {

    private Context context;
    private MusicRepository musicRepository;

    public MusicViewModel(Context context) {
        this.context = context;
        musicRepository = new MusicRepository(context);
    }

}
