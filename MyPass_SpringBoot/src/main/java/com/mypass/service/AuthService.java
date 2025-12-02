package com.mypass.service;

import com.mypass.model.User;
import com.mypass.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private Long loggedInUserId = null;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public Long getLoggedInUserId() {
        return loggedInUserId;
    }

    public void logout(HttpSession session) {
        loggedInUserId = null;
        session.invalidate();
    }

    // ✔ Correct login signature
    public boolean login(String email, String password, HttpSession session) {
        User user = repo.findByEmail(email);

        if (user == null)
            return false;

        if (!BCrypt.checkpw(password, user.getPassword()))
            return false;

        loggedInUserId = user.getId();

        session.setAttribute("userId", loggedInUserId);
        session.setAttribute("lastActionTime", System.currentTimeMillis());

        return true;
    }

    public boolean registerUser(String email, String password,
            String s1, String s2, String s3) {

        if (repo.findByEmail(email) != null)
            return false;

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(email, hashed, s1, s2, s3);

        repo.save(user);
        return true;
    }
}
