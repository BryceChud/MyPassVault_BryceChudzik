package com.mypass.controller;

import com.mypass.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "timeout", required = false) String timeout,
            Model model) {

        if (timeout != null) {
            model.addAttribute("error", "Session expired due to inactivity.");
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        boolean ok = authService.login(email, password, session);

        if (!ok) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
            @RequestParam String password,
            @RequestParam("security1") String s1,
            @RequestParam("security2") String s2,
            @RequestParam("security3") String s3,
            Model model) {

        boolean created = authService.registerUser(email, password, s1, s2, s3);

        if (!created) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
