package comp3350group10.academicassistant.Business;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;

public class CalculateGPA
{
    public static double gpa(Term currentTerm)
    {
        final double[] values = {4.50,4.00,3.50,3.00,2.50,2.00,1.00,0.00};
        final String[] grades = {"A+","A","B+","B","C+","C","D","F"};
        double termGPA = 0;

        if (currentTerm != null)
        {
            int numCourses;
            double totalGPA = 0;
            if (currentTerm.getCourseList().size() > 0)
            {
                for (numCourses = 0; numCourses < currentTerm.getCourseList().size(); numCourses++)
                {
                    int getGrade = 0;
                    Course add = currentTerm.getCourseList().get(numCourses);
                    if (add != null)
                    {
                        String courseGrade = add.getGrade();
                        boolean foundGrade = false;
                        for (getGrade = 0; getGrade < grades.length && !foundGrade; getGrade++)
                        {
                            if (courseGrade.equals(grades[getGrade]))
                            {
                                foundGrade = true;
                            }
                        }
                    }
                    totalGPA += values[getGrade-1];
                }
                termGPA = totalGPA / numCourses;
                termGPA = (termGPA * 100) / 100;
            }
        }
        return termGPA;
    }
}
