package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ynn.util.collections.graphs.test.GraphTest;

@RunWith(Suite.class)
@SuiteClasses({ GraphTest.class })
public class GraphTestSuite {

}
