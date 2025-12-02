package com.mypass.service;

import com.mypass.model.VaultItem;
import com.mypass.repository.VaultItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VaultService {

    private final VaultItemRepository repo;

    public VaultService(VaultItemRepository repo) {
        this.repo = repo;
    }

    // -------------------------------------------------------------
    // GET ALL ITEMS FOR USER
    // -------------------------------------------------------------
    public List<VaultItem> getItemsForUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // -------------------------------------------------------------
    // ADD NEW ITEM
    // -------------------------------------------------------------
    public void addItem(VaultItem item) {
        repo.save(item);
    }

    // -------------------------------------------------------------
    // GET ITEM
    // -------------------------------------------------------------
    public VaultItem getItem(Long id, Long userId) {
        VaultItem item = repo.findById(id).orElse(null);
        if (item != null && item.getUserId().equals(userId)) {
            return item;
        }
        return null;
    }

    // -------------------------------------------------------------
    // UPDATE ITEM
    // -------------------------------------------------------------
    public void updateItem(Long id, Long userId, VaultItem updated) {
        VaultItem existing = repo.findById(id).orElse(null);

        if (existing != null && existing.getUserId().equals(userId)) {
            existing.setCategory(updated.getCategory());
            existing.setLabel(updated.getLabel());
            existing.setUsername(updated.getUsername());
            existing.setPassword(updated.getPassword());
            repo.save(existing);
        }
    }

    // -------------------------------------------------------------
    // DELETE ITEM
    // -------------------------------------------------------------
    public void deleteItem(Long id, Long userId) {
        VaultItem item = repo.findById(id).orElse(null);

        if (item != null && item.getUserId().equals(userId)) {
            repo.delete(item);
        }
    }
}
