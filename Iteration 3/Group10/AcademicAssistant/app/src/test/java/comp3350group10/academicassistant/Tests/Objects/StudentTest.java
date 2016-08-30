package comp3350group10.academicassistant.Tests.Objects;

import org.junit.Test;

import comp3350group10.academicassistant.Objects.Student;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class StudentTest
{
    Student student;

    @Test
    public void testValidCreate()
    {
        System.out.println("\nStarting StudentTest: Valid creation.");
        student = new Student("Jason Bourne", 123456789, 4.5);
        assertNotNull(student);
        assertEquals(student.getName(), "Jason Bourne");
        assertEquals(student.getGpa(), 4.5, 0.0);
        assertEquals(student.getId(), 123456789);
        System.out.println("\nFinished StudentTest: Valid creation.");
    }

    @Test
    public void testInvalidCreate()
    {
        System.out.println("\nStarting StudentTest: Invalid creation.");
        try
        {
            student = new Student(" ", 123456789, 4.5);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            student = new Student("Jason Bourne", -12345, 4.5);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            student = new Student("Jason Bourne", 123456789, -4.5);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished StudentTest: Invalid creation.");
    }

    @Test
    public void testNullCreation()
    {
        System.out.println("\nStarting StudentTest: Null creation.");
        try
        {
            student = new Student(null, 123456789, 4.5);
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished StudentTest: Null creation.");
    }

    @Test
    public void testValidManipulation()
    {
        System.out.println("\nStarting StudentTest: Valid manipulation.");
        student = new Student("Jason Bourne", 123456789, 4.5);
        student.setId(69);
        student.setName("Leek Madik");
        assertEquals(student.getName(), "Leek Madik");
        assertEquals(student.getGpa(), 4.5, 0.0);
        assertEquals(student.getId(), 69);
        System.out.println("\nFinished StudentTest: Valid manipulation.");
    }

    @Test
    public void testInvalidManipulation()
    {
        System.out.println("\nStarting StudentTest: Invalid manipulation.");
        student = new Student("Jason Bourne", 123456789, 4.5);
        try
        {
            student.setId(-69);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try {
            student.setName(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished StudentTest: Invalid manipulation.");
    }

}
