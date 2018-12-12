package com.rapid.base.util;

/**
 * Created by jsx on 2016/4/11.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.rapid.base.base.BaseModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * SharedPreferences工具类
 * 使用的时候需要在Application中初始化
 */
public class SharePrefUtils {

    private SharePrefUtils() {
    }

    public static SharedPreferences getShared(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        return sp;
    }


    public static void setValue(Context context, String key, Object value) {
        Editor editor = getShared(context).edit();
        saveValue(key, value, editor);
        SharedPreferencesCompat.apply(editor);
    }

    public static void saveValue(String key, Object value, Editor sp) {
        if (value instanceof String) {
            sp.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            sp.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            sp.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            sp.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            sp.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            sp.putStringSet(key, (Set<String>) value);
        } else if (value instanceof BaseModel) {
            sp.putString(key, new Gson().toJson(value));
        } else if (value instanceof List) {
            sp.putString(key, new Gson().toJson(value));
        }
    }

    public static String getValue(Context context, String key, String defaultValue) {
        return getShared(context).getString(key, defaultValue);
    }

    public static boolean getValue(Context context, String key, boolean defaultValue) {
        return getShared(context).getBoolean(key, defaultValue);
    }

    public static float getValue(Context context, String key, float defaultValue) {
        return getShared(context).getFloat(key, defaultValue);
    }

    public static int getValue(Context context, String key, int defaultValue) {
        return getShared(context).getInt(key, defaultValue);
    }

    public static long getValue(Context context, String key, long defaultValue) {
        return getShared(context).getLong(key, defaultValue);
    }

    public static Set<String> getValue(Context context, String key, Set<String> defaultValue) {
        return getShared(context).getStringSet(key, defaultValue);
    }

    public static <T extends BaseModel> T getValue(Context context, String key, Class<T> cls) {
        String modelStr = getShared(context).getString(key, null);
        if (modelStr != null) {
            return new Gson().fromJson(modelStr, cls);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public void remove(Context context, String key) {
        Editor editor = getShared(context).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        Editor editor = getShared(context).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        return getShared(context).contains(key);
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
