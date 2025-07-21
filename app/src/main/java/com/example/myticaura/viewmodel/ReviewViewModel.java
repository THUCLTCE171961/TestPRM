package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.Review;
import com.example.myticaura.repository.ReviewRepository;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {
    private final ReviewRepository reviewRepository;

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        reviewRepository = new ReviewRepository(application);
    }

    public LiveData<List<Review>> getReviewsForProduct(int productId) {
        return reviewRepository.getReviewsForProduct(productId);
    }

    public void insert(Review review) {
        reviewRepository.insert(review);
    }
}


