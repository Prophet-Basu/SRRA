package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


import com.bean.StreamBean;
import com.util.DBSchema;
import com.util.DBSchema.Stream;
import com.util.DBUtil;

public class StreamDaoImpl implements StreamDao {

	private Connection conn=null;
	private String db=DBSchema.DB_NAME;
	private String table=Stream.TABLE_NAME;
	
	private boolean createTable(){
		boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				Statement st = conn.createStatement();
				String query = "create table "+Stream.TABLE_NAME+" ("
						+ Stream.Columns.ID+" int primary key auto_increment,"
						+ Stream.Columns.NAME+" nvarchar(100),"
						+ Stream.Columns.NOOFSEM+" int not null )";
						
				
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: "+Stream.TABLE_NAME);
				
				if(findStreamTable())
					flag=true;
		
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return flag;
	}
	
	
	@Override
	public int addStream(StreamBean strbean) {
		int id=0;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				if(!findStreamTable())
					createTable();
				
				PreparedStatement ps=conn.prepareStatement(
						"insert into " + table+"("+
						 Stream.Columns.NAME+","
						+Stream.Columns.NOOFSEM+") "
						+ "values(?,?)",
				Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1,strbean.getStrName() );
				ps.setInt(2, strbean.getStrNoOfSem());
				
				if(ps.executeUpdate()>0){
					ResultSet rs=ps.getGeneratedKeys();
					if(rs.next())
						id=rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return id;
	}
	
	
	
	@Override
	public String fetchStreamName(int strID) {
		String name=null;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				PreparedStatement ps=conn.prepareStatement("select strName from "+table+" where strID=?");
				
				ps.setInt(1, strID);
				
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					name=rs.getString(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		
		return name;
	}
	@Override
	public ArrayList<StreamBean> fetchAllStream() {
		ArrayList<StreamBean> strlist=new ArrayList<StreamBean>();
				
				conn=DBUtil.getConnection(db);
				
				if(conn!=null)
				{
					try {
						PreparedStatement ps=conn.prepareStatement("select * from "+table);
						
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							int strid=rs.getInt(1);
							String strName=rs.getString(2);
							int strNoOfSem = rs.getInt(3);
							
							strlist.add(new StreamBean(strid,strName,strNoOfSem));
						}
						
					} catch (SQLException e) {
						System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
					}
		}
		return strlist;
	}
	@Override
	public StreamBean fetchStreamDetails(int strID) {
		StreamBean strbean=null;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				PreparedStatement ps=conn.prepareStatement("select * from "+table+" where strID=?");
				
				ps.setInt(1, strID);
				
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					int streamid=strID;
					String strname=rs.getString(2);
					int strNoOfSem=rs.getInt(3);
					
					strbean=new StreamBean(streamid,strname,strNoOfSem);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return strbean;
	}



	@Override
	public boolean findStreamTable() {
		boolean flag=false;
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				PreparedStatement ps = conn.prepareStatement("SHOW TABLES LIKE '"+Stream.TABLE_NAME+"'");
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					flag=true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}


	@Override
	public boolean deleteStream(int strID) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("delete from "+table+" where "+Stream.Columns.ID+"=?");
				ps.setInt(1, strID);
				
				if(ps.executeUpdate()>0)
					flag=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		return flag;
	}


	@Override
	public boolean updateStream(StreamBean sb) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("update "+table+" set "+Stream.Columns.NAME+"=?,"
						+ Stream.Columns.NOOFSEM+"=? where "+Stream.Columns.ID+"=?");
				ps.setString(1, sb.getStrName());
				ps.setInt(2, sb.getStrNoOfSem());
				ps.setInt(3, sb.getStrID());
				
				if(ps.executeUpdate()>0)
					flag=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	@Override
	public int fetchNoOfSem(int strID){
		int semNo=0;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select "+Stream.Columns.NOOFSEM+" from "+table+" where "
						+ Stream.Columns.ID+"=?");
				ps.setInt(1, strID);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					semNo=rs.getInt(1);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return semNo;
	}
	
	
}
