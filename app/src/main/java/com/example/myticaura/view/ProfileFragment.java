package com.example.myticaura.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.myticaura.R;
import com.example.myticaura.model.entity.User;
import com.example.myticaura.utils.SessionManager;
import com.example.myticaura.view.auth.LoginActivity;
import com.example.myticaura.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private TextInputEditText editTextName, editTextEmail, editTextAddress, editTextPhone;
    private Button buttonSave, buttonLogout;
    private UserViewModel userViewModel;
    private SessionManager sessionManager;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextAddress = view.findViewById(R.id.edit_text_address);
        editTextPhone = view.findViewById(R.id.edit_text_phone);
        buttonSave = view.findViewById(R.id.button_save);
        buttonLogout = view.findViewById(R.id.button_logout);

        // Initialize SessionManager and ViewModel
        sessionManager = new SessionManager(requireContext());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Load user data
        loadUserData();

        // Set up click listeners
        buttonSave.setOnClickListener(v -> saveUserData());
        buttonLogout.setOnClickListener(v -> logoutUser());
    }

    private void loadUserData() {
        userViewModel.getUserById(sessionManager.getUserId()).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                currentUser = user;
                editTextName.setText(user.getFullName());
                editTextEmail.setText(user.getEmail());
                editTextAddress.setText(user.getAddress());
                editTextPhone.setText(user.getPhoneNumber());
            }
        });
    }

    private void saveUserData() {
        if (currentUser == null) {
            Toast.makeText(getContext(), "Không thể tải thông tin người dùng", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get text from EditText fields
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        // Validate input
        if (name.isEmpty()) {
            editTextName.setError("Vui lòng nhập họ và tên");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Vui lòng nhập email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email không hợp lệ");
            editTextEmail.requestFocus();
            return;
        }

        // Update user object
        currentUser.setFullName(name);
        currentUser.setEmail(email);
        currentUser.setAddress(address);
        currentUser.setPhoneNumber(phone);

        // Save to database
        userViewModel.update(currentUser);
        
        Toast.makeText(getContext(), "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();
    }

    private void logoutUser() {
        sessionManager.logoutUser();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}


