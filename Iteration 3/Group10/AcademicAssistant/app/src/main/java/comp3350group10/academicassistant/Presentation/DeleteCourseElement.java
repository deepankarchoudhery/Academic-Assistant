package comp3350group10.academicassistant.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.R;

public class DeleteCourseElement extends Activity {

    private ListView toDeleteList;
    private static ArrayAdapter<CourseElement> deleteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course_element);

        Button cancel = (Button)findViewById(R.id.cancelElementBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteAdapter = ViewCourse.getAdapter();
        toDeleteList = (ListView) findViewById(R.id.deleteElementList);
        toDeleteList.setAdapter(deleteAdapter);
        toDeleteList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Index", String.valueOf(position));
                setResult(200, resultIntent);
                finish();
            }
        });
    }
}
