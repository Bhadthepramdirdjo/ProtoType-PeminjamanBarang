package com.example.peminjamanbarangprototype.models;

import android.net.Uri;

public class Item {
    private String id;
    private String name;
    private int quantity;
    private String imageUri; // Store as String

    public Item(String id, String name, int quantity, String imageUri) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageUri = imageUri;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}
