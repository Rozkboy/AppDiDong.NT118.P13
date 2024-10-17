package com.example.lab2_22521602_3th4;

public class EmployeeParttime extends  Employee{

    public EmployeeParttime (String id, String name)
    {
        super(id,name);
    }
    public double Salary()
    {
        return 150;
    }
    public String toString()
    {
        return super.toString() + " -->Parttime= " + Salary();
    }
}
