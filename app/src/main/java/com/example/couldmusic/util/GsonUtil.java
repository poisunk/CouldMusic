package com.example.couldmusic.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    public static String toJson(Object obj) {
        String result = createGson().toJson(obj);
        return result;
    }

    private static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        return gsonBuilder.create();
    }


    /**
     * 将字符串转换成对应的Java对象
     */
    public static <T> T fromJSON(String json, Class<T> tClass) {
        try {
            return createGson().fromJson(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
