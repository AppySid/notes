package com.test.notes.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.notes.bean.NotesBean;
import com.test.notes.R;

import java.util.List;

/**
 * Created by Seeinside on 11/12/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    List<NotesBean> notesBeanList;
    View.OnClickListener listener;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, notes;
        View parentView;
        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            notes = view.findViewById(R.id.tvNotes);
            parentView = view.findViewById(R.id.parentView);
        }
    }


    public NotesAdapter(List<NotesBean> notesBeanList,View.OnClickListener listener) {
        this.notesBeanList = notesBeanList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_row, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("**","positi :: "+position);

        holder.title.setText(notesBeanList.get(position).getTitle());
        holder.notes.setText(notesBeanList.get(position).getNotes());
        holder.parentView.setTag(notesBeanList.get(position).getNotesId());
        holder.parentView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        Log.d("**","size :: "+notesBeanList.size());
        return notesBeanList.size();
    }

}
