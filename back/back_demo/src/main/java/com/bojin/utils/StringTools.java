package com.bojin.utils;


import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author LiTeng
 * @create 2023/5/4
 */
public class StringTools {
    public static final  String getRandomNumber(Integer count){
        return RandomStringUtils.random(count,true, true);
    }

    public static boolean isEmpty(String str){
        if (null == str || "".equals(str) || "\u0000".equals(str)){
            return true;
        }else if ("".equals(str.trim())){
            return true;
        }
        return false;
    }

    public static boolean pathIsOk(String path) {
        if (StringTools.isEmpty(path)) {
            return true;
        }
        if (path.contains("../") || path.contains("..\\")) {
            return false;
        }
        return true;

    }

    public static String getFileSuffix(String fileName) {
        Integer index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        String suffix = fileName.substring(index);
        return suffix;
    }


    public static String getFileNameNoSuffix(String fileName) {
        Integer index = fileName.lastIndexOf(".");
        if (index == -1) {
            return fileName;
        }
        fileName = fileName.substring(0, index);
        return fileName;
    }

    public static String rename(String fileName) {
        String fileNameReal = getFileNameNoSuffix(fileName);
        String suffix = getFileSuffix(fileName);
        return fileNameReal + "_" + getRandomNumber(5) + suffix;
    }


}
