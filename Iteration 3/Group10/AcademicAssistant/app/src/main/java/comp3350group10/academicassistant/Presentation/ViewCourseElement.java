package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import comp3350group10.academicassistant.Business.CourseElementLogic;
import comp3350group10.academicassistant.Business.CourseLogic;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class ViewCourseElement extends Activity
{
    private TextView title;
    private TextView totalMarks;
    private TextView percentage;
    private TextView weight;
    int termPos;
    int coursePos;
    int courseEPos;
    private TermLogic tLogic = new TermLogic();
    private CourseLogic cLogic = new CourseLogic();
    private CourseElementLogic cELogic = new CourseElementLogic();
    CourseElement element;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course_element);
        Intent myIntent = getIntent();

        termPos = myIntent.getIntExtra("TermIndex", 0);
        coursePos = myIntent.getIntExtra("CourseIndex", 0);
        courseEPos = myIntent.getIntExtra("Index", 0);

        title = (TextView)findViewById(R.id.courseElTitle);
        totalMarks = (TextView)findViewById(R.id.courseETotalMarks);
        percentage = (TextView)findViewById(R.id.courseEPercentage);
        weight = (TextView)findViewById(R.id.courseEWeightView);

        Button updateBtn = (Button)findViewById(R.id.courseEUpdateBtn);
        Button cancelBtn = (Button)findViewById(R.id.cancelCourseEBtn);
        Button aboutBtn = (Button)findViewById(R.id.courseEAboutBtn);

        final EditText achievedM = (EditText)findViewById(R.id.courseEAchievedM);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mark =  achievedM.getText().toString();
                if(mark == null || mark.isEmpty())
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(ViewCourseElement.this).create();
                    alertDialog.setTitle("Null Input" );
                    alertDialog.setMessage("Please enter a valid achieved mark");
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
                    double achievedMarks = Double.parseDouble(mark);
                    if(achievedMarks <= element.getTotalMarks())
                    {
                        updateMarks(achievedMarks);
                    }
                    else
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(ViewCourseElement.this).create();
                        alertDialog.setTitle("Invalid Input" );
                        alertDialog.setMessage("Please enter a valid achieved mark");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Term term = tLogic.getTerm(termPos);
        if (term != null)
        {
            Course course = cLogic.getCourse(term, coursePos);
            if (course != null)
            {
                element = cLogic.getElement(course, courseEPos);
                if (element != null)
                {
                    updateView(element);
                }
            }
        }

        aboutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ViewCourseElement.this).create();
                alertDialog.setTitle(element.getTitle() );
                alertDialog.setMessage("Description - " + element.getDescription() + "\nType - " + element.getType());
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
    }

    private void updateView(CourseElement element)
    {
        if (element != null)
        {
            title.setText(element.getTitle());
            totalMarks.setText(String.valueOf(element.getTotalMarks()));
            percentage.setText(String.valueOf(cELogic.getPercentage(element)));
            weight.setText(String.valueOf(element.getWeight()));
        }
    }

    private void updateMarks(double marks)
    {
        Term term = tLogic.getTerm(termPos);
        if (term != null)
        {
            Course course = cLogic.getCourse(term, coursePos);
            if (course != null)
            {
                CourseElement element = cLogic.getElement(course, courseEPos);
                if (element != null)
                {
                    element.setAchievedMarks(marks);
                    cLogic.updateCourseElement(course,element);
                    updateView(element);
                    finish();
                }
            }
        }
    }
}
