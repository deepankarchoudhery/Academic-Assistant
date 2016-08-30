package comp3350group10.academicassistant.Acceptance;

import android.app.Application;
import android.app.Presentation;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.*;

import comp3350group10.academicassistant.Presentation.CreateTerm;
import comp3350group10.academicassistant.Presentation.HomePage;
import comp3350group10.academicassistant.Presentation.ViewTerms;
import comp3350group10.academicassistant.R;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AcceptanceTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public AcceptanceTest()
    {
        super("comp3350group10.academicassistant",HomePage.class);
    }

    @Override
    public void setUp() throws Exception {

        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());

    }

    public void testAddRemoveTerm()
    {
        solo.assertCurrentActivity("Check on first activity", HomePage.class);
        solo.clickOnButton("TERM");

        solo.clickOnButton("Add a New Term");
        solo.assertCurrentActivity("Check on second activity", CreateTerm.class);
        solo.clickOnButton("Add Term");
        solo.assertCurrentActivity("Check on first activity again", ViewTerms.class);
        solo.clickOnButton("Add a New Term");
        solo.assertCurrentActivity("Check on second activity", CreateTerm.class);
        solo.clickOnButton("Add Term");
        solo.clickOnButton("OK");
        solo.assertCurrentActivity("Check on first activity", ViewTerms.class);
        solo.clickOnButton("Delete Term");

        ListView ListView=(ListView)solo.getView(R.id.deletePageListView);
        View view=ListView.getChildAt(0);
        solo.clickOnView(view);

        solo.assertCurrentActivity("Check on first activity", ViewTerms.class);
        solo.clickOnButton("Add a New Term");

        View termSpinner = solo.getView(Spinner.class, 0);
        View yearSpinner = solo.getView(Spinner.class, 1);
        solo.clickOnView(termSpinner);
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 2 ));
        solo.clickOnView(yearSpinner);
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5 ));
        solo.clickOnButton("Add Term");
        solo.assertCurrentActivity("First Activity", ViewTerms.class);

        solo.clickOnButton("Delete Term");
        ListView=(ListView)solo.getView(R.id.deletePageListView);
        view=ListView.getChildAt(0);
        solo.clickOnView(view);
    }

    public void testAddEditRemoveCourse()
    {
        solo.assertCurrentActivity("Check on first activity", HomePage.class);
        solo.clickOnButton("TERM");

        ListView ListView = (ListView)solo.getView(R.id.termsListView);
        View view = ListView.getChildAt(1);
        solo.clickOnView(view);
        solo.clickOnButton("Add a New Course");
        solo.enterText((EditText) solo.getView(R.id.deptEditText), "TEST");
        solo.enterText((EditText) solo.getView(R.id.courseNumEditText), "1000");
        solo.clickOnButton("Add Course");
        solo.clickOnButton("Add a New Course");
        solo.enterText((EditText) solo.getView(R.id.deptEditText), "TEST");
        solo.enterText((EditText) solo.getView(R.id.courseNumEditText), "2001");
        solo.clickOnButton("Add Course");

        solo.clickOnButton("DELETE COURSE");
        ListView=(ListView)solo.getView(R.id.deleteCoursePage);
        view=ListView.getChildAt(1);
        solo.clickOnView(view);
    }

    public void testEditCourseElement()
    {
        solo.assertCurrentActivity("Check on first activity", HomePage.class);
        solo.clickOnButton("TERM");

        ListView ListView = (ListView)solo.getView(R.id.termsListView);
        View view = ListView.getChildAt(1);
        solo.clickOnView(view);

        ListView=(ListView)solo.getView(R.id.CourseList);
        view=ListView.getChildAt(0);
        solo.clickOnView(view);
        solo.clickOnButton("ADD COURSE ELEMENT");
        solo.enterText((EditText) solo.getView(R.id.editTitle), "FINAL");
        solo.enterText((EditText) solo.getView(R.id.editDescription), "Final Exam");
        solo.enterText((EditText) solo.getView(R.id.editType), "Test");
        solo.enterText((EditText) solo.getView(R.id.editCourseWeight), "60");
        solo.enterText((EditText) solo.getView(R.id.editTotalMarks), "100");
        solo.clickOnButton("ADD COURSE ELEMENT");

        solo.clickOnButton("ADD COURSE ELEMENT");
        solo.enterText((EditText) solo.getView(R.id.editTitle), "MIDTERM");
        solo.enterText((EditText) solo.getView(R.id.editDescription), "Midterm Test");
        solo.enterText((EditText) solo.getView(R.id.editType), "Test");
        solo.enterText((EditText) solo.getView(R.id.editCourseWeight), "40");
        solo.enterText((EditText) solo.getView(R.id.editTotalMarks), "50");
        solo.clickOnButton("ADD COURSE ELEMENT");

        solo.clickOnButton("SET GRADE GOAL");
        View letterGradeSpinner = solo.getView(Spinner.class, 0);
        solo.clickOnView(letterGradeSpinner);
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 2 ));
        solo.clickOnButton("SET GOAL");

        ListView=(ListView)solo.getView(R.id.courseEList);
        view=ListView.getChildAt(0);
        solo.clickOnView(view);

        solo.enterText((EditText) solo.getView(R.id.courseEAchievedM), "80");
        solo.clickOnButton("UPDATE");

        ListView=(ListView)solo.getView(R.id.courseEList);
        view=ListView.getChildAt(1);
        solo.clickOnView(view);

        solo.enterText((EditText) solo.getView(R.id.courseEAchievedM), "45");
        solo.clickOnButton("UPDATE");

        solo.clickOnButton("UPDATE GRADE");
    }

    public void testViewGPA()
    {
        solo.assertCurrentActivity("Check on first activity", HomePage.class);
        solo.clickOnButton("TERM");

        solo.clickOnButton("VIEW GPA");
        solo.clickOnButton("OK");
    }

    public void testViewTranscript()
    {
        solo.assertCurrentActivity("Check on first activity", HomePage.class);
        solo.clickOnButton("TRANSCRIPT");
    }



    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

}
