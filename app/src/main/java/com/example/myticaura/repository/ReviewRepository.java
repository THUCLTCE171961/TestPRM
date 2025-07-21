package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.Review;
import com.example.myticaura.model.dao.ReviewDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReviewRepository {
    private final ReviewDao reviewDao;
    private final ExecutorService executorService;

    public ReviewRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        reviewDao = database.reviewDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Review>> getReviewsForProduct(int productId) {
        return reviewDao.getReviewsForProduct(productId);
    }

    public void insert(Review review) {
        executorService.execute(() -> reviewDao.insert(review));
    }
}

