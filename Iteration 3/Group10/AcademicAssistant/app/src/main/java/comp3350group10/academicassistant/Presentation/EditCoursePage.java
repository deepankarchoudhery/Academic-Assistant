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

public class EditCoursePage extends Activity {
    private ListView toEditList;
    private static ArrayAdapter<Course> editAdapter;
    private int coursePos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_page);
        toEditList =(ListView)findViewById(R.id.editCourseList);
        editAdapter = ViewTerm.getAdapter();
        toEditList.setAdapter(editAdapter);

        Button cancelEditBtn = (Button)findViewById(R.id.cancelEditCourse);
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
                Intent termEdit = new Intent(EditCoursePage.this,CreateCourses.class);
                termEdit.putExtra("mode", "edit");
                startActivityForResult(termEdit,0);
                coursePos = position;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200)
        {
            String department = data.getStringExtra("Department");
            int courseNum = data.getIntExtra("CourseNum",0);
            if(department!=null)
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Department", department);
                resultIntent.putExtra("CourseNum", courseNum);
                resultIntent.putExtra("CourseIndex", coursePos);
                setResult(300, resultIntent);
            }
            finish();
        }
    }
}
