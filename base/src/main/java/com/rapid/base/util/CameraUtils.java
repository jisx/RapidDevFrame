package com.rapid.base.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

public class CameraUtils {

    public static final int CAMERA_REQUEST_CODE = 202;
    public static final int ALBUM_REQUEST_CODE = 203;

    /**
     * 打开相机
     *
     * @param context
     * @param requestCode
     * @return
     */
    public static void openCamera(Activity context, int requestCode, String picturePath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(picturePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        Uri uriForFile = UriUtils.getUriForFile(context, file);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            /**
             * 指定拍照存储路径
             * 7.0 及其以上使用FileProvider替换'file://'访问
             */
            if (Build.VERSION.SDK_INT >= 24) {
                //这里的BuildConfig，需要是程序包下BuildConfig。
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            }
            context.startActivityForResult(intent, requestCode);
        }
    }
    /**
     * 打开相机
     *
     * @param context
     * @param requestCode
     * @return
     */
    public static void openCamera(Activity context, int requestCode, File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        Uri uriForFile = UriUtils.getUriForFile(context, file);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            /**
             * 指定拍照存储路径
             * 7.0 及其以上使用FileProvider替换'file://'访问
             */
            if (Build.VERSION.SDK_INT >= 24) {
                //这里的BuildConfig，需要是程序包下BuildConfig。
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            }
            context.startActivityForResult(intent, requestCode);
        }
    }

    public static void openCamera(Activity mContext, File file) {
        openCamera(mContext, CAMERA_REQUEST_CODE,file);
    }

    /**
     * 获取相册
     */
    public static void choosePhoto(Activity activity) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        activity.startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

}
