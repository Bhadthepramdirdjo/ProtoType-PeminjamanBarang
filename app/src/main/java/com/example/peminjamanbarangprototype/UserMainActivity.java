package com.example.peminjamanbarangprototype;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.peminjamanbarangprototype.databinding.ActivityUserMainBinding;
import com.example.peminjamanbarangprototype.fragments.UserItemsFragment;
import com.example.peminjamanbarangprototype.fragments.UserHistoryFragment;

public class UserMainActivity extends AppCompatActivity {

    private ActivityUserMainBinding binding;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = getIntent().getStringExtra("USERNAME");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Halo, " + username);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_user_items) {
                selectedFragment = UserItemsFragment.newInstance(username);
            } else if (id == R.id.nav_user_history) {
                selectedFragment = UserHistoryFragment.newInstance(username);
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        binding.bottomNavigation.setSelectedItemId(R.id.nav_user_items);

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
