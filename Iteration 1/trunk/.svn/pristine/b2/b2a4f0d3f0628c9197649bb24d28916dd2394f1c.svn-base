package comp3350group10.academicassistant.Business;

import java.util.Comparator;
import comp3350group10.academicassistant.Objects.Term;

public class TermComparator implements Comparator<Term>
{
    @Override
    public int compare(Term term1, Term term2)
    {
        int retVal = 0;
        int yearCompare = Integer.compare(term1.getYear(), term2.getYear());
        switch (yearCompare)
        {
            case -1:
                retVal = -1;
                break;
            case 0:
                retVal = Integer.compare(term1.getSemester().ordinal(), term2.getSemester().ordinal());
                break;
            case 1:
                retVal = 1;
                break;
        }
        return retVal;
    }
}