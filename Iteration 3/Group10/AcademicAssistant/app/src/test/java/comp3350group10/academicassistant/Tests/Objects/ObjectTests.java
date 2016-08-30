package comp3350group10.academicassistant.Tests.Objects;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                AcademicObjectTest.class,
                CourseElementTest.class,
                CourseTest.class,
                EventTest.class,
                StudentTest.class,
                TermTest.class,
        })
public class ObjectTests
{
}
