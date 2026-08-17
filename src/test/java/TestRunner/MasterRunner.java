package TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Master suite runner — executes all feature runners in logical order.
 * Run this class to execute the complete test suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterRunner.class,
        LoginRunner.class,
        AccountsOverviewRunner.class,
        OpenNewAccountRunner.class,
        TransferFundsRunner.class,
        BillPayRunner.class,
        FindTransactionRunner.class,
        ContactInfoRunner.class,
        RequestLoanRunner.class,
        LogoutRunner.class
})
public class MasterRunner {
}
