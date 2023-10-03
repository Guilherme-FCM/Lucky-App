package com.example.luckyapp.ui.capture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luckyapp.databinding.FragmentCaptureBinding;

public class CaptureFragment extends Fragment {

    private FragmentCaptureBinding binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCaptureBinding.inflate(inflater, container, false);

        CaptureViewModel captureViewModel =
                new ViewModelProvider(this).get(CaptureViewModel.class);

        captureViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            binding.join.setOnClickListener(v -> { setResult(captureViewModel.join()); });
            binding.major.setOnClickListener(v -> { setResult(captureViewModel.major().toString()); });
            binding.average.setOnClickListener(v -> { setResult(captureViewModel.average().toString()); });
            binding.descending.setOnClickListener(v -> { setResult(captureViewModel.descending().toString()); });
        });

        return binding.getRoot();
    }

    private void setResult(String text) {
        binding.result.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}