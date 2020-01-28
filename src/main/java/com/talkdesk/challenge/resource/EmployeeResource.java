package com.talkdesk.challenge.resource;

import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.services.EmployeeServices;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;


@Path("/employee")
@ApplicationScoped
public class EmployeeResource {

    @Inject
    EmployeeServices services;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(List<EmployeeDTO> employeeDTOList) {
       this.services.createEmployee(employeeDTOList);
        return Response.ok().status(201).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam Long id) {
        this.services.deleteEmployee(id);
        return Response.ok().status(202).build();
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam Long id) {
        /*return  this.services.findEmployee(id).map(Response::ok)
                .orElse(Response.status(NOT_FOUND)).build();

         */
        return null;
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam Long id, EmployeeDTO employeeDTO) {
        /* this.services.updateEmployee(id,employeeDTO);
        return Response.ok().status(202).build();

         */
        return null;
    }
}
