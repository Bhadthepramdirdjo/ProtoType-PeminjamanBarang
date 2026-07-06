package com.example.peminjamanbarangprototype.models;

public class LendingRequest {
    public enum Status { PENDING, APPROVED, REJECTED, RETURNED }

    private String id;
    private String itemId;
    private String itemName;
    private String userName;
    private int quantity;
    private String duration; // Added duration
    private Status status;
    private String returnImageUri;

    public LendingRequest(String id, String itemId, String itemName, String userName, int quantity, String duration) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.userName = userName;
        this.quantity = quantity;
        this.duration = duration;
        this.status = Status.PENDING;
    }

    public String getId() { return id; }
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getUserName() { return userName; }
    public int getQuantity() { return quantity; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getReturnImageUri() { return returnImageUri; }
    public void setReturnImageUri(String returnImageUri) { this.returnImageUri = returnImageUri; }
}
