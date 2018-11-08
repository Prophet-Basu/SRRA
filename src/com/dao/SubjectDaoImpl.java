package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.SubjectBean;
import com.util.DBSchema;
import com.util.DBSchema.Stream;
import com.util.DBSchema.Subject;
import com.util.DBUtil;

public class SubjectDaoImpl implements SubjectDao {

	private Connection conn=null;
	private String db=DBSchema.DB_NAME;
	private String table=DBSchema.Subject.TABLE_NAME;
	
	private boolean findTable() {
		boolean flag=false;
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				PreparedStatement ps = conn.prepareStatement("SHOW TABLES LIKE '"+Subject.TABLE_NAME+"'");
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
	
	private boolean createTable(){
		boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				Statement st = conn.createStatement();
				String query = "create table "+Subject.TABLE_NAME+" ("
						+ Subject.Columns.CODE+" nvarchar(10),"
						+ Subject.Columns.NAME+" nvarchar(100),"
						+ Subject.Columns.SEM+" int,"
						+ Subject.Columns.TAUGHTDURATION+" int,"
						+ Subject.Columns.STRID+" int )";
						
				
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: "+Subject.TABLE_NAME);
				
				if(findTable())
					flag=true;
		
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return flag;
	}
	
	@Override
	public boolean addSubject(SubjectBean subBean) {
		boolean flag = false;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				if(!findTable())
					createTable();
				
				PreparedStatement ps=conn.prepareStatement("insert into "+table+"("+
															Subject.Columns.CODE+","+
															Subject.Columns.NAME+","+
															Subject.Columns.SEM+","+
															Subject.Columns.TAUGHTDURATION+","+
															Subject.Columns.STRID+")"
															+"values(?,?,?,?,?)");
				ps.setString(1,subBean.getSubCode());
				ps.setString(2, subBean.getSubName());
				ps.setInt(3, subBean.getSubSem());
				ps.setInt(4, subBean.getSubTaughtDuration());
				ps.setInt(5, subBean.getStrID());
				
				if(ps.executeUpdate()>0)
					flag=true;
						
				
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public ArrayList<SubjectBean> fetchAllStreamSubjects(int strID,int subsem) {
		ArrayList<SubjectBean> sublist=new ArrayList<SubjectBean>();
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
					PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "+Subject.Columns.STRID+"=? and "+Subject.Columns.SEM+"=?");
					ps.setInt(1, strID);
					ps.setInt(2, subsem);
					
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						String subCode=rs.getString(1);
						String subName=rs.getString(2);
						int subSem=rs.getInt(3);
						int duration=rs.getInt(4);
						int streamID=strID;
						
						sublist.add(new SubjectBean(subCode, subName, subSem,duration, streamID));
					}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}	
		}
		
		return sublist;
	}

	@Override
	public ArrayList<SubjectBean> fetchAllSubjects() {
		ArrayList<SubjectBean> sublist=new ArrayList<SubjectBean>();
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
					PreparedStatement ps=conn.prepareStatement("select * from "+table);
					
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						String subCode=rs.getString(1);
						String subName=rs.getString(2);
						int subSem=rs.getInt(3);
						int duration=rs.getInt(4);
						int streamID=rs.getInt(5);
						
						sublist.add(new SubjectBean(subCode, subName, subSem,duration, streamID));
					}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		
		return sublist;
	}

	@Override
	public ArrayList<SubjectBean> fetchAllStreamSubjects(int strID) {
		ArrayList<SubjectBean> sublist=new ArrayList<SubjectBean>();
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
					PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "+Subject.Columns.STRID+"=? ");
					ps.setInt(1, strID);
					
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						String subCode=rs.getString(1);
						String subName=rs.getString(2);
						int subSem=rs.getInt(3);
						int duration=rs.getInt(4);
						int streamID=strID;
						
						sublist.add(new SubjectBean(subCode, subName, subSem,duration, streamID));
					}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}	
		}
		
		return sublist;
	}

	@Override
	public boolean deleteSubject(String subCode) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("delete from "+table+" where "+Subject.Columns.CODE+"=?");
				ps.setString(1, subCode);
				
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
	public boolean updateSubject(SubjectBean sb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkSubjectCode(String subCode) {
		boolean flag=false;
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
					PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "+Subject.Columns.CODE+"=? ");
					ps.setString(1, subCode);
					
					ResultSet rs =ps.executeQuery();
					if(rs.next()){
						flag=true;
					}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}	
		}
		return flag;
	}

	@Override
	public SubjectBean fetchSubject(int strID, String subCode) {
		SubjectBean sb=null;
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try {
				PreparedStatement ps=conn.prepareStatement("select * from "+table+" where "+Subject.Columns.STRID+"=? and "+Subject.Columns.CODE+"=?");
				ps.setInt(1, strID);
				ps.setString(2, subCode);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					subCode=rs.getString(1);
					String subName=rs.getString(2);
					int subSem=rs.getInt(3);
					int subTaughtDuration=rs.getInt(4);
					strID=rs.getInt(5);
					
					sb=new SubjectBean(subCode, subName, subSem, subTaughtDuration, strID);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb;
	}
	

}
