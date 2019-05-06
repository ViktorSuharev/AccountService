package com.visu.account.rest.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.visu.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountPayload {

    private Status status;
    private String msg;
    private Account account;

    public AccountPayload(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
