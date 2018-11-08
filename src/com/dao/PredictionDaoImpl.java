package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBSchema;
import com.util.DBUtil;

public class PredictionDaoImpl implements PredictionDao{

	private Connection conn=null;
	private String DB=DBSchema.DB_NAME;
	private String TABLE="srrapranlys";
	@Override
	public ArrayList<String> getAllParameterList() {
		ArrayList<String> paramList=new ArrayList<>();
		conn=DBUtil.getConnection(DB);
		if(conn!=null){
			try {
				PreparedStatement ps=conn.prepareStatement("SELECT COLUMN_NAME "+
						"FROM INFORMATION_SCHEMA.COLUMNS WHERE  TABLE_NAME = '"+TABLE+"' ");
				
				ResultSet rs = ps.executeQuery();
				rs.next();
				while(rs.next()){
					paramList.add(rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Error in retrieving data for Prediction!!!");
				e.printStackTrace();
			}
		}
		return paramList;
	}

}
