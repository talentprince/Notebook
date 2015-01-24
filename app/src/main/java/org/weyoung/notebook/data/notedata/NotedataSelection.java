/**
 * Copyright (C) Weyoung Notebook (talentprince.github.io)
 */
package org.weyoung.notebook.data.notedata;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import org.weyoung.notebook.data.base.AbstractSelection;

/**
 * Selection for the {@code notedata} table.
 */
public class NotedataSelection extends AbstractSelection<NotedataSelection> {
    @Override
    public Uri uri() {
        return NotedataColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code NotedataCursor} object, which is positioned before the first entry, or null.
     */
    public NotedataCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new NotedataCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public NotedataCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public NotedataCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public NotedataSelection id(long... value) {
        addEquals("notedata." + NotedataColumns._ID, toObjectArray(value));
        return this;
    }


    public NotedataSelection title(String... value) {
        addEquals(NotedataColumns.TITLE, value);
        return this;
    }

    public NotedataSelection titleNot(String... value) {
        addNotEquals(NotedataColumns.TITLE, value);
        return this;
    }

    public NotedataSelection titleLike(String... value) {
        addLike(NotedataColumns.TITLE, value);
        return this;
    }

    public NotedataSelection createTime(String... value) {
        addEquals(NotedataColumns.CREATE_TIME, value);
        return this;
    }

    public NotedataSelection createTimeNot(String... value) {
        addNotEquals(NotedataColumns.CREATE_TIME, value);
        return this;
    }

    public NotedataSelection createTimeLike(String... value) {
        addLike(NotedataColumns.CREATE_TIME, value);
        return this;
    }

    public NotedataSelection updateTime(String... value) {
        addEquals(NotedataColumns.UPDATE_TIME, value);
        return this;
    }

    public NotedataSelection updateTimeNot(String... value) {
        addNotEquals(NotedataColumns.UPDATE_TIME, value);
        return this;
    }

    public NotedataSelection updateTimeLike(String... value) {
        addLike(NotedataColumns.UPDATE_TIME, value);
        return this;
    }

    public NotedataSelection content(String... value) {
        addEquals(NotedataColumns.CONTENT, value);
        return this;
    }

    public NotedataSelection contentNot(String... value) {
        addNotEquals(NotedataColumns.CONTENT, value);
        return this;
    }

    public NotedataSelection contentLike(String... value) {
        addLike(NotedataColumns.CONTENT, value);
        return this;
    }
}
