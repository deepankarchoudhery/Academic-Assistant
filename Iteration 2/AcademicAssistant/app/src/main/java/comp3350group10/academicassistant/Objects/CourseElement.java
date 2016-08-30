package comp3350group10.academicassistant.Objects;

import java.util.Date;

public class CourseElement extends AcademicObject
{
    private String title;
    private String description;
    private String type;
    private Date date;
    private double totalMarks;
    private double weight;
    private double achievedMarks;
    private boolean completed;

    public CourseElement(String title, String description, String type, Date date, int totalMarks,
                         double weight)
    {
        if (title == null || description == null || type == null || date == null)
        {
            throw (new IllegalArgumentException("This method was passed a null argument."));
        }
        else if (title.trim().length() == 0 || type.trim().length() == 0)
        {
            throw (new IllegalArgumentException("This method was passed a blank argument."));
        }
        else if (totalMarks < 0 || weight < 0)
        {
            throw (new IllegalArgumentException("This method was passed a illegal negative value."));
        }
        else
        {
            this.title = title;
            this.description = description;
            this.type = type;
            this.date = date;
            this.totalMarks = totalMarks;
            this.weight = weight;
            achievedMarks = 0.0;
            completed = false;
        }
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        if (description != null)
        {
            this.description = description;
        }
        else
        {
            throw (new IllegalArgumentException("Method was passed a null value."));
        }
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if (type != null)
        {
            this.type = type;
        }
        else
        {
            throw (new IllegalArgumentException("Method was passed a null value."));
        }
    }

    public Date getDate()
    {
        return (Date) date.clone();
    }

    public void setDate(Date date)
    {
        if (date != null)
        {
            this.date = date;
        }
        else
        {
            throw (new IllegalArgumentException("Method was passed a null value."));
        }
    }

    public double getTotalMarks()
    {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks)
    {
        if (totalMarks >= 0)
        {
            this.totalMarks = totalMarks;
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed a negative value."));
        }
    }

    public double getAchievedMarks()
    {
        return achievedMarks;
    }

    public void setAchievedMarks(double achievedMarks)
    {
        if ((achievedMarks >= 0) && (achievedMarks <= totalMarks))
        {
            this.achievedMarks = achievedMarks;
        }
        else
        {
            throw (new IllegalArgumentException("Method was passed an illegal value."));
        }
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        if ((weight >= 0.0) && (weight <= 1.0))
        {
            this.weight = weight;
        }
        else
        {
            throw (new IllegalArgumentException("Method was passed an illegal value."));
        }
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        if (title == null)
        {
            throw (new IllegalArgumentException("Method was passed a null value."));
        }
        else if (title.trim().length() == 0)
        {
            throw (new IllegalArgumentException("Method was passed a blank value."));
        }
        else
        {
            this.title = title;
        }
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void complete()
    {
        this.completed = true;
    }

    public double getGrade()
    {
        double grade = (this.achievedMarks/this.totalMarks) * getWeight();
        grade = (grade * 100);
        grade = grade/100;
        return grade;
    }
}
