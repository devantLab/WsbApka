package com.example.peethr.wsbtest.models.converters;

import android.content.Context;

public class GetImageId {

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
