package com.example.luckyapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luckyapp.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getData().observe(getViewLifecycleOwner(), this::showDataOnScreen);
        return root;
    }

    private void showDataOnScreen(List<List<Integer>> data) {
        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data.get(0));
        binding.list1.setAdapter(adapter1);

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data.get(1));
        binding.list2.setAdapter(adapter2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}