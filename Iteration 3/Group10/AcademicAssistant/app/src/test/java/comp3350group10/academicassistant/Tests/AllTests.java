package comp3350group10.academicassistant.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350group10.academicassistant.Tests.Business.BusinessTests;
import comp3350group10.academicassistant.Tests.Integration.IntegrationTests;
import comp3350group10.academicassistant.Tests.Objects.ObjectTests;
import comp3350group10.academicassistant.Tests.Persistence.PersistenceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                //Objects:
                ObjectTests.class,
                //Logic
                BusinessTests.class,
                //Persistence
                PersistenceTests.class,
                //Integration
                IntegrationTests.class
        })
public class AllTests
{
}
