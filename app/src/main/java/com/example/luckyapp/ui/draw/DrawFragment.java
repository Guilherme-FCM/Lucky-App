package com.example.luckyapp.ui.draw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luckyapp.databinding.FragmentDrawBinding;

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

        drawViewModel.getRandomNumbers().observe(getViewLifecycleOwner(), (d) -> {
            Toast.makeText(getContext(), "Random nums " + d.toString(), Toast.LENGTH_SHORT).show();
        });
        drawViewModel.getMatchedNumbers().observe(getViewLifecycleOwner(), (d) -> {
            Toast.makeText(getContext(), "Mached nums " + d.toString(), Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}