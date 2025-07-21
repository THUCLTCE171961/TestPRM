package com.example.myticaura.view.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myticaura.R;
import com.example.myticaura.model.entity.User;
import com.example.myticaura.viewmodel.UserViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.edit_text_name);
        emailEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);
        confirmPasswordEditText = findViewById(R.id.edit_text_confirm_password);
        Button registerButton = findViewById(R.id.button_register);
        TextView loginTextView = findViewById(R.id.text_view_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        registerButton.setOnClickListener(v -> registerUser());
        loginTextView.setOnClickListener(v -> finish()); // Quay lại màn hình Login
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // 1. Kiểm tra thông tin đầu vào
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Kiểm tra xem email đã tồn tại chưa (trên luồng nền)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            User existingUser = userViewModel.findByEmail(email);

            runOnUiThread(() -> {
                if (existingUser != null) {
                    // Email đã tồn tại
                    Toast.makeText(this, "Email này đã được đăng ký", Toast.LENGTH_SHORT).show();
                } else {
                    // 3. Tạo người dùng mới
                    User newUser = new User();
                    newUser.setFullName(name);
                    newUser.setEmail(email);
                    newUser.setPasswordHash(password); // Lưu ý: Trong thực tế cần mã hóa mật khẩu

                    userViewModel.insert(newUser);

                    Toast.makeText(this, "Đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_LONG).show();
                    finish(); // Quay lại màn hình Login sau khi đăng ký thành công
                }
            });
        });
    }
}


