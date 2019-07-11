package com.app.thenhpattern.util;

import android.content.SharedPreferences;

import com.app.thenhpattern.model.vo.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class SessionManager {

    SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;
    public static final String USER = "user";
    public static final String IS_USER_LOGGED_IN = "isUserLoggedIn";

    @Inject
    public SessionManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = this.sharedPreferences.edit();
    }

    public Boolean setUser(User.CurrentUser user){
        if(user != null) {
            Gson gson = new Gson();
            editor.putString(USER, gson.toJson(user));
            editor.putBoolean(IS_USER_LOGGED_IN, true);
            if(editor.commit()){
                    return true;
            }
        }

        return false;
    }


    public Boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN,false);
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

    public User.CurrentUser getUser(){

        if(getCurrentUser() != null){
            return getCurrentUser();
        }
        return null;
    }

    private User.CurrentUser getCurrentUser(){
        Gson gson = new Gson();

        Type collectionType = new TypeToken<User.CurrentUser>(){}.getType();

        String user = sharedPreferences.getString(USER,null);

        if(user != null) {

            User.CurrentUser currentUser = gson.fromJson(sharedPreferences.getString(USER, null), collectionType);

            return currentUser;
        }

        return null;
    }

}
