package comp3350group10.academicassistant.Objects;

import java.util.Date;

public class Event extends AcademicObject
{
    private String title;
    private String description;
    private Date date;
    private int priority;
    private boolean completed;

    public Event(String title, String description, Date date, int priority)
    {
        if (title == null || description == null || date == null)
        {
            throw (new IllegalArgumentException("Method passed a null value."));
        }
        else if (priority < 0 || priority > 3)
        {
            throw (new IllegalArgumentException("Method passed an illegal value."));
        }
        else if (title.trim().length() == 0)
        {
            throw (new IllegalArgumentException("Method passed a blank value."));
        }
        else
        {
            this.title = title;
            this.description = description;
            this.date = date;
            this.priority = priority;
            completed = false;
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
            throw (new IllegalArgumentException("Method passed a null value."));
        }
        else if (title.trim().length() == 0)
        {
            throw (new IllegalArgumentException("Method passed a blank value."));
        }
        else
        {
            this.title = title;
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
            throw (new IllegalArgumentException("Method passed a null value."));
        }
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        if (date != null)
        {
            this.date = date;
        }
        else
        {
            throw (new IllegalArgumentException("Method passed a null value."));
        }
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        if ((priority >= 0) && (priority <= 3))
        {
            this.priority = priority;
        }
        else
        {
            throw (new IllegalArgumentException("Method passed an illegal value."));
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
}
