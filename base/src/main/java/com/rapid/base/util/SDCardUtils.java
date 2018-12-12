package com.rapid.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * SD卡工具箱
 */
public class SDCardUtils {
    /**
     * 获取SD卡的状态
     */
    public static String getState() {
        return Environment.getExternalStorageState();
    }


    /**
     * SD卡是否可用
     *
     * @return 只有当SD卡已经安装并且准备好了才返回true
     */
    public static boolean isAvailable() {
        return getState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取SD卡的根目录
     *
     * @return null：不存在SD卡
     */
    public static File getRootDirectory() {
        return isAvailable() ? Environment.getExternalStorageDirectory() : null;
    }


    /**
     * 获取SD卡的根路径
     *
     * @return null：不存在SD卡
     */
    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        return rootDirectory != null ? rootDirectory.getPath() : null;
    }

    /**
     * 获取sd卡路径
     *
     * @return Stringpath
     */
    public static String getSDPath() {

        File sdDir;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        } else {
            sdDir = Environment.getDownloadCacheDirectory();
        }
        return sdDir.toString();

    }

    /**
     * 获取sd卡路径
     *
     * @param path 不需要传绝对路径，只需要传sd根目录之后的文件路径
     * @return Stringpath
     */
    public static boolean createDir(String path) {

        File file = new File(getSDPath() + path);

        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

    /**
     * 从assets复制文件至sd卡中
     *
     * @param context
     * @param fileName assets文件的名字
     * @param path     sd卡的路径
     * @return
     */
    public static boolean copyFileFromAssets(Context context, String fileName, String path) {

        try {
            File newFile = new File(path + fileName);
            if (newFile.exists()) {
                return true;
            } else {
                newFile.getParentFile().mkdirs();
                newFile.createNewFile();
            }

            InputStream is = context.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void saveBitmap(Bitmap btImage,String path) {
        FileOutputStream out = null;
        File file = new File(path);
        try {
            out = new FileOutputStream(file);
            btImage.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}