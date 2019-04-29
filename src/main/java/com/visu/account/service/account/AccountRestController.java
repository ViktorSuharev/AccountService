package com.visu.account.service.account;

import com.google.inject.Inject;
import sun.jvm.hotspot.runtime.Thread;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("/accounts")
@Produces("application/json")
public class AccountRestController {

    private final AccountService service;

    @Inject
    public AccountRestController(AccountService service) {
        this.service = service;
    }

    @GET
    @Path("/{accountId}")
    public Response get(@PathParam("accountId") BigInteger accountId) {
        return Response
                .ok(service.get(accountId))
                .build();
    }

    @GET
    @Path("/hi")
    public Response hi() {
        return Response
                .ok("Hello world")
                .build();
    }

    @POST
    @Consumes("application/json")
    public Response add(Account account) {
        service.add(account);
        return Response.ok().build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(Account account) {
        service.update(account);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{accountId}")
    public Response delete(@PathParam("accountId") BigInteger accountId) {
        service.delete(accountId);
        return Response.ok().build();
    }
}
