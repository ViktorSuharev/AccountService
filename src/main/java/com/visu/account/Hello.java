package com.visu.account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Tested!";
    }
}