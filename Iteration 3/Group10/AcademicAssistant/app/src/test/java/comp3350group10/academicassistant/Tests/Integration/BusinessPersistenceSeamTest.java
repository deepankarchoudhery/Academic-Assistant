package comp3350group10.academicassistant.Tests.Integration;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import comp3350group10.academicassistant.Application.Main;
import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class BusinessPersistenceSeamTest
{
    TermLogic tLogic;
    CourseLogic cLogic;

    @Before
    public void setup()
    {
        Services.createDataAccess(Main.dbName);
        Persistence p = Services.getDataAccess(Main.dbName);

        tLogic = new TermLogic();
        cLogic = new CourseLogic();

        ArrayList<Term> termList = p.getTerms();
        int numTerms = termList.size();

        for (int i = 0; i < numTerms; i++)
        {
            termList = p.getTerms();
            p.deleteTerm(termList.get(0));
        }
    }

    @Test
    public void testTermLogicForTerms()
    {
        System.out.println("\nStarting Integration test of TermLogic (Term manipulation) to persistence");
        Term term;

        assertEquals(0, tLogic.getTerms().size());
        term = new Term(Semester.FALL, 2019);
        tLogic.addTerm(term);

        term = tLogic.getTerm(0);
        assertEquals(2019, term.getYear());

        tLogic.deleteTerm(term);
        assertEquals(0, tLogic.getTerms().size());

        term = new Term(Semester.SUMMER, 2015);
        tLogic.addTerm(term);

        term = tLogic.getTerm(0);
        assertEquals(2015, term.getYear());

        term = new Term(Semester.FALL, 2020);
        tLogic.addTerm(term);
        assertEquals(2, tLogic.getTerms().size());
        term = tLogic.getTerm(1);
        assertEquals(2015, term.getYear());
        assertEquals(Semester.SUMMER, term.getSemester());

        term = tLogic.getTerm(0);
        term.setSemester(Semester.SUMMER);
        term.setYear(2030);
        tLogic.updateTerm(term);
        assertEquals(2, tLogic.getTerms().size());
        term = tLogic.getTerm(0);
        assertEquals(Semester.SUMMER, term.getSemester());
        assertEquals(2030, term.getYear());

        try
        {
            tLogic.addTerm(null);
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }

        try
        {
            tLogic.deleteTerm(new Term(Semester.FALL, 2000));
            fail("Expected an illegal argument exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }

        term = tLogic.getTerm(0);
        tLogic.deleteTerm(term);
        assertEquals(1, tLogic.getTerms().size());
        term = tLogic.getTerm(0);
        tLogic.deleteTerm(term);
        assertEquals(0, tLogic.getTerms().size());

        System.out.println("\nFinished Integration test of TermLogic (Term manipulation) to persistence");
    }

    @Test
    public void testTermLogicForCourses()
    {
        System.out.println("\nStarting Integration test of TermLogic (Course manipulation) to persistence");
        Term term;
        Course course;

        term = new Term(Semester.FALL, 2019);
        tLogic.addTerm(term);
        course = new Course("COMP", 3350);
        tLogic.addCourse(term, course);

        term = tLogic.getTerm(0);
        assertEquals(2019, term.getYear());
        course = term.getCourse(0);
        assertEquals("COMP", course.getDepartment());

        try
        {
            tLogic.addCourse(term, null);
            tLogic.addCourse(null, course);
            fail("Expected an illegal argument exception exception.");
        }
        catch (IllegalArgumentException iae)
        {
        }

        tLogic.deleteCourse(term, course);
        term = tLogic.getTerm(0);

        try {
            tLogic.deleteCourse(term, null);
            fail("Expected a null pointer exception");
        }
        catch(IllegalArgumentException npe)
        {
        }

        System.out.println("\nFinished Integration test of TermLogic (Course manipulation) to persistence");
    }

    @Test
    public void testCourseLogic()
    {
        System.out.println("\nStarting Integration test of CourseLogic (Course manipulation) to persistence");
        CourseElement courseElement;
        Term term;
        Course course;

        term = new Term(Semester.FALL, 2019);
        tLogic.addTerm(term);
        term = tLogic.getTerm(0);
        assertEquals(Semester.FALL, term.getSemester());
        assertEquals(2019, term.getYear());

        course = new Course("COMP", 3350);
        tLogic.addCourse(term, course);

        term = tLogic.getTerm(0);
        course = cLogic.getCourse(term, 0);
        course.setCourseNum(4350);
        cLogic.updateCourse(term, course);

        course = cLogic.getCourse(term, 0);
        assertEquals(4350, course.getCourseNum());
        course = tLogic.getTerms().get(0).getCourse(0);

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
        assertEquals("Its cumulative!", course.getWorkload().get(0).getDescription());

        System.out.println("\nFinished Integration test of CourseLogic (Course manipulation) to persistence");
    }
}
