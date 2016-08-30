package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350group10.academicassistant.R;

public class CreateCourse extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        final EditText deptInput = (EditText)findViewById(R.id.deptEditText);
        final EditText courseNumInput = (EditText)findViewById(R.id.courseNumEditText);

        Button addButton = (Button)findViewById(R.id.submitTermBtn);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCourseToList(deptInput.toString(), Integer.parseInt(courseNumInput.toString()));
            }
            });
    }

    private void addCourseToList(String department, int courseNum)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Department", department);
        resultIntent.putExtra("CourseNum", courseNum);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
