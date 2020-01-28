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
    public Response deleteCall(@PathParam Long id) {
        this.services.deleteEmployee(id);
        return Response.ok().status(202).build();
    }
}
