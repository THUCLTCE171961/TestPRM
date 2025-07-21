package com.example.myticaura.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myticaura.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        // Nếu có intent truyền tên fragment thì ưu tiên load
        String requestedFragment = getIntent().getStringExtra("FRAGMENT");
        if (savedInstanceState == null) {
            if ("orders".equals(requestedFragment)) {
                loadFragment(new OrderFragment());
                bottomNav.setSelectedItemId(R.id.nav_orders);
            } else {
                loadFragment(new HomeFragment());
                bottomNav.setSelectedItemId(R.id.nav_home);
            }
        }
    }

    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_cart:
                        selectedFragment = new CartFragment();
                        break;
                    case R.id.nav_orders:
                        selectedFragment = new OrderFragment();
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                    return true;
                }
                return false;
            };

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
