package org.rest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeRepository{
    private static EmployeeRepository empRepoInstance=new EmployeeRepository();
    private EmployeeRepository(){}
    public static EmployeeRepository getInstance() {
        System.out.println("Getting EmployeeRepository Instance");
        return empRepoInstance;
    }

    // Fetch all Employees
    public List<Employee> getEmployees(){
        List<Employee> employees=new ArrayList<>();
        String query="select * from employees";
        Connection con=DBConnect.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet result=stmt.executeQuery(query);
            while (result.next()){
                Employee emp=new Employee();
                emp.setId(result.getInt(1));
                emp.setName(result.getString(2));
                emp.setDepartment(result.getString(3));
                emp.setSalary(result.getInt(4));
                emp.setAge(result.getInt(5));
                emp.setMobile(result.getLong(6));
                employees.add(emp);
            }
            result.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Error on Getting Employees");
        }
        try {
            con.close();
        } catch (SQLException e) {}
        return employees;
    }

    //Fetch an Employee
    public Employee getEmployee(int id){
        Employee emp=new Employee();
        String query="select * from employees where Id=?";
        Connection con=DBConnect.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result=stmt.executeQuery();
            if(result.next()){
                emp.setId(result.getInt(1));
                emp.setName(result.getString(2));
                emp.setDepartment(result.getString(3));
                emp.setSalary(result.getInt(4));
                emp.setAge(result.getInt(5));
                emp.setMobile(result.getLong(6));
            }
            result.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Error on Getting Employee BY ID");
        }
        try {
            con.close();
        } catch (SQLException e) {}
        return emp;
    }

    //Add an Employee
    public void addEmployee(Employee emp){
        String query="insert into employees(Name,Department,Salary,Age,Mobile) values(?,?,?,?,?) ";
        Connection con=DBConnect.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            String name=emp.getName();
            String department=emp.getDepartment();
            int salary=emp.getSalary();
            int age=emp.getAge();
            long mobile=emp.getMobile();
            stmt.setString(1,name);
            stmt.setString(2,department);
            stmt.setInt(3,salary);
            stmt.setInt(4,age);
            stmt.setLong(5,mobile);
            int rows=stmt.executeUpdate();
            System.out.println(rows+" rows inserted");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Error While Inserting Employee");
        }
        try {
            con.close();
        } catch (SQLException e) {};
    }

    //Update an Employee
    public void updateEmployee(Employee emp){
        String query="update employees set Name=?,Department=?,Salary=?,Age=?,Mobile=? where Id=?";
        Connection con=DBConnect.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            String name=emp.getName();
            String department=emp.getDepartment();
            int salary=emp.getSalary();
            int age=emp.getAge();
            long mobile=emp.getMobile();
            int id=emp.getId();
            stmt.setString(1,name);
            stmt.setString(2,department);
            stmt.setInt(3,salary);
            stmt.setInt(4,age);
            stmt.setLong(5,mobile);
            stmt.setInt(6,id);
            int rows=stmt.executeUpdate();
            System.out.println(rows+" rows updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Error While Updating Employee");
        }
        try {
            con.close();
        } catch (SQLException e) {}
    }

    //Delete an Employee
    public void deleteEmployee(int id){
        String query="delete from employees where Id=?";
        Connection con=DBConnect.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            int rows=stmt.executeUpdate();
            System.out.println(rows+" rows deleted");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Error While Deleting Employee BY ID");
        }
        try {
            con.close();
        } catch (SQLException e) {}
    }

    //Get the Vehicle details of Employee by employee id
    public List<Vehicle> getVehicleDetails(int empId){
        List<Vehicle> vehicles=new ArrayList<>();
        String query="select * from empVehicles where EmpId=?";
        Connection con=DBConnect.getConnection();
        try{
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1,empId);
            ResultSet result=stmt.executeQuery();
            while (result.next()){
                Vehicle vehicle=new Vehicle();
                vehicle.setVehicleId(result.getInt(1));
                vehicle.setRegNo(result.getString(2));
                vehicle.setCompanyName(result.getString(3));
                vehicles.add(vehicle);
            }
            result.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error while getting Vehicle Details");
        }
        try {
            con.close();
        } catch (SQLException e) {}
        return vehicles;
    }
}
