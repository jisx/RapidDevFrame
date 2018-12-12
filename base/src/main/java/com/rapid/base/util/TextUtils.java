package com.rapid.base.util;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class TextUtils {
    public static boolean isEmpty(EditText text) {
        Editable editable = text.getText();
        if (editable == null) {
            return true;
        }

        return isEmpty(editable.toString());
    }

    public static boolean isEmpty(String text) {
        if (text == null) {
            return true;
        }

        if (text.isEmpty()) {
            return true;
        }

        return false;
    }


    public static boolean isEmpty(CharSequence sequence) {
        if (sequence == null) {
            return true;
        }
        if (sequence.length() == 0 || isEmpty(sequence.toString())) {
            return true;
        }
        return false;
    }


    public static boolean isEmpty(TextView textView) {

        if (textView == null) {
            return true;
        }
        return isEmpty(textView.getText());
    }

    public static String mobileMosaic(String mobile) {
        if (ValidationUtils.isMobile(mobile)) {
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }

        return mobile;

    }
}
