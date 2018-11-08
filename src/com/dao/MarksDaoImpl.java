package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.MarksBean;
import com.util.DBSchema;
import com.util.DBSchema.Marks;
import com.util.DBSchema.Teacher;
import com.util.DBUtil;

public class MarksDaoImpl implements MarksDao{
	
	private Connection conn = null;
	private String db = DBSchema.DB_NAME;
	private String table = DBSchema.Marks.TABLE_NAME;
	
	@Override
	public boolean createMarksTable(int strID, String sesID) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
					SessionDao sesdao=new SessionDaoImpl();
					String sesName=sesdao.createSessionName(sesID);
					//System.out.println(sesName);
				
					Statement st=conn.createStatement();
					String query="create table "+ table + String.format("%02d", strID) +sesName+" ("+
					Marks.Columns.SUBCODE+" nvarchar(10),"+
					Marks.Columns.STUDROLL+" bigint,"+
					Marks.Columns.SESSECNAME+ " nvarchar(40),"+
					Marks.Columns.SEMNO+" int,"+
					Marks.Columns.SEMMARKS+" int,"+
					Marks.Columns.SEMGRADE+" nvarchar(2),"+
					Marks.Columns.INTERNAL1MARKS+" int,"+
					Marks.Columns.INTERNAL2MARKS+" int,"+
					Marks.Columns.CLSPERFMARKS+" int)";
					
					st.executeUpdate(query);
				
					if(findMarksTable(strID, sesID))
						flag=true;
				
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! In Create marks table" + e.getMessage());
			}
		}
		
		return flag;
	}

	@Override
	public boolean findMarksTable(int strID, String sesID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
					SessionDao sesdao=new SessionDaoImpl();
					String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement(
						"SHOW TABLES LIKE '" + Marks.TABLE_NAME + String.format("%02d", strID) +sesName+"'");
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
	public boolean insertMarks(MarksBean mb,int strID,String sesID) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				if(!findMarksTable(strID, sesID))
					createMarksTable(strID, sesID);
				
				PreparedStatement ps = conn.prepareStatement("insert into " + table + String.format("%02d", strID) +sesName+"("
						+ Marks.Columns.SUBCODE + "," + Marks.Columns.STUDROLL + "," + Marks.Columns.SESSECNAME + ","
						+ Marks.Columns.SEMNO + "," + Marks.Columns.SEMMARKS + "," + Marks.Columns.SEMGRADE + ","+Marks.Columns.INTERNAL1MARKS + ","
						+ Marks.Columns.INTERNAL2MARKS + "," + Marks.Columns.CLSPERFMARKS 
						+ ") " + "values(?,?,?,?,?,?,?,?,?)");
				
				ps.setString(1, mb.getSubCode());
				ps.setLong(2, mb.getStudRoll());
				ps.setString(3,mb.getSesSecName());
				ps.setInt(4, mb.getSemNo());
				ps.setInt(5, mb.getMarksSem());
				ps.setString(6, mb.getMarksGrade());
				ps.setInt(7,mb.getMarksInternal1());
				ps.setInt(8,mb.getMarksInternal2());
				ps.setInt(9,mb.getMarksClsPerf());
				
				if (ps.executeUpdate() > 0){
					flag=true;
				}
				
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! In Insert Marks Table" + e.getMessage());
			}
			 
		}
		return flag;
	}

	@Override
	public boolean updateSemMarks(MarksBean mb, int strID, String sesID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + sesName + " set "
						+ Marks.Columns.SEMMARKS + "=?,"
						+ Marks.Columns.SEMGRADE + "=?,"
						+ " where " + Marks.Columns.SUBCODE + "=?," + Marks.Columns.STUDROLL + "=?," + Marks.Columns.SESSECNAME + "=?,"
						+ Marks.Columns.SEMNO + "=?,");
				
				ps.setInt(1, mb.getMarksSem());
				ps.setString(2, mb.getMarksGrade());
				ps.setString(3, mb.getSubCode());
				ps.setLong(4, mb.getStudRoll());
				ps.setString(5,mb.getSesSecName());
				ps.setInt(6, mb.getSemNo());
				
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean updateInternalMarks(MarksBean mb, int strID, String sesID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				
					SessionDao sesdao=new SessionDaoImpl();
					String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + sesName + " set "
						+ Marks.Columns.INTERNAL1MARKS + "=?,"
						+ Marks.Columns.INTERNAL2MARKS + "=?," + Marks.Columns.CLSPERFMARKS + "=?,"
						+ " where " + Marks.Columns.SUBCODE + "=?," + Marks.Columns.STUDROLL + "=?," + Marks.Columns.SESSECNAME + "=?,"
						+ Marks.Columns.SEMNO + "=?");
				
				ps.setInt(1,mb.getMarksInternal1());
				ps.setInt(2,mb.getMarksInternal2());
				ps.setInt(3,mb.getMarksClsPerf());
				ps.setString(4, mb.getSubCode());
				ps.setLong(5, mb.getStudRoll());
				ps.setString(6,mb.getSesSecName());
				ps.setInt(7, mb.getSemNo());
				
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public ArrayList<MarksBean> findStudentParticularSemesterMarks(int strID, String sesID, long studRoll,int semNo) {
		ArrayList<MarksBean> marks=new ArrayList<MarksBean>();
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("select * from "+ table + String.format("%02d", strID) + sesName +" where "
															+ Marks.Columns.STUDROLL + "=? and "+ Marks.Columns.SEMNO + "=?");
				
				ps.setLong(1, studRoll);
				ps.setInt(2, semNo);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					String subCode=rs.getString(1);
					long roll=rs.getLong(2);
					String sesSecName=rs.getString(3);
					int semno=rs.getInt(4);
					int marksSem=rs.getInt(5);
					String marksGrade=rs.getString(6);
					int marksInternal1=rs.getInt(7);
					int marksInternal2=rs.getInt(8);
					int marksClsPerf=rs.getInt(9);
					
					MarksBean mb=new MarksBean(subCode, roll, sesSecName, semno, marksSem, marksGrade, marksInternal1, marksInternal2, marksClsPerf);
					marks.add(mb);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return marks;
	}

	@Override
	public int findStudentParticularSubjectMarks(int strID, String sesID, long studRoll, int semNo, String subCode) {
			int marks=0;
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("select mSem from "+ table + String.format("%02d", strID) + sesName +" where "
															+ Marks.Columns.STUDROLL + "=? and "+ Marks.Columns.SEMNO + "=? and "+ Marks.Columns.SUBCODE + "=?");
				
				ps.setLong(1, studRoll);
				ps.setInt(2, semNo);
				ps.setString(3, subCode);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					marks=rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return marks;
	}
	
	@Override
	public int findCountOfStudent(int strID,String sesID,String sesSecname,String subCode,String semGrade){
		int count=0;

		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);

				PreparedStatement ps=conn.prepareStatement("select count(*) from "+ table + String.format("%02d", strID) + sesName +" where "
						+ Marks.Columns.SESSECNAME + "=? and "+ Marks.Columns.SEMGRADE + "=? and "+ Marks.Columns.SUBCODE + "=?");

				ps.setString(1, sesSecname);
				ps.setString(2, semGrade);
				ps.setString(3, subCode);

				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					count=rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}

		return count;
	}

}
