package comp3350group10.academicassistant.Business;

import comp3350group10.academicassistant.Objects.CourseElement;

public class CourseElementLogic
{
    public static double getMarks(CourseElement element)
    {
        if (element.getAchievedMarks() < 0 || element.getAchievedMarks() > element.getTotalMarks())
        {
            throw (new IllegalArgumentException("An illegal argument was passed to this method."));
        }

        double grade = 0;
        if (element instanceof CourseElement)
        {
            if (element != null)
            {
                grade = (element.getAchievedMarks() / element.getTotalMarks()) * element.getWeight();
                grade = (grade * 100);
                grade = grade / 100;
            }
        }
        return grade;
    }

    public static double getPercentage(CourseElement element)
    {
        if (element.getAchievedMarks() < 0 || element.getAchievedMarks() > element.getTotalMarks())
        {
            throw (new IllegalArgumentException("An illegal argument was passed to this method."));
        }

        double percentage = 0;
        if (element instanceof CourseElement)
        {
            if (element != null)
            {
                percentage = (element.getAchievedMarks() / element.getTotalMarks()) * 100;
                percentage = (percentage * 100);
                percentage = percentage / 100;
            }
        }
        return percentage;
    }
}