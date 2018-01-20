package com.fortune.jisx.sql.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * 日志类，记录本地的系统日志
 *
 * @author jisx
 * @date Created in 10:35 2018/1/4
 * @modify By:
 */  
@Entity
public class LogEntity {

    @Id(autoincrement = true)
    private Long id;
    /**
     * 所在类名
     */
    private String className;

    /**
     * 日志信息
     */
    private String message;
    /**
     * 获取手机厂商
     */
    private String deviceBrand;
    /**
     * 手机型号
     */
    private String model;
    /**
     * sdk的版本
     */
    private int sdk;
    /**
     * 手机IMEI
     */
    private String imei;
    /**
     * 手机版本号
     */
    private String systemVersion;
    /**
     * 创建时间
     */
    private Date createTime;
    @Generated(hash = 1117729741)
    public LogEntity(Long id, String className, String message, String deviceBrand,
            String model, int sdk, String imei, String systemVersion,
            Date createTime) {
        this.id = id;
        this.className = className;
        this.message = message;
        this.deviceBrand = deviceBrand;
        this.model = model;
        this.sdk = sdk;
        this.imei = imei;
        this.systemVersion = systemVersion;
        this.createTime = createTime;
    }
    @Generated(hash = 1472642729)
    public LogEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClassName() {
        return this.className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDeviceBrand() {
        return this.deviceBrand;
    }
    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getSdk() {
        return this.sdk;
    }
    public void setSdk(int sdk) {
        this.sdk = sdk;
    }
    public String getImei() {
        return this.imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getSystemVersion() {
        return this.systemVersion;
    }
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
