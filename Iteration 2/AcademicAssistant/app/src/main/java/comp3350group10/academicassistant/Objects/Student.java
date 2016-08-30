package comp3350group10.academicassistant.Objects;

import java.util.ArrayList;

public class Student extends AcademicObject
{
    private String name;
    private int id;
    private double gpa;
    private ArrayList<Term> terms;

    public Student(String name, int id, double gpa)
    {
        if (name == null)
        {
            throw (new IllegalArgumentException("An null argument was passed to this method."));
        }
        else if (id < 0 || gpa < 0 || name.trim().length() == 0)
        {
            throw (new IllegalArgumentException("An illegal argument was passed to this method."));
        }
        else
        {
            this.name = name;
            this.id = id;
            this.gpa = gpa;
            terms = new ArrayList<>();
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name != null)
        {
            this.name = name;
        }
        else
        {
            throw (new IllegalArgumentException("An null argument was passed to this method."));
        }
    }

    public int getId()
    {
        return id;
    }

    public ArrayList<Term> getTerms() {
        return (ArrayList<Term>) terms.clone();
    }

    public void setId(int id)
    {
        if (id > 0)
        {
            this.id = id;
        }
        else
        {
            throw (new IllegalArgumentException("An illegal argument was passed to this method."));
        }
    }

    public double getGpa()
    {
        return gpa;
    }


}
