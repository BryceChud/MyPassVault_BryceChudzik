package com.mypass.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionTimeoutInterceptor implements HandlerInterceptor {

    // 2 minutes inactivity limit
    private static final long MAX_INACTIVE_TIME = 2 * 60 * 1000;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        // If no session → proceed normally (public pages)
        if (session == null) {
            return true;
        }

        Long lastAction = (Long) session.getAttribute("lastActionTime");
        long now = System.currentTimeMillis();

        if (lastAction != null) {
            long inactive = now - lastAction;

            if (inactive > MAX_INACTIVE_TIME) {
                session.invalidate();
                response.sendRedirect("/login?timeout");
                return false;
            }
        }

        // Update last action timestamp
        session.setAttribute("lastActionTime", now);
        return true;
    }
}
