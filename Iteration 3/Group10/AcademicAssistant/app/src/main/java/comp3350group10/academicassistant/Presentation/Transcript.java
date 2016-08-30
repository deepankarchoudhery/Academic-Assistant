package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import comp3350group10.academicassistant.Business.CourseComparator;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class Transcript extends Activity
{
    private TermLogic logic = new TermLogic();
    private CourseLogic cLogic = new CourseLogic();
    public static ArrayAdapter<String> adapter;
    private TextView gpa;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);
        gpa = (TextView)findViewById(R.id.overallGPA);
        updateListView();
        showGPA();
    }

    private void updateListView()
    {
        ArrayList<Term> terms = logic.getTerms();
        ArrayList<Course> courses = new ArrayList<>();

        for (int i = 0; i < terms.size(); i++)
        {
            Term curr = terms.get(i);
            ArrayList<Course> curCourseList = curr.getCourseList();
            for (int j = 0; j < curCourseList.size(); j++)
            {
                courses.add(curCourseList.get(j));
            }
        }

        Collections.sort(courses, new CourseComparator());
        ArrayList<String> stringOutput = addGrade(courses);

        ListView list = (ListView)findViewById(R.id.transcriptList);
        adapter = new ArrayAdapter<>(this, R.layout.list_view_style, stringOutput);
        list.setAdapter(adapter);
    }

    private ArrayList<String> addGrade(ArrayList<Course> courses)
    {
        ArrayList<String> stringCourses = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++)
        {
            Course curr = courses.get(i);
            cLogic.updateGrade(curr);
            stringCourses.add(curr.getDepartment() + " " + curr.getCourseNum() + " - " + curr.getGrade());
        }
        return stringCourses;
    }

    private void showGPA()
    {
        double overallGPA = logic.overallGPA();
        gpa.setText("GPA : " + overallGPA);
    }
}
