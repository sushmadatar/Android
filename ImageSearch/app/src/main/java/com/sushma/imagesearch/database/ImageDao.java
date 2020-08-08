package com.sushma.imagesearch.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Query("SELECT * FROM Image")
    List<Image> getAll();

    @Query("SELECT * FROM Image WHERE id IN (:imageIds)")
    List<Image> loadAllByIds(String[] imageIds);

    @Query("SELECT * FROM Image WHERE id =:imageId")
    Image getById(String imageId);

    @Insert
    void insertAll(Image... images);

    @Insert
    void insert(Image image);

    @Delete
    void delete(Image image);
}
