package comp3350group10.academicassistant.Objects;

public abstract class AcademicObject
{
    private int persistentID;
    public AcademicObject()
    {
        persistentID = -1;
    }

    public boolean setPersistentID(int persistentID)
    {
        boolean correct = false;
        if (persistentID >= 0)
        {
            this.persistentID = persistentID;
            correct = true;
        }
        else
        {
            throw (new IllegalArgumentException("This method was passed an illegal argument."));
        }
        return correct;
    }

    public int getPersistentID()
    {
        return persistentID;
    }

    public boolean idSet()
    {
        return (persistentID != -1);
    }

    public boolean equals(AcademicObject other)
    {
        boolean isEquals = false;
        if (other != null)
        {
            if (this.idSet() && other.idSet())
            {
                if (other.getPersistentID() == this.getPersistentID())
                {
                    isEquals = true;
                }
            }
            else
            {
                throw (new IllegalArgumentException("Two uninitialized termed were compared."));
            }
        }
        else
        {
            throw (new IllegalArgumentException("A null argument was passed to this method."));
        }
        return isEquals;
    }
}
