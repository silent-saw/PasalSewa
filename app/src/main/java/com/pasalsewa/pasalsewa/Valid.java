package com.pasalsewa.pasalsewa;

/**
 * Created by lazyboy on 11/20/17.
 */

public  class Valid {

    public static boolean checkEmptyString(String s){

        if(s.trim().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }


}
