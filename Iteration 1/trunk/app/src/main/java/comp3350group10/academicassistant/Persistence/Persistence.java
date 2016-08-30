package comp3350group10.academicassistant.Persistence;

import comp3350group10.academicassistant.Objects.*;
import java.util.ArrayList;

public class Persistence
{
    private ArrayList<Term> terms;

    private int nextTermID;

    public Persistence()
    {
        terms = new ArrayList<>();
        nextTermID = 1;
        loadStubDatabase();
    }

    public void loadStubDatabase()
    {
        createStub(Semester.FALL, 2015);
        createStub(Semester.SUMMER, 2015);
        createStub(Semester.WINTER, 2015);
        createStub(Semester.WINTER, 2016);
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
}
