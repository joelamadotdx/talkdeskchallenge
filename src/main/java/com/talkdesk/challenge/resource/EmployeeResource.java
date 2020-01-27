package com.talkdesk.challenge.resource;

import com.talkdesk.challenge.services.EmployeeServices;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/employee")
@ApplicationScoped
public class EmployeeResource {

    @Inject
    EmployeeServices services;
    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCal() {
       this.services.createEmployee();
        return Response.ok().status(201).build();
    }
}
