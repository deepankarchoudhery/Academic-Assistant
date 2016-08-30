package comp3350group10.academicassistant.Tests.Objects;

import org.junit.Test;

import java.util.Date;

import comp3350group10.academicassistant.Objects.CourseElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CourseElementTest
{
    CourseElement courseElement;

    @Test
    public void testValidCreate()
    {
        System.out.println("\nStarting CourseElementTest: Valid creation.");

        courseElement = new CourseElement("Final Exam", "It's cumulative!",
                "Test", new Date(2016, 10, 1), 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDate(), (new Date(2016, 10, 1)));
        assertEquals(courseElement.getDescription(), "It's cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);
        System.out.println("\nFinished CourseElementTest: Valid creation.");
    }

    @Test
    public void testNullCreate()
    {
        System.out.println("\nStarting CourseElementTest: Null creation.");
        try
        {
            courseElement = new CourseElement(null, "It's cumulative!",
                    "Test", new Date(2016, 10, 1), 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", null,
                    "Test", new Date(2016, 10, 1), 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    null, new Date(2016, 10, 1), 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    "Test", null, 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementTest: Null creation.");
    }

    @Test
    public void testNegCreate()
    {
        System.out.println("\nStarting CourseElementTest: Negative creation.");
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    "Test", new Date(2016, 10, 1), -1, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    "Test", new Date(2016, 10, 1), 100, -0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementTest: Negative creation.");

    }

    @Test
    public void testBlankCreate()
    {
        System.out.println("\nStarting CourseElementTest: Blank creation.");
        try
        {
            courseElement = new CourseElement(" ", "It's cumulative!",
                    "Test", new Date(2016, 10, 1), 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    " ", new Date(2016, 10, 1), 100, 0.4);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementTest: Blank creation.");
    }

    @Test
    public void testNegativeCreate() {
        System.out.println("\nStarting CourseElementTest: Negative creation.");
        try {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    "Test", new Date(2016, 10, 1), -1, 0.4);
            fail("Expected illegal argument error");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            courseElement = new CourseElement("Final Exam", "It's cumulative!",
                    "Test", new Date(2016, 10, 1), 100, -1);
            fail("Expected illegal argument error");
        } catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementTest: Negative creation.");
    }

    @Test
    public void testZeroGrade()
    {
        System.out.println("\nStarting CourseElementTest: Test an achieved grade of 0.");
        courseElement = new CourseElement("Final Exam", "It's cumulative!",
                "Test", new Date(2016, 10, 1), 100, 0.4);
        courseElement.setAchievedMarks(0.0);
        assertEquals(courseElement.getGrade(), 0.0, 0);
        System.out.println("\nFinished CourseElementTest: Test an achieved grade of 0.");
    }

    @Test
    public void testFullGrade()
    {
        System.out.println("\nStarting CourseElementTest: Test an achieved full grade.");
        courseElement = new CourseElement("Final Exam", "It's cumulative!",
                "Test", new Date(2016, 10, 1), 100, 0.4);
        courseElement.setAchievedMarks(100);
        assertEquals(courseElement.getGrade(), 0.4, 0);
        System.out.println("\nFinished CourseElementTest: Test an achieved full grade.");
    }

    @Test
    public void testMidGrade()
    {
        System.out.println("\nStarting CourseElementTest: Test an achieved mid-area grade.");
        courseElement = new CourseElement("Final Exam", "It's cumulative!",
                "Test", new Date(2016, 10, 1), 100, 0.4);
        courseElement.setAchievedMarks(40);
        assertEquals(courseElement.getGrade(), 0.16, 0.001);
        System.out.println("\nFinished CourseElementTest: Test an achieved mid-area grade.");
    }

    @Test
    public void testNegGrade()
    {
        System.out.println("\nStarting CourseElementTest: Test an achieved negative grade.");
        courseElement = new CourseElement("Final Exam", "It's cumulative!",
                "Test", new Date(2016, 10, 1), 100, 0.4);
        try
        {
            courseElement.setAchievedMarks(-10);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementTest: Test an achieved negative grade.");
    }
}
