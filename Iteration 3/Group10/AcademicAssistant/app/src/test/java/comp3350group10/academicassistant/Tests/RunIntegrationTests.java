package comp3350group10.academicassistant.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350group10.academicassistant.Tests.Integration.IntegrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
        IntegrationTests.class
})
public class RunIntegrationTests
{
}
