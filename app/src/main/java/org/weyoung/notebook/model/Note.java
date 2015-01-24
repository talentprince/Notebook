package org.weyoung.notebook.model;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class Note {
    private String title;
    private String createTime;
    private String modifyTime;
    private String content;

    public Note(String title, String createTime, String modifyTime, String content) {
        this.title = title;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public String getContent() {
        return content;
    }
}
