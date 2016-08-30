package comp3350group10.academicassistant.Tests.Objects;

import org.junit.Test;

import java.util.Date;

import comp3350group10.academicassistant.Objects.Event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class EventTest
{
    Event event;

    @Test
    public void testValidCreate()
    {
        System.out.println("\nStarting EventTest: Valid creation.");

        event = new Event("Study with Jason.", "30 minutes per unit.",new Date(2016, 10, 1), 0);
        assertNotNull(event);
        assertEquals(event.getDate(), (new Date(2016, 10, 1)));
        assertEquals(event.getDescription(), "30 minutes per unit.");
        assertEquals(event.getTitle(), "Study with Jason.");
        assertEquals(event.getDate(), new Date(2016, 10, 1));
        assertEquals(event.getPriority(), 0);

        System.out.println("\nFinished EventTest: Valid creation.");
    }

    @Test
    public void testNullCreate()
    {
        System.out.println("\nStarting EventTest: Null creation.");
        try
        {
            event = new Event(null, "Finishing discussion for 3350 iteration 1.", new Date(2016, 10, 1), 1);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            event = new Event("Group meeting.", null, new Date(2016, 10, 1), 2);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        try
        {
            event = new Event("Group meeting", "Finishing discussion for 3350 iteration 1.", null, 3);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished EventTest: Null creation.");
    }

    @Test
    public void testBlankCreate()
    {
        System.out.println("\nStarting EventTest: Blank creation.");
        try
        {
            event = new Event(" ", "It's cumulative!", new Date(2016, 10, 1), 1);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished EventTest: Blank creation.");
    }

    @Test
    public void testNegativeCreate() {
        System.out.println("\nStarting EventTest: Negative creation.");
        try {
            event = new Event("Group meeting.", "Finishing discussion for 3350 iteration 1.",
                    new Date(2016, 10, 1), -1);
            fail("Expected illegal argument error");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("\nFinished EventTest: Negative creation.");
    }


}
