package com.example.peminjamanbarangprototype.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjamanbarangprototype.databinding.ItemBarangBinding;
import com.example.peminjamanbarangprototype.models.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
        void onDeleteClick(Item item);
    }

    public ItemAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBarangBinding binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.binding.tvName.setText(item.getName());
        holder.binding.tvQuantity.setText("Stok: " + item.getQuantity());
        // In a real app, use Glide to load item.getImageUri()
        
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
        holder.binding.btnDelete.setOnClickListener(v -> listener.onDeleteClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBarangBinding binding;
        public ViewHolder(ItemBarangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
