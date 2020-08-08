package com.sushma.imagesearch.rest.api;

import com.sushma.imagesearch.rest.model.ImageAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ImageAPIInterface {

    @Headers("Authorization: Client-ID 137cda6b5008a7c")
    @GET("/3/gallery/search/1?")
    Call<ImageAPIResponse> searchImages(@Query("q") String queryString);
}
