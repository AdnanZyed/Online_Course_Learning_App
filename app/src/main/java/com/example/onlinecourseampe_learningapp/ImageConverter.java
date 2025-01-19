package com.example.onlinecourseampe_learningapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageConverter {

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        if (byteArray != null) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        return null; // تحقق من أن البيانات ليست null
    }
}
