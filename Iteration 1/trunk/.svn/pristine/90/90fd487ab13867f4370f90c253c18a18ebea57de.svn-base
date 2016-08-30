package comp3350group10.academicassistant.Presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Business.TermComparator;
import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.Persistence.Persistence;
import comp3350group10.academicassistant.R;

public class ViewTerms extends Activity
{
    private TermLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_terms);

        Button addButton = (Button)findViewById(R.id.addTermBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerms.this, CreateTerm.class);
                startActivityForResult(i, 1);
            }
        });

        logic = new TermLogic();
        updateListView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String semester = data.getStringExtra("Semester");
            String year = data.getStringExtra("Year");
            addTerm(new Term(Semester.valueOf(semester), Integer.parseInt(year)));
        }
    }

    public void addTerm(Term newTerm)
    {
        if (!logic.isDuplicate(newTerm))
        {
            logic.addTerm(newTerm);
            updateListView();
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(ViewTerms.this).create();
            alertDialog.setTitle("Term Already Exists");
            alertDialog.setMessage("The term, "+ newTerm.getSemester().toString() + " " + newTerm.getYear() + ", has already been created.");
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

    private void updateListView()
    {
        ArrayAdapter<Term> adapter = new ArrayAdapter<>(this, R.layout.add_term, logic.getTerms());
        ListView list = (ListView) findViewById(R.id.termsListView);
        list.setAdapter(adapter);
    }
}