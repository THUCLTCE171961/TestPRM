package com.example.myticaura.model.database;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myticaura.model.dao.CartDao;
import com.example.myticaura.model.dao.CartItemDao;
import com.example.myticaura.model.dao.CategoryDao;
import com.example.myticaura.model.dao.MessageDao;
import com.example.myticaura.model.dao.OrderDao;
import com.example.myticaura.model.dao.ProductDao;
import com.example.myticaura.model.dao.ReviewDao;
import com.example.myticaura.model.dao.StoreLocationDao;
import com.example.myticaura.model.dao.UserDao;
import com.example.myticaura.model.entity.*;
import com.example.myticaura.model.database.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        User.class,
        Product.class,
        Category.class,
        Cart.class,
        CartItem.class,
        Order.class,
        OrderItem.class,
        Review.class,
        Message.class,
        StoreLocation.class
}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    // Khai báo abstract cho tất cả các DAO
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
    public abstract CartDao cartDao();
    public abstract CartItemDao cartItemDao();
    public abstract OrderDao orderDao();
    public abstract ReviewDao reviewDao();
    public abstract MessageDao messageDao();
    public abstract StoreLocationDao storeLocationDao();
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "myticaura_feng_shui_store.db")
                            // .fallbackToDestructiveMigration() // Dùng khi bạn thay đổi schema và không cần giữ lại dữ liệu cũ
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ProductDao productDao = INSTANCE.productDao();
                UserDao userDao = INSTANCE.userDao();
                StoreLocationDao storeLocationDao = INSTANCE.storeLocationDao();
                CategoryDao categoryDao = INSTANCE.categoryDao(); // <-- LẤY INSTANCE CỦA CATEGORYDAO



                // Thêm dữ liệu mẫu
                // DỮ LIỆU CATEGORY PHẢI ĐƯỢC THÊM TRƯỚC PRODUCT
                categoryDao.insertAll(DatabaseInitializer.getSampleCategories());
                productDao.insertAll(DatabaseInitializer.getSampleProducts());

            });
        }
    };


}






