package com.sushma.imagesearch.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sushma.imagesearch.R;
import com.sushma.imagesearch.database.Comment;
import com.sushma.imagesearch.ui.controller.ImageDetailsController;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private static List<Comment> comments;
    private final ImageDetailsController controller;

    public CommentAdapter(Context context, List<Comment> comments, ImageDetailsController controller) {
        this.context = context;
        this.comments = comments;
        this.controller = controller;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView comment;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            comment = (TextView) v.findViewById(R.id.tv_comment);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.comment_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = comments.get(position).getComment();
        holder.comment.setText(name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void updateComments(List<Comment> newlist) {
        comments = newlist;
        notifyDataSetChanged();
    }

}
