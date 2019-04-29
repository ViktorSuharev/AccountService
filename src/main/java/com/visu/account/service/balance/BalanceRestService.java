package com.visu.account.service.balance;

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.BigInteger;

@Path("/accounts")
@Produces("application/json")
public class BalanceRestService {

    private final BalanceService service;

    @Inject
    public BalanceRestService(BalanceService service) {
        this.service = service;
    }

    @GET
    @Path("/{accountId}/balance")
    public Response get(@PathParam("accountId") BigInteger accountId) {
        return Response
                .ok(service.get(accountId))
                .build();
    }

    @PUT
    @Path("/{accountId}/balance")
    public Response set(@PathParam("accountId") BigInteger accountId, @QueryParam("amount") BigDecimal amount) {
        service.set(accountId, amount);
        return Response.ok().build();
    }

    @PUT
    @Path("/{accountId}/balance/topup")
    public Response topUp(@PathParam("accountId") BigInteger accountId, @QueryParam("amount") BigDecimal amount) {
        service.topUp(accountId, amount);
        return Response.ok().build();
    }

    @PUT
    @Path("/{accountId}/balance/withdraw")
    public Response withdraw(@PathParam("accountId") BigInteger accountId, @QueryParam("amount") BigDecimal amount) {
        service.withdraw(accountId, amount);
        return Response.ok().build();
    }
}
