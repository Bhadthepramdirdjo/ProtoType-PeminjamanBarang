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
import com.example.peminjamanbarangprototype.adapters.RequestAdapter;
import com.example.peminjamanbarangprototype.databinding.FragmentUserItemsBinding;
import com.example.peminjamanbarangprototype.models.Item;
import com.example.peminjamanbarangprototype.models.LendingRequest;

import java.util.ArrayList;
import java.util.List;

public class UserHistoryFragment extends Fragment {

    private FragmentUserItemsBinding binding;
    private RequestAdapter adapter;
    private String username;
    private List<LendingRequest> userRequests;

    public static UserHistoryFragment newInstance(String username) {
        UserHistoryFragment fragment = new UserHistoryFragment();
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

        userRequests = new ArrayList<>();
        for (LendingRequest r : DataRepository.getInstance().getRequests()) {
            if (r.getUserName().equals(username)) {
                userRequests.add(r);
            }
        }

        adapter = new RequestAdapter(userRequests, false, new RequestAdapter.OnRequestClickListener() {
            @Override
            public void onApprove(LendingRequest request) { }

            @Override
            public void onReject(LendingRequest request) { }

            @Override
            public void onReturn(LendingRequest request) {
                request.setStatus(LendingRequest.Status.RETURNED);
                // Return stock
                for (Item item : DataRepository.getInstance().getItems()) {
                    if (item.getId().equals(request.getItemId())) {
                        item.setQuantity(item.getQuantity() + request.getQuantity());
                        break;
                    }
                }
                Toast.makeText(getContext(), "Barang dikembalikan dengan bukti gambar", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        binding.rvUserItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvUserItems.setAdapter(adapter);
    }
}
