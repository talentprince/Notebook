package org.weyoung.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.weyoung.notebook.NotingActivity;
import org.weyoung.notebook.R;
import org.weyoung.notebook.data.Note;
import org.weyoung.notebook.utils.TimeUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.create_time)
    TextView createTime;
    @InjectView(R.id.modify_time)
    TextView modifyTime;
    private View view;

    public NoteViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.inject(this, itemView);
    }

    public void populate(final Note note) {
        title.setText(note.getTitle());
        createTime.setText(note.getCreateTime());
        modifyTime.setText(TimeUtil.getDiff(note.getCreateTime(), note.getUpdateTime()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();
                Intent intent = new Intent(context, NotingActivity.class);
                intent.putExtra("note", note);
                context.startActivity(intent);
            }
        });
    }
}
