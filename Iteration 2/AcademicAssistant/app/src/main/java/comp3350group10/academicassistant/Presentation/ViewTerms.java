package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350group10.academicassistant.Application.Main;
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
        copyDatabaseToDevice();
        Main.startUp();

        Button addButton = (Button)findViewById(R.id.addTermBtn);
        Button deleteButton = (Button)findViewById(R.id.deleteTermBtn);
        Button editButton = (Button)findViewById(R.id.editTermBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewTerms.this, CreateTerm.class);
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
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    @Override
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
        adapter = new ArrayAdapter<>(this, R.layout.add_term, logic.getTerms());
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