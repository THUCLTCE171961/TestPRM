package com.example.myticaura.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myticaura.R;
import com.example.myticaura.model.entity.OrderWithItems;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class OrderAdapter extends ListAdapter<OrderWithItems, OrderAdapter.OrderViewHolder> {

    public OrderAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<OrderWithItems> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrderWithItems>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderWithItems oldItem, @NonNull OrderWithItems newItem) {
            return oldItem.order.getOrderId() == newItem.order.getOrderId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderWithItems oldItem, @NonNull OrderWithItems newItem) {
            return oldItem.order.getStatus().equals(newItem.order.getStatus()) &&
                    oldItem.order.getTotalAmount() == newItem.order.getTotalAmount();
        }
    };

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderDate, orderTotal, orderStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id_text);
            orderDate = itemView.findViewById(R.id.order_date_text);
            orderTotal = itemView.findViewById(R.id.order_total_text);
            orderStatus = itemView.findViewById(R.id.order_status_text);
        }

        public void bind(OrderWithItems orderWithItems) {
            orderId.setText(String.format("Mã đơn hàng: #%d", orderWithItems.order.getOrderId()));
            orderStatus.setText(String.format("Trạng thái: %s", orderWithItems.order.getStatus()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            orderDate.setText(String.format("Ngày đặt: %s", dateFormat.format(orderWithItems.order.getOrderDate())));

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            orderTotal.setText(String.format("Tổng tiền: %s", currencyFormat.format(orderWithItems.order.getTotalAmount())));
        }
    }
}


