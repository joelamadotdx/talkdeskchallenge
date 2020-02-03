package com.talkdesk.challenge.resource;

import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.services.EmployeeServices;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;


@Path("/employee")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeServices services;

    @POST
    @Path("/create")
    public Response create(@NotNull List<EmployeeDTO> employeeDTOList) {
        List<EmployeeDTO> employee = this.services.createEmployee(employeeDTOList);
        return Response.ok().status(CREATED).entity(employee).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@NotNull @PathParam Long id) {
        this.services.deleteEmployee(id);
        return Response.ok().status(202).build();
    }

    @GET
    @Path("/find/{id}")
    public Response find(@NotNull @PathParam Long id) {
        return this.services.findEmployee(id)
                            .map(item -> Response.ok(item).build())
                            .orElse(Response.status(NOT_FOUND).build());
    }

    @PUT
    @Path("/update/{id}")
    public Response update(@NotNull @PathParam Long id, @NotNull EmployeeDTO employeeDTO) {
        this.services.updateEmployee(id, employeeDTO);
        return Response.ok().status(202).build();

    }
}
