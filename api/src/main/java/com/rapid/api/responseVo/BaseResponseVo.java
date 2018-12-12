package com.rapid.api.responseVo;

public class BaseResponseVo<T> {
    /**
     * 前台自定义 code = 0 ，是请求抛出了异常。
     */
    private int code;

    private String message;

    private T content;

    private Throwable mThrowable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return getCode() == 200;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + (content == null ? "" : content.toString()) +
                ", mThrowable=" + mThrowable +
                '}';
    }
}
