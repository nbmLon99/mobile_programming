package com.nbmlon.a2022mobileprogrammingteamproject.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class myTypeConverter {

    @androidx.room.TypeConverter
    public static List<String> toList(String data) {
        if (data == null) {
            return Collections.EMPTY_LIST;
        }

        Type listType = new TypeToken<List<String>>() {}.getType();

        return new Gson().fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String fromList(List<String> someObjects) {
        return new Gson().toJson(someObjects);
    }
}
