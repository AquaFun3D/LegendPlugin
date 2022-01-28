package com.aquafun3d.legendplugin.utils;

import java.sql.*;

/**
 * Class to handle basic MySQL database interaction
 */
public class MySQL {

	/**
	 * connection variable of the database
	 */
	private Connection connection;

	/**
	 * Connect to MySQL Database
	 */
	public void connect(){
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
	public void disconnect(){
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
	public boolean isConnected(){
		return connection != null;
	}

	/**
	 * Create a Table in database if not existing
	 */
	public void createTable(){
		if(isConnected()){
			try {
				PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS legend.bansystem (Playername VARCHAR(16), UUID VARCHAR(100), Reason VARCHAR(128), Duration VARCHAR(100), Perma BOOLEAN)");
				ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Makes an Update to the database by a given query
	 * @param query given query to update in database
	 */
	public void update(String query){
		if(isConnected()){
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.executeQuery();
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
	public ResultSet getResult(String query){
		if(isConnected()){
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
