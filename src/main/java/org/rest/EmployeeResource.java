package org.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    @Path("{empId}")
    @Produces({"application/xml","application/json"})
    public Response getEmployee(@PathParam("empId") int id){
        Employee employee=empRepo.getEmployee(id);
        Response response=null;
        if(employee.getId()==0)
            response=Response.status(Response.Status.NOT_FOUND).entity("Record Not Found").build();
        else
            response=Response.status(Response.Status.OK).entity(employee).build();
        return response;
    }

    //Add an Employee using Form
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response addEmployeeBean(@BeanParam Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        Response response=Response.status(Response.Status.CREATED).entity("Created").build();
        return response;
    }

    //Add an Employee using XML or JSON
    @POST
    @Consumes({"application/xml","application/json"})
    public Response addEmployee(Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        Response response=Response.status(Response.Status.CREATED).entity("Created").build();
        return response;
    }

    //Update an Employee detail using form
    @PATCH
    @Path("{empId}")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateEmployeeBean(@BeanParam Employee emp){
        System.out.println("Updating employee having ID : "+ emp.getId());
        if(empRepo.getEmployee(emp.getId()).getId()==0){
            System.out.println("ID not found");
           return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.updateEmployee(emp);
        return Response.status(Response.Status.OK).entity("Records Updated").build();
    }

    //Update an Employee detail using XML or JSON
    @PATCH
    @Path("{empId}")
    @Consumes({"application/xml","application/json"})
    public Response updateEmployee(Employee emp){
        System.out.println("Updating employee having ID : "+ emp.getId());
        if(empRepo.getEmployee(emp.getId()).getId()==0){
            System.out.println("ID not found");
            return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.updateEmployee(emp);
        return Response.status(Response.Status.OK).entity("Records Updated").build();
    }

    //Delete an employee
    @DELETE
    @Path("{empId}")
    public Response deleteEmployee(@PathParam("empId") int id){
        System.out.println("Deleting Employee Having ID = "+id);
        if(empRepo.getEmployee(id).getId()==0){
            return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.deleteEmployee(id);
        return Response.status(Response.Status.NO_CONTENT).entity("Records Deleted").build();
    }

    //Get the Vehicle details of Employee by employee id
    @GET
    @Path("{empId}/Vehicle")
    @Produces({"Application/xml","Application/json"})
    public List<Vehicle> getVehicleDetails(@PathParam("empId") int empId){
        return empRepo.getVehicleDetails(empId);
    }
}
