package utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 类的注释
 *
 * @Package utils
 * @ClassName JsonUtil
 * @Description Json转换工具
 * @Author liyuzhi
 * @Date 2018-05-07 14:02
 */

public class JsonUtil {
    public static <T> String toJson(Object object) {
        String s = null;
        try {
            Gson gson = new Gson();
            s = gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static <T> Object toObject(String jsonStr, Class<T> obj) {

        if (jsonStr != null) {
            T t;
            try {
                Gson gson = new Gson();
                t = gson.fromJson(jsonStr, obj);
                return t;
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                throw new JsonSyntaxException("");
            }
        }

        return null;
    }
}