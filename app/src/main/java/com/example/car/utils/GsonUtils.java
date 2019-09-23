package com.example.car.utils;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by Harrison.Pan on 2016/09/16.
 */
public class GsonUtils {
    @NonNull
    private static GsonUtils gsonUtil = new GsonUtils();
    private Gson mGson;

    private GsonUtils() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(Double.class, new DoubleTypeSerializer())
                .registerTypeAdapter(String.class, new StringTypeDeserializer());
        this.mGson = gsonBuilder.create();
    }

    public static <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
        return gsonUtil.mGson.getAdapter(type);
    }

    public static <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        return gsonUtil.mGson.getDelegateAdapter(skipPast, type);
    }

    public static <T> TypeAdapter<T> getAdapter(@NonNull Class<T> type) {
        return gsonUtil.mGson.getAdapter(type);
    }

    public static JsonElement toJsonTree(Object src) {
        return gsonUtil.mGson.toJsonTree(src);
    }

    public static JsonElement toJsonTree(Object src, @NonNull Type typeOfSrc) {
        return gsonUtil.mGson.toJsonTree(src, typeOfSrc);
    }

    public static String toJson(Object src) {
        return gsonUtil.mGson.toJson(src);
    }

    public static String toJson(Object src, @NonNull Type typeOfSrc) {
        return gsonUtil.mGson.toJson(src, typeOfSrc);
    }

    public static void toJson(Object src, Appendable writer) throws JsonIOException {
        gsonUtil.mGson.toJson(src, writer);
    }

    public static void toJson(Object src, @NonNull Type typeOfSrc, Appendable writer) throws JsonIOException {
        gsonUtil.mGson.toJson(src, typeOfSrc, writer);
    }

    public static void toJson(Object src, @NonNull Type typeOfSrc, @NonNull JsonWriter writer) throws JsonIOException {
        gsonUtil.mGson.toJson(src, typeOfSrc, writer);
    }

    public static String toJson(JsonElement jsonElement) {
        return gsonUtil.mGson.toJson(jsonElement);
    }

    public static void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        gsonUtil.mGson.toJson(jsonElement, writer);
    }

    public static void toJson(JsonElement jsonElement, @NonNull JsonWriter writer) throws JsonIOException {
        gsonUtil.mGson.toJson(jsonElement, writer);
    }

    public static <T> T fromJson(String json, @NonNull Class<T> classOfT) throws JsonSyntaxException {
        return gsonUtil.mGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return gsonUtil.mGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(@NonNull Reader json, @NonNull Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return gsonUtil.mGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(@NonNull Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return gsonUtil.mGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(@NonNull JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return gsonUtil.mGson.fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(JsonElement json, @NonNull Class<T> classOfT) throws JsonSyntaxException {
        return gsonUtil.mGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        return gsonUtil.mGson.fromJson(json, typeOfT);
    }

    /**
     * 解决其他类型的数据无法被解析成String类型的问题
     */
    class StringTypeDeserializer implements JsonDeserializer<String> {
        @Override
        public String deserialize(
                JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            if (!(json instanceof JsonPrimitive)) {
                return json.toString();
            }
            return json.getAsString();
        }
    }

    /**
     * 解决Long类型序列化过程中被解析成Double类型的问题
     */
    class DoubleTypeSerializer implements JsonSerializer<Double> {
        @Override
        public JsonElement serialize(Double src,
                                     Type typeOfSrc,
                                     JsonSerializationContext context) {
            if (src == src.longValue()) {
                return new JsonPrimitive(src.longValue());
            }
            return new JsonPrimitive(src);
        }
    }
}
