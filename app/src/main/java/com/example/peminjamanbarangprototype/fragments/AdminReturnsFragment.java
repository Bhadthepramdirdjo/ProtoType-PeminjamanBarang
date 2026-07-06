package com.example.peminjamanbarangprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.peminjamanbarangprototype.DataRepository;
import com.example.peminjamanbarangprototype.adapters.RequestAdapter;
import com.example.peminjamanbarangprototype.databinding.FragmentUserItemsBinding;
import com.example.peminjamanbarangprototype.models.LendingRequest;

import java.util.ArrayList;
import java.util.List;

public class AdminReturnsFragment extends Fragment {

    private FragmentUserItemsBinding binding;
    private RequestAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<LendingRequest> returnedRequests = new ArrayList<>();
        for (LendingRequest r : DataRepository.getInstance().getRequests()) {
            if (r.getStatus() == LendingRequest.Status.RETURNED) {
                returnedRequests.add(r);
            }
        }

        adapter = new RequestAdapter(returnedRequests, false, new RequestAdapter.OnRequestClickListener() {
            @Override
            public void onApprove(LendingRequest request) { }
            @Override
            public void onReject(LendingRequest request) { }
            @Override
            public void onReturn(LendingRequest request) { }
        });

        binding.rvUserItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvUserItems.setAdapter(adapter);
    }
}
