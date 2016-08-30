package comp3350group10.academicassistant.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Course extends AcademicObject
{
    private String department;
    private int courseNum;
    private ArrayList<Course> prereqs;
    private ArrayList<CourseElement> workload;
    private String grade;
    private double totalMarks;

    public Course(String department, int courseNum)
    {
        if (department == null)
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        else if (courseNum < 0)
        {
            throw (new IllegalArgumentException("An illegal course number was passed to this method."));
        }
        else if (department.trim().length() == 0)
        {
            throw (new IllegalArgumentException("An empty (whitespace) department was passed to this method."));
        }
        else
        {
            this.department = department;
            this.courseNum = courseNum;
            this.prereqs = new ArrayList<Course>();
            this.workload = new ArrayList<CourseElement>();
            this.grade = "";
            this.totalMarks = 0;
        }
    }

    public boolean addPrereq(Course course)
    {
        boolean success = false;
        if (course == null)
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        else if (course == this)
        {
            throw (new IllegalArgumentException("A course cannot be used as a prerequisite for itself."));
        }
        else if (course.getPrereqs().contains(this))
        {
            throw (new IllegalArgumentException("A circular prerequisite dependency was found and prevented."));
        }
        else
        {
            if (!prereqs.contains(course))
            {
                prereqs.add(course);
                success = true;
            }
            else
            {
                throw new IllegalArgumentException("Duplicate prerequisite passed to this method.");
            }
        }
        return success;
    }

    public boolean removePrereq(Course course)
    {
        boolean success = false;
        if (course != null)
        {
            if (prereqs.contains(course))
            {
                prereqs.remove(course);
                success = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        return success;
    }

    public boolean addWork(CourseElement workItem)
    {
        boolean success = false;
        if (workItem != null)
        {
            if (!workload.contains(workItem))
            {
                workload.add(workItem);
                success = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        return success;
    }

    public boolean removeWork(CourseElement workItem)
    {
        boolean success = false;
        if (workItem != null)
        {
            if (workload.contains(workItem))
            {
                workload.remove(workItem);
                success = true;
            }
        }
        else
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        return success;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        if (department != null)
        {
            this.department = department;
        }
        else
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
    }

    public int getCourseNum()
    {
        return courseNum;
    }

    public void setCourseNum(int courseNum)
    {
        if (courseNum >= 0)
        {
            this.courseNum = courseNum;
        }
        else
        {
            throw (new IllegalArgumentException("An illegal argument was passed to this method."));
        }
    }

    public ArrayList<Course> getPrereqs()
    {
        return (ArrayList<Course>) prereqs.clone();
    }

    public ArrayList<CourseElement> getWorkload()
    {
        return (ArrayList<CourseElement>) workload.clone();
    }

    public String getGrade()
    {
        return this.grade;
    }

    public double getTotalMarks() {return this.totalMarks;}

    public void setGrade(String grade)
    {
        List<String> grades = Arrays.asList("A+","A","B+","B","C+","C","D","F");
        if(grades.contains(grade))
        {
            this.grade = grade;
        }
        else
        {
            throw (new IllegalArgumentException("Invalid grade was passed."));
        }
    }
    public void setTotalMarks(double marks)
    {
        this.totalMarks = marks;
    }

    public String toString()
    {
        return department + " " + courseNum;
    }
}
