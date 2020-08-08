package com.sushma.imagesearch.ui.controller;

import android.view.View;

import com.sushma.imagesearch.R;
import com.sushma.imagesearch.model.ImagesData;
import com.sushma.imagesearch.ui.MainActivity;

public class MainActivityController implements View.OnClickListener {
    private final MainActivity activity;

    public MainActivityController(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                activity.callAPI();
                break;
            case R.id.grid_item_image:
                ImagesData.getInstance().setSelectedImageData(ImagesData.getInstance().getImageById((String) v.getTag()));
                activity.startImageDetailsActivity();
                break;

        }
    }
}
