package com.epam.finalproject.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestAttributesWrapper {
    private final Map<String, Object> attributesMap = new HashMap<>();

    public Object put(String key, Object value) {
        return attributesMap.put(key, value);
    }

    public Object get(Object key) {
        return attributesMap.get(key);
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return attributesMap.entrySet();
    }
}
