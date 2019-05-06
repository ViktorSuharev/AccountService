package com.visu.account.rest;

import com.google.inject.Guice;
import com.visu.account.config.DiConfig;
import com.visu.account.model.Account;
import com.visu.account.rest.payload.AccountPayload;
import com.visu.account.rest.payload.TransferDto;
import com.visu.account.service.AccountService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.visu.account.rest.payload.PayloadFactory.createPayload;
import static com.visu.account.rest.payload.PayloadFactory.createPayloadWithData;

@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON})
public class AccountRestService {

    private final AccountService service = Guice.createInjector(new DiConfig()).getInstance(AccountService.class);

    @GET
    @Path("/{accountId}")
    public Response get(@PathParam("accountId") Long accountId) {
        AccountPayload payload = createPayloadWithData(() -> service.get(accountId), "Account found");

        return createResponse(payload);
    }

    @POST
    @Consumes("application/json")
    public Response add(Account account) {
        AccountPayload payload = createPayload(() -> service.add(account), "Account added");

        return createResponse(payload);
    }

    @PUT
    @Consumes("application/json")
    public Response update(Account account) {
        AccountPayload payload = createPayload(() -> service.update(account), "Account updated");

        return createResponse(payload);
    }

    @DELETE
    @Path("/{accountId}")
    public Response delete(@PathParam("accountId") Long accountId) {
        AccountPayload payload = createPayload(() -> service.delete(accountId), "Account deleted");

        return createResponse(payload);
    }

    @PUT
    @Path("/transfer")
    @Consumes("application/json")
    public Response transfer(TransferDto transferDto) {
        AccountPayload payload = createPayload(
                () -> service.transfer(transferDto.getSenderId(), transferDto.getReceiverId(), transferDto.getAmount()),
                "Transfer executed");

        return createResponse(payload);
    }

    private Response createResponse(AccountPayload payload) {
        return Response.ok(payload).build();
    }
}
