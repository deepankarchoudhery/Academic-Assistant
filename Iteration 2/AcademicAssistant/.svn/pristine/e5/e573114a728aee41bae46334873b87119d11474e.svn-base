package comp3350group10.academicassistant.Application;

import comp3350group10.academicassistant.Persistence.*;

public class Services
{
	private static Persistence dataAccessService = null;

	public static Persistence createDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new SQLDB(dbName);
			dataAccessService.open(Main.getDBPathName());
		}
		return dataAccessService;
	}

	public static Persistence createDataAccess(Persistence alternateDataAccessService)
	{
		if (dataAccessService == null)
		{
			dataAccessService = alternateDataAccessService;
			dataAccessService.open(Main.getDBPathName());
		}
		return dataAccessService;
	}

	public static Persistence getDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}
