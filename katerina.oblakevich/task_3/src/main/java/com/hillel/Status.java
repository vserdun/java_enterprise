package com.hillel;

enum Status {
    STATUS_GREEN("Green"), STATUS_YELLOW("Yellow"), STATUS_RED("Red");
    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}