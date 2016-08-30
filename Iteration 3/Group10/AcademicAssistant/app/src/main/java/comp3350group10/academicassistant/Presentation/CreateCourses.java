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

        final Button addButton = (Button)findViewById(R.id.submitCourseBtn);
        final TextView title = (TextView)findViewById(R.id.titleTextView);
        setTitles(getIntent().getStringExtra("mode"), addButton, title);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int number = 0;
                AlertDialog alertDialog = new AlertDialog.Builder(CreateCourses.this).create();
                try
                {
                    if (isEmpty(deptInput) || isEmpty(courseNumInput))
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
                        String dept = deptInput.getText().toString();
                        String num = courseNumInput.getText().toString();
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

    private void setTitles(String task, Button addButton, TextView title)
    {
        if (task.equals("create"))
        {
            addButton.setText("Add Course");
            title.setText("Create a Course");
        }
        else if (task.equals("edit"))
        {
            addButton.setText("Save Changes");
            title.setText("Edit Course");
        }
    }

    private void addCourseToList(String department, int courseNum)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Department", department);
        resultIntent.putExtra("CourseNum", courseNum);
        setResult(200, resultIntent);
        finish();
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
}

