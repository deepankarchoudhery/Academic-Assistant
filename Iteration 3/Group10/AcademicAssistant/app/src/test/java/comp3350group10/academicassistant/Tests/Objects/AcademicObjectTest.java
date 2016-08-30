package comp3350group10.academicassistant.Tests.Objects;


import org.junit.Test;

import comp3350group10.academicassistant.Objects.AcademicObject;
import comp3350group10.academicassistant.Objects.Course;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AcademicObjectTest
{
    AcademicObject aObject;

    @Test
    public void testEqualsNull()
    {
        aObject = new Course("COMP", 1010);
        System.out.println("\nStarting AcademicObjectTest: Valid creation.");
        try
        {
            aObject.equals(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished AcademicObjectTest: Valid creation.");
    }


    @Test
    public void testValidEquals()
    {
        aObject = new Course("COMP", 1010);
        AcademicObject aObject2 = new Course("COMP", 1010);
        aObject.setPersistentID(25);
        aObject2.setPersistentID(25);
        assertTrue(aObject.idSet());
        assertTrue(aObject2.idSet());
        assertTrue(aObject.equals(aObject2));
    }

    @Test
    public void testInvalidEquals()
    {
        aObject = new Course("COMP", 1010);
        AcademicObject aObject2 = new Course("COMP", 1010);
        aObject.setPersistentID(25);
        aObject2.setPersistentID(24);
        assertTrue(aObject.idSet());
        assertTrue(aObject2.idSet());
        assertFalse(aObject.equals(aObject2));
    }

    @Test
    public void testValidID()
    {
        aObject = new Course("COMP", 1010);
        assertEquals(aObject.getPersistentID(), -1);
        assertFalse(aObject.idSet());
        aObject.setPersistentID(12345);
        assertEquals(aObject.getPersistentID(), 12345);
        assertTrue(aObject.idSet());
    }

    @Test
    public void testIllegalID()
    {
        aObject = new Course("COMP", 1010);
        try
        {
            assertFalse(aObject.idSet());
            aObject.setPersistentID(-10);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
    }

    @Test
    public void test1UninitializedEquals()
    {
        aObject = new Course("COMP", 1010);
        AcademicObject aObject2 = new Course("COMP", 1010);
        aObject.setPersistentID(1);
        assertTrue(aObject.idSet());
        assertFalse(aObject2.idSet());
        try
        {
            aObject.equals(aObject2);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
    }

    @Test
    public void test2UninitializedEquals()
    {
        aObject = new Course("COMP", 1010);
        AcademicObject aObject2 = new Course("COMP", 1010);
        assertFalse(aObject.idSet());
        assertFalse(aObject2.idSet());
        try
        {
            aObject.equals(aObject2);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
    }
}
