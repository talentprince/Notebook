/**
 * Copyright (C) Weyoung Notebook (talentprince.github.io)
 */
package org.weyoung.notebook.data.notedata;

import java.util.Date;

import android.database.Cursor;

import org.weyoung.notebook.data.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code notedata} table.
 */
public class NotedataCursor extends AbstractCursor {
    public NotedataCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    public String getTitle() {
        Integer index = getCachedColumnIndexOrThrow(NotedataColumns.TITLE);
        return getString(index);
    }

    /**
     * Get the {@code create_time} value.
     * Cannot be {@code null}.
     */
    public String getCreateTime() {
        Integer index = getCachedColumnIndexOrThrow(NotedataColumns.CREATE_TIME);
        return getString(index);
    }

    /**
     * Get the {@code update_time} value.
     * Cannot be {@code null}.
     */
    public String getUpdateTime() {
        Integer index = getCachedColumnIndexOrThrow(NotedataColumns.UPDATE_TIME);
        return getString(index);
    }

    /**
     * Get the {@code content} value.
     * Can be {@code null}.
     */
    public String getContent() {
        Integer index = getCachedColumnIndexOrThrow(NotedataColumns.CONTENT);
        return getString(index);
    }
}
