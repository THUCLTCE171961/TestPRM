package com.example.myticaura.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myticaura.R;
import com.example.myticaura.model.entity.Review; // Giả sử có quan hệ lấy được tên user
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ReviewAdapter extends ListAdapter<Review, ReviewAdapter.ReviewViewHolder> {

    public ReviewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Review> DIFF_CALLBACK = new DiffUtil.ItemCallback<Review>() {
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getReviewId() == newItem.getReviewId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getComment().equals(newItem.getComment()) && oldItem.getRating() == newItem.getRating();
        }
    };

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView userName, comment, date;
        RatingBar ratingBar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.review_user_name);
            comment = itemView.findViewById(R.id.review_comment);
            date = itemView.findViewById(R.id.review_date);
            ratingBar = itemView.findViewById(R.id.review_rating_bar);
        }

        public void bind(Review review) {
            // TODO: Bạn cần một cách để lấy tên User từ userId.
            // Tạm thời hiển thị ID
            userName.setText("User " + review.getUserId());
            comment.setText(review.getComment());
            ratingBar.setRating(review.getRating());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            date.setText(dateFormat.format(review.getCreatedAt()));
        }
    }
}


