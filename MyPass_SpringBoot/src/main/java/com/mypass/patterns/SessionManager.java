package com.mypass.patterns;

public class SessionManager {

    private static SessionManager instance; // SINGLE instance

    private Long userId; // currently logged-in user

    // private constructor prevents new SessionManager()
    private SessionManager() {
    }

    // Global access point
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(Long id) {
        this.userId = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void logout() {
        this.userId = null;
    }
}
