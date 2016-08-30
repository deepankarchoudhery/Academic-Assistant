package comp3350group10.academicassistant.Tests.Persistence;

import org.junit.Test;

import java.util.ArrayList;

import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataAccessTest
{
    Persistence persistence;

    @Test
    public void testOpenValid()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Opening the db regularly.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);

        System.out.println("Finished DataAccessTest: Opening the db regularly.\n");
    }

    @Test
    public void testOpenNull()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Opening the db using null parameter.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        ArrayList<Term> termImage = persistence.getTerms();
        persistence.open(null);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        assertEquals(persistence.getTerms(), termImage);
        System.out.println("Finished DataAccessTest: Opening the db using null parameter.\n");
    }

    @Test
    public void testCloseValid()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Close the db regularly.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.close();
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);
        System.out.println("Finished DataAccessTest: Close the db regularly.\n");
    }

    @Test
    public void testGetTermsEmpty()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Try to get terms from empty db.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.close();
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);
        System.out.println("Finished DataAccessTest: Try to get terms from empty db.\n");
    }

    @Test
    public void testGetTermsPopulated()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Get terms from populated db.\n");
        assertNotNull(persistence.getTerms());
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished DataAccessTest: Get terms from populated db.\n");
    }

    @Test
    public void testValidTermUpdate()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Perform an update on an existing term.\n");
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
        System.out.println("Finished DataAccessTest: Perform an update on an existing term.\n");
    }

    @Test
    public void testInvalidUpdate()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Perform an invalid (year) update on a null term.\n");
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
        System.out.println("Finished DataAccessTest: Perform an invalid (year) update on a null term.\n");
    }

    @Test
    public void testNullTermUpdate()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Perform an update on a null term.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        testTerm.setYear(2000);
        persistence.updateTerm(null);
        assertEquals(persistence.getTerms().size(), 5);
        assertEquals(persistence.getTerms().get(4).getYear(), 2000);
        System.out.println("Finished DataAccessTest: Perform an update on a null term.\n");
    }

    @Test
    public void testMissingTermUpdate()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Perform an update on a term that is not in the db.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        persistence.updateTerm(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished DataAccessTest: Perform an update on a term that is not in the db.\n");
    }

    @Test
    public void testValidDeleteTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Delete a valid, existing term.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().size(), 5);
        persistence.deleteTerm(testTerm);
        assertEquals(persistence.getTerms().size(),4);
        assertFalse(persistence.getTerms().contains(testTerm));
        System.out.println("Finished DataAccessTest: Delete a valid, existing term.\n");
    }

    @Test
    public void testDeleteNullTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Try to delete a null term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        persistence.deleteTerm(null);
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished DataAccessTest: Try to delete a null term.\n");
    }

    @Test
    public void testDeleteMissingTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Try to delete a term that is not in the db.\n");
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertFalse(persistence.getTerms().contains(testTerm));
        persistence.deleteTerm(testTerm);
        assertEquals(persistence.getTerms().size(),4);
        assertFalse(persistence.getTerms().contains(testTerm));
        System.out.println("Finished DataAccessTest: Try to delete a term that is not in the db.\n");
    }

    @Test
    public void testAddValidTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Add a valid term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        assertEquals(persistence.getTerms().size(), 5);
        System.out.println("Finished DataAccessTest: Add a valid term.\n");
    }

    @Test
    public void testAddNullTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Add a null term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        assertFalse(persistence.addTerm(null));
        assertEquals(persistence.getTerms().size(), 4);
        System.out.println("Finished DataAccessTest: Add a null term.\n");
    }

    @Test
    public void testAddDuplicateTerm()
    {
        Services.closeDataAccess();
        //Use the code below to inject the stub database
        persistence = Services.createDataAccess(new StubDB());
        System.out.println("Starting DataAccessTest: Add a duplicate term.\n");
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 4);
        Term testTerm = new Term(Semester.FALL, 2050);
        assertNotNull(testTerm);
        assertTrue(persistence.addTerm(testTerm));
        assertFalse(persistence.addTerm(testTerm));
        assertTrue(persistence.getTerms().contains(testTerm));
        assertEquals(persistence.getTerms().get(4).getYear(), 2050);
        assertEquals(persistence.getTerms().size(), 5);
        System.out.println("Finished DataAccessTest: Add a duplicate term.\n");
    }
}
