package com.example.groupstudyingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursePageAdapter extends RecyclerView.Adapter<CoursePageAdapter.ViewHolder> {

    private List<Question> questions;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CoursePageAdapter(Context context, List<Question> questions) {
        this.mInflater = LayoutInflater.from(context);
        this.questions = questions;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.question_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Question question = questions.get(position);
        holder.questionName.setText(question.getTitle());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    // total number of questions
    @Override
    public int getItemCount() {
        return questions.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView questionName;
        ImageView removeButton;
        ViewHolder(View itemView) {
            super(itemView);
            questionName = itemView.findViewById(R.id.questionName);
            removeButton = itemView.findViewById(R.id.removeQuestionButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Question getItem(int id) {
        return questions.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}