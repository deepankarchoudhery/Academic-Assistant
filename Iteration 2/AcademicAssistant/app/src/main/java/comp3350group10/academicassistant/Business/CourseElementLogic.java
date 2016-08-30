package comp3350group10.academicassistant.Business;


import comp3350group10.academicassistant.Objects.CourseElement;

public class CourseElementLogic {
    public static double getMarks(CourseElement element) {

        double grade = 0;
        if (element instanceof CourseElement)
        {
            if (element != null)
            {
                grade = (element.getAchievedMarks() / element.getTotalMarks() * element.getWeight());
                grade = (grade * 100);
                grade = grade / 100;
            }
        }
        return grade;
    }
}