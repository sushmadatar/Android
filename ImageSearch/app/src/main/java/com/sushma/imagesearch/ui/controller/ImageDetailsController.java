package com.sushma.imagesearch.ui.controller;

import android.view.View;

import com.sushma.imagesearch.R;
import com.sushma.imagesearch.ui.ImageDetailsActivity;

public class ImageDetailsController implements View.OnClickListener {
    private final ImageDetailsActivity activity;

    public ImageDetailsController(ImageDetailsActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comment:
                activity.addComment();
                break;
            case R.id.iv_back:
                activity.finish();
                break;
        }
    }
}
