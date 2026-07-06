package com.example.peminjamanbarangprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.peminjamanbarangprototype.DataRepository;
import com.example.peminjamanbarangprototype.adapters.ItemAdapter;
import com.example.peminjamanbarangprototype.databinding.FragmentUserItemsBinding;
import com.example.peminjamanbarangprototype.models.Item;
import com.example.peminjamanbarangprototype.models.LendingRequest;

public class UserItemsFragment extends Fragment {

    private FragmentUserItemsBinding binding;
    private String username;

    public static UserItemsFragment newInstance(String username) {
        UserItemsFragment fragment = new UserItemsFragment();
        Bundle args = new Bundle();
        args.putString("USERNAME", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("USERNAME");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemAdapter adapter = new ItemAdapter(DataRepository.getInstance().getItems(), new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                // Request item
                requestItem(item);
            }

            @Override
            public void onDeleteClick(Item item) {
                // Users can't delete items
            }
        });

        binding.rvUserItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvUserItems.setAdapter(adapter);
    }

    private void requestItem(Item item) {
        if (item.getQuantity() > 0) {
            android.view.View dialogView = LayoutInflater.from(getContext()).inflate(com.example.peminjamanbarangprototype.R.layout.dialog_request_item, null);
            android.widget.TextView tvName = dialogView.findViewById(com.example.peminjamanbarangprototype.R.id.tv_dialog_item_name);
            com.google.android.material.textfield.TextInputEditText etDuration = dialogView.findViewById(com.example.peminjamanbarangprototype.R.id.et_duration);

            tvName.setText("Barang: " + item.getName());

            new com.google.android.material.dialog.MaterialAlertDialogBuilder(getContext())
                    .setTitle("Konfirmasi Peminjaman")
                    .setView(dialogView)
                    .setPositiveButton("Ajukan", (dialog, which) -> {
                        String duration = etDuration.getText().toString();
                        if (!duration.isEmpty()) {
                            LendingRequest request = new LendingRequest(
                                    String.valueOf(System.currentTimeMillis()),
                                    item.getId(),
                                    item.getName(),
                                    username,
                                    1,
                                    duration
                            );
                            DataRepository.getInstance().addRequest(request);
                            item.setQuantity(item.getQuantity() - 1);
                            Toast.makeText(getContext(), "Pengajuan terkirim untuk " + item.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Lama peminjaman harus diisi", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        } else {
            Toast.makeText(getContext(), "Stok habis!", Toast.LENGTH_SHORT).show();
        }
    }
}
