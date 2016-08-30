package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350group10.academicassistant.R;

public class CreateCourses extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_courses);
        final EditText deptInput = (EditText)findViewById(R.id.deptEditText);
        final EditText courseNumInput = (EditText)findViewById(R.id.courseNumEditText);

        Button addButton = (Button)findViewById(R.id.submitCourseBtn);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String dept = deptInput.getText().toString();
                String num = courseNumInput.getText().toString();
                int number = 0;
                    AlertDialog alertDialog = new AlertDialog.Builder(CreateCourses.this).create();
                try
                {
                    if (dept == null || num == null)
                    {
                        alertDialog.setTitle("Course invalid");
                        alertDialog.setMessage("Please enter a valid Course.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    }
                    else
                    {
                        number = Integer.parseInt(num);
                        addCourseToList(dept, number);
                    }
                }
                catch(NumberFormatException e)
                {
                    alertDialog.setTitle("Course number invalid");
                    alertDialog.setMessage("Please enter a valid Course number");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void addCourseToList(String department, int courseNum)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Department", department);
        resultIntent.putExtra("CourseNum", courseNum);
        setResult(200, resultIntent);
        finish();
    }
}

