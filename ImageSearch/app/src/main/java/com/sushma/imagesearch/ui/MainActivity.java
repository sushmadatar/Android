package com.sushma.imagesearch.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


import com.sushma.imagesearch.Event.EventTypes;
import com.sushma.imagesearch.Event.MessageEvent;
import com.sushma.imagesearch.R;
import com.sushma.imagesearch.adapter.ImageAdapter;
import com.sushma.imagesearch.model.ImagesData;
import com.sushma.imagesearch.rest.model.ImageAPIResponse;
import com.sushma.imagesearch.rest.service.ImageAPIService;
import com.sushma.imagesearch.ui.controller.MainActivityController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {
    GridView gridView;
    ImageAdapter adapter;
    MainActivityController controller;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        EventBus.getDefault();
        controller = new MainActivityController(this);
        initUI();
    }

    public void initUI() {
        try {
            //init tvTittle bar
            View tittleBar = findViewById(R.id.title_bar);
            TextView tvTittle = tittleBar.findViewById(R.id.tv_title);
            tvTittle.setText("Image Search");

            gridView = findViewById(R.id.gv_image_grid);

            adapter = new ImageAdapter(gridView.getContext(), ImagesData.getInstance().getImages(this), controller);

            gridView.setAdapter(adapter);

            etSearch = findViewById(R.id.et_search);
            Button btnSearch = findViewById(R.id.btn_search);
            btnSearch.setOnClickListener(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        switch (event.getEventType()) {
            case EventTypes.search_response_success:
                try {
                    if (event.getEventObject() != null) {
                        ImagesData.getInstance().setReponseImages((ImageAPIResponse) event.getEventObject());
                        adapter.updateImages(ImagesData.getInstance().getImages(this));
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void startImageDetailsActivity() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent activity = new Intent(MainActivity.this, ImageDetailsActivity.class);
                    startActivity(activity);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callAPI() {
        try {
            if(!etSearch.getText().toString().isEmpty() && etSearch.getText().toString().length()>0) {
                ImageAPIService api = new ImageAPIService();
                api.searchImage(etSearch.getText().toString());
            }
            else
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Error")
                                .setMessage("Please enter string to search")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
