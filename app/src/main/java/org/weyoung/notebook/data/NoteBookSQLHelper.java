/**
 * Copyright (C) Weyoung Notebook (talentprince.github.io)
 */
package org.weyoung.notebook.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import org.weyoung.notebook.BuildConfig;
import org.weyoung.notebook.data.notedata.NotedataColumns;

public class NoteBookSQLHelper extends SQLiteOpenHelper {
    private static final String TAG = NoteBookSQLHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;
    private static NoteBookSQLHelper sInstance;
    private final Context mContext;
    private final NoteBookSQLHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    private static final String SQL_CREATE_TABLE_NOTEDATA = "CREATE TABLE IF NOT EXISTS "
            + NotedataColumns.TABLE_NAME + " ( "
            + NotedataColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NotedataColumns.TITLE + " TEXT NOT NULL, "
            + NotedataColumns.CREATE_TIME + " TEXT NOT NULL, "
            + NotedataColumns.UPDATE_TIME + " TEXT NOT NULL, "
            + NotedataColumns.CONTENT + " TEXT "
            + ", CONSTRAINT unique_title UNIQUE (title) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_INDEX_NOTEDATA_TITLE = "CREATE INDEX IDX_NOTEDATA_TITLE "
            + " ON " + NotedataColumns.TABLE_NAME + " ( " + NotedataColumns.TITLE + " );";

    // @formatter:on

    public static NoteBookSQLHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static NoteBookSQLHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */

    private static NoteBookSQLHelper newInstancePreHoneycomb(Context context) {
        return new NoteBookSQLHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    private NoteBookSQLHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        mOpenHelperCallbacks = new NoteBookSQLHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static NoteBookSQLHelper newInstancePostHoneycomb(Context context) {
        return new NoteBookSQLHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private NoteBookSQLHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new NoteBookSQLHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_NOTEDATA);
        db.execSQL(SQL_CREATE_INDEX_NOTEDATA_TITLE);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
