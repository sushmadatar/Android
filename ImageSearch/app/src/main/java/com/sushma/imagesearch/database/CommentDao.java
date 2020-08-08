package com.sushma.imagesearch.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao()
public interface CommentDao {

    @Query("SELECT * FROM Comment")
    List<Comment> getAll();

    @Query("SELECT * FROM Comment WHERE id IN (:commentIds)")
    List<Comment> loadAllByIds(int[] commentIds);

    @Query("SELECT * FROM Comment WHERE imageId=:imageId")
    List<Comment> getCommentById(String imageId);

    @Insert
    void insertAll(Comment... comments);

    @Insert
    void insert(Comment comment);

    @Delete
    void delete(Comment comment);
}
