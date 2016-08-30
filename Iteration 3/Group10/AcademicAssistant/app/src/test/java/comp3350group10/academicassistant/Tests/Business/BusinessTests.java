package comp3350group10.academicassistant.Tests.Business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CalculateGPATest.class,
                TermComparatorTest.class,
                CourseComparatorTest.class,
                TermLogicTest.class,
                CourseLogicTest.class,
        })
public class BusinessTests
{
}
