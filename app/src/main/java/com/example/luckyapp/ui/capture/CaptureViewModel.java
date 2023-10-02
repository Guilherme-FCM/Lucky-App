package com.example.luckyapp.ui.capture;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.luckyapp.utils.JsonDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CaptureViewModel extends ViewModel {

    private final MutableLiveData<List<List<Integer>>> data;
    private List<Integer> flatData;

    public CaptureViewModel() {
        data = new MutableLiveData<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler();

        executor.execute(() -> {
            List<List<Integer>> response = JsonDataService.getJsonData();
            handler.post(() -> {
                flatData = flatList(response);
                data.setValue(response);
            });
        });
    }

    public LiveData<List<List<Integer>>> getData() {
        return data;
    }

    public String join() {
        StringBuilder builder = new StringBuilder();
        flatData.forEach(builder::append);
        return builder.toString();
    }

    public Integer major() {
        return flatData.stream().mapToInt(i -> i).max().orElse(0);
    }

    public Double average() {
        return flatData.stream().mapToInt(i -> i).average().orElse(0);
    }

    public List<Integer> descending() {
        List<Integer> list = flatData.stream().sorted().collect(Collectors.toList());
        ArrayList<Integer> revertedList = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--)
            revertedList.add(list.get(i));

        return revertedList;
    }

    private List<Integer> flatList(List<List<Integer>> list) {
        List<Integer> flatList = new ArrayList<>();

        for (List<Integer> l : list)
            flatList.addAll(l);

        return flatList;
    }
}