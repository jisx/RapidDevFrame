package com.fortune.jisx.api.okhttp.request;

/**
 * File description.
 *
 * @author jisx
 * @date 2017/6/27
 */
public interface UploadProgressListener {
    void onProgress(long bytesWritten, long contentLength, boolean hasFinish);
}
