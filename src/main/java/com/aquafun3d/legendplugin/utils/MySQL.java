package com.aquafun3d.legendplugin.utils;

import java.sql.*;

/**
 * Class to handle basic MySQL database interaction
 */
public class MySQL {

	/**
	 * connection variable of the database
	 */
	private static Connection connection;

	/**
	 * Connect to MySQL Database
	 */
	public static void connect(){
		if(!isConnected()){
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","me","Lolli");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Database connected");
			createTable();
		}
	}

	/**
	 * Disconnect from MySQL Databse
	 */
	public static void disconnect(){
		if(!isConnected()){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Database disconnected");
		}
	}

	/**
	 * Check if database is connected
	 * @return true if connected, false if not
	 */
	public static boolean isConnected(){
		return connection != null;
	}

	/**
	 * Create a Table in database if not existing
	 */
	public static void createTable(){
		if(isConnected()){
			try {
				connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS legend.bansystem (Playername VARCHAR(16), UUID VARCHAR(100), Reason VARCHAR(128), Duration VARCHAR(100), Perma BOOLEAN)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Makes an Update to the database by a given query
	 * @param query given query to update in database
	 */
	public static void update(String query){
		if(isConnected()){
			try {
				connection.createStatement().executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gives a Result form database by a given query
	 * @param query Query to get result of
	 * @return Result of given query
	 */
	public static ResultSet getResult(String query){
		if(isConnected()){
			try {
				return connection.createStatement().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
