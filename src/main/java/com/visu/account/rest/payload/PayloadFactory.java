package com.visu.account.rest.payload;

import com.visu.account.model.Account;

import java.util.concurrent.Callable;

public class PayloadFactory {

    public static AccountPayload createPayload(Runnable runnable, String msg) {
        try {
            runnable.run();

            return createPayloadInCaseOfSuccess(msg);
        } catch (Exception e) {
            return createPayloadInCaseOfFail(e);
        }
    }

    public static AccountPayload createPayloadWithData(Callable<Account> callable, String msg) {
        try {
            Account account = callable.call();

            AccountPayload payload = createPayloadInCaseOfSuccess(msg);
            payload.setAccount(account);

            return payload;
        } catch (Exception e) {
            return createPayloadInCaseOfFail(e);
        }
    }

    private static AccountPayload createPayloadInCaseOfSuccess(String msg) {
        AccountPayload payload = new AccountPayload();
        payload.setStatus(Status.SUCCESS);
        payload.setMsg(msg);

        return payload;
    }

    private static AccountPayload createPayloadInCaseOfFail(Exception e) {
        AccountPayload payload = new AccountPayload();
        payload.setStatus(Status.FAILED);
        payload.setMsg(e.getMessage());

        return payload;
    }
}
