package com.sushma.imagesearch.model;

import android.content.Context;

import com.sushma.imagesearch.rest.model.ImageAPIResponse;

import java.util.ArrayList;
import java.util.List;

public class ImagesData {

    private static volatile ImagesData instance = new ImagesData();
    private ImageAPIResponse reponseImages;
    private ImageData selectedImageData;
    private Context context;

    //private constructor.
    private ImagesData() {
    }

    public static ImagesData getInstance() {
        return instance;
    }

    public ImageAPIResponse getReponseImages() {
        return reponseImages;
    }

    public void setReponseImages(ImageAPIResponse reponseImages) {
        this.reponseImages = reponseImages;
    }


    public List<ImageData> getImages(final Context context) {
        this.context = context;
        List<ImageData> images = new ArrayList<>();
        try {
            if (reponseImages == null) {
                return images;
            }
            ImageAPIResponse.Data[] data = reponseImages.getData();
            for (int i = 0; i < reponseImages.getData().length; i++) {
                ImageAPIResponse.Data imageData = data[i];
                ImageAPIResponse.Images[] imagesRespnse = imageData.getImages();
                if (imageData != null || imageData.getImages().length > 0) {
                    for (int j = 0; j < imageData.getImages().length; j++) {
                        if (imagesRespnse.length > 0 && imagesRespnse != null) {
                          /*  AddComment add = new AddComment();
                            add.execute(imagesRespnse[j].getId());*/
                            images.add(new ImageData(imagesRespnse[j].getId(), imagesRespnse[j].getLink()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public ImageData getImageById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                return null;
            }
            if (reponseImages != null) {
                ImageAPIResponse.Data[] data = reponseImages.getData();
                for (int i = 0; i < reponseImages.getData().length; i++) {
                    ImageAPIResponse.Data imageData = data[i];
                    ImageAPIResponse.Images[] imagesRespnse = imageData.getImages();
                    for (int j = 0; j < imageData.getImages().length; j++) {
                        if (imagesRespnse.length > 0 && imagesRespnse != null && imagesRespnse[j].getId().equals(id)) {
                            return new ImageData(imagesRespnse[j].getId(), imagesRespnse[j].getLink());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageData getSelectedImageData() {
        return selectedImageData;
    }

    public void setSelectedImageData(ImageData imageData) {
        this.selectedImageData = imageData;
    }

    public class ImageData {
        String id;
        String link;

        public ImageData(String id, String link) {
            this.id = id;
            this.link = link;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }


}
