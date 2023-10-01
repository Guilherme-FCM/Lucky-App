package com.example.luckyapp.ui.home;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.luckyapp.utils.HttpRequest;
import com.example.luckyapp.utils.JsonDataService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<List<Integer>>> data;

    public HomeViewModel() {
        data = new MutableLiveData<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler();

        executor.execute(() -> {
            List<List<Integer>> response = JsonDataService.getJsonData();
            handler.post(() -> {
                Log.i("s", response.toString());
                data.setValue(response);
            });
        });
    }

    public LiveData<List<List<Integer>>> getData() {
        return data;
    }
}