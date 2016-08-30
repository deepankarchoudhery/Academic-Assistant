package comp3350group10.academicassistant.Business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Application.Main;
import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

public class CourseLogic
{
    private Persistence dataAccess;
    public CourseLogic()
    {
        dataAccess = Services.getDataAccess(Main.getDBPathName());
    }

    private void initializeCourseList(ArrayList<Course> courses)
    {
        if (courses == null)
        {
            throw (new IllegalArgumentException("A null value was passed to this method."));
        }
        else
        {
            Collections.sort(courses, new CourseComparator());
            Collections.reverse(courses);
        }
    }

    public ArrayList<Course> getCourses(Term term)
    {
        if (!dataAccess.getTerms().contains(term))
        {
            throw (new IllegalArgumentException("Input term does not exist."));
        }

        ArrayList<Course> courses = term.getCourseList();
        initializeCourseList(courses);
        return courses;
    }

    public boolean isDuplicate(Term term, Course course)
    {
        boolean isDuplicate = false;

        if (term == null || course == null)
        {
            throw (new IllegalArgumentException("A null value was passed to this method."));
        }
        else if (!dataAccess.getTerms().contains(term))
        {
            throw (new IllegalArgumentException("Input term does not exist."));
        }
        else
        {
            if (term.contains(course))
            {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    public static void updateGrade(Course update)
    {
        double total = 0;
        double marks = 0;
        if (update.getWorkload() != null)
        {
            for (int i =0; i < update.getWorkload().size(); i++)
            {
                total += update.getWorkload().get(i).getGrade();
            }
            update.setTotalMarks(total);
            marks = update.getTotalMarks();
            if (marks >= 90)
            {
                update.setGrade("A+");
            }
            else if (marks >= 80)
            {
                update.setGrade("A");
            }
            else if (marks > 75)
            {
                update.setGrade("B+");
            }
            else if (marks >= 70)
            {
                update.setGrade("B");
            }
            else if (marks >= 65)
            {
                update.setGrade("C+");
            }
            else if (marks >= 60)
            {
                update.setGrade("C");
            }
            else if (marks >= 50)
            {
                update.setGrade("D");
            }
            else
            {
                update.setGrade("F");
            }
        }
        else
        {
            update.setGrade("F");
        }
    }
}
