package org.weyoung.notebook.data;

import java.io.Serializable;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class Note implements Serializable{
    private String title;
    private String createTime;
    private String updateTime;
    private String content;

    public Note(String title, String createTime, String updateTime, String content) {
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getContent() {
        return content;
    }
}
