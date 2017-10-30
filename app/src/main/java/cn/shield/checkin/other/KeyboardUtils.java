package cn.shield.checkin.other;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static cn.shield.checkin.other.Application.mContext;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 14:18<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class KeyboardUtils {

    public static void closeKeyboard(View field) {
        try {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(field.getWindowToken(), 0);
        } catch (Exception ex) {
            Log.e("HIDETAG", "Error occurred trying to hide the keyboard.  Exception=" + ex);
        }
    }

    public static void showKeyboard(View field) {
        try {
            field.requestFocus();
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(field, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception ex) {
            Log.e("HIDETAG", "Error occurred trying to show the keyboard.  Exception=" + ex);
        }
    }
}
