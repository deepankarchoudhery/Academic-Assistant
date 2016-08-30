package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import comp3350group10.academicassistant.Objects.Term;
import comp3350group10.academicassistant.R;

public class EditTermPage extends Activity
{
    private ListView toEditList;
    private static ArrayAdapter<Term> editAdapter;
    private int termPos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        toEditList =(ListView)findViewById(R.id.editPageListView);
        editAdapter = ViewTerms.getAdapter();
        toEditList.setAdapter(editAdapter);

        Button cancelEditBtn = (Button)findViewById(R.id.cancelEditBtn);
        cancelEditBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        toEditList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent termEdit = new Intent(EditTermPage.this,CreateTerm.class);
                termEdit.putExtra("mode", "edit");
                startActivityForResult(termEdit, 0);
                termPos = position;
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            String semester = data.getStringExtra("Semester");
            String year = data.getStringExtra("Year");
            Intent result = new Intent();
            result.putExtra("Semester", semester);
            result.putExtra("Year", year);
            result.putExtra("Position", termPos);
            setResult(200, result);
            finish();
        }
    }
}

