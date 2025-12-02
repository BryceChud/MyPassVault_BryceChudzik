package com.mypass.repository;

import com.mypass.model.VaultItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaultItemRepository extends JpaRepository<VaultItem, Long> {
    List<VaultItem> findByUserId(Long userId);
}
