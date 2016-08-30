package comp3350group10.academicassistant.Business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;


public class TermLogic
{
    private ArrayList<Term> terms;
    private Persistence dataAccess;

    public TermLogic()
    {
        dataAccess = new Persistence();
        terms = dataAccess.getTerms();
        initializeTermList();
    }

    private void initializeTermList()
    {
        Collections.sort(terms, new TermComparator());
        Collections.reverse(terms);
    }

    public ArrayList<Term> getTerms()
    {
        return (ArrayList<Term>) terms.clone();
    }

    public boolean isDuplicate(Term term)
    {
        boolean isDuplicate = false;
        if (terms.contains(term))
        {
            isDuplicate = true;
        }
        return isDuplicate;
    }

    public void addTerm(Term newTerm)
    {
        if (newTerm != null && !terms.contains(newTerm))
        {
            terms.add(newTerm);
            Collections.sort(terms, new TermComparator());
            Collections.reverse(terms);
        }
    }
}