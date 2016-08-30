package comp3350group10.academicassistant.Tests.Persistence;

import java.util.ArrayList;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

public class StubDB implements Persistence
{
    private ArrayList<Term> terms;

    private int nextTermID;

    public StubDB()
    {
        terms = new ArrayList<>();
        nextTermID = 1;
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
        boolean added = false;
        if(term!=null && !terms.contains(term))
        {
            term.setPersistentID(nextTermID);
            nextTermID++;
            terms.add(term);
            added = true;
        }
        return added;
    }

    public boolean deleteTerm(Term term)
    {
        boolean deleted = false;
        if(term!=null && terms.contains(term))
        {
            terms.remove(term);
            deleted = true;
        }
        return deleted;
    }

    public void updateTerm(Term term)
    {
        if(term!=null)
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
        boolean added = false;
        if(term!=null && course!=null)
        {
            term.addCourse(course);
            this.updateTerm(term);
            added = true;
        }
        return added;
    }

    public boolean deleteCourse(Term term, Course course)
    {
        boolean removed = false;
        if(term!=null && course!=null)
        {
            term.removeCourse(course);
            this.updateTerm(term);
            removed = true;
        }
        return removed;
    }
}
