package com.example.luckyapp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest<R> {
    HttpURLConnection connection;

    public HttpRequest(String address) throws IOException {
        URL url = new URL(address);
        connection = (HttpURLConnection) url.openConnection();
    }

    public R get() throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<R>(){}.getType();

        connection.setRequestMethod("GET");
        return gson.fromJson(makeRequest(), type);
    }

    public String convertToJson(InputStream input) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        String result = "";
        while ((result = bufferedReader.readLine()) != null) {
            stringBuilder.append(result).append("\n");
        }
        return stringBuilder.toString();
    }

    public String makeRequest() throws IOException {
        InputStream inputStream = connection.getInputStream();
        return convertToJson(new BufferedInputStream(inputStream));
    }
}
