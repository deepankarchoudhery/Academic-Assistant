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

public class CreateCourseElement extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course_element);
        final EditText title = (EditText)findViewById(R.id.editTitle);
        final EditText description = (EditText)findViewById(R.id.editDescription);
        final EditText type = (EditText)findViewById(R.id.editType);
        final EditText weight = (EditText)findViewById(R.id.editCourseWeight);
        final EditText totalMarks = (EditText)findViewById(R.id.editTotalMarks);
        Button addButton = (Button)findViewById(R.id.addCourseEBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(CreateCourseElement.this).create();
                if (isEmpty(title) || isEmpty(description) || isEmpty(type) || isEmpty(weight) || isEmpty(totalMarks))
                {
                    alertDialog.setTitle("Please fill out all the ");
                    alertDialog.setMessage("Please enter a valid Course.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
                else
                {
                    String courseETitle = title.getText().toString();
                    String courseEDescrip = description.getText().toString();
                    String courseEType = type.getText().toString();
                    double courseEWeight = Double.valueOf(weight.getText().toString());
                    double courseETotalMarks = Double.valueOf(totalMarks.getText().toString());
                    addCourseE(courseETitle, courseEDescrip, courseEType, courseEWeight, courseETotalMarks);
                }
            }
        });
    }

    private void addCourseE(String title, String description, String type, double weight, double totalMarks)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Title", title);
        resultIntent.putExtra("Description", description);
        resultIntent.putExtra("Type", type);
        resultIntent.putExtra("Weight", weight);
        resultIntent.putExtra("TotalMarks", totalMarks);
        setResult(10, resultIntent);
        finish();
    }
    private boolean isEmpty(EditText myeditText)
    {
        return myeditText.getText().toString().trim().length() == 0;
    }
}
