package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {
		Connection connection = null;
		String dbEnv = null;
		String databaseServer = null;
		String databaseName = null;
		String instanceName = null;
		String userName = null;
		String password = null;
		String url = null;
		if ((System.getProperty("dbEnv") == null)) {
			databaseServer = getDatabaseProp("DatabaseServer");
			databaseName = getDatabaseProp("DatabaseName");
			instanceName = getDatabaseProp("InstanceName");
			userName = getDatabaseProp("userName");
			password = getDatabaseProp("password");
			url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
		} else {
			dbEnv = System.getProperty("dbEnv");
			switch (dbEnv) {
			
			case "DEV":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("DEV_DatabaseServer");
				databaseName = getDatabaseProp("DEV_DatabaseName");
				userName = getDatabaseProp("DEV_userName");
				password = getDatabaseProp("DEV_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName;
				break;
				
			case "QA":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("DatabaseServer");
				databaseName = getDatabaseProp("DatabaseName");
				instanceName = getDatabaseProp("InstanceName");
				userName = getDatabaseProp("userName");
				password = getDatabaseProp("password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;

			case "UAT":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("UAT_DatabaseServer");
				databaseName = getDatabaseProp("UAT_DatabaseName");
				instanceName = getDatabaseProp("UAT_InstanceName");
				userName = getDatabaseProp("UAT_userName");
				password = getDatabaseProp("UAT_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;
				
			case "BUGFIX":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("BugFix_DatabaseServer");
				databaseName = getDatabaseProp("BugFix_DatabaseName");
				instanceName = getDatabaseProp("BugFix_InstanceName");
				userName = getDatabaseProp("BugFix_userName");
				password = getDatabaseProp("BugFix_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;

			case "Training":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("Training_DatabaseServer");
				databaseName = getDatabaseProp("Training_DatabaseName");
				instanceName = getDatabaseProp("Training_InstanceName");
				userName = getDatabaseProp("Training_userName");
				password = getDatabaseProp("Training_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;

			case "PREPROD":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("PREPROD_DatabaseServer");
				databaseName = getDatabaseProp("PREPROD_DatabaseName");
				instanceName = getDatabaseProp("PREPROD_InstanceName");
				userName = getDatabaseProp("PREPROD_userName");
				password = getDatabaseProp("PREPROD_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;
			
			case "iDev":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("iDev_DatabaseServer");
				databaseName = getDatabaseProp("iDev_DatabaseName");
				instanceName = getDatabaseProp("iDev_InstanceName");
				userName = getDatabaseProp("iDev_userName");
				password = getDatabaseProp("iDev_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
				break;
			
			case "AppSrvr1":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("AppSrvr1_DatabaseServer");
				databaseName = getDatabaseProp("AppSrvr1_DatabaseName");
				userName = getDatabaseProp("AppSrvr1_userName");
				password = getDatabaseProp("AppSrvr1_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName;
				break;
			
			case "AppSrvr2":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("AppSrvr2_DatabaseServer");
				databaseName = getDatabaseProp("AppSrvr2_DatabaseName");
				userName = getDatabaseProp("AppSrvr2_userName");
				password = getDatabaseProp("AppSrvr2_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName;
				break;
				
			case "AppDRSrvr":
				System.out.println("Execution:   " + dbEnv);
				databaseServer = getDatabaseProp("AppDRSrvr_DatabaseServer");
				databaseName = getDatabaseProp("AppDRSrvr_DatabaseName");
				userName = getDatabaseProp("AppDRSrvr_userName");
				password = getDatabaseProp("AppDRSrvr_password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName;
				break;
			
			default:
				databaseServer = getDatabaseProp("DatabaseServer");
				databaseName = getDatabaseProp("DatabaseName");
				instanceName = getDatabaseProp("InstanceName");
				userName = getDatabaseProp("userName");
				password = getDatabaseProp("password");
				url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;
			}
		}

		//String url = "jdbc:jtds:sqlserver://" + databaseServer + "/" + databaseName + ";instance=" + instanceName;

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * MethodName: getDatabaseProp() Objective:This method is used to get the
	 * database Properties.
	 * 
	 * @param propName
	 *            gets the prop name
	 * @return the db prop
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getDatabaseProp(String propName) {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/com/resources/dbconnection.properties";
		String property = null;
		try {
			property = getProperty(propName, filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}

	/**
	 * MethodName: getProperty() Objective:This generic method is used to get
	 * the Property values.
	 * 
	 * @param propertyname
	 *            the propertyname
	 * @param filepath
	 *            gets the filepath
	 * @return the property
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getProperty(String propertyname, String filepath) throws IOException {
		Properties prop = new Properties();
		InputStream input = new FileInputStream(filepath);
		prop.load(input);
		return prop.getProperty(propertyname);
	}

}
