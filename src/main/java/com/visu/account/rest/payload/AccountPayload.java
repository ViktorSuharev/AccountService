package com.visu.account.rest.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.visu.account.model.Account;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountPayload {

    private Status status;
    private String msg;
    private Account account;
}
