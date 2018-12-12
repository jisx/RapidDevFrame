package com.rapid.api.okhttp.progress;

/**
 * File description.
 *
 * @author jisx
 */
public interface UploadProgressListener {
    void onProgress(long bytesWritten, long contentLength, boolean hasFinish);
}
