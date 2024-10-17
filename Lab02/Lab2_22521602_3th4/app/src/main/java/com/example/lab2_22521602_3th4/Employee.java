package com.example.lab2_22521602_3th4;

public abstract class Employee {
    protected String id;
    protected String name;
    public Employee (String id,String name)
    {
        this.id = id;
        this.name = name;
    }

    public abstract double Salary();

    public String toString()
    {
        return this.id + " - " + this.name;
    }
}