package com.example.luckyapp.ui.draw;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.luckyapp.utils.JsonDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DrawViewModel extends ViewModel {
    private final MutableLiveData<List<Integer>> randomNumbers;
    private final MutableLiveData<List<List<Integer>>> matchedNumbers;

    public DrawViewModel() {
        randomNumbers = new MutableLiveData<>(createRandomList());
        matchedNumbers = new MutableLiveData<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler();

        executor.execute(() -> {
            List<List<Integer>> response = JsonDataService.getJsonData();
            handler.post(() -> {
                matchedNumbers.setValue(matchNumbers(response));
            });
        });
    }

    public LiveData<List<Integer>> getRandomNumbers() {
        return randomNumbers;
    }

    public LiveData<List<List<Integer>>> getMatchedNumbers() {
        return matchedNumbers;
    }

    private List<Integer> createRandomList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add( getRandomInt() );
        return list;
    }

    private Integer getRandomInt() {
        Random random = new Random();
        return random.nextInt(10) + 10;
    }

    private List<List<Integer>> matchNumbers(List<List<Integer>> data) {
        return data.stream().map(list -> list.stream()
                .filter(i -> randomNumbers.getValue().contains(i))
                .collect(Collectors.toList())
        ).collect(Collectors.toList());
    }
}