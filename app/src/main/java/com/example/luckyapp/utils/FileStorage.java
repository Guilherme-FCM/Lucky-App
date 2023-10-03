package com.example.luckyapp.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class FileStorage {
    private static final String FILE_NAME = "file.txt";
    private static final String FOLDER_NAME = Environment.DIRECTORY_DOWNLOADS;

    public static boolean write(String text) {
        File parentFolder = Environment.getExternalStoragePublicDirectory(FOLDER_NAME);
        File file = new File(parentFolder, FILE_NAME);
        byte[] data = text.getBytes();

        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
