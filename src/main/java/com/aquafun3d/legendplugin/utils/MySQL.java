package com.aquafun3d.legendplugin.utils;

import java.sql.*;

public class MySQL {


	private static Connection connection;

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

	public static boolean isConnected(){
		return connection != null;
	}

	public static void createTable(){
		if(isConnected()){
			try {
				connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS legend.bansystem (Playername VARCHAR(16), UUID VARCHAR(100), Reason VARCHAR(128), Duration VARCHAR(100))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(String query){
		if(isConnected()){
			try {
				connection.createStatement().executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

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
