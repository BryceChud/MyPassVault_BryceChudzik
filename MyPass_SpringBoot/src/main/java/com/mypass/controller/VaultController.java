package com.mypass.controller;

import com.mypass.model.VaultItem;
import com.mypass.service.AuthService;
import com.mypass.service.VaultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VaultController {

  private final VaultService vaultService;
  private final AuthService authService;

  public VaultController(VaultService vaultService, AuthService authService) {
    this.vaultService = vaultService;
    this.authService = authService;
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    Long userId = authService.getLoggedInUserId();
    if (userId == null)
      return "redirect:/login";
    model.addAttribute("items", vaultService.getItemsForUser(userId));
    return "dashboard";
  }

  @GetMapping("/vault/add")
  public String addPage() {
    return "add";
  }

  @PostMapping("/vault/add")
  public String addItem(VaultItem item) {
    Long uid = authService.getLoggedInUserId();
    item.setUserId(uid);
    vaultService.addItem(item);
    return "redirect:/dashboard";
  }

  @GetMapping("/vault/edit/{id}")
  public String editPage(@PathVariable Long id, Model model) {
    Long uid = authService.getLoggedInUserId();
    model.addAttribute("item", vaultService.getItem(id, uid));
    return "edit-item";
  }

  @PostMapping("/vault/edit/{id}")
  public String saveEdit(@PathVariable Long id, VaultItem updated) {
    Long uid = authService.getLoggedInUserId();
    vaultService.updateItem(id, uid, updated);
    return "redirect:/dashboard";
  }

  @GetMapping("/vault/delete/{id}")
  public String delete(@PathVariable Long id) {
    Long uid = authService.getLoggedInUserId();
    vaultService.deleteItem(id, uid);
    return "redirect:/dashboard";
  }
}
