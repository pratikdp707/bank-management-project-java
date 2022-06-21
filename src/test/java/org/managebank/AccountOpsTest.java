package org.managebank;

import static org.junit.jupiter.api.Assertions.*;

class AccountOpsTest {

    @org.junit.jupiter.api.Test
    void validateDeposit() {
        AccountOps accountOps = new AccountOps();
        assertEquals(accountOps.validateDeposit(1000), true);
        assertEquals(accountOps.validateDeposit(60000), false);
        assertEquals(accountOps.validateDeposit(300), false);
        accountOps.setBalance(90000);
        assertEquals(accountOps.validateDeposit(11000), false);
        assertEquals(accountOps.validateDeposit(5000), true);

    }

    @org.junit.jupiter.api.Test
    void validateWithdraw() {
        AccountOps accountOps = new AccountOps();
        accountOps.setBalance(70000);
        assertEquals(accountOps.validateWithdraw(1000), true);
        assertEquals(accountOps.validateWithdraw(80000), false);
        assertEquals(accountOps.validateWithdraw(30000), false);
        assertEquals(accountOps.validateWithdraw(500), false);
    }
}