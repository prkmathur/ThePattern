package com.app.thenhpattern.util;

import android.util.Log;

import androidx.databinding.ViewDataBinding;

import java.util.regex.Pattern;

public class Utility {

    private static final Utility utility = new Utility();

    public static Utility getInstance(){
        return utility;
    }

    public void showLog(Class name,Object message){
        Log.d(name.getName(),message.toString());
    }

    public static Boolean checkNull(String s){
       if(s == null || s.trim().length() == 0){
           return false;
       }else{
           return true;
       }
    }

    public static Boolean passwordMatch(String s1,String s2){

        if(checkNull(s1) || checkNull(s2))
            return false;
        else{
            if(s1.trim().equalsIgnoreCase(s2.trim()))
                return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email.trim().length() == 0)
            return false;
        return pat.matcher(email).matches();
    }

}
