package com.example.myticaura.model.database;

import com.example.myticaura.model.entity.Category;
import com.example.myticaura.model.entity.Product;
import com.example.myticaura.model.entity.StoreLocation;
import com.example.myticaura.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {

    // --- DỮ LIỆU MẪU CHO CATEGORY ---
    public static Category[] getSampleCategories() {
        return new Category[]{
                new Category("Nến Thơm Thư Giãn", "Các loại nến giúp giảm căng thẳng, mang lại cảm giác bình yên, phù hợp cho phòng ngủ hoặc không gian thiền định."),
                new Category("Nến Thơm Năng Lượng", "Hương thơm tươi mát, sảng khoái từ cam, chanh, bạc hà giúp tăng cường sự tập trung và sáng tạo, lý tưởng cho không gian làm việc."),
                new Category("Phụ Kiện Nến", "Các dụng cụ đi kèm không thể thiếu như khay đựng nến, dụng cụ cắt bấc, bật lửa dài để nâng cao trải nghiệm sử dụng nến.")
        };
    }

    // --- DỮ LIỆU MẪU CHO PRODUCT ---
    public static Product[] getSampleProducts() {
        return new Product[]{
                // Các sản phẩm thuộc Category 1: Nến Thơm Thư Giãn
                new Product("Nến thơm Tĩnh Lặng", "Hương gỗ đàn hương và trầm hương sâu lắng, giúp tập trung và thiền định.", 180000.0, 80, "https://i.imgur.com/uP2Gv0g.jpeg", 1, "Sáp đậu nành & Tinh dầu thiên nhiên"),
                new Product("Nến thơm Hoàng Hôn", "Sự kết hợp ngọt ngào, lãng mạn giữa hương hoa hồng và vani ấm áp.", 175000.0, 90, "https://i.imgur.com/sW9tH2c.jpeg", 1, "Sáp cọ & Tinh dầu vani"),
                new Product("Nến thơm Tán Hương", "Nến thơm từ sáp đậu nành cao cấp, hương hoa oải hương (lavender) tinh khiết giúp thư giãn tối đa.", 150000.0, 100, "https://i.imgur.com/v4Vn2J6.jpeg", 1, "Sáp đậu nành & Tinh dầu oải hương"),

                // Các sản phẩm thuộc Category 2: Nến Thơm Năng Lượng
                new Product("Nến thơm Ban Mai", "Hương cam bergamot và hoa nhài tươi mát, mang lại cảm giác sảng khoái để khởi đầu ngày mới.", 160000.0, 120, "https://i.imgur.com/qL3bA8d.jpeg", 2, "Sáp ong & Tinh dầu cam"),
                new Product("Nến thơm Mộc Mạc", "Hương gỗ thông và tuyết tùng mạnh mẽ, mang cảm giác gần gũi với thiên nhiên trong lành.", 200000.0, 70, "https://i.imgur.com/3rXF5fT.jpeg", 2, "Sáp cọ & Tinh dầu gỗ thông"),

                // Các sản phẩm thuộc Category 3: Phụ Kiện
                new Product("Dụng cụ cắt bấc nến", "Làm từ thép không gỉ, giúp cắt bấc (tim) nến dễ dàng để ngọn lửa cháy đều và sạch hơn.", 85000.0, 150, "https://i.imgur.com/aG6Jk8r.jpeg", 3, "Thép không gỉ"),
                new Product("Bật lửa điện hồ quang", "Bật lửa an toàn, không dùng gas, sạc lại qua cổng USB, lý tưởng để thắp các loại nến trong hũ sâu.", 120000.0, 95, "https://i.imgur.com/Yh3sN9t.jpeg", 3, "Kim loại & Nhựa ABS")
        };
    }

}


