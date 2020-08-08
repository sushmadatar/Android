package com.sushma.imagesearch.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sushma.imagesearch.R;
import com.sushma.imagesearch.adapter.CommentAdapter;
import com.sushma.imagesearch.database.AppDatabase;
import com.sushma.imagesearch.database.Comment;
import com.sushma.imagesearch.database.Image;
import com.sushma.imagesearch.model.ImagesData;
import com.sushma.imagesearch.ui.controller.ImageDetailsController;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailsActivity extends Activity {

    ImageDetailsController controller;
    private ImagesData.ImageData imageData;
    CommentAdapter adapter;
    List<Comment> comments = new ArrayList<>();
    Button btnComment;
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_details);

        //init the controller
        controller = new ImageDetailsController(this);
        imageData = ImagesData.getInstance().getSelectedImageData();

        initUI();
    }

    public void initUI() {
        try {
            //init tvTittle bar
            View tittleBar = findViewById(R.id.title_bar);
            TextView tvTittle = tittleBar.findViewById(R.id.tv_title);
            tvTittle.setText("Image Details");

            ImageView ivBack = tittleBar.findViewById(R.id.iv_back);
            ivBack.setVisibility(View.VISIBLE);
            ivBack.setOnClickListener(controller);

            //load the image
            ImageView ivImageDetails = findViewById(R.id.iv_image_detail);
            Picasso.with(this)
                    .load(imageData.getLink())
                    .fit()
                    .into(ivImageDetails);

            btnComment = findViewById(R.id.btn_comment);
            btnComment.setOnClickListener(controller);
            etComment = findViewById(R.id.et_comment);
            RecyclerView rvComments = findViewById(R.id.rv_comments);
            rvComments.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CommentAdapter(this, comments, controller);
            rvComments.setAdapter(adapter);
            GetComment comments = new GetComment();
            comments.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void addComment() {
        String comment = etComment.getText().toString();
        if (comment.isEmpty() || comment.length() <= 0) {
            new AlertDialog.Builder(ImageDetailsActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter comment to submit")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            AddComment addComment = new AddComment();
            addComment.execute(comment);
        }
    }

    //Database operation should be done on non-UI thread
    class AddComment extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                if (imageData != null) {
                    if (AppDatabase.getInstance(getApplicationContext()).imageDao().getById(imageData.getId()) == null) {
                        AppDatabase.getInstance(getApplicationContext()).imageDao().insert(new Image(imageData.getId(), "testImage"));
                    }
                    AppDatabase.getInstance(getApplicationContext()).commentDao().insert(new Comment(imageData.getId(), params[0]));
                    comments = AppDatabase.getInstance(getApplicationContext()).commentDao().getCommentById(imageData.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                adapter.updateComments(comments);
                Toast.makeText(getApplicationContext(), "comment added", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Database operation should be done on non-UI thread
    class GetComment extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (imageData != null)
                    comments = AppDatabase.getInstance(getApplicationContext()).commentDao().getCommentById(imageData.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                adapter.updateComments(comments);
                Toast.makeText(getApplicationContext(), "fetched comments done", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
