package com.example.luckyapp.ui.draw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.luckyapp.databinding.FragmentDrawBinding;

import java.util.List;

public class DrawFragment extends Fragment {

    private FragmentDrawBinding binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDrawBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DrawViewModel drawViewModel = new DrawViewModel();

        drawViewModel.getMatchedNumbers().observe(getViewLifecycleOwner(), this::showMatchListInfo);
        drawViewModel.getRandomNumbers().observe(getViewLifecycleOwner(), this::showRandomList);

        return root;
    }

    private void showRandomList(List<Integer> data) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data);
        binding.list.setAdapter(adapter);
    }

    private void showMatchListInfo(List<List<Integer>> data) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            List<Integer> list = data.get(i);
            builder.append(list.size() + " números compatíveis no vetor " + (i + 1) + ": " + list + "\n");
        }

        binding.matchListInfo.setText(builder.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}