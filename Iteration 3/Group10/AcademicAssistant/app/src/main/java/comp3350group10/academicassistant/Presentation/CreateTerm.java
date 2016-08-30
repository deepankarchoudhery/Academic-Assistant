package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.R;

public class CreateTerm extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_term);

        final Spinner semesterSpinner = (Spinner)findViewById(R.id.semesterSpinner);
        final Spinner yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        final Button addButton = (Button)findViewById(R.id.submitTermBtn);
        final TextView title = (TextView)findViewById(R.id.createTermTItle);

        setTitles(getIntent().getStringExtra("mode"), addButton, title);

        initializeSpinners(semesterSpinner, yearSpinner);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                addTermToList(semesterSpinner.getSelectedItem().toString(), yearSpinner.getSelectedItem().toString());
            }
        });
    }

    private void setTitles(String task, Button addButton, TextView title)
    {
        if (task.equals("create"))
        {
            addButton.setText("Add Term");
            title.setText("Create a Term");
        }
        else if (task.equals("edit"))
        {
            addButton.setText("Save Changes");
            title.setText("Edit Term");
        }
    }

    private void addTermToList(String semester, String year)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Semester", semester);
        resultIntent.putExtra("Year", year);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void initializeSpinners(Spinner semesterSpinner, Spinner yearSpinner)
    {
        Semester[] semesterArray = new Semester[] { Semester.FALL, Semester.SUMMER, Semester.WINTER };
        ArrayAdapter<Semester> semesterArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, semesterArray);
        semesterSpinner.setAdapter(semesterArrayAdapter);

        Integer[] yearArray = new Integer[] { 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
        ArrayAdapter<Integer> yearArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, yearArray);
        yearSpinner.setAdapter(yearArrayAdapter);
    }
}
