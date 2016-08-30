package comp3350group10.academicassistant.Tests.Business;

import org.junit.Test;

import java.util.ArrayList;

import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Tests.Persistence.StubDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CourseLogicTest
{
    CourseLogic cLogic;
    TermLogic tLogic;

    @Test
    public void testValidGrades()
    {
        System.out.println("\nStarting CourseLogicTest: testValidGrades\n");
        Course testCourse = new Course("COMP", 3350 );
        CourseElement hw = new CourseElement("Homework", "5 in total",
                "Assignments", 50, 40);
        CourseElement exam = new CourseElement("Final Exam", "End of year exam",
                "Exam", 50, 60);
        hw.setAchievedMarks(40);
        exam.setAchievedMarks(30);
        testCourse.addWork(hw);
        testCourse.addWork(exam);
        CourseLogic.updateGrade(testCourse);
        assertTrue(testCourse.getGrade().equals("C+"));
        System.out.println("\nFinished CourseLogicTest: test valid grades.");
    }

    @Test
    public void testInitializeValid()
    {
        System.out.println("\nStarting CourseLogicTest: Valid initialization.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        Course courseOne = new Course("COMP", 2140);
        Course courseTwo = new Course("COMP", 1010);
        Course courseThree = new Course("COMP", 1020);
        tLogic.addCourse(testTerm, courseOne);
        tLogic.addCourse(testTerm, courseTwo);
        tLogic.addCourse(testTerm, courseThree);
        ArrayList<Course> testList = cLogic.getCourses(testTerm);
        assertEquals(testList.get(0), courseOne);
        assertEquals(testList.get(1), courseThree);
        assertEquals(testList.get(2), courseTwo);
        System.out.println("\nFinished CourseLogicTest: Invalid initialization.\n");
    }

    @Test
    public void testGet1Course()
    {
        System.out.println("\nStarting CourseLogicTest: Get one course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        Course testCourse = new Course("COMP", 1010);
        tLogic.addCourse(testTerm, testCourse);
        assertFalse(cLogic.getCourses(testTerm).isEmpty());
        Course getCourse = cLogic.getCourses(testTerm).get(0);
        assertEquals(getCourse, testCourse);
        System.out.println("\nFinished CourseLogicTest: Get one course.\n");
    }

    @Test
    public void testGetInvalidCourse()
    {
        System.out.println("\nStarting CourseLogicTest: Input term does not exist.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2000);
        try
        {
            cLogic.getCourses(testTerm);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Input term does not exist.\n");
    }

    @Test
    public void testGetMultipleCourses()
    {
        System.out.println("\nStarting CourseLogicTest: Get multiple courses.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        Course courseOne = new Course("COMP", 1010);
        Course courseTwo = new Course("COMP", 1020);
        Course courseThree = new Course("COMP", 2140);
        tLogic.addCourse(testTerm, courseOne);
        tLogic.addCourse(testTerm, courseTwo);
        tLogic.addCourse(testTerm, courseThree);
        ArrayList<Course> testList = cLogic.getCourses(testTerm);
        assertEquals(testList.get(0), courseThree);
        assertEquals(testList.get(1), courseTwo);
        assertEquals(testList.get(2), courseOne);
        System.out.println("\nFinished CourseLogicTest: Get multiple courses.\n");
    }

    @Test
    public void testGetEmptyCourses()
    {
        System.out.println("\nStarting CourseLogicTest: Get zero courses.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2000);
        tLogic.addTerm(testTerm);
        assertEquals(cLogic.getCourses(testTerm).size(), 0);
        System.out.println("\nFinished CourseLogicTest: Get zero course.\n");
    }

    @Test
    public void testDuplicateTrue()
    {
        System.out.println("\nStarting CourseLogicTest: Check for duplicate (true).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2015);
        Course testCourse = new Course("COMP", 1010);
        tLogic.addCourse(testTerm, testCourse);
        assertTrue(cLogic.isDuplicate(testTerm, testCourse));
        System.out.println("\nFinished CourseLogicTest: Check for duplicate (true).\n");
    }

    @Test
    public void testDuplicateFalse()
    {
        System.out.println("\nStarting CourseLogicTest: Check for duplicate (false).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2030);
        tLogic.addTerm(testTerm);
        Course testCourse = new Course("COMP", 1010);
        assertFalse(cLogic.isDuplicate(testTerm, testCourse));
        System.out.println("\nFinished CourseLogicTest: Check for duplicate (false).\n");
    }

    @Test
    public void testDuplicateNull()
    {
        System.out.println("\nStarting CourseLogicTest: Check for duplicate (null parameter).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();
        Term testTerm = new Term(Semester.FALL, 2030);
        Course testCourse = new Course("COMP", 1010);
        try
        {
            cLogic.isDuplicate(testTerm, null);
            cLogic.isDuplicate(null, testCourse);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Check for duplicate (null parameter).\n");
    }

    @Test
    public void testUpdateCourseValid()
    {
        System.out.println("\nStarting CourseLogicTest: Update a course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        Term testTerm = new Term(Semester.FALL, 2030);
        assertEquals(2030, testTerm.getYear());
        Course course = new Course("COMP", 1030);
        assertEquals(1030, course.getCourseNum());
        tLogic.addCourse(testTerm, course);
        assertEquals(2016, tLogic.getTerm(0).getYear());
        course.setCourseNum(2000);
        assertEquals(2000, course.getCourseNum());
        testTerm = tLogic.getTerm(0);
        cLogic.updateCourse(testTerm, course);
        testTerm = tLogic.getTerm(0);
        assertEquals(2000, testTerm.getCourseList().get(0).getCourseNum());
        System.out.println("\nFinished CourseLogicTest: Update a course.\n");
    }

    @Test
    public void testUpdateCourseNull()
    {
        System.out.println("\nStarting CourseLogicTest: Update a null course.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        Term testTerm = new Term(Semester.FALL, 2030);
        assertEquals(2030, testTerm.getYear());
        Course course = new Course("COMP", 1030);
        assertEquals(1030, course.getCourseNum());
        tLogic.addCourse(testTerm, course);
        assertEquals(2016, tLogic.getTerm(0).getYear());
        course.setCourseNum(2000);
        assertEquals(2000, course.getCourseNum());
        testTerm = tLogic.getTerm(0);
        try
        {
            cLogic.updateCourse(testTerm, null);
            cLogic.updateCourse(null, course);
            fail("Expected illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Update a null course.\n");
    }

    @Test
    public void testUpdateCourseNoChange()
    {
        System.out.println("\nStarting CourseLogicTest: Update a course with no changes.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        Term testTerm = new Term(Semester.FALL, 2030);
        assertEquals(2030, testTerm.getYear());
        Course course = new Course("COMP", 1030);
        assertEquals(1030, course.getCourseNum());
        tLogic.addCourse(testTerm, course);
        assertEquals(2016, tLogic.getTerm(0).getYear());
        testTerm = tLogic.getTerm(0);
        cLogic.updateCourse(testTerm, course);
        testTerm = tLogic.getTerm(0);
        assertEquals(1030, testTerm.getCourseList().get(0).getCourseNum());
        System.out.println("\nFinished CourseLogicTest: Update a course with no changes.\n");
    }

    @Test
    public void testAddCourseElementValid()
    {
        System.out.println("\nStarting CourseLogicTest: Add a course element.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        cLogic.addCourseElement(course, courseElement);
        assertEquals(100, course.getWorkload().get(0).getTotalMarks(), 0.0);
        System.out.println("\nFinished CourseLogicTest: Add a course element.\n");
    }

    @Test
    public void testAddCourseElementNull()
    {
        System.out.println("\nStarting CourseLogicTest: Add a course element.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        try
        {
            cLogic.addCourseElement(course, null);
            cLogic.addCourseElement(null, courseElement);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Add a null course element.\n");
    }

    @Test
    public void testUpdateElementValid()
    {
        System.out.println("\nStarting CourseLogicTest: Update a course element.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        cLogic.addCourseElement(course, courseElement);
        assertEquals(100, course.getWorkload().get(0).getTotalMarks(), 0.0);
        courseElement.setAchievedMarks(10);
        cLogic.updateCourseElement(course, courseElement);
        assertEquals(10, course.getWorkload().get(0).getAchievedMarks(), 0.0);
        System.out.println("\nFinished CourseLogicTest: Update a course element.\n");
    }

    @Test
    public void testUpdateElementNull()
    {
        System.out.println("\nStarting CourseLogicTest: Update a course element (null).\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        try
        {
            cLogic.updateCourseElement(null, courseElement);
            cLogic.updateCourseElement(course, null);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Update a course element (null).\n");
    }

    @Test
    public void testDeleteElementValid()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a course element.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        cLogic.addCourseElement(course, courseElement);
        assertEquals(1, course.getWorkload().size());
        cLogic.removeCourseElement(course, courseElement);
        assertEquals(0, course.getWorkload().size());
        System.out.println("\nFinished CourseLogicTest: Delete a course element.\n");
    }

    @Test
    public void testDeleteElementNull()
    {
        System.out.println("\nStarting CourseLogicTest: Delete a null course element.\n");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        CourseElement courseElement;
        Course course;
        cLogic = new CourseLogic();
        tLogic = new TermLogic();

        course = new Course("COMP", 1030);
        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        try
        {
            cLogic.removeCourseElement(null, courseElement);
            cLogic.removeCourseElement(course, null);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseLogicTest: Delete a null course element.\n");
    }
}
