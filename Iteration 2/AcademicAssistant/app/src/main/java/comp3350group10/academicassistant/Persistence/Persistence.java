package comp3350group10.academicassistant.Persistence;

import java.util.ArrayList;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;

public interface Persistence
{
    void open(String dbPath);
    void close();
    ArrayList<Term> getTerms();
    void updateTerm(Term term);
    boolean deleteTerm(Term term);
    boolean addTerm(Term term);
    boolean addCourse(Term term, Course course);
    boolean deleteCourse(Term term, Course course);
}
