package com.example.peethr.wsbtest.models.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageSharedPreferences {

    public static final String PREF_FILE = "com.example.peethr.wsbtest.preferences";
    public static final String KEY_LANGUAGE = "KEY_LANGUAGE";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    public ManageSharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_FILE, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean checkLanguage()
    {
        if(sharedPreferences.getString(KEY_LANGUAGE, "default").equals("default"))
        {
            return false;
        }
        else return true;
    }

    public void setLanguage(String language)
    {
        editor.putString(KEY_LANGUAGE, language);
        editor.apply();
    }

    public String getLanguage()
    {
        return sharedPreferences.getString(KEY_LANGUAGE, "default");
    }

}
