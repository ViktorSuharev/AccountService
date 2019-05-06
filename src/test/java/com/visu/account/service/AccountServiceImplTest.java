package com.visu.account.service;

import com.visu.account.dao.AccountDao;
import com.visu.account.exception.AccountException;
import com.visu.account.model.Account;
import org.jooq.exception.DataAccessException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    @Mock
    private AccountDao dao = mock(AccountDao.class);

    private final AccountService service =  new AccountServiceImpl(dao);

    @Test
    public void get() {
        Account stub = new Account(1L, BigDecimal.ONE);
        when(dao.get(1L)).thenReturn(stub);
        Account account = service.get(1L);

        Assert.assertEquals(stub, account);
    }

    @Test(expected = AccountException.class)
    public void getNonExistent() {
        when(dao.get(1L)).thenReturn(null);
        service.get(1L);
    }

    @Test
    public void add() {
        Account stub = new Account(1L, BigDecimal.ONE);
        when(dao.add(stub)).thenReturn(true);
        service.add(stub);
    }

    @Test(expected = DataAccessException.class)
    public void addExistent() {
        Account stub = new Account(1L, BigDecimal.ONE);
        when(dao.add(stub)).thenThrow(DataAccessException.class);
        service.add(stub);
    }

    @Test
    public void update() {
        Account stub = new Account(1L, BigDecimal.ONE);
        when(dao.update(stub)).thenReturn(true);
        service.update(stub);
    }

    @Test(expected = AccountException.class)
    public void updateNonExistent() {
        Account stub = new Account(1L, BigDecimal.ONE);
        when(dao.update(stub)).thenReturn(false);
        service.update(stub);
    }

    @Test
    public void delete() {
        when(dao.delete(1L)).thenReturn(true);
        service.delete(1L);
    }

    @Test(expected = AccountException.class)
    public void deleteNonExistent() {
        when(dao.delete(1L)).thenReturn(false);
        service.delete(1L);
    }

    @Test
    public void transfer() {
        doNothing().when(dao).transfer(1L, 2L, BigDecimal.ONE);
        service.transfer(1L, 2L, BigDecimal.ONE);
    }

    @Test(expected = RuntimeException.class)
    public void transferFailed() {
        doThrow(RuntimeException.class).when(dao).transfer(1L, 2L, BigDecimal.ONE);
        service.transfer(1L, 2L, BigDecimal.ONE);
    }
}