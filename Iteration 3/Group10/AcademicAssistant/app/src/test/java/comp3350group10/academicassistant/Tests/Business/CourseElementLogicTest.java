package comp3350group10.academicassistant.Tests.Business;

import org.junit.Test;

import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Business.CourseElementLogic;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Tests.Persistence.StubDB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

public class CourseElementLogicTest
{
    CourseElement courseElement;
    CourseElementLogic ceLogic;

    @Test
    public void testGetMarksHundred()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get full marks.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(100);
        assertEquals(0.4, ceLogic.getMarks(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get full marks.");
    }

    @Test
    public void testGetMarksZero()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get zero marks.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(0);
        assertEquals(0.0, ceLogic.getMarks(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get zero marks.");
    }

    @Test
    public void testGetMarksHalf()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get half marks.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(50);
        assertEquals(0.2, ceLogic.getMarks(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get half marks.");
    }

    @Test
    public void testGetMarksNegative()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get marks with a negative mark.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

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
            courseElement.setAchievedMarks(-5);
            assertEquals(0.0, ceLogic.getMarks(courseElement), 0.0);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementLogicTest: Get marks with a negative mark.");
    }

    @Test
    public void testGetMarksExceedTotal()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get marks in excess of the total marks.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

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
            courseElement.setAchievedMarks(1000);
            assertEquals(0.0, ceLogic.getMarks(courseElement), 0.0);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementLogicTest: Get marks in excess of the total marks.");
    }

    @Test
    public void testGetPercentageHundred()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get percentage regularly.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(100);
        assertEquals(100.0, ceLogic.getPercentage(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get marks regularly.");
    }

    @Test
    public void testGetPercentageZero()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get a zero percentage.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(0);
        assertEquals(0.0, ceLogic.getPercentage(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get a zero percentage.");
    }

    @Test
    public void testGetPercentageFifty()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get a fifty percentage.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");
        assertEquals(courseElement.getTitle(), "Final Exam");
        assertEquals(courseElement.getTotalMarks(), 100, 0.0);
        assertEquals(courseElement.getType(), "Test");
        assertEquals(courseElement.getWeight(), 0.4, 0.0);

        courseElement.setAchievedMarks(50);
        assertEquals(50.0, ceLogic.getPercentage(courseElement), 0.0);
        System.out.println("\nFinished CourseElementLogicTest: Get a fifty percentage.");
    }

    @Test
    public void testGetPercentageNegative()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get percentage with a negative mark.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

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
            courseElement.setAchievedMarks(-5);
            assertEquals(0.0, ceLogic.getMarks(courseElement), 0.0);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementLogicTest: Get percentage with a negative mark.");
    }

    @Test
    public void testGetPercentageExceedTotal()
    {
        System.out.println("\nStarting CourseElementLogicTest: Get percentage in excess of the total marks.");
        Services.closeDataAccess();
        Services.createDataAccess(new StubDB());
        ceLogic = new CourseElementLogic();

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
            courseElement.setAchievedMarks(1000);
            assertEquals(0.0, ceLogic.getPercentage(courseElement), 0.0);
            fail("Expected an illegal argument exception.");
        }
        catch(IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished CourseElementLogicTest: Get percentage in excess of the total marks.");
    }
}
