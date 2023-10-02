package com.example.luckyapp.ui.combine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luckyapp.databinding.FragmentCombineBinding;
import com.example.luckyapp.ui.draw.DrawViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombineFragment extends Fragment {

    private FragmentCombineBinding binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = FragmentCombineBinding.inflate(inflater, container, false);

        CombineViewModel combineViewModel =
                new ViewModelProvider(this).get(CombineViewModel.class);

        final TextView textView = binding.textSlideshow;
        combineViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Toast.makeText(getContext(), getRandomPickSum(), Toast.LENGTH_SHORT).show();
        return binding.getRoot();
    }

    private Integer getRandomPickSum() {
        List<Integer> list = randomPick();
        return list.stream().mapToInt(i -> i).sum();
    }

    private List<Integer> randomPick() {
        String name = "test";
        Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            int position = random.nextInt(DrawViewModel.randomList.size());
            list.add(DrawViewModel.randomList.get(position));
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}