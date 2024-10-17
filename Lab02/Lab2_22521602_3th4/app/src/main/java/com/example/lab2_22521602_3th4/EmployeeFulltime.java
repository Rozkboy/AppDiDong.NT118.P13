package com.example.lab2_22521602_3th4;

public class EmployeeFulltime extends  Employee{

    public EmployeeFulltime(String id,String name)
    {
        super (id,name);
    }

    public double Salary()
    {
        return 500;
    }

    public String toString()
    {
        return super.toString() +  " -->Fulltime= "  + Salary();
    }
}
