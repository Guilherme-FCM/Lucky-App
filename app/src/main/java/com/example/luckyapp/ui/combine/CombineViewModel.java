package com.example.luckyapp.ui.combine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CombineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CombineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 'Combinar' fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}