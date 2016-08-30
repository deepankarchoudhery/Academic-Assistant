package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.R;

public class DeleteCourses extends Activity
{
    private ListView toDeleteList;
    private static ArrayAdapter<Course> deleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_courses);
        Button cancelDeleteBtn = (Button)findViewById(R.id.courseCancelDeleteBtn);
        cancelDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteAdapter = ViewTerm.getAdapter();
        toDeleteList = (ListView) findViewById(R.id.deleteCoursePage);
        toDeleteList.setAdapter(deleteAdapter);
        toDeleteList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Index", String.valueOf(position));
                setResult(400, resultIntent);
                finish();
            }
        });
    }
}
