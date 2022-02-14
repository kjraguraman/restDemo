package org.rest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("Employees")
public class EmployeeResource {
    EmployeeRepository empRepo=EmployeeRepository.getInstance();

    //Fetch all employees
    @GET
    @Produces({"application/xml","application/json"})
    public Response getEmployees(){
       List<Employee> employees=empRepo.getEmployees();
       if(employees.size()>0){
           GenericEntity<List<Employee>> genericEntity = new GenericEntity<List<Employee>>(employees){};
           return Response.ok(genericEntity).build();
       }
       return Response.status(Response.Status.NOT_FOUND).entity("Records not found").build();

    }

    //Fetch an employee detail
    @GET
    @Path("{empId}")
    @Produces({"application/xml","application/json"})
    public Response getEmployee(@PathParam("empId") int id){
        Employee employee=empRepo.getEmployee(id);
        if(employee.getId()==0)
            return Response.status(Response.Status.NOT_FOUND).entity("Id Not Found").build();
        return Response.ok(employee).build();
    }

    //Add an Employee using Form
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response addEmployeeBean(@Valid @BeanParam Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        return Response.status(Response.Status.CREATED).entity("Created").build();
    }

    //Add an Employee using XML or JSON
    @POST
    @Consumes({"application/xml","application/json"})
    public Response addEmployee(@Valid Employee emp){
        System.out.println("Adding new Employee");
        empRepo.addEmployee(emp);
        return Response.status(Response.Status.CREATED).entity("Created").build();
    }

    //Update an Employee detail using form
    @PATCH
    @Path("{empId}")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateEmployeeBean(@Valid @BeanParam Employee emp,@PathParam("empId") int empId){
        System.out.println("Updating employee having ID : "+ empId);
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("ID not found");
           return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.updateEmployee(empId,emp);
        return Response.status(Response.Status.OK).entity("Records Updated").build();
    }

    //Update an Employee detail using XML or JSON
    @PATCH
    @Path("{empId}")
    @Consumes({"application/xml","application/json"})
    public Response updateEmployee(@Valid Employee emp,@PathParam("empId") int empId){
        System.out.println("Updating employee having ID : "+ empId);
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("ID not found");
            return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.updateEmployee(empId,emp);
        return Response.status(Response.Status.OK).entity("Records Updated").build();
    }

    //Delete an employee
    @DELETE
    @Path("{empId}")
    public Response deleteEmployee(@PathParam("empId") int id){
        System.out.println("Deleting Employee Having ID = "+id);
        if(empRepo.getEmployee(id).getId()==0){
            System.out.println("Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("ID Not Found").build();
        }
        empRepo.deleteEmployee(id);
        return Response.status(Response.Status.NO_CONTENT).entity("Records Deleted").build();
    }

    //Get the Vehicle details of Employee by employee id
    @GET
    @Path("{empId}/Vehicles")
    @Produces({"Application/xml","Application/json"})
    public Response getVehicleDetails(@PathParam("empId") int empId){
        if(empRepo.getEmployee(empId).getId()==0)
            return Response.status(Response.Status.NOT_FOUND).entity("Id Not Found").build();
        List<Vehicle> vehicles=empRepo.getVehicles(empId);
        if(vehicles.size()>0){
            GenericEntity<List<Vehicle>> genericEntity = new GenericEntity<List<Vehicle>>(vehicles){};
            return Response.ok(genericEntity).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Employee "+empId+" don't have vehicles").build();
    }

    //Get a vehicle details of Employee by vehicle id
    @GET
    @Path("{empId}/Vehicles/{vehicleId}")
    @Produces({"Application/xml","Application/json"})
    public Response getVehicle(@PathParam("empId") int empId,@PathParam("vehicleId") int vehicleId){
        System.out.println("Getting vehicle for Employee having id : "+empId);
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        Vehicle vehicle=empRepo.getVehicle(vehicleId);
        if(vehicle.getVehicleId()==0)
            return Response.status(Response.Status.NOT_FOUND).entity("Employee don't have vehicle with id :"+vehicleId).build();
        return Response.status(Response.Status.NOT_FOUND).entity(vehicle).build();
    }

    //Add employee vehicle using form
    @POST
    @Path("{empId}/Vehicles")
    @Consumes("application/x-www-form-urlencoded")
    public Response addVehicleBean(@Valid @BeanParam Vehicle vehicle,@PathParam("empId") int empId){
        System.out.println("Adding Vehicle to employee");
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        empRepo.addVehicle(empId,vehicle);
        return Response.status(Response.Status.CREATED).entity("Vehicle added").build();
    }

    //Add employee vehicle using xml or json
    @POST
    @Path("{empId}/Vehicles")
    @Consumes({"application/xml","application/json"})
    public Response addVehicle(@Valid Vehicle vehicle,@PathParam("empId") int empId){
        System.out.println("Adding Vehicle to employee");
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        empRepo.addVehicle(empId,vehicle);
        return Response.status(Response.Status.CREATED).entity("Vehicle added").build();
    }

    //Update employee vehicle using form
    @PATCH
    @Path("{empId}/Vehicles/{vehicleId}")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateVehicleBean(@Valid @BeanParam Vehicle vehicle,@PathParam("empId") int empId,@PathParam("vehicleId") int vehicleId){
        System.out.println("Updating Vehicle having ID : "+vehicleId );
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        if(empRepo.getVehicle(vehicleId).getVehicleId()==0){
            System.out.println("Vehicle Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee don't have vehicle with id :"+vehicleId).build();
        }
        empRepo.updateVehicle(vehicleId,vehicle);
        return Response.status(Response.Status.OK).entity("Updated").build();
    }

    // Update employee vehicle using xml or json
    @PATCH
    @Path("{empId}/Vehicles/{vehicleId}")
    @Consumes({"application/xml","application/json"})
    public Response updateVehicle(@Valid Vehicle vehicle,@PathParam("empId") int empId,@PathParam("vehicleId") int vehicleId){
        System.out.println("Updating Vehicle having ID : "+ vehicleId);
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        if(empRepo.getVehicle(vehicleId).getVehicleId()==0){
            System.out.println("Vehicle Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee don't have vehicle with id :"+vehicleId).build();
        }
        empRepo.updateVehicle(vehicleId, vehicle);
        return Response.status(Response.Status.OK).entity("Updated").build();
    }

    // Delete employee vehicle
    @DELETE
    @Path("{empId}/Vehicles/{vehicleId}")
    public Response deleteVehicle(@PathParam("empId") int empId,@PathParam("vehicleId") int vehicleId){
        System.out.println("Deleting Vehicle Having ID = "+vehicleId);
        if(empRepo.getEmployee(empId).getId()==0){
            System.out.println("Employee Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee Id Not Found").build();
        }
        if(empRepo.getVehicle(vehicleId).getVehicleId()==0){
            System.out.println("Vehicle Id not found");
            return Response.status(Response.Status.NOT_FOUND).entity("Employee don't have vehicle with id :"+vehicleId).build();
        }
        empRepo.deleteVehicle(vehicleId);
        return Response.status(Response.Status.NO_CONTENT).entity("Deleted").build();
    }
}
