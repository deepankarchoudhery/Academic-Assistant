package comp3350group10.academicassistant.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350group10.academicassistant.Tests.Business.CalculateGPATest;
import comp3350group10.academicassistant.Tests.Business.TermComparatorTest;
import comp3350group10.academicassistant.Tests.Objects.AcademicObjectTest;
import comp3350group10.academicassistant.Tests.Objects.CourseElementTest;
import comp3350group10.academicassistant.Tests.Objects.CourseTest;
import comp3350group10.academicassistant.Tests.Objects.EventTest;
import comp3350group10.academicassistant.Tests.Objects.StudentTest;
import comp3350group10.academicassistant.Tests.Objects.TermTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                //Objects:
                AcademicObjectTest.class,
                CourseElementTest.class,
                CourseTest.class,
                EventTest.class,
                StudentTest.class,
                TermTest.class,
                //Logic
                CalculateGPATest.class,
                TermComparatorTest.class
        })
public class AllTests
{
}
