package org.rest;


import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
    @PathParam("empId")
    private int id;
    @FormParam("name")
    private String  name;
    @FormParam("department")
     private String department;
    @FormParam("salary")
    private int salary;
    @FormParam("age")
    private int age;
    @FormParam("mobile")
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
