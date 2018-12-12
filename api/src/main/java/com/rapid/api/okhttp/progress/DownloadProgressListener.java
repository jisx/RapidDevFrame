package com.rapid.api.okhttp.progress;

/**
 * File description.
 */
public interface DownloadProgressListener {

    void onLoading(long totalLength, long totalBytesRead);
}
