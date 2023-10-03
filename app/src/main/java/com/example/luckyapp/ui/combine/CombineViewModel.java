package com.example.luckyapp.ui.combine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.luckyapp.ui.draw.DrawViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombineViewModel extends ViewModel {
    public CombineViewModel() {

    }

    public Integer sumRandomPick(String name) {
        List<Integer> list = randomPick(name);
        return list.stream().mapToInt(i -> i).sum();
    }

    private List<Integer> randomPick(String name) {
        Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            int position = random.nextInt(DrawViewModel.randomList.size());
            list.add(DrawViewModel.randomList.get(position));
        }
        return list;
    }
}