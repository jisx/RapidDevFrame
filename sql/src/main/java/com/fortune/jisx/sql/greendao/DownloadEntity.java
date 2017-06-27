package com.fortune.jisx.sql.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by jisx on 2017/6/20.
 */
@Entity
public class DownloadEntity {

    @Id(autoincrement = true)
    private Long id;

    private String url;

    private Long readLength;

    private Long TotalLength;

    private String hash;

    @Generated(hash = 336219858)
    public DownloadEntity(Long id, String url, Long readLength, Long TotalLength,
            String hash) {
        this.id = id;
        this.url = url;
        this.readLength = readLength;
        this.TotalLength = TotalLength;
        this.hash = hash;
    }

    @Generated(hash = 1671715506)
    public DownloadEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getReadLength() {
        return this.readLength;
    }

    public void setReadLength(Long readLength) {
        this.readLength = readLength;
    }

    public Long getTotalLength() {
        return this.TotalLength;
    }

    public void setTotalLength(Long TotalLength) {
        this.TotalLength = TotalLength;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
