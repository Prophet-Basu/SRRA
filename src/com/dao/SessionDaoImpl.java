package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.SessionBean;
import com.util.DBSchema;
import com.util.DBSchema.Session;
import com.util.DBSchema.Teaches;
import com.util.DBUtil;

public class SessionDaoImpl implements SessionDao {

	private Connection conn = null;
	private String db = DBSchema.DB_NAME;
	private String table = Session.TABLE_NAME;

	@Override
	public boolean addSessionDetails(SessionBean sbean) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			if(!findSessionTable())
				createSessionTable();
			try {
				PreparedStatement ps = conn.prepareStatement(
						"insert into " + table + "(" + Session.Columns.STRID + "," + Session.Columns.START_DATE + ","
								+ Session.Columns.END_DATE + "," + Session.Columns.SECNAME +","+Session.Columns.ID+ ") values(?,?,?,?,?)");

				ps.setInt(1, sbean.getStrID());
				ps.setString(2, sbean.getSesStartDate());
				ps.setString(3, sbean.getSesEndDate());
				ps.setString(4, sbean.getSesSecName());
				ps.setString(5, sbean.getSesID());

				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean findSessionTable() {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("SHOW TABLES LIKE '" + table + "'");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean createSessionTable() {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {

				Statement st = conn.createStatement();
				String query = "create table " + table + "("
						+ Session.Columns.STRID + " int,"
						+ Session.Columns.ID + " nvarchar(50),"
						+ Session.Columns.START_DATE+" nvarchar(12),"
						+ Session.Columns.END_DATE+ " nvarchar(12),"
						+ Session.Columns.SECNAME+ " nvarchar(40),"
						+ "primary key ("+Session.Columns.ID+","+Session.Columns.SECNAME+")"
						+ ")";

				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: " + table);

//				Statement st2 = conn.createStatement();
//				st2.executeUpdate("ALTER TABLE " + table + " AUTO_INCREMENT=1");

				if (findSessionTable())
					flag = true;

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return flag;
	}

	@Override
	public ArrayList<SessionBean> fetchSessionDetails(int strID) {
		ArrayList<SessionBean> slist=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "
						+ Teaches.Columns.STRID+"=?");
				
				ps.setInt(1, strID);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					slist.add(new SessionBean(rs.getString(2), rs.getInt(1), rs.getString(3), rs.getString(5), rs.getString(4)));
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
		}
		return slist;
	}

	@Override
	public ArrayList<String> fetchSectionsforSession(int strID, String sesID) {
		ArrayList<String> slist=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select "+Teaches.Columns.SESSECNAME+" from "+table+" where "
						+ Teaches.Columns.STRID+"=? and "+Teaches.Columns.SESID+"=?");
				
				ps.setInt(1, strID);
				ps.setString(2, sesID);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					slist.add(rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! in SessionDao"+e.getMessage());
				e.printStackTrace();
			}
		}
		return slist;
	}

	@Override
	public ArrayList<String> fetchDistinctSessions(int strID) {
		ArrayList<String> slist=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select distinct "+Session.Columns.ID+" from "+table+" where "
						+ Teaches.Columns.STRID+"=?");
				
				ps.setInt(1, strID);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					slist.add(rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
		}
		return slist;
	}
	
	@Override
	public ArrayList<SessionBean> fetchAllSession() {
		ArrayList<SessionBean> strlist=new ArrayList<SessionBean>();
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select distinct * from "+table);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					int strID=rs.getInt(1);
					String sesid=rs.getString(2);
					String sesStrtDate=rs.getString(3);
					String sesEndDate= rs.getString(4);
					String secName=rs.getString(5);
					
					strlist.add(new SessionBean(sesid,strID,secName,sesStrtDate,sesEndDate));
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		return strlist;
	}

	@Override
	public SessionBean fetchSingleSession(String sesID) {
		
		SessionBean sesbean=null;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "+Session.Columns.ID+"=?");
				
				ps.setString(1, sesID);
				
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					int strID=rs.getInt(1);
					String sessionID=rs.getString(2);
					String sesStrtDate=rs.getString(3);
					String sesEndDate=rs.getString(4);
					String secName=rs.getString(5);
					
					sesbean=new SessionBean(sesID,strID,sesStrtDate,secName,sesEndDate);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Can't Fetch Session" + e.getMessage());
			}
		}
		return sesbean;
	}

	@Override
	public String createSessionName(String sesID) {
		SessionBean sesbean=fetchSingleSession(sesID);
		//System.out.println(sesbean.getSesStartDate());
		String name=sesbean.getSesStartDate()+sesbean.getSesEndDate();
		return name;
	}

	

}
