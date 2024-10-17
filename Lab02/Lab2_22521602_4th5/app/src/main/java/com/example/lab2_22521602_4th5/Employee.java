package com.example.lab2_22521602_4th5;

// Employee.java
public class Employee {
    private String fullName;
    private String position;
    private boolean isManager;

    public Employee(String fullName, String position, boolean isManager) {
        this.fullName = fullName;
        this.position = position;
        this.isManager = isManager;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public boolean isManager() {
        return isManager;
    }
}

