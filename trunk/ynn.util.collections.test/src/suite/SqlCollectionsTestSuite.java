package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ynn.util.collections.sql.test.QueryableArrayListTest;
import ynn.util.collections.sql.test.UpdatableArrayListTest;

@RunWith(Suite.class)
@SuiteClasses({ QueryableArrayListTest.class, UpdatableArrayListTest.class })
public class SqlCollectionsTestSuite {

}
