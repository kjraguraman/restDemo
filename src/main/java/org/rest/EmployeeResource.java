package org.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Employees")
public class EmployeeResource {
    EmployeeRepository empRepo=EmployeeRepository.getInstance();

    //Fetch all employees
    @GET
    @Produces({"application/xml","application/json"})
    public List<Employee> getEmployees(){
        return empRepo.getEmployees();
    }

    //Fetch an employee detail
    @GET
    @Path("Employee/{empId}")
    @Produces({"application/xml","application/json"})
    public Employee getEmployee(@PathParam("empId") int id){
        return empRepo.getEmployee(id);
    }

    //Add an Employee using Form
    @POST
    @Path("Employee")
    @Consumes("application/x-www-form-urlencoded")
    public String addEmployeeBean(@BeanParam Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        return "Successfully added";
    }

    //Add an Employee using XML or JSON
    @POST
    @Path("Employee")
    @Consumes({"application/xml","application/json"})
    public String addEmployee(Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        return "Successfully added";
    }

    //Update an Employee detail using form
    @PUT
    @Path("Employee/{empId}")
    @Consumes("application/x-www-form-urlencoded")
    public String updateEmployeeBean(@BeanParam Employee emp){
        System.out.println("Updating employee having ID : "+ emp.getId());
        if(getEmployee(emp.getId()).getId()==0){
            System.out.println("User not found");
            return "User not found";
        }
        empRepo.updateEmployee(emp);
        return "Updated successfully";
    }

    //Update an Employee detail using XML or JSON
    @PUT
    @Path("Employee/{empId}")
    @Consumes({"application/xml","application/json"})
    public String updateEmployee(Employee emp){
        System.out.println("Updating employee having ID : "+ emp.getId());
        if(getEmployee(emp.getId()).getId()==0){
            System.out.println("User not found");
            return "User not found";
        }
        empRepo.updateEmployee(emp);
        return "Updated successfully";
    }

    //Delete an employee
    @DELETE
    @Path("Employee/{empId}")
    public String deleteEmployee(@PathParam("empId") int id){
        System.out.println("Deleting Employee Having ID = "+id);
        if(empRepo.getEmployee(id).getId()==0){
            System.out.println("Record Not Found");
            return "Record not found";
        }
        empRepo.deleteEmployee(id);
        return "Successfully Deleted";
    }

    //Get the Vehicle details of Employee by employee id
    @GET
    @Path("Employee/{empId}/Vehicle")
    @Produces({"Application/xml","Application/json"})
    public List<Vehicle> getVehicleDetails(@PathParam("empId") int empId){
        return empRepo.getVehicleDetails(empId);
    }
}
