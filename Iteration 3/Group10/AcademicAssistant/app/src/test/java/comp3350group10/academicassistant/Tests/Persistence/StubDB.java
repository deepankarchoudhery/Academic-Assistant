package comp3350group10.academicassistant.Tests.Persistence;

import java.util.ArrayList;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

public class StubDB implements Persistence
{
    private ArrayList<Term> terms;

    private int nextTermID;
    private int nextCourseID;
    private int nextCourseElementID;

    public StubDB()
    {
        terms = new ArrayList<>();
        nextTermID = 1;
        nextCourseID = 1;
        nextCourseElementID = 1;
    }

    public void open(String dbPath)
    {
        if (dbPath != null)
        {
            createStub(Semester.FALL, 2015);
            createStub(Semester.SUMMER, 2015);
            createStub(Semester.WINTER, 2015);
            createStub(Semester.WINTER, 2016);
        }
    }

    public void close()
    {
        terms.clear();
    }

    private void createStub(Semester semester, Integer year)
    {
        Term newTerm = new Term(semester, year);
        newTerm.setPersistentID(nextTermID);
        terms.add(newTerm);
        nextTermID++;
    }

    public ArrayList<Term> getTerms()
    {
        return (ArrayList<Term>) terms.clone();
    }

    public boolean addTerm(Term term)
    {
        boolean wasAdded = false;

        if (term != null && !terms.contains(term))
        {
            term.setPersistentID(nextTermID);
            nextTermID++;
            terms.add(term);
            wasAdded = true;
        }

        return wasAdded;
    }

    public boolean deleteTerm(Term term)
    {
        boolean wasDeleted = false;

        if (term != null && terms.contains(term))
        {
            terms.remove(term);
            wasDeleted = true;
        }

        return wasDeleted;
    }

    public void updateTerm(Term term)
    {
        if (term != null)
        {
            if (terms.contains(term))
            {
                terms.remove(term);
                terms.add(term);
            }
        }
    }
    public boolean addCourse(Term term, Course course)
    {
        boolean wasAdded = false;

        if (term != null && course != null)
        {
            course.setPersistentID(nextCourseID);
            nextCourseID++;
            term.addCourse(course);
            this.updateTerm(term);
            wasAdded = true;
        }

        return wasAdded;
    }

    public boolean deleteCourse(Term term, Course course)
    {
        boolean wasRemoved = false;

        if (term != null && course != null)
        {
            term.removeCourse(course);
            this.updateTerm(term);
            wasRemoved = true;
        }

        return wasRemoved;
    }

    public boolean updateCourse(Term targetTerm, Course targetCourse)
    {
        boolean wasUpdated = false;

        if (targetTerm != null && targetCourse != null)
        {
            targetTerm.removeCourse(targetCourse);
            targetTerm.addCourse(targetCourse);
            this.updateTerm(targetTerm);
            wasUpdated = true;
        }

        return wasUpdated;
    }

    public boolean addCourseElement(Course targetCourse, CourseElement newElement)
    {
        boolean wasAdded = false;

        if (targetCourse != null && newElement != null)
        {
            newElement.setPersistentID(nextCourseElementID);
            nextCourseElementID++;
            targetCourse.addWork(newElement);
            wasAdded = true;
        }

        return wasAdded;
    }

    public boolean deleteCourseElement(Course targetCourse, CourseElement targetElement)
    {
        boolean wasRemoved = false;

        if (targetCourse != null && targetElement != null)
        {
            targetCourse.removeWork(targetElement);
            wasRemoved = true;
        }

        return wasRemoved;
    }

    public boolean updateCourseElement(Course targetCourse, CourseElement targetElement)
    {
        boolean wasUpdated = false;

        if (targetCourse != null && targetElement != null)
        {
            targetCourse.removeWork(targetElement);
            targetCourse.addWork(targetElement);
            wasUpdated = true;
        }

        return wasUpdated;
    }
}
