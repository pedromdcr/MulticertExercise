package com.pedromdcr.multicert.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientManager {

	private static Connection connection;
	
	// Database fields
	public static final String DATABASE_NAME = "multicertdb";
	public static final String SCHEMA_NAME = "multicert";
	public static final String TABLE_NAME = "CLIENTS";
	public static final String CLIENT_NAME = "name";
	public static final String CLIENT_NIF = "nif";
	public static final String CLIENT_ADDRESS = "address";
	public static final String CLIENT_PHONENUMBER = "phonenumber";
	
	public void init() {
		
		try
	    {
			// Step 1: "Load" the JDBC driver
			Class.forName("org.postgresql.Driver"); 

			// Step 2: Establish the connection to the database 
			String url = "jdbc:postgresql://127.0.0.1:5432/multicertdb"; 
			connection = DriverManager.getConnection(url, "postgres", "multicert");  
	    }
	    catch (ClassNotFoundException e)
	    {
	    	System.err.println("Unable to locate JDBC driver. Exception message:");
	    	System.err.println(e.getMessage()); 
	    } 
		catch (SQLException e)
	    {
			System.err.println("Error accessing database. Exception message:");
			System.err.println(e.getMessage()); 
	    } 
		
	}

	public String addClient(String name, String nif, String address, String phoneNumber) {
		
		if(connection != null) {
			
			String query = "INSERT INTO multicert.CLIENTS ("
				    + " NAME,"
				    + " NIF,"
				    + " ADDRESS,"
				    + " PHONENUMBER) VALUES ("
				    + "?, ?, ?, ?)";
			
			try {
				// set all the preparedStatement parameters
			    PreparedStatement stmt = connection.prepareStatement(query);
			    stmt.setString(1, name/*client.getName()*/);
			    stmt.setString(2, nif/*client.getNif()*/);
			    stmt.setString(3, address/*client.getAddress()*/);
			    stmt.setString(4, phoneNumber/*client.getPhoneNumber()*/);

			    // execute the preparedStatement insert
			    stmt.executeUpdate();
			    stmt.close();
			} 
			catch (SQLException e)
			{
				System.err.println("Error preparing statement or updating database. Exception message:");
				System.err.println(e.getMessage()); 
			}

			String response = "Added client with info: \n"; 
			response += "Name: " + name/*client.getName()*/ + ",\n";
			response += "NIF: " + nif/*client.getNif()*/ + ",\n";
			response += "Address: " + address/*client.getAddress()*/ + ",\n";
			response += "Phone: " + phoneNumber/*client.getPhoneNumber()*/ + ".\n";
			
			return response;
			
		}
		
		return "Error connecting to database. Try again";
		
	}
	
	public String listAllClients() {
		
		if(connection != null) {
		
			String response = ""; 
			try{
				Statement stmt = connection.createStatement();
		        ResultSet rs;
		
		        rs = stmt.executeQuery("SELECT * FROM multicert.CLIENTS");
		        while(rs.next()) {
		            String name = rs.getString(CLIENT_NAME);
		            String nif = rs.getString(CLIENT_NIF);
		            String address = rs.getString(CLIENT_ADDRESS);
		            String phoneNumber = rs.getString(CLIENT_PHONENUMBER);
		            response += name + " | " + nif + " | " + address + " | " + phoneNumber + "\n";
		        }
		        
		        //connection.close();
		    }
			catch (SQLException e) {
		        System.err.println("Error querying database. Exception message:");
		        System.err.println(e.getMessage());
		    }
			
			return response;
		}
		
		return "Error connecting to database. Try again";
	}
	
	public String getClientByNif(String nif) {
		
		if(connection != null) {
		
			String query = "SELECT * FROM multicert.CLIENTS" + 
							" WHERE nif = ?";
			ResultSet rs;
			String response = ""; 
			try{
				
				// set all the preparedStatement parameters
			    PreparedStatement stmt = connection.prepareStatement(query);
			    stmt.setString(1, nif/*client.getNif()*/);
			    
			    // execute the preparedStatement insert
			    rs = stmt.executeQuery();
			    
			    while(rs.next()) {
		            String name = rs.getString(CLIENT_NAME);
		            String address = rs.getString(CLIENT_ADDRESS);
		            String phoneNumber = rs.getString(CLIENT_PHONENUMBER);
		            response += name + " | " + nif + " | " + address + " | " + phoneNumber + "\n";
		        }
			    
			    stmt.close();
				
				//connection.close();
			}
			catch (SQLException e) {
				System.err.println("Error preparing statement or updating database. Exception message:");
				System.err.println(e.getMessage());
			}
			
			return response;
		}
		
		return "Error connecting to database. Try again";
	}
	
	public String getClientByName(String name) {
		
		if(connection != null) {
		
			String query = "SELECT * FROM multicert.CLIENTS" + 
							" WHERE name = ?";
			ResultSet rs;
			String response = ""; 
			try{
				
				// set all the preparedStatement parameters
			    PreparedStatement stmt = connection.prepareStatement(query);
			    stmt.setString(1, name);
			    
			    // execute the preparedStatement insert
			    rs = stmt.executeQuery();
			    
			    while(rs.next()) {
		            String nif = rs.getString(CLIENT_NIF);
		            String address = rs.getString(CLIENT_ADDRESS);
		            String phoneNumber = rs.getString(CLIENT_PHONENUMBER);
		            response += name + " | " + nif + " | " + address + " | " + phoneNumber + "\n";
		        }
			    
			    stmt.close();
				
				//connection.close();
			}
			catch (SQLException e) {
				System.err.println("Error preparing statement or updating database. Exception message:");
				System.err.println(e.getMessage());
			}
			
			return response;
		}
		
		return "Error connecting to database. Try again";
	}
	
	public String removeClient(String name, String nif, String address, String phoneNumber) {
		
		if(connection != null) {
			
			String query = "DELETE FROM multicert.CLIENTS" + 
					" WHERE name = ?" +
					" AND nif = ?" +
					" AND address = ?" +
					" AND phonenumber = ?;";
			
			try{
				
				// set all the preparedStatement parameters
			    PreparedStatement stmt = connection.prepareStatement(query);
			    stmt.setString(1, name/*client.getName()*/);
			    stmt.setString(2, nif/*client.getNif()*/);
			    stmt.setString(3, address/*client.getAddress()*/);
			    stmt.setString(4, phoneNumber/*client.getPhoneNumber()*/);

			    // execute the preparedStatement insert
			    stmt.executeUpdate();
			    stmt.close();
				
				//connection.close();
			}
			catch (SQLException e) {
				System.err.println("Error preparing statement or updating database. Exception message:");
				System.err.println(e.getMessage());
			}
			
			String response = "Removed client with info: \n"; 
			response += "Name: " + name/*client.getName()*/ + ",\n";
			response += "NIF: " + nif/*client.getNif()*/ + ",\n";
			response += "Address: " + address/*client.getAddress()*/ + ",\n";
			response += "Phone: " + phoneNumber/*client.getPhoneNumber()*/ + ".";
			
			return response;
		}
		
		return "Error connecting to database. Try again";
	}

}
