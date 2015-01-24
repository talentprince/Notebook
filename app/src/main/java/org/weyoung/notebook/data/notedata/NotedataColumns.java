/**
 * Copyright (C) Weyoung Notebook (talentprince.github.io)
 */
package org.weyoung.notebook.data.notedata;

import android.net.Uri;
import android.provider.BaseColumns;

import org.weyoung.notebook.data.NotebookProvider;
import org.weyoung.notebook.data.notedata.NotedataColumns;

/**
 * Columns for the {@code notedata} table.
 */
public class NotedataColumns implements BaseColumns {
    public static final String TABLE_NAME = "notedata";
    public static final Uri CONTENT_URI = Uri.parse(NotebookProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = new String(BaseColumns._ID);

    public static final String TITLE = "title";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String CONTENT = "content";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            CREATE_TIME,
            UPDATE_TIME,
            CONTENT
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c == TITLE || c.contains("." + TITLE)) return true;
            if (c == CREATE_TIME || c.contains("." + CREATE_TIME)) return true;
            if (c == UPDATE_TIME || c.contains("." + UPDATE_TIME)) return true;
            if (c == CONTENT || c.contains("." + CONTENT)) return true;
        }
        return false;
    }

}
