package org.weyoung.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.weyoung.notebook.NotingActivity;
import org.weyoung.notebook.R;
import org.weyoung.notebook.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    List<Note> notes = new ArrayList<>();

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.note, null);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder noteViewHolder, int i) {
        noteViewHolder.populate(notes.get(i));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
