package com.zmcursor.mydaliy.Model.Storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.zmcursor.mydaliy.AppConfig
import java.io.*

/**
 * Created by ZMcursor on 2018/5/31 0031.
 */

object DataStorage {

    private val TAG = "DataStorage"

    private val dirNameOfTopStories: String
        get() = AppConfig.image_dir + "top_stories"

    fun saveImage(context: Context, image: Bitmap, date: String, id: String) {
        Log.e(TAG, "saveImage:" + date + id)
        val imageFile = getImageFile(context, date, id)
        val imageDir = imageFile.parentFile
        try {
            //            if ((imageDir.exists() || imageDir.mkdirs()) && imageFile.createNewFile()) return;
            if (!imageDir.exists() && !imageDir.mkdirs()) return
            val fos = FileOutputStream(imageFile, false)
            image.compress(Bitmap.CompressFormat.WEBP, 75, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getImage(context: Context, date: String, id: String): Bitmap? {
        val imageFile = getImageFile(context, date, id)
        var image: Bitmap? = null
        if (imageFile.exists() && imageFile.isFile) {
            try {
                val fis = FileInputStream(imageFile)
                image = BitmapFactory.decodeStream(fis)
                Log.e(TAG, "getImage: " + imageFile.name)
            } catch (ignored: FileNotFoundException) {
            }
        }
        imageFile.setLastModified(System.currentTimeMillis())
        return image
    }

    fun deleteImageOfDate(context: Context, date: String) {
        val imageDir = File(context.filesDir, getDirNameOfDate(date))
        val files = imageDir.listFiles() ?: return
        for (file in files) {
            Log.e(TAG, "deleteImageOfDate:" + file.name)
            file.delete()
        }
        imageDir.delete()
    }

    fun initTopImage(context: Context) {
        Log.e(TAG, "initTopImage")
        val imageDir = File(context.filesDir, dirNameOfTopStories)
        val files = imageDir.listFiles() ?: return
        for (imageFile in files) {
            imageFile.setLastModified(0)
        }
    }

    fun deleteOldTopImage(context: Context) {
        Log.e(TAG, "deleteOldTopImage")
        val imageDir = File(context.filesDir, dirNameOfTopStories)
        val files = imageDir.listFiles() ?: return
        files.filter { it.lastModified() < 10000 }.forEach { it.delete() }
    }

    private fun getImageFile(context: Context, date: String?, id: String): File {
//        val imageFile: File = if (date == null) {
//            File(context.filesDir, dirNameOfTopStories + "/" + id)
//        } else {
//            File(context.filesDir, getDirNameOfDate(date) + "/" + id)
//        }
        return if (date == null) {
            File(context.filesDir, dirNameOfTopStories + "/" + id)
        } else {
            File(context.filesDir, getDirNameOfDate(date) + "/" + id)
        }
    }

    private fun getDirNameOfDate(date: String): String {
        return AppConfig.image_dir + "Date" + date
    }
}
