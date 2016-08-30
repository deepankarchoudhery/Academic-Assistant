package comp3350group10.academicassistant.Tests.Business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350group10.academicassistant.Business.TermComparator;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import java.util.ArrayList;
import java.util.Collections;

public class TermComparatorTest
{
    Term test;
    Term testTwo;
    Term testThree;
    Semester fall;
    Semester winter;
    Semester summer;
    ArrayList<Term> terms;
    @Before
    public void setUp()
    {
        fall = Semester.FALL;
        winter = Semester.WINTER;
        summer = Semester.SUMMER;
        terms = new ArrayList<Term>();
    }

    @Test
    public void testValidCompare() {
        System.out.println("\nStarting TermComparatorTest: Valid Compare.");
        test = new Term(fall, 2015);
        testTwo = new Term(winter, 2016);
        testThree = new Term(summer, 2016);
        terms.add(testTwo);
        terms.add(test);
        terms.add(testThree);
        test.setPersistentID(1);
        testTwo.setPersistentID(2);
        testThree.setPersistentID(3);
        Collections.sort(terms, new TermComparator());
        assertTrue(terms.get(0).equals(test));
        assertTrue(terms.get(1).equals(testTwo));
        assertTrue(terms.get(2).equals(testThree));
        System.out.println("\nFinished TermComparatorTest: Valid Compare.");
    }
}