package comp3350group10.academicassistant.Business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Application.Main;
import comp3350group10.academicassistant.Application.Services;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;

public class CourseLogic
{
    private Persistence dataAccess;
    private static CourseElementLogic cELogic = new CourseElementLogic();
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
            for (int i = 0; i < update.getWorkload().size(); i++)
            {
                total += update.getWorkload().get(i).getWeight();
                marks += cELogic.getMarks(update.getWorkload().get(i));
            }
            marks = (marks/total) * 100;

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

    public Course getCourse(Term term, int index)
    {
        Course course = null;
        if (term == null)
        {
            throw (new IllegalArgumentException("A null value was passed to this method."));
        }
        else
        {
            System.out.println(term);
            course = term.getCourse(index);
        }
        return course;
    }

    public void setGoal(Course course, String goal)
    {
        if (course != null && goal != null)
        {
            course.setGoal(goal);
        }
        else
        {
            throw (new IllegalArgumentException("A null value was passed to this method."));
        }
    }

    public void updateCourse(Term term,Course course)
    {
        if (term != null && course != null)
        {
            dataAccess.updateCourse(term, course);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public void addCourseElement(Course course, CourseElement element)
    {
        if (course != null && element != null)
        {
            dataAccess.addCourseElement(course, element);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public CourseElement getElement(Course course, int index)
    {
        CourseElement element = null;
        if (course != null)
        {
            element = course.getCourseElement(index);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        return element;
    }

    public void removeCourseElement(Course course, CourseElement element)
    {
        if (course != null && element != null)
        {
            dataAccess.deleteCourseElement(course, element);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public void updateCourseElement(Course course, CourseElement element)
    {
        if (course != null && element != null)
        {
            dataAccess.updateCourseElement(course, element);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }
}
