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
            LendingRequest request = new LendingRequest(
                    String.valueOf(System.currentTimeMillis()),
                    item.getId(),
                    item.getName(),
                    username,
                    1
            );
            DataRepository.getInstance().addRequest(request);
            item.setQuantity(item.getQuantity() - 1);
            Toast.makeText(getContext(), "Request submitted for " + item.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Out of stock!", Toast.LENGTH_SHORT).show();
        }
    }
}
