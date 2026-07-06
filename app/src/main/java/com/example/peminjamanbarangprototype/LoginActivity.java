package com.example.peminjamanbarangprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peminjamanbarangprototype.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (username.equals("admin123") && password.equals("admin123")) {
                // Login as Admin
                startActivity(new Intent(this, AdminMainActivity.class));
                finish();
            } else if (username.equals("user123") && password.equals("user123")) {
                // Login as User
                Intent intent = new Intent(this, UserMainActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
