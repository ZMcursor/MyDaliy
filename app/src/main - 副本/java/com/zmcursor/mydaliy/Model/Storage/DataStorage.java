package com.zmcursor.mydaliy.Model.Storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.zmcursor.mydaliy.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ZMcursor on 2018/5/31 0031.
 */

public class DataStorage {

    private static final String TAG = "DataStorage";

    public static void saveImage(Context context, Bitmap image, String date, String id) {
        Log.e(TAG, "saveImage:" + date + id);
        File imageFile = getImageFile(context, date, id);
        File imageDir = imageFile.getParentFile();
        try {
//            if ((imageDir.exists() || imageDir.mkdirs()) && imageFile.createNewFile()) return;
            if (!imageDir.exists() && !imageDir.mkdirs()) return;
            FileOutputStream fos = new FileOutputStream(imageFile, false);
            image.compress(Bitmap.CompressFormat.WEBP, 75, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getImage(Context context, String date, String id) {
        File imageFile = getImageFile(context, date, id);
        Bitmap image = null;
        if (imageFile.exists() && imageFile.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(imageFile);
                image = BitmapFactory.decodeStream(fis);
                Log.e(TAG, "getImage: " + imageFile.getName());
            } catch (FileNotFoundException ignored) {
            }
        }
        imageFile.setLastModified(System.currentTimeMillis());
        return image;
    }

    public static void deleteImageOfDate(Context context, String date) {
//        Log.e(TAG, "deleteImageOfDate");
        File imageDir = new File(context.getFilesDir(), getDirNameOfDate(date));
        File[] files = imageDir.listFiles();
        if (files == null) return;
        for (File file : files) {
            Log.e(TAG, "deleteImageOfDate:" + file.getName());
            file.delete();
        }
        imageDir.delete();
    }

    public static void initTopImage(Context context) {
        Log.e(TAG, "initTopImage");
        File imageDir = new File(context.getFilesDir(), getDirNameOfTopStories());
        File[] files = imageDir.listFiles();
        if (files == null) return;
        for (File imageFile : files) {
            imageFile.setLastModified(0);
        }
    }

    public static void deleteOldTopImage(Context context) {
        Log.e(TAG, "deleteOldTopImage");
        File imageDir = new File(context.getFilesDir(), getDirNameOfTopStories());
        File[] files = imageDir.listFiles();
        if (files == null) return;
        for (File imageFile : files) {
            if (imageFile.lastModified() < 10000) imageFile.delete();
        }
    }

    private static File getImageFile(Context context, String date, String id) {
        File imageFile;
        if (date == null) {
            imageFile = new File(context.getFilesDir(), getDirNameOfTopStories() + "/" + id);
        } else {
            imageFile = new File(context.getFilesDir(), getDirNameOfDate(date) + "/" + id);
        }
        return imageFile;
    }

    private static String getDirNameOfDate(String date) {
        return AppConfig.image_dir + "Date" + date;
    }

    private static String getDirNameOfTopStories() {
        return AppConfig.image_dir + "top_stories";
    }
}
