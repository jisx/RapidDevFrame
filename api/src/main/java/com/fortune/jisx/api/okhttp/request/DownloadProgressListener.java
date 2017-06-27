package com.fortune.jisx.api.okhttp.request;

/**
 * File description.
 *
 * @author jisx
 * @date 2017/6/27
 */
public interface DownloadProgressListener {

    void onLoading(long totalLength, long totalBytesRead);
}
