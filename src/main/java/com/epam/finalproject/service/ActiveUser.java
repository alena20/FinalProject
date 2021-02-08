package com.epam.finalproject.service;

import java.util.HashMap;


//key - userId, value - isActive
public class ActiveUser extends HashMap<Integer, Boolean> {
    private static final ActiveUser INSTANCE = new ActiveUser();

    private ActiveUser() { }

    public static ActiveUser getInstance() {
        return INSTANCE;
    }
}