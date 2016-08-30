package comp3350group10.academicassistant.Tests.Objects;

import org.junit.Before;
import org.junit.Test;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;

import static org.junit.Assert.*;

public class TermTest
{
    Term test;
    Term testTwo;
    Term testThree;
    Course courseTest;
    Course courseTwo;
    Semester fall;
    Semester winter;
    Semester summer;

    @Before
    public void setUp()
    {
        fall = Semester.FALL;
        winter = Semester.WINTER;
        summer = Semester.SUMMER;
    }
    @Test
    public void testValidCreate()
    {
        System.out.println("\nStarting TermTest: Valid creation.");
        test = new Term(fall,2014);
        testTwo = new Term(fall,2013);
        testThree = new Term(fall,2014);
        assertNotNull(test);
        assertNotNull(test.getCourseList());
        assertEquals(test.getCourseList().size(), 0);
        assertEquals(test.getYear(), 2014);
        assertEquals(test.getSemester(), Semester.FALL);
        assertEquals(0,test.getGPA(),0);

        System.out.println("\nFinished TermTest: Valid creation.");
    }

    @Test
    public void testInvalidCreate()
    {
        System.out.println("\nStarting TermTest: Invalid creation.");
        try
        {
            test = new Term(fall,2101);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            test = new Term(fall,1700);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermTest: Invalid creation.");
    }

    @Test
    public void testNullCourse()
    {
        System.out.println("\nStarting TermTest: Null courses");
        test = new Term(winter,2020);
        try
        {
            test.addCourse(null);
            fail("Term shouldn't add a null course");
        }
        catch(IllegalArgumentException iae){}
        System.out.println("\nFinished TermTest: Null courses.");
    }

    @Test
    public void testValidCourses()
    {
        System.out.println("\nStarting TermTest: Valid Courses.");
        test = new Term(fall,2015);
        courseTest = new Course("COMP", 2080);
        courseTwo = new Course("COMP",2150);
        test.addCourse(courseTest);
        assertEquals(1,test.getCourseList().size());
        test.addCourse(courseTwo);
        assertEquals(2,test.getCourseList().size());
        test.removeCourse(courseTest);
        assertEquals(1,test.getCourseList().size());
        System.out.println("\nFinished TermTest: Valid Courses.");

    }

    @Test
    public void testTrueContains()
    {
        System.out.println("\nStarting TermTest: Term does contain the course specified.");
        test = new Term(fall,2014);
        courseTest = new Course("COMP", 2080);
        assertNotNull(test);
        assertNotNull(courseTest);
        test.addCourse(courseTest);
        assertTrue(test.contains(courseTest));
        System.out.println("\nFinished TermTest: Term does contain the course specified.");
    }

    @Test
    public void testFalseContains()
    {
        System.out.println("\nStarting TermTest: Term does not contain the course specified.");
        test = new Term(fall,2014);
        courseTest = new Course("COMP", 2080);
        assertNotNull(test);
        assertNotNull(courseTest);
        assertFalse(test.contains(courseTest));
        System.out.println("\nFinished TermTest: Term does not contain the course specified.");
    }

    @Test
    public void testNullContains()
    {
        System.out.println("\nStarting TermTest: Checking if term contains a null value.");
        test = new Term(fall,2014);
        assertNotNull(test);
        try
        {
            test.addCourse(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermTest: Checking if term contains a null value.");
    }
}
