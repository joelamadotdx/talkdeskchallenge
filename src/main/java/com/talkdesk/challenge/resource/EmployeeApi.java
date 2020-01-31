package com.talkdesk.challenge.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.LONG;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface EmployeeApi {

    @GET
    @Path("/find/{id}")
    @Operation(
            operationId = "GetUser",
            summary = "Find User by Id"
    )
    Response get(
            @PathParam("id")
            @Parameter(ref = "id", schema = @Schema(type = LONG)
                    String id);
}
