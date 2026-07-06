package com.example.peminjamanbarangprototype.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjamanbarangprototype.databinding.ItemRequestBinding;
import com.example.peminjamanbarangprototype.models.LendingRequest;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<LendingRequest> requests;
    private OnRequestClickListener listener;
    private boolean isAdmin;

    public interface OnRequestClickListener {
        void onApprove(LendingRequest request);
        void onReject(LendingRequest request);
        void onReturn(LendingRequest request);
    }

    public RequestAdapter(List<LendingRequest> requests, boolean isAdmin, OnRequestClickListener listener) {
        this.requests = requests;
        this.isAdmin = isAdmin;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRequestBinding binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LendingRequest request = requests.get(position);
        holder.binding.tvUserName.setText(request.getUserName());
        holder.binding.tvItemName.setText("Barang: " + request.getItemName() + " (" + request.getDuration() + ")");
        holder.binding.tvStatus.setText("Status: " + request.getStatus().name());

        if (request.getStatus() == LendingRequest.Status.RETURNED) {
            holder.binding.tvReturnInfo.setVisibility(View.VISIBLE);
        } else {
            holder.binding.tvReturnInfo.setVisibility(View.GONE);
        }

        if (isAdmin && request.getStatus() == LendingRequest.Status.PENDING) {
            holder.binding.layoutActions.setVisibility(View.VISIBLE);
            holder.binding.btnApprove.setOnClickListener(v -> listener.onApprove(request));
            holder.binding.btnReject.setOnClickListener(v -> listener.onReject(request));
        } else if (!isAdmin && request.getStatus() == LendingRequest.Status.APPROVED) {
            holder.binding.layoutActions.setVisibility(View.VISIBLE);
            holder.binding.btnApprove.setText("Kembalikan");
            holder.binding.btnApprove.setOnClickListener(v -> listener.onReturn(request));
            holder.binding.btnReject.setVisibility(View.GONE);
        } else {
            holder.binding.layoutActions.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRequestBinding binding;
        public ViewHolder(ItemRequestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
