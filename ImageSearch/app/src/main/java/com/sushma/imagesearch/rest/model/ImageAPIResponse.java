package com.sushma.imagesearch.rest.model;

public class ImageAPIResponse {
    private Data[] data;

    private String success;

    private String status;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", success = "+success+", status = "+status+"]";
    }

    public class Data
    {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Images[] getImages() {
            return images;
        }

        public void setImages(Images[] images) {
            this.images = images;
        }

        private Images[] images;
    }
    public class Images
    {
        private String id;
        public String getLink() {
            return link;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setLink(String link) {
            this.link = link;
        }

        private String link;
    }

}


