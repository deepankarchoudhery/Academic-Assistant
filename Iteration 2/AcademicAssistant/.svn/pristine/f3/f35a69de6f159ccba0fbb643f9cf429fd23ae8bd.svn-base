package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class ViewTerm extends Activity
{
    private int position;
    private TermLogic logic = new TermLogic();
    public static ArrayAdapter<Course> adapter;
    private ArrayList<Course> courses;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_term);
        Intent myIntent = getIntent();

        position = myIntent.getIntExtra("Index", 0);
        updateView(position);
        Button addButton = (Button)findViewById(R.id.AddCourseBtn);
        Button deleteButton = (Button)findViewById(R.id.DeleteCourseBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerm.this, CreateCourses.class);
                startActivityForResult(i, 1);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerm.this, DeleteCourses.class);
                startActivityForResult(i, 1);
            }
        });

    }

    public void viewTitle(int pos)
    {
        TextView textView = (TextView)findViewById(R.id.TermTitle);
        Term term = logic.getTerm(pos);
        if (term != null)
        {
            textView.setText(term.toString());
        }
    }

    private void showCourses(int position)
    {
        Term toView = logic.getTerm(position);
        courses = toView.getCourseList();
        adapter = new ArrayAdapter<>(this, R.layout.add_term, courses);
        ListView list = (ListView) findViewById(R.id.CourseList);
        list.setAdapter(adapter);

        Button deleteCourseBtn = (Button)findViewById(R.id.DeleteCourseBtn);
        if (courses.isEmpty())
        {
            deleteCourseBtn.setEnabled(false);
        }
        else
        {
            deleteCourseBtn.setEnabled(true);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200)
        {
            String department = data.getStringExtra("Department");
            int courseNum = data.getIntExtra("CourseNum",0);
            addCourse(new Course(department, courseNum));
        }
        else if (resultCode == 400)
        {
            String coursePos = data.getStringExtra("Index");
            if (coursePos!=null)
            {
                int coursePosition = Integer.parseInt(coursePos);
                deleteCourse(coursePosition);
            }
        }
    }

    public void addCourse(Course newCourse)
    {
        if (logic.getTerm(position) != null)
        {
            Term toAdd = logic.getTerm(position);
            if (newCourse != null & toAdd.getCourseList() != null)
            {
                if (toAdd.getCourseList().contains(newCourse))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(ViewTerm.this).create();
                    alertDialog.setTitle("Course Already Exists");
                    alertDialog.setMessage("The course, "+ newCourse.toString() + ", has already been created.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });
                    alertDialog.show();
                }
                else
                {
                    logic.addCourse(toAdd,newCourse);
                }
            }
        }
        updateView(position);
    }

    public void deleteCourse(int pos)
    {
        if (logic.getTerm(position) != null)
        {
            Term deleteFrom = logic.getTerm(position);
            if (deleteFrom.getCourseList() != null)
            {
                Course delete = deleteFrom.getCourseList().get(pos);
                logic.deleteCourse(deleteFrom,delete);
            }
        }
        updateView(position);
    }

    public void updateView(int pos)
    {
        viewTitle(pos);
        showCourses(pos);
        setAdapter(pos);
    }

    private void setAdapter(int pos)
    {
        Term term = logic.getTerm(pos);
        if (term != null)
        {
            if (term.getCourseList() != null)
            {
                adapter = new ArrayAdapter<>(this, R.layout.add_term, term.getCourseList());
            }
        }
    }

    public static ArrayAdapter<Course> getAdapter()
    {
        return adapter;
    }
}
