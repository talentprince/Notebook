/**
 * Copyright (C) Weyoung Notebook (talentprince.github.io)
 */
package org.weyoung.notebook.data.notedata;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import org.weyoung.notebook.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code notedata} table.
 */
public class NotedataContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return NotedataColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, NotedataSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public NotedataContentValues putTitle(String value) {
        if (value == null) throw new IllegalArgumentException("value for title must not be null");
        mContentValues.put(NotedataColumns.TITLE, value);
        return this;
    }



    public NotedataContentValues putCreateTime(String value) {
        if (value == null) throw new IllegalArgumentException("value for createTime must not be null");
        mContentValues.put(NotedataColumns.CREATE_TIME, value);
        return this;
    }



    public NotedataContentValues putUpdateTime(String value) {
        if (value == null) throw new IllegalArgumentException("value for updateTime must not be null");
        mContentValues.put(NotedataColumns.UPDATE_TIME, value);
        return this;
    }



    public NotedataContentValues putContent(String value) {
        mContentValues.put(NotedataColumns.CONTENT, value);
        return this;
    }

    public NotedataContentValues putContentNull() {
        mContentValues.putNull(NotedataColumns.CONTENT);
        return this;
    }

}
