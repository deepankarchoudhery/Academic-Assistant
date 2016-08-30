package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.Arrays;
import java.util.List;
import comp3350group10.academicassistant.R;

public class SetGradeGoal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_grade_goal);
        final Spinner gradeSpinner = (Spinner)findViewById(R.id.gradeSpinner);
        initializeSpinners(gradeSpinner);
        Button set = (Button)findViewById(R.id.setGoalBtn);
        Button cancel = (Button) findViewById(R.id.cancelSetBtn);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGradeGoal(gradeSpinner.getSelectedItem().toString());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeSpinners(Spinner gradeSpinner)
    {
        List<String> grades = Arrays.asList("A+","A","B+","B","C+","C","D","F");
        ArrayAdapter<String> gradeArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, grades);
        gradeSpinner.setAdapter(gradeArrayAdapter);
    }

    private void addGradeGoal(String grade)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Grade", grade);
        setResult(100, resultIntent);
        finish();
    }
}
