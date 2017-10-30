package cn.shield.checkin.other;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 13:55<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class PreferenceUtils {

    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defValue) {

        if (defValue instanceof String) {
            return (T) getPreference().getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            Integer value = getPreference().getInt(key, (Integer) defValue);
            return (T) value;
        } else if (defValue instanceof Boolean) {
            Boolean value = getPreference().getBoolean(key, (Boolean) defValue);
            return (T) value;
        } else if (defValue instanceof Float) {
            Float value = getPreference().getFloat(key, (Float) defValue);
            return (T) value;
        } else if (defValue instanceof Double) {
            Float value = getPreference().getFloat(key, ((Double) defValue).floatValue());
            Double dValue = value.doubleValue();
            return (T) dValue;
        } else if (defValue instanceof Long) {
            Long value = getPreference().getLong(key, (Long) defValue);
            return (T) value;
        } else if (defValue instanceof Set) {
            return (T) getPreference().getStringSet(key, (Set<String>) defValue);
        }
        return (T) new Object();
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean save(String key, T value) {
        SharedPreferences.Editor editor = getPreference().edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else if (value instanceof Double) {
            editor.putFloat(key, ((Double) value).floatValue());
        }
        return editor.commit();
    }

    private static SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(Application.mContext);
    }
}
