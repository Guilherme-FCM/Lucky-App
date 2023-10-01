package com.example.luckyapp.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDataService {
    public static final String URL = "https://raw.githubusercontent.com/Guilherme-FCM/Lucky-App/master/app/src/main/res/data.json";

    public static List<List<Integer>> getJsonData() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<List<Integer>>>(){}.getType();
        try {
            HttpRequest request = new HttpRequest(URL);
            return gson.fromJson(request.get(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
