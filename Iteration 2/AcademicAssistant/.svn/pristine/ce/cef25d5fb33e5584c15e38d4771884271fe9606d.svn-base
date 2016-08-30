package comp3350group10.academicassistant.Business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Application.Main;
import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

public class TermLogic
{
    private Persistence dataAccess;

    public TermLogic()
    {
        dataAccess = Services.getDataAccess(Main.getDBPathName());
    }

    private void initializeTermList(ArrayList<Term> terms)
    {
        if (terms != null)
        {
            Collections.sort(terms, new TermComparator());
            Collections.reverse(terms);
        }
        else
        {
            throw (new IllegalArgumentException("There are no terms to be accessed."));
        }
    }

    public ArrayList<Term> getTerms()
    {
        ArrayList<Term> terms = dataAccess.getTerms();
        initializeTermList(terms);
        return terms;
    }

    public boolean isDuplicate(Term term)
    {
        boolean isDuplicate = false;
        if (dataAccess.getTerms().contains(term))
        {
            isDuplicate = true;
        }
        return isDuplicate;
    }

    public void addTerm(Term newTerm)
    {
        if (newTerm != null && !dataAccess.getTerms().contains(newTerm))
        {
            dataAccess.addTerm(newTerm);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public Term getTerm(int index)
    {
        if (!(index < getTerms().size() && index >= 0))
        {
            throw (new IllegalArgumentException("This method was passed an invalid index parameter."));
        }
        return getTerms().get(index);
    }

    public void deleteTerm(Term term)
    {
        if (term != null && dataAccess.getTerms().contains(term))
        {
            dataAccess.deleteTerm(term);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }
    public void updateTerm(Term term)
    {
        if(term!=null)
        {
            dataAccess.updateTerm(term);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public void addCourse(Term term, Course course)
    {

        if(term!=null && course!=null)
        {
            dataAccess.addCourse(term,course);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }
    public boolean deleteCourse(Term term, Course course)
    {
        boolean returnVal = false;
        if(term!=null && course!=null)
        {
            returnVal = dataAccess.deleteCourse(term,course);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        return returnVal;
    }

}