package comp3350group10.academicassistant.Tests.Integration;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import comp3350group10.academicassistant.Application.Main;
import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataAccessIntegrationTest
{
    public static Persistence persistence;

    @Before
    public void setup()
    {
        Services.createDataAccess(Main.dbName);
        Persistence p = Services.getDataAccess(Main.dbName);
        ArrayList<Term> termList = p.getTerms();
        int numTerms = termList.size();

        for (int i = 0; i < numTerms; i++)
        {
            termList = p.getTerms();
            p.deleteTerm(termList.get(0));
        }
        Services.closeDataAccess();
        Services.createDataAccess(Main.dbName);
    }

    @Test
    public void testAccessTerms()
    {
        System.out.println("\nStarting Integration test of Persistence (Accessing terms)");
        Term term;
        persistence = Services.getDataAccess(Main.dbName);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);

        Term newTerm1 = new Term(Semester.FALL, 2015);
        Term newTerm2 = new Term(Semester.WINTER, 2016);
        Term newTerm3 = new Term(Semester.SUMMER, 2016);
        persistence.addTerm(newTerm1);
        persistence.addTerm(newTerm2);
        persistence.addTerm(newTerm3);

        term = persistence.getTerms().get(0);
        assertNotNull(term);
        assertEquals(Semester.FALL, term.getSemester());
        assertEquals(2015, term.getYear());

        term = persistence.getTerms().get(1);
        assertNotNull(term);
        assertEquals(Semester.WINTER, term.getSemester());
        assertEquals(2016, term.getYear());

        term = persistence.getTerms().get(2);
        assertNotNull(term);
        assertEquals(Semester.SUMMER, term.getSemester());
        assertEquals(2016, term.getYear());

        persistence.deleteTerm(term);
        assertEquals(persistence.getTerms().size(), 2);
        term = persistence.getTerms().get(0);
        persistence.deleteTerm(term);
        assertEquals(persistence.getTerms().size(), 1);
        term = persistence.getTerms().get(0);
        persistence.deleteTerm(term);
        assertEquals(persistence.getTerms().size(), 0);
        System.out.println("\nFinished Integration test of Persistence (Accessing terms)");
    }

    @Test
    public void testManipulateTerms()
    {
        System.out.println("\nStarting Integration test of Persistence (Manipulating terms)");
        persistence = Services.getDataAccess(Main.dbName);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);

        Term newTerm1 = new Term(Semester.SUMMER, 2020);
        Term newTerm2 = new Term(Semester.SUMMER, 2021);
        Term newTerm3 = new Term(Semester.FALL, 2021);
        assertNotNull(newTerm1);
        assertNotNull(newTerm2);
        assertNotNull(newTerm3);

        assertTrue(persistence.addTerm(newTerm1));
        assertTrue(persistence.addTerm(newTerm2));
        assertTrue(persistence.addTerm(newTerm3));

        Term term = persistence.getTerms().get(0);
        term.setYear(2025);
        term.setSemester(Semester.FALL);
        persistence.updateTerm(term);
        term = persistence.getTerms().get(0);
        assertEquals(Semester.FALL, term.getSemester());
        assertEquals(2025, term.getYear());

        term = new Term(Semester.SUMMER, 2030);
        persistence.addTerm(term);
        persistence.updateTerm(term);
        System.out.println("\nFinished Integration test of Persistence (Manipulating terms)");}

    @Test
    public void testAccessCourses()
    {
        System.out.println("\nStarting Integration test of Persistence (Accessing courses)");
        persistence = Services.getDataAccess(Main.dbName);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);
        Term term;
        Course course1;
        Course course2;

        term = new Term(Semester.FALL, 2010);
        assertEquals(Semester.FALL, term.getSemester());
        course1 = new Course("COMP", 3350);
        assertEquals("COMP", course1.getDepartment());
        course2 = new Course("COMP", 4350);
        assertEquals(4350, course2.getCourseNum());

        persistence.addTerm(term);
        assertEquals(1, persistence.getTerms().size());
        persistence.addCourse(term, course1);
        term = persistence.getTerms().get(0);
        assertEquals(1, term.getCourseList().size());
        persistence.addCourse(term, course2);
        term = persistence.getTerms().get(0);
        assertEquals(2, term.getCourseList().size());
        persistence.deleteCourse(term, course1);
        term = persistence.getTerms().get(0);
        assertEquals(1, term.getCourseList().size());
        assertEquals(4350, term.getCourse(0).getCourseNum());
        persistence.addCourse(term, course1);
        term = persistence.getTerms().get(0);
        assertEquals(2, term.getCourseList().size());

        persistence.deleteCourse(term, course1);
        persistence.deleteCourse(term, course2);
        term = persistence.getTerms().get(0);
        assertEquals(0, term.getCourseList().size());

        persistence.addCourse(term, course1);
        course1.setCourseNum(1010);
        persistence.updateCourse(term, course1);
        term = persistence.getTerms().get(0);
        course1 = term.getCourse(0);
        assertEquals(1010, course1.getCourseNum());
        System.out.println("\nFinished Integration test of Persistence (Accessing courses)");
    }

    @Test
    public void testAccessCourseElements()
    {
        System.out.println("\nStarting Integration test of Persistence (Accessing course elements)");
        CourseElement courseElement;
        Term term;
        Course course;
        persistence = Services.getDataAccess(Main.dbName);
        assertNotNull(persistence);
        assertEquals(persistence.getTerms().size(), 0);

        courseElement = new CourseElement("Final Exam", "Its cumulative!",
                "Test", 100, 0.4);
        assertNotNull(courseElement);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");

        term = new Term(Semester.FALL, 2010);
        assertEquals(Semester.FALL, term.getSemester());
        course = new Course("COMP", 3350);
        assertEquals("COMP", course.getDepartment());

        persistence.addTerm(term);
        assertEquals(1, persistence.getTerms().size());
        persistence.addCourse(term, course);
        term = persistence.getTerms().get(0);
        assertEquals(1, term.getCourseList().size());
        persistence.addCourseElement(course, courseElement);
        assertEquals(1, course.getWorkload().size());

        courseElement = course.getCourseElement(0);
        assertEquals(courseElement.getAchievedMarks(), 0.0, 0.0);
        assertEquals(courseElement.getDescription(), "Its cumulative!");

        persistence.deleteCourseElement(course, courseElement);
        assertEquals(0, course.getWorkload().size());

        persistence.deleteCourse(term, course);
        term = persistence.getTerms().get(0);
        assertEquals(0, term.getCourseList().size());

        course.addWork(courseElement);
        persistence.addCourse(term, course);
        term = persistence.getTerms().get(0);
        assertEquals(1, term.getCourseList().size());
        assertEquals(1, course.getWorkload().size());
        System.out.println("\nFinished Integration test of Persistence (Accessing course elements)");
    }
}
