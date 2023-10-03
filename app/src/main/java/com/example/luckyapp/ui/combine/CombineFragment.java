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

        CombineViewModel combineViewModel = new CombineViewModel();

        binding.button.setOnClickListener(v -> {
            String name = binding.edit.getText().toString();
            Integer sum = combineViewModel.sumRandomPick(name);
            Toast.makeText(getContext(), "Resultado da soma: " + sum.toString(), Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}