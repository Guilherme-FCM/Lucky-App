package com.example.luckyapp.ui.home;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.luckyapp.utils.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> data;
    private final String URL = "https://raw.githubusercontent.com/Guilherme-FCM/Lucky-App/master/app/src/main/res/data.json";

    public HomeViewModel() {
        data = new MutableLiveData<>("Aguardando dados...");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler();

        executor.execute(() -> {
            List<List<Integer>> data = request();
            handler.post(() -> {
                this.data.setValue(data.toString());
            });
        });
    }

    public LiveData<String> getText() {
        return data;
    }

    private List<List<Integer>> request() {
        try {
            HttpRequest<List<List<Integer>>> request = new HttpRequest<>(URL);
            return request.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}