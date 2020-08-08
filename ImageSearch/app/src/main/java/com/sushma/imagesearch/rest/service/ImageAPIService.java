package com.sushma.imagesearch.rest.service;

import com.sushma.imagesearch.Event.EventTypes;
import com.sushma.imagesearch.Event.MessageEvent;
import com.sushma.imagesearch.rest.api.ImageAPIInterface;
import com.sushma.imagesearch.rest.RetrofitClient;
import com.sushma.imagesearch.rest.model.ImageAPIResponse;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageAPIService implements Callback<ImageAPIResponse> {

    public void searchImage(String searchstring) {
        ImageAPIInterface gerritAPI = RetrofitClient.getRetrofitInstance().create(ImageAPIInterface.class);

        Call<ImageAPIResponse> call = gerritAPI.searchImages(searchstring);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ImageAPIResponse> call, Response<ImageAPIResponse> response) {
        if (response.isSuccessful()) {
            ImageAPIResponse changesList = response.body();
            EventBus.getDefault().post(new MessageEvent(EventTypes.search_response_success,changesList));
            //changesList.forEach(change -> System.out.println(" hi there"));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ImageAPIResponse> call, Throwable t) {
        t.printStackTrace();
    }

}
