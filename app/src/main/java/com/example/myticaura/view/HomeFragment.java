package com.example.myticaura.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myticaura.R;
import com.example.myticaura.adapter.ProductAdapter;
import com.example.myticaura.viewmodel.ProductViewModel;

public class HomeFragment extends Fragment {

    private ProductViewModel productViewModel;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProgressBar progressBar;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_view_products);
        searchView = view.findViewById(R.id.search_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 cột
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        // Khởi tạo ViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Quan sát dữ liệu ban đầu (tất cả sản phẩm)
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            adapter.submitList(products);
            progressBar.setVisibility(View.GONE);
        });

        // Xử lý tìm kiếm động
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Không cần xử lý onSubmit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String trimmed = newText.trim();
                if (TextUtils.isEmpty(trimmed)) {
                    // Nếu ô tìm kiếm trống, lấy lại tất cả
                    productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
                        adapter.submitList(products);
                    });
                } else {
                    // Tìm theo query
                    productViewModel.searchProducts(trimmed).observe(getViewLifecycleOwner(), products -> {
                        adapter.submitList(products);
                    });
                }
                return true;
            }
        });

        // Click vào item để đến chi tiết
        adapter.setOnItemClickListener(product -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", product.getProductId());
            startActivity(intent);
        });
    }
}
