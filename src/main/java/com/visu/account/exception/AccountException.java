package com.visu.account.exception;

public class AccountException extends RuntimeException {

    private static final String ACCOUNT_NOT_FOUND = "Account not found: id = ";
    private static final String NEGATIVE_BALANCE = "Prohibited operation. Leads to negative balance of account: id = ";

    public AccountException(String message) {
        super(message);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

    public static AccountException createWithAccountNotFound(Long id) {
        return new AccountException(ACCOUNT_NOT_FOUND + id);
    }
}
