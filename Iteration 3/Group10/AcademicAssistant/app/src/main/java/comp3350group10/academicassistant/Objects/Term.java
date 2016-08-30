package comp3350group10.academicassistant.Objects;

import java.util.ArrayList;

public class Term extends AcademicObject
{
    private Semester semester;
    private int year;
    private double GPA;
    private ArrayList<Course> courseList;

    public Term(Semester semester, int year)
    {
        if (year >= 2000 && year < 2100) {
            this.semester = semester;
            this.year = year;
            this.GPA = 0;
            this.courseList = new ArrayList<>();
        }
        else
        {
            throw (new IllegalArgumentException("An invalid year was passed to this method."));
        }
    }

    public Semester getSemester()
    {
        return semester;
    }

    public ArrayList<Course> getCourseList()
    {
        return (ArrayList<Course>) courseList.clone();
    }

    public int getYear() { return year; }

    public double getGPA() {
        return GPA;
    }

    public void setYear(int year)
    {
        if (year >= 2000 && year < 2100)
        {
            this.year = year;
        }
        else
        {
            throw (new IllegalArgumentException("An invalid year was passed to this method."));
        }
    }

    public void setSemester(Semester semester)
    {
        if (semester != null)
        {
            this.semester = semester;
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
    }

    public boolean contains (Course course)
    {
        boolean contains = false;
        if (course != null)
        {
            if (courseList.contains(course))
            {
                contains = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        return contains;
    }

    public boolean addCourse(Course course)
    {
        boolean added = false;
        if (course != null)
        {
            if (!contains(course))
            {
                courseList.add(course);
                added = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        return added;
    }

    public boolean removeCourse(Course course)
    {
        boolean removed = false;
        if (course != null)
        {
            if (courseList.contains(course))
            {
                courseList.remove(course);
                removed = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        return removed;
    }

    public boolean equals(Object o)
    {
        boolean retVal = false;
        if (o instanceof Term)
        {
            Term other = (Term) o;

            if (this.getYear() == other.getYear())
            {
                if (this.getSemester().equals(other.getSemester()))
                {
                    retVal = true;
                }
            }
        }
        return retVal;
    }

    public Course getCourse(int index)
    {
        Course course;
        if (index < courseList.size())
        {
            course = courseList.get(index);
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed an invalid argument."));
        }
        return course;
    }

    public String toString()
    {
        return semester.toString() + " " + String.valueOf(year);
    }
}
