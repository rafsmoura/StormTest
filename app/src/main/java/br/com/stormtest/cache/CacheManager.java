package br.com.stormtest.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.stormtest.models.Content;

/**
 * Created by root on 05/04/16.
 */
public class CacheManager {

    private static CacheManager ourInstance = new CacheManager();

    private final String SP_NAME = "StormTest";
    private final String FAVORITES_KEY = "Favorites";
    private final String REQUEST_KEY = "Request";

    private Context context;


    public static CacheManager getInstance() {
        return ourInstance;
    }

    private CacheManager() {
    }

    public Boolean addToFavorites(Context context, Content content) {

        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        String jsonContents = preferences.getString(FAVORITES_KEY, null);

        List<Content> contents;

        if (TextUtils.isEmpty(jsonContents)) {

            contents = new ArrayList<>();
            contents.add(content);

            Gson gson = new Gson();

            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            String serializedJson = gson.toJson(contents, contentType);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FAVORITES_KEY, serializedJson);
            editor.apply();

            return true;

        } else {

            Gson gson = new Gson();

            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            contents = gson.fromJson(jsonContents, contentType);

            for (Content favorite : contents) {

                if (favorite.getId() == content.getId()) {
                    removeFavorite(context, content);
                    return false;
                }
            }

            contents.add(content);

            String serializedJson = gson.toJson(contents, contentType);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FAVORITES_KEY, serializedJson);
            editor.apply();

            return true;

        }

    }

    public List<Content> getFavorites(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        String jsonContents = preferences.getString(FAVORITES_KEY, null);

        List<Content> contents;

        if (!TextUtils.isEmpty(jsonContents)) {

            Gson gson = new Gson();
            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            contents = gson.fromJson(jsonContents, contentType);

            return contents;

        } else {

            return new ArrayList<>();
        }

    }

    public void removeFavorite(Context context, Content content) {
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        String jsonContents = preferences.getString(FAVORITES_KEY, null);

        List<Content> contents;

        if (!TextUtils.isEmpty(jsonContents)) {

            Gson gson = new Gson();
            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            contents = gson.fromJson(jsonContents, contentType);

            for (Content favorite : contents) {

                if (favorite.getId() == content.getId()) {
                    contents.remove(favorite);
                    break;
                }
            }

            String serializedJson = gson.toJson(contents, contentType);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FAVORITES_KEY, serializedJson);
            editor.apply();

        }
    }

    public boolean isFavorite(Context context, Content content) {
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        String jsonContents = preferences.getString(FAVORITES_KEY, null);

        List<Content> contents;

        if (TextUtils.isEmpty(jsonContents)) {
            return false;
        } else {
            Gson gson = new Gson();
            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            contents = gson.fromJson(jsonContents, contentType);


            for (Content favorite : contents) {

                if (favorite.getId() == content.getId()) {
                    return true;
                }
            }

            return false;
        }
    }

    public void saveRequest(Context context, List<Content> request) {

        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();

        Type contentType = new TypeToken<ArrayList<Content>>() {
        }.getType();

        String serializedJson = gson.toJson(request, contentType);

        ReturnWrapper wrapper = new ReturnWrapper(serializedJson, System.currentTimeMillis());

        String jsonWrapper = gson.toJson(wrapper, ReturnWrapper.class);

        editor.putString(REQUEST_KEY, jsonWrapper);
        editor.apply();

    }

    public boolean savedRequestIsValid(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        SharedPreferences.Editor editor = preferences.edit();

        String jsonWrapper = preferences.getString(REQUEST_KEY, null);


        if (TextUtils.isEmpty(jsonWrapper)) {
            return false;
        } else {

            Gson gson = new Gson();
            ReturnWrapper wrapper = gson.fromJson(jsonWrapper, ReturnWrapper.class);
            long fiveMin = 1000 * 60 * 5;
            if (System.currentTimeMillis() - wrapper.getTime() > fiveMin) {
                return false;
            } else {
                return true;
            }

        }


    }


    public List<Content> getContents(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, 1);

        SharedPreferences.Editor editor = preferences.edit();

        String jsonWrapper = preferences.getString(REQUEST_KEY, null);

        if (!TextUtils.isEmpty(jsonWrapper)) {

            Gson gson = new Gson();
            ReturnWrapper wrapper = gson.fromJson(jsonWrapper, ReturnWrapper.class);

            Type contentType = new TypeToken<ArrayList<Content>>() {
            }.getType();

            return gson.fromJson(wrapper.getRequestReturn(), contentType);

        } else {
            return new ArrayList<>();
        }

    }


}
