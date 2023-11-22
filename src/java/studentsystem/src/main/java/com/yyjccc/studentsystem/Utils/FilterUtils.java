package com.yyjccc.studentsystem.Utils;

public  class FilterUtils {
    public static boolean argFilter(String args,String[] requires){
        for (int i = 0; i < requires.length; i++) {
            if(args.equals(requires[i])){
                return true;
            }
        }
        return false;
    }
}
