package comp3350group10.academicassistant.Tests.Persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class PersistenceTest
{
    Persistence persistence;

    @Before
    public void setUp()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
    }

    @Test
    public void testOpenValid()
    {
        System.out.println("Starting PersistenceTest: Opening the db regularly.\n");
        //Open is called automatically via Services.CreateDataAccess()
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);

        System.out.println("Finished PersistenceTest: Opening the db regularly.\n");
    }

    @Test
    public void testOpenNull()
    {
        System.out.println("Starting PersistenceTest: Opening the db using null parameter.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        ArrayList<Term> termImage = persistence.getTerms();
        persistence.open(null);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        assertEquals(persistence.getTerms(), termImage);
        System.out.println("Finished PersistenceTest: Opening the db using null parameter.\n");
    }

    @Test
    public void testCloseValid()
    {
        System.out.println("Starting PersistenceTest: Close the db regularly.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.close();
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);
        System.out.println("Finished PersistenceTest: Close the db regularly.\n");
    }

    @Test
    public void testGetTermsEmpty()
    {
        System.out.println("Starting PersistenceTest: Try to get terms from empty db.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.close();
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);
        System.out.println("Finished PersistenceTest: Try to get terms from empty db.\n");
    }

    @Test
    public void testGetTermsPopulated()
    {
        System.out.println("Starting PersistenceTest: Get terms from populated db.\n");
        assertNotNull(persistence.getTerms());
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished PersistenceTest: Get terms from populated db.\n");
    }

    @Test
    public void testValidTermUpdate()
    {
        System.out.println("Starting PersistenceTest: Perform an update on an existing term.\n");
        Term testTerm = new Term(Semester.FALL, 2030);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2030);
        testTerm.setYear(2029);
        persistence.updateTerm(testTerm);
        assertEquals(persistence.getTerms().size(), 5);
        System.out.println(persistence.getTerms());
        assertEquals(persistence.getTerms().get(4).getYear(), 2029);
        System.out.println("Finished PersistenceTest: Perform an update on an existing term.\n");
    }

    @Test
    public void testInvalidUpdate()
    {
        System.out.println("Starting PersistenceTest: Perform an invalid (year) update on a null term.\n");
        Term testTerm = new Term(Semester.FALL, 2030);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2030);
        try
        {
            testTerm.setYear(2101);
            persistence.updateTerm(testTerm);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }
        System.out.println("Finished PersistenceTest: Perform an invalid (year) update on a null term.\n");
    }

    @Test
    public void testNullTermUpdate()
    {
        System.out.println("Starting PersistenceTest: Perform an update on a null term.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        testTerm.setYear(2000);
        persistence.updateTerm(null);
        assertEquals(persistence.getTerms().size(), 5);
        assertEquals(persistence.getTerms().get(4).getYear(), 2000);
        System.out.println("Finished PersistenceTest: Perform an update on a null term.\n");
    }

    @Test
    public void testMissingTermUpdate()
    {
        System.out.println("Starting PersistenceTest: Perform an update on a term that is not in the db.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        persistence.updateTerm(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished PersistenceTest: Perform an update on a term that is not in the db.\n");
    }

    @Test
    public void testValidDeleteTerm()
    {
        System.out.println("Starting PersistenceTest: Delete a valid, existing term.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().size(), 5);
        persistence.deleteTerm(testTerm);
        assertEquals(persistence.getTerms().size(),4);
        assertFalse(persistence.getTerms().contains(testTerm));
        System.out.println("Finished PersistenceTest: Delete a valid, existing term.\n");
    }

    @Test
    public void testDeleteNullTerm()
    {
        System.out.println("Starting PersistenceTest: Try to delete a null term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.deleteTerm(null);
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished PersistenceTest: Try to delete a null term.\n");
    }

    @Test
    public void testDeleteMissingTerm()
    {
        System.out.println("Starting PersistenceTest: Try to delete a term that is not in the db.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        persistence.deleteTerm(testTerm);
        assertEquals(persistence.getTerms().size(),4);
        assertFalse(persistence.getTerms().contains(testTerm));
        System.out.println("Finished PersistenceTest: Try to delete a term that is not in the db.\n");
    }

    @Test
    public void testAddValidTerm()
    {
        System.out.println("Starting PersistenceTest: Add a valid term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        assertEquals(persistence.getTerms().size(), 5);
        System.out.println("Finished PersistenceTest: Add a valid term.\n");
    }

    @Test
    public void testAddNullTerm()
    {
        System.out.println("Starting PersistenceTest: Add a null term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        assertFalse(persistence.addTerm(null));
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished PersistenceTest: Add a null term.\n");
    }

    @Test
    public void testAddDuplicateTerm()
    {
        System.out.println("Starting PersistenceTest: Add a duplicate term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertFalse(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        assertEquals(persistence.getTerms().size(), 5);
        System.out.println("Finished PersistenceTest: Add a duplicate term.\n");
    }
}
