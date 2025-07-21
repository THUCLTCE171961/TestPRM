package com.example.myticaura.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myticaura.R;
import com.example.myticaura.adapter.OrderAdapter;
import com.example.myticaura.utils.SessionManager;
import com.example.myticaura.viewmodel.OrderViewModel;

public class OrderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_orders);
        TextView emptyText = view.findViewById(R.id.empty_orders_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final OrderAdapter adapter = new OrderAdapter();
        recyclerView.setAdapter(adapter);

        SessionManager sessionManager = new SessionManager(requireContext());
        OrderViewModel orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        orderViewModel.getOrdersForUser(sessionManager.getUserId()).observe(getViewLifecycleOwner(), orders -> {
            if (orders == null || orders.isEmpty()) {
                emptyText.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                emptyText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.submitList(orders);
            }
        });

        return view;
    }
}


