package com.mypass.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class SecurityWarningSubject {

    private static SecurityWarningSubject instance;

    private final List<Observer> observers = new ArrayList<>();

    private SecurityWarningSubject() {
    }

    public static SecurityWarningSubject getInstance() {
        if (instance == null) {
            instance = new SecurityWarningSubject();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}
