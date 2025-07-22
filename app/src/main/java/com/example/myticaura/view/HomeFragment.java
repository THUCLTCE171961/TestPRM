package com.example.myticaura.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ProductViewModel productViewModel;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProgressBar progressBar;
    private SearchView searchView;

    // --- SỬA 1: Khai báo các View cho bộ lọc ---
    private Spinner spinnerCategory;
    private EditText etMinPrice, etMaxPrice;
    private Button btnFilter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- SỬA 2: Ánh xạ tất cả các View ---
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_view_products);
        searchView = view.findViewById(R.id.search_view);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        etMinPrice = view.findViewById(R.id.et_min_price);
        etMaxPrice = view.findViewById(R.id.et_max_price);
        btnFilter = view.findViewById(R.id.btn_filter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // --- Cấu hình các thành phần ---
        setupRecyclerView();
        setupCategorySpinner();
        setupListeners(); // Gộp các listener vào một hàm

        // --- Quan sát LiveData DUY NHẤT từ ViewModel ---
        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            progressBar.setVisibility(View.GONE);
            adapter.submitList(products);
        });
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupCategorySpinner() {
        // Dữ liệu giả, bạn nên lấy từ ViewModel
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Tất cả danh mục"); // Vị trí 0
        categoryNames.add("Fashion");        // Vị trí 1
        categoryNames.add("Electronics");    // Vị trí 2

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, categoryNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
    }

    // --- SỬA 3: Thiết lập listener cho nút Lọc ---
    private void setupListeners() {
        // Listener cho nút Lọc
        btnFilter.setOnClickListener(v -> {
            applyAllFilters();
        });

        // Listener cho click item
        adapter.setOnItemClickListener(product -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", product.getProductId());
            startActivity(intent);
        });
    }

    // --- SỬA 4: Hàm để thu thập và áp dụng tất cả bộ lọc ---
    private void applyAllFilters() {
        // Lấy query từ SearchView
        String query = searchView.getQuery().toString().trim();

        // Lấy categoryId từ Spinner
        int selectedPosition = spinnerCategory.getSelectedItemPosition();
        // Nếu chọn "Tất cả" (vị trí 0), categoryId là null, ngược lại là vị trí được chọn
        Integer categoryId = (selectedPosition == 0) ? null : selectedPosition;

        // Lấy giá min/max từ EditText
        String minPriceStr = etMinPrice.getText().toString();
        String maxPriceStr = etMaxPrice.getText().toString();

        // Chuyển đổi sang Double, nếu rỗng thì là null
        Double minPrice = TextUtils.isEmpty(minPriceStr) ? null : Double.parseDouble(minPriceStr);
        Double maxPrice = TextUtils.isEmpty(maxPriceStr) ? null : Double.parseDouble(maxPriceStr);

        // Gọi phương thức duy nhất trong ViewModel
        productViewModel.applyFilters(query, categoryId, minPrice, maxPrice);
    }
}


