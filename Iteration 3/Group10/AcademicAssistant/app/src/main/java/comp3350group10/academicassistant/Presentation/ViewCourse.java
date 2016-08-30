package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class ViewCourse extends Activity {

    private int termPosition;
    private TermLogic logic = new TermLogic();
    public static ArrayAdapter<CourseElement> adapter;
    private CourseLogic cLogic = new CourseLogic();
    private int coursePos;
    ArrayList<CourseElement> elements;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        Intent myIntent = getIntent();
        coursePos = myIntent.getIntExtra("CourseIndex", 0);
        termPosition = myIntent.getIntExtra("Index", 0);
        updateView();

        Button addCourseE = (Button)findViewById(R.id.addCourseElementBtn);
        addCourseE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCourse.this, CreateCourseElement.class);
                startActivityForResult(i, 1);
            }
        });

        Button setGoal = (Button)findViewById(R.id.setGradeBtn);
        setGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCourse.this,SetGradeGoal.class);
                startActivityForResult(i, 1);
            }
        });

        Button deleteCourseE = (Button)findViewById(R.id.deleteCourseEBtn);
        deleteCourseE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCourse.this,DeleteCourseElement.class);
                startActivityForResult(i,1);
            }
        });

        Button updateGrade = (Button)findViewById(R.id.updateCourseGradeBtn);;
        updateGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrade();
                updateView();
            }
        });
        ListView list = (ListView) findViewById(R.id.courseEList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent courseE = new Intent(ViewCourse.this, ViewCourseElement.class);
                courseE.putExtra("CourseIndex", coursePos);
                courseE.putExtra("TermIndex", termPosition);
                courseE.putExtra("Index", position);
                startActivity(courseE);
            }
        });
    }

    private void updateView()
    {
        Term term = getTerm();
        if (term != null)
        {
            Course course = cLogic.getCourse(term, coursePos);
            if(course != null)
            {
                updateList(course);
                updateGrade(course);
                updateTitle(course);
            }
        }
    }

    private void updateList(Course course)
    {
        if (course != null)
        {
            elements = course.getWorkload();
            adapter = new ArrayAdapter<>(this, R.layout.list_view_style, elements);
            ListView list = (ListView)findViewById(R.id.courseEList);
            list.setAdapter(adapter);
        }
    }
    private void updateGrade(Course course)
    {
        if (course != null)
        {
            TextView gradeView = (TextView)findViewById(R.id.grade);
            gradeView.setText("Grade - " + course.getGrade());

            TextView goalView = (TextView)findViewById(R.id.setGoalText);
            goalView.setText("Goal Grade - " + course.getGoal());
        }
    }
    private void updateTitle(Course course)
    {
        if (course != null)
        {
            TextView title = (TextView)findViewById(R.id.courseTitle);
            title.setText(course.toString());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10)
        {
            String courseETitle = data.getStringExtra("Title");
            String courseEDescrip = data.getStringExtra("Description");
            String courseEType = data.getStringExtra("Type");
            double courseEWeight = data.getDoubleExtra("Weight",0);
            double courseETotalMarks = data.getDoubleExtra("TotalMarks",0);
            CourseElement element = new CourseElement(courseETitle,courseEDescrip,courseEType,courseETotalMarks,courseEWeight);
            addCourseElement(element);
        }
        else if(resultCode == 100)
        {
            String grade = data.getStringExtra("Grade");
            Term term  = getTerm();
            if(term!=null)
            {
                Course course = cLogic.getCourse(term,coursePos);
                if (course != null)
                {
                    cLogic.setGoal(course,grade);
                    cLogic.updateCourse(term,course);
                }
            }
        }
        else if(resultCode == 200)
        {
            String courseEPos = data.getStringExtra("Index");
            if (courseEPos!=null)
            {
                int coursePosition = Integer.parseInt(courseEPos);
                deleteCourseE(coursePosition);
            }
        }
        updateView();
    }

    private void addCourseElement(CourseElement element)
    {
        if(element!=null)
        {
            Term term = getTerm();
            if (term != null)
            {
                Course course = cLogic.getCourse(term, coursePos);
                if (course != null)
                {
                    cLogic.addCourseElement(course,element);
                }
            }
        }
        updateView();
    }

    private void deleteCourseE(int index)
    {
        Term term = getTerm();
        if (term != null)
        {
            Course course = cLogic.getCourse(term, coursePos);
            if (course != null)
            {
                CourseElement element = cLogic.getElement(course,index);
                if (element != null)
                {
                    cLogic.removeCourseElement(course,element);
                }
            }
        }
        updateView();
    }
    public Term getTerm()
    {
        return logic.getTerm(termPosition);
    }

    public static ArrayAdapter<CourseElement> getAdapter()
    {
        return adapter;
    }

    private void calculateGrade()
    {
        Term term = getTerm();
        if (term != null)
        {
            Course course = cLogic.getCourse(term, coursePos);
            if (course != null)
            {
                cLogic.updateGrade(course);
                cLogic.updateCourse(term,course);
            }
        }

    }
}
