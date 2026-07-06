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

public class AdminRequestsFragment extends Fragment {

    private FragmentUserItemsBinding binding; // Reusing the same layout (just a RecyclerView)
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

        adapter = new RequestAdapter(DataRepository.getInstance().getRequests(), true, new RequestAdapter.OnRequestClickListener() {
            @Override
            public void onApprove(LendingRequest request) {
                request.setStatus(LendingRequest.Status.APPROVED);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onReject(LendingRequest request) {
                request.setStatus(LendingRequest.Status.REJECTED);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onReturn(LendingRequest request) {
                // Not used for Admin in this context
            }
        });

        binding.rvUserItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvUserItems.setAdapter(adapter);
    }
}
