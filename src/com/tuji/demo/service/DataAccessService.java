package com.tuji.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessService {
	 private final static String url = "jdbc:postgresql://localhost/tuji_platform";
	 private final static String username = "postgres";
	 private final static String password = "sys";
	 private static final DataAccessService dataaccessservice = new DataAccessService();
	 
	 
	 public DataAccessService() {
		  try {
			  Class.forName("org.postgresql.Driver");
		  	  } 
		  catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }
	 }
	 public static DataAccessService getInstance() {
		  return dataaccessservice;
		 }

	
	
	protected static Connection getConnection() {
		  Connection connection = null;
		  try {
		   connection = DriverManager.getConnection(url, username, password);
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		  return connection;
		 }
	
	protected void closeResultSet(ResultSet set) {
		  try {
		   if (set != null) {
		    set.close();
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }


	protected void closeStatement(Statement statement) {
		  try {
		   if (statement != null) {
		    statement.close();
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }


	protected void closeConnection(Connection connection) {
		  try {
		   if (connection != null) {
		    connection.close();
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }


	protected void close(Connection connection, Statement statement, ResultSet set) {
		  closeResultSet(set);
		  closeStatement(statement);
		  closeConnection(connection);
		 }
	
	
	
	public ResultSet executeQuery(String p_sql) throws SQLException
	{
		ResultSet resultSet = null;
		 System.out.println("a1");
  	  	Connection connection = DataAccessService.getConnection();
  	  	PreparedStatement statement = null;
  	  	statement = connection.prepareStatement(p_sql);
  	  	resultSet = statement.executeQuery();
	  	
		
		return resultSet;
	}
	
	
		 
		


}
