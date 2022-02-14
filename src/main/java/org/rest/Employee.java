package org.rest;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
    @PathParam("empId")
    private int id;

    @FormParam("name")
    @NotNull(message = "Name required")
    private String  name;

    @FormParam("department")
    @NotNull(message = "Department required")
    private String department;
    @FormParam("salary")
    @Min(value = 10000,message = "Salary should greater than or equals to 10000")
    private int salary;

    @FormParam("age")
    @Min(value = 18, message = "Age should greater than or equals to 18")
    private int age;

    @FormParam("mobile")
    @Digits(integer = 10,fraction = 0,message = "Mobile.no should be exact 10 characters." )
    @Min(value= 600000000)
    private long mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
