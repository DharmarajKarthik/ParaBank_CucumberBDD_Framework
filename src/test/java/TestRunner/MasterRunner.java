package TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterRunner.class,
        LoginRunner.class,
}
)
public class MasterRunner {
}

