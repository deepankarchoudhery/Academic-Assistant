package comp3350group10.academicassistant.Tests.Business;

import org.junit.Before;
import org.junit.Test;

import comp3350group10.academicassistant.Business.CalculateGPA;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



public class CalculateGPATest
{
    Semester fall;
    Semester summer;

    Term testTerm;
    Course testCourse;
    Course courseTwo;
    Course courseThree;

    @Before
    public void setUp()
    {
        fall = Semester.FALL;
        summer = Semester.SUMMER;
        testCourse = new Course("COMP", 3350 );
        courseTwo = new Course("COMP", 3430);
        courseThree = new Course("COMP", 3170);
    }
    @Test
    public void testNullTerm()
    {
        System.out.println("\nStarting testCalculateGPA: Null term\n");
        double gpa = CalculateGPA.gpa(null);
        assertEquals(gpa,0,0);
        System.out.println("\nFinished testCalculateGPA: Null term\n");
    }

    @Test
    public void testEmptyTerm()
    {
        System.out.println("\nStarting testCalculateGPA: Empty course\n");
        testTerm = new Term(fall,2016);
        double gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,0,0);
        System.out.println("\nFinished testCalculateGPA: Empty Course\n");
    }

    @Test
    public void testNullCourse()
    {
        System.out.println("\nStarting testCalculateGPA: Null course\n");
        testTerm = new Term(summer,2020);
        try
        {
            testTerm.addCourse(null);
            fail("Adding a null course shouldn't work");
        }
        catch(IllegalArgumentException exception){}

        double gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,0,0);
        System.out.println("\nFinished testCalculateGPA: Null course\n");
    }
    @Test
    public void testEmptyCourse()
    {
        System.out.println("\nStarting testCalculateGPA: Empty course\n");
        testTerm = new Term(fall,2012);
        testTerm.addCourse(testCourse);
        double gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,0.0,0.0);
        System.out.println("\nFinished testCalculateGPA: Empty Course\n");
    }

    @Test
    public void testValidTerm()
    {
        System.out.println("\nStarting testCalculateGPA: Valid term\n");
        testTerm = new Term(fall, 2012);
        testCourse.setGrade("A+");
        courseTwo.setGrade("A");
        testTerm.addCourse(testCourse);
        testTerm.addCourse(courseTwo);
        double gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,4.25,0);
        testTerm.removeCourse(courseTwo);
        gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,4.50,0);
        System.out.println("\nFinished testCalculateGPA: Valid term\n");
    }

    @Test
    public void testInvalidGrades()
    {
        System.out.println("\nStarting testCalculateGPA: testInvalidGrades\n");
        try
        {
            testTerm = new Term(fall, 2012);
            testCourse.setGrade(" ");
            courseTwo.setGrade("C-");
            courseThree.setGrade("X");
            fail("Course shouldn't be allowed to assign/set invalid grades,");
        }
        catch(IllegalArgumentException iae){}
        testTerm.addCourse(testCourse);
        testTerm.addCourse(courseTwo);
        testTerm.addCourse(courseThree);
        double gpa = CalculateGPA.gpa(testTerm);
        assertEquals(gpa,0,0);

        System.out.println("\nFinished testCalculateGPA: testInvalidGrades\n");
    }
}
