package comp3350group10.academicassistant.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.CourseElement;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;

public class SQLDB implements Persistence {
    private Statement st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12, st13, st14;
    private Connection c1;
    private ResultSet rs1, rs2, rs3, rs4;

    private String dbName;
    private String dbType;

    private ArrayList<Term> terms;
    private int nextTermID = 1001;
    private int nextCourseID = 1001;
    private int nextCourseElementID = 1001;

    private String cmdString;

    public SQLDB(String dbName)
    {
        this.dbName = dbName;
        terms = new ArrayList<Term>();
    }

    public void open(String dbPath)
    {
        String url;

        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
            st4 = c1.createStatement();
            st5 = c1.createStatement();
            st6 = c1.createStatement();
            st7 = c1.createStatement();
            st8 = c1.createStatement();
            st9 = c1.createStatement();
            st10 = c1.createStatement();
            st11 = c1.createStatement();
            st12 = c1.createStatement();
            st13 = c1.createStatement();
            st14 = c1.createStatement();
            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {
            cmdString = "shutdown compact";
            rs1 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }

        System.out.println("Closed " + dbType + " database " + dbName);
    }

    private void loadTerms()
    {
        Term newTerm;
        int termID;
        String semesterString;
        Semester semester;
        int year;

        terms.clear();

        try
        {
            cmdString = "Select * from TERMS";
            rs2 = st2.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs2.next())
            {
                termID = rs2.getInt("termID");
                semesterString = rs2.getString("semester");

                if (semesterString.equals("FALL"))
                {
                    semester = Semester.FALL;
                }
                else if (semesterString.equals("WINTER"))
                {
                    semester = Semester.WINTER;
                }
                else
                {
                    semester = Semester.SUMMER;
                }

                year = rs2.getInt("year");
                newTerm = new Term(semester, year);

                newTerm.setPersistentID(termID);

                if(termID >= nextTermID)
                {
                    nextTermID = termID + 1;
                }

                loadCourses(newTerm);
                terms.add(newTerm);
            }
            rs2.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    private void loadCourses(Term targetTerm)
    {
        Course newCourse;
        String department;
        int courseNumber;
        int courseID;
        String grade;
        String goal;
        double totalMarks;

        try
        {
            cmdString = "Select * from Courses where termID=" + targetTerm.getPersistentID();
            rs3 = st3.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs3.next())
            {
                courseID = rs3.getInt("courseID");
                department = rs3.getString("department");
                courseNumber = rs3.getInt("number");
                grade = rs3.getString("Grade");
                goal = rs3.getString("Goal");
                totalMarks = rs3.getDouble("TotalMarks");

                newCourse = new Course(department, courseNumber);
                newCourse.setPersistentID(courseID);

                if(courseID >= nextCourseID)
                {
                    nextCourseID = courseID + 1;
                }

                if (!grade.equals(""))
                {
                    newCourse.setGrade(grade);
                }
                if (!goal.equals(""))
                {
                    newCourse.setGoal(goal);
                }
                if (totalMarks != 0)
                {
                    newCourse.setTotalMarks(totalMarks);
                }

                loadCourseElements(newCourse);

                targetTerm.addCourse(newCourse);
            }
            rs3.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    private void loadCourseElements(Course targetCourse)
    {
        CourseElement newCourseElement;
        int courseElementID;
        String title;
        String description;
        String type;
        double totalMarks;
        double marksAchieved;
        double weight;

        try
        {
            cmdString = "Select * from CourseElements where CourseID=" + targetCourse.getPersistentID();
            rs4 = st4.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs4.next())
            {
                title = rs4.getString("Title");
                description = rs4.getString("Description");
                type = rs4.getString("Type");
                totalMarks = rs4.getDouble("TotalMarks");
                marksAchieved = rs4.getDouble("MarksAchieved");
                weight = rs4.getDouble("weight");
                courseElementID = rs4.getInt("courseElementID");

                newCourseElement = new CourseElement(title,description,type,totalMarks,weight);
                newCourseElement.setPersistentID(courseElementID);
                newCourseElement.setAchievedMarks(marksAchieved);

                if(courseElementID >= nextCourseElementID)
                {
                    nextCourseElementID = courseElementID + 1;
                }

                targetCourse.addWork(newCourseElement);
            }
            rs4.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    public ArrayList<Term> getTerms()
    {
        return (ArrayList<Term>) terms.clone();
    }


    public boolean addTerm(Term newTerm)
    {
        String values;
        boolean wasSuccessful = false;

        if (newTerm != null)
        {
            try
            {
                newTerm.setPersistentID(nextTermID);
                nextTermID++;

                values = newTerm.getPersistentID()
                        + ", '" + newTerm.getSemester().toString()
                        + "', " + newTerm.getYear();
                cmdString = "Insert into Terms " + " Values(" + values + ")";
                st5.executeUpdate(cmdString);

                wasSuccessful = true;
                loadTerms();
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasSuccessful;
    }

    public boolean deleteTerm(Term targetTerm)
    {
        boolean wasSuccessful = false;

        if (targetTerm != null)
        {
            try
            {
                cmdString = "Delete from Terms where termID=" + targetTerm.getPersistentID();
                st6.executeUpdate(cmdString);

                try
                {
                    ArrayList<Course> courses = targetTerm.getCourseList();

                    for (int i=0; i<courses.size(); i++)
                    {
                        deleteCourse(targetTerm,courses.get(i));
                    }

                    wasSuccessful = true;
                    loadTerms();
                } catch (Exception e)
                {
                    processSQLError(e);
                }
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasSuccessful;
    }

    public void updateTerm(Term targetTerm)
    {
        String values;
        String where;

        if (targetTerm != null)
        {
            try
            {
                values = "Semester='" + targetTerm.getSemester().toString()
                        + "', Year=" + targetTerm.getYear();
                where = "where TermID=" + targetTerm.getPersistentID();
                cmdString = "Update Terms " + " Set " + values + " " + where;
                st7.executeUpdate(cmdString);

                loadTerms();
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }
    }

    public boolean addCourse(Term targetTerm, Course newCourse)
    {
        boolean wasAdded = false;
        String values;

        if (targetTerm != null && newCourse != null)
        {
            try
            {
                newCourse.setPersistentID(nextCourseID);
                nextCourseID++;

                values = newCourse.getPersistentID()
                        + ", " + targetTerm.getPersistentID()
                        + ", '" + newCourse.getDepartment() + "', " + newCourse.getCourseNum()
                        + ", '" + newCourse.getGrade() + "', " + newCourse.getTotalMarks()
                        + ", '" + newCourse.getGoal() + "'";
                cmdString = "Insert into Courses" + " Values(" + values + ")";
                st8.executeUpdate(cmdString);

                wasAdded = true;
                loadTerms();
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasAdded;
    }

    public boolean deleteCourse(Term targetTerm, Course targetCourse)
    {
        boolean wasSuccessful = false;

        if (targetTerm != null && targetCourse != null)
        {
            try
            {
                cmdString = "Delete from Courses where courseID=" + targetCourse.getPersistentID();
                st9.executeUpdate(cmdString);
                try
                {
                    cmdString = "Delete from CourseElements where courseID=" + targetCourse.getPersistentID();
                    st10.executeUpdate(cmdString);

                    wasSuccessful = true;
                    loadTerms();
                } catch (Exception e)
                {
                    processSQLError(e);
                }
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasSuccessful;
    }

    public boolean updateCourse(Term targetTerm, Course targetCourse)
    {
        boolean wasUpdated = false;
        String values;
        String where;

        if (targetTerm != null && targetCourse != null)
        {
            try
            {
                values = "TermID=" + targetTerm.getPersistentID()
                        + ", Department='" + targetCourse.getDepartment() + "', Number=" + targetCourse.getCourseNum()
                        + ", Grade='" + targetCourse.getGrade() + "', TotalMarks=" + targetCourse.getTotalMarks()
                        + ", Goal='" + targetCourse.getGoal() + "'";
                where = "where CourseID=" + targetCourse.getPersistentID();
                cmdString = "Update Courses" + " Set " + values + " " + where;
                st11.executeUpdate(cmdString);

                wasUpdated = true;
                loadTerms();
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasUpdated;
    }

    public boolean addCourseElement(Course targetCourse, CourseElement newElement)
    {
        boolean wasAdded = false;
        String values;

        if (targetCourse != null && newElement != null)
        {
            try
            {
                newElement.setPersistentID(nextCourseElementID);
                nextCourseElementID++;
                targetCourse.addWork(newElement);

                values = newElement.getPersistentID() + "," + targetCourse.getPersistentID()
                        + ",'" + newElement.getTitle() + "','" + newElement.getDescription()
                        + "','" +  newElement.getType() + "'," + newElement.getTotalMarks()
                        + "," + newElement.getAchievedMarks() + "," + newElement.getWeight();
                cmdString = "Insert into CourseElements" + " Values(" + values + ")";
                st12.executeUpdate(cmdString);

                wasAdded = true;
                loadTerms();
            } catch (Exception e) {
                processSQLError(e);
            }
        }

        return wasAdded;
    }

    public boolean deleteCourseElement(Course targetCourse, CourseElement targetElement)
    {
        boolean wasRemoved = false;

        if (targetCourse != null && targetElement != null)
        {
            try
            {
                targetCourse.removeWork(targetElement);

                cmdString = "Delete from CourseElements where courseElementID=" + targetElement.getPersistentID();
                st13.executeUpdate(cmdString);

                wasRemoved = true;
                loadTerms();
            } catch (Exception e) {
                processSQLError(e);
            }
        }

        return wasRemoved;
    }

    public boolean updateCourseElement(Course targetCourse, CourseElement targetElement)
    {
        boolean wasUpdated = false;

        String values;
        String where;

        if (targetCourse != null && targetElement != null)
        {
            try
            {
                targetCourse.removeWork(targetElement);
                targetCourse.addWork(targetElement);

                values = "CourseID=" + targetCourse.getPersistentID()
                        + ", Title='" + targetElement.getTitle() + "', Description='" + targetElement.getDescription()
                        + "', Type='" + targetElement.getType() + "', TotalMarks=" + targetElement.getTotalMarks()
                        + ", MarksAchieved=" + targetElement.getAchievedMarks() + ", Weight=" + targetElement.getWeight();
                where = "where CourseElementID=" + targetElement.getPersistentID();
                cmdString = "Update CourseElements " + " Set " + values + " " + where;
                st14.executeUpdate(cmdString);

                wasUpdated = true;
                loadTerms();
            } catch (Exception e)
            {
                processSQLError(e);
            }
        }

        return wasUpdated;
    }

    public void processSQLError(Exception e)
    {
        System.out.println("*** SQL Error: " + e.getMessage());

        e.printStackTrace();
    }
}
