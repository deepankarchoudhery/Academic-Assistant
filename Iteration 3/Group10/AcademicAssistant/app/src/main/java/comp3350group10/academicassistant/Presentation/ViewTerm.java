package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import comp3350group10.academicassistant.Business.CalculateGPA;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class ViewTerm extends Activity
{
    private int termPosition;
    private TermLogic logic = new TermLogic();
    private CourseLogic cLogic = new CourseLogic();
    public static ArrayAdapter<Course> adapter;
    private ArrayList<Course> courses;
    private Term term;
    private CalculateGPA calculator = new CalculateGPA();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_term);
        Intent myIntent = getIntent();
        termPosition = myIntent.getIntExtra("Index", 0);
        term = logic.getTerm(termPosition);
        updateView(termPosition);

        Button addButton = (Button)findViewById(R.id.AddCourseBtn);
        Button deleteButton = (Button)findViewById(R.id.DeleteCourseBtn);
        Button gpaButton = (Button)findViewById(R.id.viewTermGPA);
        Button editButton = (Button)findViewById(R.id.editCourseBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerm.this, CreateCourses.class);
                i.putExtra("mode", "create");
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

        gpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Term calcTerm = logic.getTerm(termPosition);
                AlertDialog alertDialog = new AlertDialog.Builder(ViewTerm.this).create();
                alertDialog.setTitle(term.toString() + " GPA");
                double gpaFourScale = ((calculator.gpa(calcTerm) * 4.0) / 4.5);
                NumberFormat formatter = new DecimalFormat("#0.00");
                alertDialog.setMessage("Your GPA this term is " + calculator.gpa(calcTerm) + "(4.5 scale) or " + (formatter.format(gpaFourScale)) + " (4.0 scale)"   );
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerm.this, EditCoursePage.class);
                startActivityForResult(i, 1);

            }
        });
        ListView list = (ListView) findViewById(R.id.CourseList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent termInfo = new Intent(ViewTerm.this, ViewCourse.class);
                termInfo.putExtra("CourseIndex", position);
                termInfo.putExtra("Index", termPosition);
                startActivity(termInfo);
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
        adapter = new ArrayAdapter<>(this, R.layout.list_view_style, courses);
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
            if (coursePos != null)
            {
                int coursePosition = Integer.parseInt(coursePos);
                deleteCourse(coursePosition);
            }
        }
        else if(resultCode == 300)
        {
            String department = data.getStringExtra("Department");
            int courseNum = data.getIntExtra("CourseNum", 0);
            int pos = data.getIntExtra("CourseIndex", 0);
            if (department != null && courseNum != 0)
            {
                Term term = logic.getTerm(termPosition);
                if (term != null)
                {
                    Course course = cLogic.getCourse(term,pos);
                    if (course != null)
                    {
                        course.setDepartment(department);
                        course.setCourseNum(courseNum);
                        cLogic.updateCourse(term, course);
                    }
                }
            }
            updateView(termPosition);
        }
    }

    public void addCourse(Course newCourse)
    {
        if (logic.getTerm(termPosition) != null)
        {
            Term term = logic.getTerm(termPosition);
            if (newCourse != null & term.getCourseList() != null)
            {
                if (term.getCourseList().contains(newCourse))
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
                    logic.addCourse(term,newCourse);
                }
            }
        }
        updateView(termPosition);
    }

    public void deleteCourse(int pos)
    {
        if (logic.getTerm(termPosition) != null)
        {
            Term deleteFrom = logic.getTerm(termPosition);
            if (deleteFrom.getCourseList() != null)
            {
                Course delete = deleteFrom.getCourseList().get(pos);
                logic.deleteCourse(deleteFrom,delete);
            }
        }
        updateView(termPosition);
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
                adapter = new ArrayAdapter<>(this, R.layout.list_view_style, term.getCourseList());
            }
        }
    }

    public static ArrayAdapter<Course> getAdapter()
    {
        return adapter;
    }
}
