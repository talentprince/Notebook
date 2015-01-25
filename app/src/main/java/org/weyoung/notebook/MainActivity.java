package org.weyoung.notebook;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.weyoung.notebook.data.Note;
import org.weyoung.notebook.data.notedata.NotedataColumns;
import org.weyoung.notebook.data.notedata.NotedataCursor;
import org.weyoung.notebook.utils.SyncUtils;
import org.weyoung.notebook.ui.NoteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @InjectView(R.id.notes)
    RecyclerView recyclerView;
    @InjectView(R.id.add)
    View addButton;

    private NoteRecyclerAdapter noteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        
        initNoteList();

        getLoaderManager().initLoader(0, null, this);
    }

    private void initNoteList() {
        noteRecyclerAdapter = new NoteRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteRecyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SyncUtils.CheckAccount(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sync) {
            SyncUtils.TriggerRefresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add)
    void addNote() {
        Intent intent = new Intent(this, NotingActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this);
        cursorLoader.setUri(NotedataColumns.CONTENT_URI);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<Note> notes = new ArrayList<>();
        NotedataCursor cursor = new NotedataCursor(data);
        while(cursor.moveToNext()) {
            notes.add(new Note(cursor.getTitle(), cursor.getCreateTime(), cursor.getUpdateTime(), cursor.getContent()));
        }
        noteRecyclerAdapter.setNotes(notes);
        noteRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
