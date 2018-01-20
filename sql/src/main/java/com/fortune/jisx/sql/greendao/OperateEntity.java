package com.fortune.jisx.sql.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;


/**
 * 操作日志，记录本地的操作日志
 *
 * @author jisx
 * @date Created in 10:35 2018/1/4
 * @modify By:
 */
@Entity
public class OperateEntity {

    @Id(autoincrement = true)
    private Long id;
    /**
     * 操作者
     */
    private String userCode;

    /**
     * 操作内容
     */
    private String content;
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
    @Generated(hash = 990413387)
    public OperateEntity(Long id, String userCode, String content, String model,
            int sdk, String imei, String systemVersion, Date createTime) {
        this.id = id;
        this.userCode = userCode;
        this.content = content;
        this.model = model;
        this.sdk = sdk;
        this.imei = imei;
        this.systemVersion = systemVersion;
        this.createTime = createTime;
    }
    @Generated(hash = 824659189)
    public OperateEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserCode() {
        return this.userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
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
