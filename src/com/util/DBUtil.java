package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String PASS;
	private static String USER;
	private static String CONN_STRING;
	
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection conn = null;

	private DBUtil() {
	}

	public static Connection getConnection(String db) {
		try {
			
			
			FileInputStream fstream = new FileInputStream("config.ini");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			String line;
			line = br.readLine(); 
			
			CONN_STRING=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
			
			USER=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
				
			PASS=line.substring(line.indexOf('=')+1);
			
			br.close();
			
		} catch (FileNotFoundException e) {} catch (IOException e) {
//			File fout = new File("config.ini");
//			FileOutputStream fos;
//			try {
//				fos = new FileOutputStream(fout);
//				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//				bw.write("CONN_STRING="+"jdbc:mysql://localhost:3306/");
//				bw.newLine();
//				bw.write("USER=");
//				bw.newLine();
//				bw.write("PASS=");
//				bw.close();
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			
		}
		
		if (conn == null) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot find Driver class...");
			}

			String cs = CONN_STRING+db;

			try {
				conn = DriverManager.getConnection(cs, USER, PASS);
			} catch (SQLException e) {
				System.out.println("Connection failed" + e.getMessage()+"Check config.ini file");
			}
		}

		return conn;
	}
	
	public static void closeConnection()
	{
		if(conn!=null)
		{
			try {
				conn.close();
				conn=null;
			} 
			catch (SQLException e) {
				System.out.println("Unable to close connection...");
			}
		}
	}

}
