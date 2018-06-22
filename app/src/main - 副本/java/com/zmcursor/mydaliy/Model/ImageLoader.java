package com.zmcursor.mydaliy.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.zmcursor.mydaliy.AppConfig;
import com.zmcursor.mydaliy.Model.Bean.BaseStory;
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Model.Bean.Story;
import com.zmcursor.mydaliy.Model.Bean.TopStory;
import com.zmcursor.mydaliy.Model.Storage.DataStorage;

import java.util.List;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class ImageLoader {

    private static final int maxSavedDate = 7;

    private Context context;
    private DateList dateList;

    public ImageLoader(Context context) {
        this.context = context;

        SharedPreferences sp = context.getSharedPreferences(AppConfig.save_data, Context.MODE_PRIVATE);
        dateList = new DateList(sp.getString(AppConfig.date_list_key, null));
    }

    public void saveDateList() {
        SharedPreferences sp = context.getSharedPreferences(AppConfig.save_data, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AppConfig.date_list_key, dateList.toString());
        editor.apply();
    }

    public void getTopImages(List<TopStory> topStories) {
        DataStorage.initTopImage(context);
        for (TopStory topStory : topStories) {
            if (topStory.getImageBitmap() == null) {
                Bitmap image = DataStorage.getImage(context, null, topStory.id);
                if (image == null) downloadImage(null, topStory, true);
            }
        }
        DataStorage.deleteOldTopImage(context);
    }

    public void getImages(StoriesOfDate storiesOfDate, String beforeDate) {
        String old = dateList.add(storiesOfDate.getDate(), beforeDate);
        boolean saveImage = true;
        boolean dateSaved = false;
        if ("0".equals(old)) saveImage = false;
        else if ("1".equals(old)) dateSaved = true;
        else if (old != null) DataStorage.deleteImageOfDate(context, old);

        for (Story story : storiesOfDate.getStories()) {
            if (story.getImageBitmap() == null) {
                Bitmap image = null;
                if (dateSaved)
                    image = DataStorage.getImage(context, storiesOfDate.getDate(), story.id);
                if (image == null) downloadImage(storiesOfDate.getDate(), story, saveImage);
            }
        }
    }

    private void downloadImage(String date, BaseStory story, boolean isSave) {
        DataLoader.download(false, story.getImageUrl(), data -> {
            if (data != null) {
                Bitmap image = (Bitmap) data;
                story.setImageBitmap(image);
                if (isSave) DataStorage.saveImage(context, image, date, story.id);
            }
        });
    }

    private class DateList {
        private String[] dateList;

        DateList(String list) {
            if (list != null) dateList = splitDate(list, '#');
            else dateList = new String[maxSavedDate];
        }

        String add(String date, String beforeDate) {
            int pos = 0;
            if (beforeDate != null) {
                pos = have(beforeDate) + 1;
                if (pos >= dateList.length) return "0";
            }
            if (!date.equals(dateList[pos])) {
                return add(pos, date);
            }
            return "1";
        }

        private int have(String date) {
            int pos = 0;
            while (pos < dateList.length && !date.equals(dateList[pos])) pos++;
            return pos;
        }

        private String add(int pos, String date) {
            String old;
            int i = dateList.length - 1;
            old = dateList[i];
            while (i > pos) {
                dateList[i] = dateList[--i];
            }
            dateList[i] = date;
            return old;
        }

        private String[] splitDate(String list, char regex) {
            String[] dates = new String[maxSavedDate];
            for (short i = 0, r = 0, l = 0; r < list.length(); r++) {
                if (list.charAt(r) == regex) {
                    dates[i] = list.substring(l, r);
                    l = ++r;
                    i++;
                }
            }
            return dates;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String s : dateList) {
                if (s == null) break;
                sb.append(s);
                sb.append('#');
            }
            return sb.toString();
        }
    }
}
