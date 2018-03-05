package com.future.awaker.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Copyright ©2017 by ruzhan
 */

@Entity(tableName = "banner")
public class BannerItem {

    @NonNull
    @PrimaryKey
    public String id;
    public String title;
    public String content;
    public String img_url;
    public String out_url;
    public String newsid;
    public String adv_type;
    public String status;
    public String sort;

    public BannerItem(@NonNull String id, String title, String content,
                      String img_url, String out_url, String newsid,
                      String adv_type, String status, String sort) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img_url = img_url;
        this.out_url = out_url;
        this.newsid = newsid;
        this.adv_type = adv_type;
        this.status = status;
        this.sort = sort;
    }
}
