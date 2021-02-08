package edu.epam.web.util;

public class IdGenerator {
    private long id = 0;

    public long getId() {
        return id++;
    }
}
