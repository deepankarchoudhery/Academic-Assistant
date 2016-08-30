package comp3350group10.academicassistant.Business;

import java.util.Comparator;

import comp3350group10.academicassistant.Objects.Course;

public class CourseComparator implements Comparator<Course>
{
    @Override
    public int compare(Course course1, Course course2)
    {
        int retVal = 0;
        int deptCompare = course1.getDepartment().compareTo(course2.getDepartment());

        switch (deptCompare)
        {
            case -1:
                retVal = -1;
                break;
            case 0:
                retVal = Integer.compare(course1.getCourseNum(), course2.getCourseNum());
                break;
            case 1:
                retVal = 1;
                break;
        }
        return retVal;
    }
}
