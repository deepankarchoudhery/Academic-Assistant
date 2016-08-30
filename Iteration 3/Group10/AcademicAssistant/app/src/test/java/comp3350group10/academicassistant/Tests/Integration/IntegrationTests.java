package comp3350group10.academicassistant.Tests.Integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                BusinessPersistenceSeamTest.class,
                DataAccessIntegrationTest.class
        })
public class IntegrationTests
{
}
