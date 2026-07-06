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
import com.example.peminjamanbarangprototype.R;
import com.example.peminjamanbarangprototype.adapters.ItemAdapter;
import com.example.peminjamanbarangprototype.databinding.FragmentAdminItemsBinding;
import com.example.peminjamanbarangprototype.models.Item;

public class AdminItemsFragment extends Fragment {

    private FragmentAdminItemsBinding binding;
    private ItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        binding.fabAdd.setOnClickListener(v -> {
            // Logic to add item (Show Dialog)
            showAddItemDialog();
        });
    }

    private void setupRecyclerView() {
        adapter = new ItemAdapter(DataRepository.getInstance().getItems(), new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                showEditItemDialog(item);
            }

            @Override
            public void onDeleteClick(Item item) {
                DataRepository.getInstance().deleteItem(item.getId());
                adapter.notifyDataSetChanged();
            }
        });
        binding.rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvItems.setAdapter(adapter);
    }

    private void showEditItemDialog(Item item) {
        android.view.View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_item, null);
        com.google.android.material.textfield.TextInputEditText etName = dialogView.findViewById(R.id.et_item_name);
        com.google.android.material.textfield.TextInputEditText etQuantity = dialogView.findViewById(R.id.et_item_quantity);

        etName.setText(item.getName());
        etQuantity.setText(String.valueOf(item.getQuantity()));

        new com.google.android.material.dialog.MaterialAlertDialogBuilder(getContext())
                .setTitle("Edit Barang")
                .setView(dialogView)
                .setPositiveButton("Simpan", (dialog, which) -> {
                    String name = etName.getText().toString();
                    String qtyStr = etQuantity.getText().toString();
                    if (!name.isEmpty() && !qtyStr.isEmpty()) {
                        item.setName(name);
                        item.setQuantity(Integer.parseInt(qtyStr));
                        DataRepository.getInstance().updateItem(item);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void showAddItemDialog() {
        android.view.View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_item, null);
        com.google.android.material.textfield.TextInputEditText etName = dialogView.findViewById(R.id.et_item_name);
        com.google.android.material.textfield.TextInputEditText etQuantity = dialogView.findViewById(R.id.et_item_quantity);

        new com.google.android.material.dialog.MaterialAlertDialogBuilder(getContext())
                .setTitle("Tambah Barang")
                .setView(dialogView)
                .setPositiveButton("Simpan", (dialog, which) -> {
                    String name = etName.getText().toString();
                    String qtyStr = etQuantity.getText().toString();
                    if (!name.isEmpty() && !qtyStr.isEmpty()) {
                        String id = String.valueOf(System.currentTimeMillis());
                        DataRepository.getInstance().addItem(new Item(id, name, Integer.parseInt(qtyStr), null));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }
}
