package comp3350group10.academicassistant.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import comp3350group10.academicassistant.Objects.Course;
import comp3350group10.academicassistant.Objects.Semester;
import comp3350group10.academicassistant.Objects.Term;

public class SQLDB implements Persistence {
    private Statement st1, st2, st3, st4, st5, st6, st7, st8;
    private Connection c1;
    private ResultSet rs1, rs2;

    private String dbName;
    private String dbType;

    private ArrayList<Term> terms;
    private int nextTermID = 1005;
    private int nextCourseID = 1011;

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
            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    public void close()
    {
        try
        {
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }

        System.out.println("Closed " + dbType + " database " + dbName);
    }

    private void loadTerms()
    {
        int termID;
        String semesterString;
        Semester semester;
        int year;
        Term newTerm;

        terms.clear();

        try
        {
            cmdString = "Select * from TERMS";
            rs1 = st1.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs1.next())
            {
                termID = rs1.getInt("termID");
                semesterString = rs1.getString("semester");

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

                year = rs1.getInt("year");
                newTerm = new Term(semester, year);

                newTerm.setPersistentID(termID);
                nextTermID = termID + 1;

                loadCourses(newTerm);
                terms.add(newTerm);
            }
            rs1.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    private void loadCourses(Term targetTerm)
    {
        String department;
        int courseNumber;
        int courseID;
        Course newCourse;

        try
        {
            cmdString = "Select * from Courses where termID=" + targetTerm.getPersistentID();
            rs2 = st2.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs2.next())
            {
                courseID = rs2.getInt("courseID");
                department = rs2.getString("department");
                courseNumber = rs2.getInt("number");

                newCourse = new Course(department, courseNumber);
                newCourse.setPersistentID(courseID);
                nextCourseID = courseID + 1;

                targetTerm.addCourse(newCourse);
            }
            rs2.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    public ArrayList<Term> getTerms() {
        return (ArrayList<Term>) terms.clone();
    }


    public boolean addTerm(Term newTerm)
    {
        String values;
        boolean wasSuccessful = false;

        try
        {
            newTerm.setPersistentID(nextTermID);
            nextTermID++;

            values = newTerm.getPersistentID()
                    + ", '" + newTerm.getSemester().toString()
                    + "', " + newTerm.getYear();
            cmdString = "Insert into Terms " + " Values(" + values + ")";
            st3.executeUpdate(cmdString);
            wasSuccessful = true;
            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }

        return wasSuccessful;
    }

    public boolean deleteTerm(Term targetTerm)
    {
        boolean wasSuccessful = false;

        try
        {
            cmdString = "Delete from Terms where termID=" + targetTerm.getPersistentID();
            st4.executeUpdate(cmdString);

            try
            {
                cmdString = "Delete from Courses where termID=" + targetTerm.getPersistentID();
                st5.executeUpdate(cmdString);
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

        return wasSuccessful;
    }

    public void updateTerm(Term targetTerm)
    {
        String values;
        String where;

        try
        {
            values = "Semester='" + targetTerm.getSemester().toString()
                    + "', Year=" + targetTerm.getYear();
            where = "where TermID=" + targetTerm.getPersistentID();
            cmdString = "Update Terms " + " Set " + values + " " + where;
            st6.executeUpdate(cmdString);

            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }
    }

    public boolean addCourse(Term targetTerm, Course newCourse)
    {
        boolean wasAdded = false;
        String values;

        try
        {
            newCourse.setPersistentID(nextCourseID);
            nextCourseID++;

            values = newCourse.getPersistentID()
                    + ", " + targetTerm.getPersistentID()
                    + ", '" + newCourse.getDepartment() + "', " + newCourse.getCourseNum();
            cmdString = "Insert into Courses " + " Values(" + values + ")";
            st7.executeUpdate(cmdString);
            wasAdded = true;
            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }

        return wasAdded;
    }

    public boolean deleteCourse(Term targetTerm, Course targetCourse)
    {
        boolean wasSuccessful = false;

        try
        {
            cmdString = "Delete from Courses where courseID=" + targetCourse.getPersistentID();
            st8.executeUpdate(cmdString);
            wasSuccessful = true;
            loadTerms();
        } catch (Exception e)
        {
            processSQLError(e);
        }

        return wasSuccessful;
    }


    public void processSQLError(Exception e)
    {
        System.out.println("*** SQL Error: " + e.getMessage());

        e.printStackTrace();
    }
}
