package com.example.peminjamanbarangprototype;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.peminjamanbarangprototype.databinding.ActivityAdminMainBinding;
import com.example.peminjamanbarangprototype.fragments.AdminItemsFragment;
import com.example.peminjamanbarangprototype.fragments.AdminRequestsFragment;
import com.example.peminjamanbarangprototype.fragments.AdminReturnsFragment;

public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_items) {
                selectedFragment = new AdminItemsFragment();
            } else if (id == R.id.nav_requests) {
                selectedFragment = new AdminRequestsFragment();
            } else if (id == R.id.nav_returns) {
                selectedFragment = new AdminReturnsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Set default fragment
        binding.bottomNavigation.setSelectedItemId(R.id.nav_items);

        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_logout) {
                startActivity(new android.content.Intent(this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
