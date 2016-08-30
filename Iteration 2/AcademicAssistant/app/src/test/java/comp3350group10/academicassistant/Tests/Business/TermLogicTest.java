package comp3350group10.academicassistant.Tests.Business;

import org.junit.Test;

import java.util.ArrayList;

import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Tests.Persistence.StubDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TermLogicTest
{
    TermLogic logic;
    CourseLogic cLogic;

    @Test
    public void testInitializeValid()
    {
        System.out.println("\nStarting TermLogicTest: Valid initialization.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testOne = new Term(Semester.FALL, 2000);
        Term testTwo = new Term(Semester.FALL, 2001);
        Term testThree = new Term(Semester.FALL, 2002);
        logic.addTerm(testOne);
        logic.addTerm(testTwo);
        logic.addTerm(testThree);
        ArrayList<Term> testList = logic.getTerms();
        assertEquals(testList.get(6), testOne);
        assertEquals(testList.get(5), testTwo);
        assertEquals(testList.get(4), testThree);
        System.out.println("\nFinished TermLogicTest: Invalid initialization.\n");
    }

    @Test
    public void testGetEmptyTerms()
    {
        System.out.println("\nStarting TermLogicTest: Get terms with no new terms.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        assertEquals(logic.getTerms().size(), 4);
        System.out.println("\nFinished TermLogicTest: Get terms with no new terms.\n");
    }

    @Test
    public void testGetOneTerm()
    {
        System.out.println("\nStarting TermLogicTest: Get terms with one term.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2000);
        logic.addTerm(testTerm);
        assertEquals(logic.getTerms().size(), 5);
        assertEquals(testTerm, logic.getTerms().get(4));
        System.out.println("\nFinished TermLogicTest: Get terms with one term.\n");
    }

    @Test
    public void testGetMultipleTerms()
    {
        System.out.println("\nStarting TermLogicTest: Get terms with multiple terms.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testOne = new Term(Semester.FALL, 2000);
        logic.addTerm(testOne);
        Term testTwo = new Term(Semester.FALL, 2001);
        logic.addTerm(testTwo);
        assertEquals(logic.getTerms().size(), 6);
        assertEquals(testOne, logic.getTerms().get(5));
        assertEquals(testTwo, logic.getTerms().get(4));
        System.out.println("\nFinished TermLogicTest: Get terms with multiple terms.\n");
    }

    @Test
    public void testDuplicateTrue()
    {
        System.out.println("\nStarting TermLogicTest: Check for duplicate (true).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        try
        {
            Term testOne = new Term(Semester.FALL, 2015);
            logic.addTerm(testOne);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermLogicTest: Check for duplicate (true).\n");
    }

    @Test
    public void testDuplicateFalse()
    {
        System.out.println("\nStarting TermLogicTest: Check for duplicate (false).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testOne = new Term(Semester.FALL, 2000);
        logic.addTerm(testOne);
        Term testTwo = new Term(Semester.FALL, 2016);
        assertFalse(logic.isDuplicate(testTwo));
        System.out.println("\nFinished TermLogicTest: Check for duplicate (false).\n");
    }

    @Test
    public void testAddTermNull()
    {
        System.out.println("\nStarting TermTest: Add a null Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testTerm = null;
        assertEquals(testTerm, null);
        try
        {
            logic.addTerm(testTerm);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermLogicTest: Add a null Course.\n");
    }

    @Test
    public void testAddTermValid()
    {
        System.out.println("\nStarting TermLogicTest: Add a valid Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        System.out.println(logic.getTerms().toString());
        Term testTerm = new Term(Semester.FALL, 2000);
        logic.addTerm(testTerm);
        System.out.println(logic.getTerms().toString());
        assertEquals(logic.getTerms().size(), 5);
        assertTrue(logic.getTerms().contains(testTerm));
        System.out.println("\nFinished TermLogicTest: Add a valid Course.\n");
    }

    @Test
    public void testDeleteTermExists()
    {
        System.out.println("\nStarting TermLogicTest: Delete an existing Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testOne = new Term(Semester.FALL, 2000);
        logic.addTerm(testOne);
        System.out.println("\nFinished TermLogicTest: Delete an existing Course.\n");
    }

    @Test
    public void testDeleteTermNotExists()
    {
        System.out.println("\nStarting TermLogicTest: Delete a term which does not exist.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        try
        {
            logic.deleteTerm(new Term(Semester.FALL, 2000));
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermLogicTest: Delete a term which does not exist.\n");
    }

    @Test
    public void testDeleteTermNull()
    {
        System.out.println("\nStarting TermLogicTest: Delete a null term.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        try
        {
            logic.deleteTerm(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermLogicTest: Delete a null term.\n");
    }

    @Test
    public void testGetTermValidIndex()
    {
        System.out.println("\nStarting TermLogicTest: Get a term at a valid index.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testOne = new Term(Semester.FALL, 2000);
        logic.addTerm(testOne);
        assertNotNull(logic.getTerm(0));
        System.out.println("\nFinished TermLogicTest: Get a term at a valid index.\n");
    }

    @Test
    public void testGetTermInvalidIndex()
    {
        System.out.println("\nStarting TermLogicTest: Get a term at an invalid index.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2000);
        logic.addTerm(testTerm);
        assertFalse(logic.getTerms().isEmpty());
        try
        {
            logic.getTerm(1);
            logic.getTerm(-1);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished TermLogicTest: Get a term at an invalid index.\n");
    }

    @Test
    public void testAddCourseNull()
    {
        System.out.println("\nStarting CourseLogicTest: Add a null Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2030);
        Course testCourse = new Course("COMP", 1010);
        try
        {
            logic.addCourse(testTerm, null);
            logic.addCourse(null, testCourse);
            fail("Expected an illegal argument exception exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Add a null Course.\n");
    }

    @Test
    public void testAddCourseValid()
    {
        System.out.println("\nStarting CourseLogicTest: Add a valid Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2030);
        Course testCourse = new Course("COMP", 1010);
        try
        {
            logic.addCourse(testTerm, testCourse);
            assertEquals(cLogic.getCourses(testTerm).size(), 1);
            assertTrue(cLogic.getCourses(testTerm).contains(testCourse));
            fail("Expected a illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Add a valid Course.\n");
    }

    @Test
    public void testDeleteValidCourse()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a valid Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        Course testCourse = new Course("COMP", 1010);
        logic.deleteCourse(testTerm, testCourse);
        System.out.println("\nFinished CourseLogicTest: Delete a valid Course.\n");
    }

    @Test
    public void testDeleteNullCourse()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a null Course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        logic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        try {
            logic.deleteCourse(testTerm, null);
            fail("Expected a null pointer exception");
        }
        catch(IllegalArgumentException npe)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Delete a null Course.\n");
    }

    @Test
    public void testDeleteNullTerm()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a course with null term.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        Term testTerm = new Term(Semester.FALL, 2015);
        logic = new TermLogic();
        Course testCourse = new Course("COMP", 1010);
        try
        {
            logic.deleteCourse(null, testCourse);
            fail("Expected a illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Delete a course with null term.\n");
    }

    @Test
    public void testDeleteMissingCourse()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a Course that does not exist.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        Term testTerm = new Term(Semester.FALL, 2015);
        logic = new TermLogic();
        Course testCourse = new Course("COMP", 1010);
        logic.deleteCourse(testTerm, testCourse);
        System.out.println("\nFinished CourseLogicTest: Delete a Course that does not exist.\n");
    }

    @Test
    public void testDeleteMissingTerm()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a Course from a term that doesn't exist.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        Term testTerm = new Term(Semester.FALL, 2055);
        logic = new TermLogic();
        Course testCourse = new Course("COMP", 1010);
        logic.deleteCourse(testTerm, testCourse);
        System.out.println("\nFinished CourseLogicTest: Delete a Course from a term that doesn't exist.\n");
    }
}
