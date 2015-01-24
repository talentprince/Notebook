package org.weyoung.notebook.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.weyoung.notebook.model.Note;

import butterknife.ButterKnife;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    public NoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(itemView);
    }

    public void populate(Note note) {

    }
}
