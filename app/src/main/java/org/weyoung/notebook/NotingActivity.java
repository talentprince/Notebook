package org.weyoung.notebook;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.weyoung.notebook.data.Note;
import org.weyoung.notebook.data.notedata.NotedataContentValues;
import org.weyoung.notebook.data.notedata.NotedataSelection;
import org.weyoung.notebook.utils.TimeUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class NotingActivity extends ActionBarActivity {

    @InjectView(R.id.title)
    EditText title;
    @InjectView(R.id.note)
    EditText noteView;
    @InjectView(R.id.save)
    Button save;

    Note oldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noting);
        ButterKnife.inject(this);
        if(getIntent().getSerializableExtra("note") != null) {
            oldNote = (Note) getIntent().getSerializableExtra("note");
            title.setText(oldNote.getTitle());
            noteView.setText(oldNote.getContent());
            save.setText(R.string.modify);
        }
        getSupportActionBar().setTitle(R.string.noting);
    }

    @OnClick(R.id.save)
    void save() {
        if(!checkData())
            return;
        NotedataContentValues contentValues = new NotedataContentValues();
        contentValues.putTitle(title.getText().toString()).putContent(noteView.getText().toString()).putUpdateTime(TimeUtil.getCurrentTime());
        if (oldNote != null) {
            NotedataSelection where = new NotedataSelection();
            where.title(oldNote.getTitle());
            contentValues.update(getContentResolver(), where);
        } else {
            contentValues.putCreateTime(TimeUtil.getCurrentTime());
            contentValues.insert(getContentResolver());
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_noting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            if(checkData()) {
                NotedataSelection where = new NotedataSelection();
                where.title(oldNote.getTitle());
                where.delete(getContentResolver());
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(title.getText().toString())) {
            Toast.makeText(this, R.string.titleneeded, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
