package com.example.myticaura.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myticaura.view.MapsFragment;
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
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (id == R.id.nav_cart) {
                    selectedFragment = new CartFragment();
                } else if (id == R.id.nav_orders) {
                    selectedFragment = new OrderFragment();
                } else if (id == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                }
                else if (id == R.id.nav_map) {
                    selectedFragment = new MapsFragment();
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
