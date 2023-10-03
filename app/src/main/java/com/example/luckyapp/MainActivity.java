package com.example.luckyapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.luckyapp.ui.capture.CaptureViewModel;
import com.example.luckyapp.utils.FileStorage;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.luckyapp.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private final int PERMISSIONS_CODE = 1;
    private final String PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_draw,
                R.id.nav_combine,
                R.id.nav_capture
        ).setOpenableLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_store) requestWritePermission();
        else return super.onOptionsItemSelected(item);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void requestWritePermission() {
        int havePermission = ContextCompat.checkSelfPermission(this, PERMISSION);

        if (havePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, PERMISSIONS_CODE);
        } else saveDataOnFile();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_CODE)
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permissão Concedida! :)", Toast.LENGTH_SHORT).show();
            saveDataOnFile();
        }

        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION))
                requestWritePermission();

            else Toast.makeText(this, "Permissão NÃO Concedida :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDataOnFile() {
        CaptureViewModel captureViewModel = new CaptureViewModel();
        captureViewModel.getData().observe(this, (d) -> {
            String text = String.format(
                    "JUNTAR: %s \n" +
                    "DECRESCENTE: %s",
                    captureViewModel.join(),
                    captureViewModel.descending()
            );
            boolean couldWrite = FileStorage.write(text);

            String message = couldWrite ? "Dados salvos com sucesso no arquivo externo" : "Dados não puderam ser salvos";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}