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

import java.text.DecimalFormat;
import java.text.NumberFormat;

import comp3350group10.academicassistant.Business.TermLogic;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class ViewTerms extends Activity
{
    private TermLogic logic;
    public static ArrayAdapter<Term> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_terms);

        Button addButton = (Button)findViewById(R.id.addTermBtn);
        Button deleteButton = (Button)findViewById(R.id.deleteTermBtn);
        Button editButton = (Button)findViewById(R.id.editTermBtn);
        Button gpaButton = (Button)findViewById(R.id.viewGPABtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerms.this, CreateTerm.class);
                i.putExtra("mode", "create");
                startActivityForResult(i, 1);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerms.this, DeleteTerm.class);
                startActivityForResult(i, 1);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerms.this, EditTermPage.class);
                i.putExtra("mode", "edit");
                startActivityForResult(i, 1);
            }
        });


        logic = new TermLogic();
        updateListView();
        ListView list = (ListView) findViewById(R.id.termsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent termInfo = new Intent(ViewTerms.this,ViewTerm.class);
                termInfo.putExtra("Index",position);
                startActivity(termInfo);
            }
        });
        updateListView();

        gpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                AlertDialog alertDialog = new AlertDialog.Builder(ViewTerms.this).create();
                alertDialog.setTitle("Overall GPA");
                alertDialog.setMessage("Your overall GPA is " + logic.overallGPA() + "(4.5 scale) or " + formatter.format(((logic.overallGPA()/4.50)*4.0)) + " (4.0 scale)"   );
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

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            String semester = data.getStringExtra("Semester");
            String year = data.getStringExtra("Year");
            addTerm(new Term(Semester.valueOf(semester), Integer.parseInt(year)));
        }
        else if (resultCode == 100)
        {
            String index = data.getStringExtra("Index");
            int position = Integer.parseInt(index);
            logic.deleteTerm(adapter.getItem(position));
            updateListView();
        }
        else if (resultCode == 200) {
            String semester = data.getStringExtra("Semester");
            String year = data.getStringExtra("Year");
            int pos = data.getIntExtra("Position",0);
            if (semester != null && year != null)
            {
                Term term = logic.getTerm(pos);
                if (term != null)
                {
                    term.setSemester(Semester.valueOf(semester));
                    term.setYear(Integer.parseInt(year));
                    logic.updateTerm(term);
                }
            }
            updateListView();
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
        adapter = new ArrayAdapter<>(this, R.layout.list_view_style, logic.getTerms());
        ListView list = (ListView) findViewById(R.id.termsListView);
        list.setAdapter(adapter);

        Button deleteTermBtn = (Button)findViewById(R.id.deleteTermBtn);
        if (logic.getTerms().isEmpty())
        {
            deleteTermBtn.setEnabled(false);
        }
        else
        {
            deleteTermBtn.setEnabled(true);
        }
    }

    public static ArrayAdapter<Term> getAdapter()
    {
        return adapter;
    }
}