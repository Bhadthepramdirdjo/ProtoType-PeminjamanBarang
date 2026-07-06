package com.example.peminjamanbarangprototype;

import com.example.peminjamanbarangprototype.models.Item;
import com.example.peminjamanbarangprototype.models.LendingRequest;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private static DataRepository instance;
    private List<Item> items;
    private List<LendingRequest> requests;

    private DataRepository() {
        items = new ArrayList<>();
        requests = new ArrayList<>();
        // Add some dummy data
        items.add(new Item("1", "Laptop Dell", 5, null));
        items.add(new Item("2", "Proyektor", 2, null));
        items.add(new Item("3", "Kabel HDMI", 10, null));
    }

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public List<Item> getItems() { return items; }
    public void addItem(Item item) { items.add(item); }
    public void deleteItem(String id) {
        items.removeIf(i -> i.getId().equals(id));
    }
    public void updateItem(Item updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                break;
            }
        }
    }

    public List<LendingRequest> getRequests() { return requests; }
    public void addRequest(LendingRequest request) { requests.add(request); }
}
