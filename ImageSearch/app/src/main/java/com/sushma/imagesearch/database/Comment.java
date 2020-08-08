package com.sushma.imagesearch.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Image.class,
        parentColumns = "id",
        childColumns = "imageId",
        onDelete = CASCADE))
public class Comment {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String imageId;

    public Comment(String imageId, String comment) {
        this.imageId = imageId;
        this.comment = comment;
    }

    @NonNull
    @ColumnInfo(name = "comment")
    public String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
