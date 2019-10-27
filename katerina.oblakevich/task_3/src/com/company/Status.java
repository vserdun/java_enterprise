package com.company;

import java.util.Objects;

public class Status {
    private String name;
    public static final int STATUS_GREEN = 1;
    public static final int STATUS_YELLOW = 2;
    public static final int STATUS_RED = 3;

    public Status(int stat) {
        if (stat == STATUS_GREEN)
            name = "Green";
        if (stat == STATUS_YELLOW)
            name = "Yellow";
        if (stat == STATUS_RED)
            name = "Red";
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
