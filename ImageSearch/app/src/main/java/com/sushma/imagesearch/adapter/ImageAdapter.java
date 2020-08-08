package com.sushma.imagesearch.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sushma.imagesearch.R;
import com.sushma.imagesearch.model.ImagesData;
import com.sushma.imagesearch.ui.controller.MainActivityController;

import java.util.List;


public class ImageAdapter extends BaseAdapter {
    private Context context;
    private static List<ImagesData.ImageData> images;
    private final MainActivityController controller;

    public ImageAdapter(Context context, List<ImagesData.ImageData> images, MainActivityController controller) {
        this.context = context;
        this.images = images;
        this.controller = controller;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.image_grid_item, null);
        } else {
            gridView = convertView;
        }

        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.grid_item_image);
        if (images != null) {
            // set image based on selected text
            imageView.setTag(images.get(position).getId());
            imageView.setOnClickListener(controller);
            Picasso.with(context)
                    .load(images.get(position).getLink())
                    .fit()
                    .into(imageView);
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateImages(List<ImagesData.ImageData> newlist) {
        images = newlist;
        this.notifyDataSetChanged();
    }
}
