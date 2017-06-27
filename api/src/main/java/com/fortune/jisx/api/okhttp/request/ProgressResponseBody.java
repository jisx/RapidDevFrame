package com.fortune.jisx.api.okhttp.request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 上传进度
 *
 * @author jisx
 * @date 2017/6/27
 */
public class ProgressResponseBody extends ResponseBody {
    private ResponseBody mResponseBody;
    private DownloadProgressListener mProgressListener;
    private BufferedSource mBufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, DownloadProgressListener progressListener) {
        this.mResponseBody = responseBody;
        this.mProgressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null) {
            mBufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return mBufferedSource;
    }

    /**
     * 回调进度接口
     *
     * @param source
     * @return Source
     */
    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                mProgressListener.onLoading(mResponseBody.contentLength(), totalBytesRead);
                return bytesRead;
            }
        };
    }
}
