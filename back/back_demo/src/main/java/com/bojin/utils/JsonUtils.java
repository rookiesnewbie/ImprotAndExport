package com.bojin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Author LiTeng
 * @Date 2023/9/19 16:25
 * Version 1.0
 * @Description
 */
public class JsonUtils {

    public static String convertObj2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T convertJson2Obj(String json, Class<T> classz) {
        return JSONObject.parseObject(json, classz);
    }

    public static <T> List<T> convertJsonArray2List(String json, Class<T> classz) {
        return JSONArray.parseArray(json, classz);
    }
}
