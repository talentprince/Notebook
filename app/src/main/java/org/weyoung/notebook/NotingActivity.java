package org.weyoung.notebook;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_noting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
