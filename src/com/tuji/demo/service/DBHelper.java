package com.tuji.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	  private String connectionDriver = null;
		private String connectionString = null;
		private Connection connection = null;
		private Statement[] stmt = null;
		private ResultSet[] result = null;
		private Boolean isAutoClose = true;

		public DBHelper() {
		}
		
		

		public DBHelper(String connectionString) {
			this.connectionString = connectionString;
		}

		public DBHelper(String connectionString, String connectionDriver) {
			this.connectionString = connectionString;
			this.connectionDriver = connectionDriver;
		}

		public void setQuerySize(int size) {
			if (size > 0) {
				stmt = new Statement[size];
				result = new ResultSet[size];
			} else {
				stmt = null;
				result = null;
			}
		}

		private Boolean isOpen() {
			if (connection != null) {
				Boolean isClosed = true;
				try {
					isClosed = connection.isClosed();
				} catch (SQLException e) {
				}
				return !isClosed;
			}
			return false;
		}

		public void open() {
			isAutoClose = false;
			dbOpen();
		}

		private void dbOpen() {
			if (connectionDriver != null) {
				try {
					Class.forName(connectionDriver).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			try {
				connection = DriverManager.getConnection(connectionString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void close() {
			isAutoClose = true;
			dbClose();
		}

		private void dbClose() {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public List<Map<String, Object>> executeQuery(String sql) {
			return executeQuery(sql, null);
		}

		public List<Map<String, Object>> executeQuery(String sql,
				Hashtable<String, Object> settings) {
			if (!this.isOpen())
				this.dbOpen();
			if (this.isOpen()) {
				Statement statement = null;
				ResultSet resultSet = null;
				try {
					statement = connection.createStatement();
					if (settings != null && settings.containsKey("queryTimeout")) {
						statement.setQueryTimeout((Integer) settings
								.get("queryTimeout"));
					}
					resultSet = statement.executeQuery(sql);
					ResultSetMetaData meta = resultSet.getMetaData();
					String[] resultFields = new String[meta.getColumnCount()];
					for (int i = 0; i < resultFields.length; i++)
						resultFields[i] = meta.getColumnLabel(i + 1);
					List<Map<String, Object>> returnValue = new ArrayList<Map<String, Object>>();
					while (resultSet.next()) {
						Map<String, Object> item = new LinkedHashMap<String, Object>();
						for (int i = 0; i < resultFields.length; i++)
							item.put(resultFields[i], resultSet.getObject(i + 1));
						returnValue.add(item);
					}
					return returnValue;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (resultSet != null) {
						try {
							resultSet.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						resultSet = null;
					}
					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						statement = null;
					}
					if (this.isAutoClose)
						this.dbClose();
				}
			}
			return null;
		}

		public Boolean executeQuery(String sql, int queryIndex) {
			return executeQuery(sql, queryIndex, null);
		}

		public Boolean executeQuery(String sql, int queryIndex,
				Hashtable<String, Object> settings) {
			if (!this.isOpen())
				this.dbOpen();
			if (this.isOpen()) {
				try {
					this.stmt[queryIndex] = connection.createStatement();
					if (settings != null && settings.containsKey("queryTimeout")) {
						this.stmt[queryIndex].setQueryTimeout((Integer) settings
								.get("queryTimeout"));
					}
					this.result[queryIndex] = this.stmt[queryIndex]
							.executeQuery(sql);
					return true;
				} catch (SQLException e) {
					if (this.result[queryIndex] != null) {
						try {
							this.result[queryIndex].close();
						} catch (SQLException eii) {
							eii.printStackTrace();
						}
						this.result[queryIndex] = null;
					}
					if (this.stmt[queryIndex] != null) {
						try {
							this.stmt[queryIndex].close();
						} catch (SQLException eii) {
							eii.printStackTrace();
						}
						this.stmt[queryIndex] = null;
					}
					if (this.isAutoClose)
						this.dbClose();
					e.printStackTrace();
				}
			}
			return false;
		}

		public Map<String, Object> read(int queryIndex) {
			if (this.result[queryIndex] != null) {
				try {
					if (this.result[queryIndex].next()) {
						ResultSetMetaData meta = this.result[queryIndex]
								.getMetaData();
						String[] resultFields = new String[meta.getColumnCount()];
						for (int i = 0; i < resultFields.length; i++)
							resultFields[i] = meta.getColumnLabel(i + 1);
						Map<String, Object> item = new LinkedHashMap<String, Object>();
						for (int i = 0; i < resultFields.length; i++)
							item.put(resultFields[i],
									this.result[queryIndex].getObject(i + 1));
						return item;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		public void release(int queryIndex) {
			if (this.result[queryIndex] != null) {
				try {
					this.result[queryIndex].close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.result[queryIndex] = null;
			}
			if (this.stmt[queryIndex] != null) {
				try {
					this.stmt[queryIndex].close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.stmt[queryIndex] = null;
			}
			if (this.isAutoClose)
				this.dbClose();
		}

		public int executeUpdate(String sql) {
			if (!this.isOpen())
				this.dbOpen();
			if (this.isOpen()) {
				Statement statement = null;
				try {
					statement = connection.createStatement();
					return statement.executeUpdate(sql);
				} catch (SQLException e) {
				} finally {
					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						statement = null;
					}
					if (this.isAutoClose)
						this.dbClose();
				}
			}
			return -1;
		}
	}
