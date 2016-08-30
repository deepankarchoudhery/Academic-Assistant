package comp3350group10.academicassistant.Tests.Objects;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CourseTest
{
    Course course;
    Course prereq;
    CourseElement work;

    @Before
    public void setUp()
    {
        course = new Course("COMP", 3350);
        assertNotNull(course.getPrereqs());
        assertEquals(course.getPrereqs().size(), 0);
        assertEquals(course.getWorkload().size(), 0);

        prereq = new Course("COMP", 2150);
        assertNotNull(prereq);

        work = new CourseElement("Final Test", "Cumulative final worth 40%", "Test", new Date(2016,10,04),100, .4);
        assertNotNull(work);
    }

    @Test
    public void testValidCreate()
    {
        System.out.println("\nStarting CourseTest: Valid creation.");

        assertNotNull(course);
        assertNotNull(course.getPrereqs());
        assertNotNull(course.getWorkload());
        assertEquals(course.getPrereqs().size(), 0);
        assertEquals(course.getWorkload().size(), 0);
        assertEquals(course.getCourseNum(), 3350);
        assertEquals(course.getDepartment(), "COMP");


        System.out.println("\nFinished CourseTest: Valid creation.");
    }

    @Test
    public void testCreateBlankDept()
    {
        System.out.println("\nStarting CourseTest: Course creation with whitespace department.");
        try
        {
            course = new Course(" ", 3350);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Course creation with whitespace department.");
    }

    @Test
    public void testCreateNullDept()
    {
        System.out.println("\nStarting CourseTest: Course creation with null department.");
        try
        {
            course = new Course(null, 3350);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Course creation with null department.");
    }

    @Test
    public void testCreateNegCourseNum() {
        System.out.println("\nStarting CourseTest: Course creation with negative course number.");
        try
        {
            course = new Course("COMP", -1);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Course creation with negative course number.");
    }

    @Test
    public void testAddValidPrereq()
    {
        System.out.println("\nStarting CourseTest: Add valid prerequisite to course.");

        assertTrue(course.addPrereq(prereq));
        assertEquals(course.getPrereqs().size(), 1);
        assertTrue(course.getPrereqs().contains(prereq));
        System.out.println("\nFinished CourseTest: Add valid prerequisite to course.");
    }

    @Test
    public void testAddMultiPrereq()
    {
        System.out.println("\nStarting CourseTest: Add multiple prerequisites to course.");
        Course prereq2 = new Course("COMP", 1010);
        assertNotNull(prereq2);
        Course prereq3 = new Course("COMP", 1020);
        assertNotNull(prereq3);

        assertTrue(course.addPrereq(prereq));
        assertEquals(course.getPrereqs().size(), 1);
        assertTrue(course.getPrereqs().contains(prereq));
        assertTrue(course.addPrereq(prereq2));
        assertEquals(course.getPrereqs().size(), 2);
        assertTrue(course.getPrereqs().contains(prereq2));
        assertTrue(course.addPrereq(prereq3));
        assertEquals(course.getPrereqs().size(), 3);
        assertTrue(course.getPrereqs().contains(prereq3));
        System.out.println("\nFinished CourseTest: Add multiple prerequisites to course.");
    }

    @Test
    public void testAddNullPrereq()
    {
        System.out.println("\nStarting CourseTest: Add null prerequisite to course.");
        try
        {
            course.addPrereq(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Add null prerequisite to course.");
    }

    @Test
    public void testAddCircularPrereq()
    {
        System.out.println("\nStarting CourseTest: Add course as prerequisite that contains this course as a prerequisite.");
        try
        {
            assertTrue(prereq.addPrereq(course));
            assertEquals(prereq.getPrereqs().size(), 1);
            assertTrue(prereq.getPrereqs().contains(course));

            course.addPrereq(prereq);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Add course as prerequisite that contains this course as a prerequisite.");
    }

    @Test
    public void testAddSelfPrereq()
    {
        System.out.println("\nStarting CourseTest: Add course as a prerequisite to itself.");
        try
        {
            course.addPrereq(course);
            fail("Expected an illegal argument exception.");
        } catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Add course as a prerequisite to itself.");
    }

    @Test
    public void testAddDuplicatePrereq()
    {
        System.out.println("\nStarting CourseTest: Add duplicate prerequisite to course.");
        assertTrue(course.addPrereq(prereq));
        assertTrue(course.getPrereqs().contains(prereq));
        assertEquals(course.getPrereqs().size(), 1);
        try
        {
            assertTrue(course.addPrereq(prereq));
            course.addPrereq(prereq);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Add duplicate prerequisite to course.");
    }

    @Test
    public void testRemoveExistingPrereq()
    {
        System.out.println("\nStarting CourseTest: Remove a valid, existing prerequisite.");
        assertTrue(course.addPrereq(prereq));
        assertTrue(course.getPrereqs().contains(prereq));
        assertEquals(course.getPrereqs().size(), 1);

        assertTrue(course.removePrereq(prereq));
        assertFalse(course.getPrereqs().contains(prereq));
        assertEquals(course.getPrereqs().size(), 0);
        System.out.println("\nFinished CourseTest: Remove a valid, existing prerequisite.");
    }

    @Test
    public void testRemoveMultiPrereq()
    {
        System.out.println("\nStarting CourseTest: Remove multiple valid, existing prerequisites.");
        Course prereq2 = new Course("COMP", 1010);
        assertNotNull(prereq2);
        Course prereq3 = new Course("COMP", 1020);
        assertNotNull(prereq3);

        assertTrue(course.addPrereq(prereq));
        assertEquals(course.getPrereqs().size(), 1);
        assertTrue(course.getPrereqs().contains(prereq));
        assertTrue(course.addPrereq(prereq2));
        assertEquals(course.getPrereqs().size(), 2);
        assertTrue(course.getPrereqs().contains(prereq2));
        assertTrue(course.addPrereq(prereq3));
        assertEquals(course.getPrereqs().size(), 3);
        assertTrue(course.getPrereqs().contains(prereq3));

        assertTrue(course.removePrereq(prereq));
        assertEquals(course.getPrereqs().size(), 2);
        assertFalse(course.getPrereqs().contains(prereq));
        assertTrue(course.removePrereq(prereq2));
        assertEquals(course.getPrereqs().size(), 1);
        assertFalse(course.getPrereqs().contains(prereq2));
        assertTrue(course.removePrereq(prereq3));
        assertEquals(course.getPrereqs().size(), 0);
        assertFalse(course.getPrereqs().contains(prereq3));
        System.out.println("\nFinished CourseTest: Remove multiple valid, existing prerequisites.");
    }

    @Test
    public void testRemoveNotPrereq()
    {
        System.out.println("\nStarting CourseTest: Remove non-existent prerequisite.");
        assertFalse(course.getPrereqs().contains(prereq));
        assertFalse(course.removePrereq(prereq));
        System.out.println("\nFinished CourseTest: Remove non-existent prerequisite.");
    }

    @Test
    public void testRemoveNullPrereq()
    {
        System.out.println("\nStarting CourseTest: Remove a null prerequisite.");
        assertTrue(course.addPrereq(prereq));
        assertTrue(course.getPrereqs().contains(prereq));
        assertEquals(course.getPrereqs().size(), 1);
        try
        {
            course.removePrereq(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Remove a null prerequisite.");
    }

    @Test
    public void testAddValidWork()
    {
        System.out.println("\nStarting CourseTest: Add a valid CourseElement to course.");
        assertTrue(course.addWork(work));
        assertTrue(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 1);
        System.out.println("\nFinished CourseTest: Add a valid CourseElement to course.");
    }

    @Test
    public void testAddMultiWork()
    {
        System.out.println("\nStarting CourseTest: Add multiple CourseElements to a course workload.");
        CourseElement work2 = new CourseElement("Midterm Test", "Cumulative midterm worth 20%",
                "Test", new Date(2016,10,03),100, .2);
        assertNotNull(work2);

        CourseElement work3 = new CourseElement("Term Project", "Project is worth 20%", "Project",
                new Date(2016,10,05),100, .2);
        assertNotNull(work3);

        assertTrue(course.addWork(work));
        assertTrue(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 1);
        assertTrue(course.addWork(work2));
        assertTrue(course.getWorkload().contains(work2));
        assertEquals(course.getWorkload().size(), 2);
        assertTrue(course.addWork(work3));
        assertTrue(course.getWorkload().contains(work3));
        assertEquals(course.getWorkload().size(), 3);
        System.out.println("\nFinished CourseTest: Add multiple CourseElements to a course workload.");
    }

    @Test
    public void testAddNullWork()
    {
        System.out.println("\nStarting CourseTest: Add a null CourseElement to a course workload.");
        try
        {
            course.addWork(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Add a null CourseElement to a course workload.");
    }

    @Test
    public void testRemoveValidWork()
    {
        System.out.println("\nStarting CourseTest: Remove a valid CourseElement from a course workload.");
        assertTrue(course.addWork(work));
        assertEquals(course.getWorkload().size(), 1);
        assertTrue(course.getWorkload().contains(work));

        assertTrue(course.removeWork(work));
        assertFalse(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 0);
        System.out.println("\nFinished CourseTest: Remove a valid  CourseElement to a course workload.");
    }

    @Test
    public void testRemoveMultiWork()
    {
        System.out.println("\nStarting CourseTest: Remove multiple valid CourseElements from a course workload.");
        CourseElement work2 = new CourseElement("Midterm Test", "Cumulative midterm worth 20%",
                "Test", new Date(2016,10,03),100, .2);
        assertNotNull(work2);

        CourseElement work3 = new CourseElement("Term Project", "Project is worth 20%", "Project",
                new Date(2016,10,05),100, .2);
        assertNotNull(work3);

        assertTrue(course.addWork(work));
        assertTrue(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 1);
        assertTrue(course.addWork(work2));
        assertTrue(course.getWorkload().contains(work2));
        assertEquals(course.getWorkload().size(), 2);
        assertTrue(course.addWork(work3));
        assertTrue(course.getWorkload().contains(work3));
        assertEquals(course.getWorkload().size(), 3);

        assertTrue(course.removeWork(work));
        assertFalse(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 2);
        assertTrue(course.removeWork(work2));
        assertFalse(course.getWorkload().contains(work2));
        assertEquals(course.getWorkload().size(), 1);
        assertTrue(course.removeWork(work3));
        assertFalse(course.getWorkload().contains(work3));
        assertEquals(course.getWorkload().size(), 0);
        System.out.println("\nFinished CourseTest: Remove multiple valid CourseElements from a course workload.");
    }

    @Test
    public void testRemoveNullWork()
    {
        System.out.println("\nStarting CourseTest: Remove a null CourseElement from a course workload.");
        try
        {
            course.removeWork(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseTest: Remove a null CourseElement from a course workload.");
    }

    @Test
    public void testRemoveNotWork()
    {
        System.out.println("\nStarting CourseTest: Remove a CourseElement that is not in course workload.");
        assertFalse(course.getWorkload().contains(work));
        assertEquals(course.getWorkload().size(), 0);
        assertFalse(course.removeWork(work));
        System.out.println("\nFinished CourseTest: Remove a CourseElement that is not in course workload.");
    }
    @Test
    public void testInvalidGrades()
    {
        Course testCourse = new Course("COMP", 3350 );
        Course courseTwo = new Course("COMP", 3430);
        Course courseThree = new Course("COMP", 3170);
        System.out.println("\nStarting testCalculateGPA: testInvalidGrades\n");
        try
        {
            testCourse.setGrade(" ");
            courseTwo.setGrade("X");
            courseThree.setGrade("something wrong");
            fail("Course shouldn't be allowed to assign/set invalid grades,");
        }
        catch(IllegalArgumentException iae){}
        System.out.println("\nFinished CourseTest: test invalid grades.");
    }

    @Test
    public void testValidGrades()
    {
        System.out.println("\nStarting testCalculateGPA: testValidGrades\n");
        Course testCourse = new Course("COMP", 3350 );
        CourseElement hw = new CourseElement("Homework", "5 in total",
                "Assignments", new Date(2016, 10, 1), 50, 40);
        CourseElement exam = new CourseElement("Final Exam", "End of year exam",
                "Exam", new Date(2016, 10, 1), 50, 60);
        hw.setAchievedMarks(40);
        exam.setAchievedMarks(30);
        testCourse.addWork(hw);
        testCourse.addWork(exam);
        testCourse.updateGrade();
        System.out.println(testCourse.getGrade());
        assertTrue(testCourse.getGrade().equals("C+"));
        System.out.println("\nFinished CourseTest: test valid grades.");
    }
}
