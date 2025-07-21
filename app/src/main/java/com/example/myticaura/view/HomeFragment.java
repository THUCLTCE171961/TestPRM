package com.example.myticaura.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_view_products);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Hiển thị 2 cột
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        // Khởi tạo ViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Quan sát dữ liệu
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            adapter.submitList(products);
            progressBar.setVisibility(View.GONE);
        });

        // Xử lý sự kiện click vào sản phẩm
        adapter.setOnItemClickListener(product -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", product.getProductId());
            startActivity(intent);
        });
    }
}


