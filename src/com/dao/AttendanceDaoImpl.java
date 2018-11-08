package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.AttendanceBean;
import com.bean.SubjectBean;
import com.util.DBSchema;
import com.util.DBSchema.Attendance;
import com.util.DBSchema.Stream;
import com.util.DBSchema.Teacher;
import com.util.DBUtil;

public class AttendanceDaoImpl implements AttendanceDao {

	private Connection conn;
	private String db = DBSchema.DB_NAME, table = DBSchema.Attendance.TABLE_NAME;

	@Override
	public boolean createAttendanceTable(int strID, String sesID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				Statement st = conn.createStatement();
				String query = "create table " + table + String.format("%02d", strID) + sesName + "("
						+ Attendance.Columns.SUBCODE + " nvarchar(10)," + Attendance.Columns.SESSECNAME
						+ " nvarchar(40)," + Attendance.Columns.SEMNO + " int," + Attendance.Columns.ATTNTABLENAME
						+ " nvarchar(150)," + Attendance.Columns.ATTNTOTAL + " int)";

				st.executeUpdate(query);
				System.out.println("Created new table: " + Attendance.TABLE_NAME);

				if (findAttendanceTable(strID, sesID))
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
				e.printStackTrace();
			}

		}
		return flag;
	}

	@Override
	public boolean findAttendanceTable(int strID, String sesID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				PreparedStatement ps = conn.prepareStatement(
						"SHOW TABLES LIKE '" + Teacher.TABLE_NAME + String.format("%02d", strID) + sesName + "'");
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
	public boolean insertAttendanceDetails(AttendanceBean abean, int strID, String sesID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			if (!findAttendanceTable(strID, sesID))
				createAttendanceTable(strID, sesID);

			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				System.out.println(abean.getSesSecName());
				// sesName.replaceAll("\\s", "");
				PreparedStatement ps = conn.prepareStatement(
						"insert into " + table + String.format("%02d", strID) + sesName + " values(?,?,?,?,?)");

				String subj_attn_tablename = table + String.format("%02d", strID) + sesName + abean.getSubCode()
						+ abean.getSesSecName().replaceAll("\\s", "");
				ps.setString(1, abean.getSubCode());
				ps.setString(2, abean.getSesSecName());
				ps.setInt(3, abean.getSemNo());
				ps.setString(4, subj_attn_tablename);
				ps.setInt(5, 0);

				if (ps.executeUpdate() > 0) {
					flag = true;
					if (!findSubjectAttendanceTable(subj_attn_tablename))
						createSubjectAttendanceTable(strID, sesID, abean.getSesSecName(), subj_attn_tablename);
					else
						System.out.println("Table already exists!\n");
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!");
				e.printStackTrace();
			}
		}
		return flag;
	}

	private boolean createSubjectAttendanceTable(int strID, String sesID, String sesSecName, String sub_table) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				StudentAcademicDetailsDao sadao = new StudentAcademicDetailsDaoImpl();
				ArrayList<Long> rollList = sadao.fetchStudentRoll(strID, sesID, sesSecName);
				Statement st = conn.createStatement();
				String query = "create table " + sub_table + "(" + Attendance.SubjectTable.Columns.ATTNDATE
						+ " nvarchar(12) primary key,";
				for (long roll : rollList) {
					query += "r"+String.valueOf(roll) + " int,";
				}
				query += Attendance.SubjectTable.Columns.ATTNSTUDCOUNT + " int)";
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: " + sub_table);

				if (findSubjectAttendanceTable(sub_table))
					flag = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	private boolean findSubjectAttendanceTable(String sub_table) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("SHOW TABLES LIKE '" + sub_table + "'");
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
	public boolean insertSubjectAttendanceforDate(int strID, String sesID, AttendanceBean abean, String date,
			ArrayList<Boolean> attnList, int attnCount) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				PreparedStatement ps = conn.prepareStatement("select " + Attendance.Columns.ATTNTABLENAME + " from "
						+ table + String.format("%02d", strID) + sesName + " where " + Attendance.Columns.SUBCODE
						+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
				ps.setString(1, abean.getSubCode());
				ps.setString(2, abean.getSesSecName());
				ps.setInt(3, abean.getSemNo());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String sub_table = rs.getString(1);
					String query = "insert into " + sub_table + " values(?,";
					for (int i = 0; i < attnList.size(); i++)
						query += "?,";
					query += "?)";
					ps = conn.prepareStatement(query);
					ps.setString(1, date);
					int count = 2;
					for (Boolean b : attnList) {
						// b==true?ps.setInt(count, 1):ps.setInt(count, 0);
						if (b) {
							ps.setInt(count, 1);
						} else {
							ps.setInt(count, 0);
						}
						count++;
					}
					ps.setInt(count, attnCount);

					if (ps.executeUpdate() > 0) {
						flag = true;
						ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + sesName + " set "
								+ Attendance.Columns.ATTNTOTAL + "=" + Attendance.Columns.ATTNTOTAL + "+1 where "
								+ Attendance.Columns.SUBCODE + "=? and " + Attendance.Columns.SESSECNAME + "=? and "
								+ Attendance.Columns.SEMNO + "=?");
						ps.setString(1, abean.getSubCode());
						ps.setString(2, abean.getSesSecName());
						ps.setInt(3, abean.getSemNo());
						ps.executeUpdate();
					}
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! in insertion of subject");
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean updateSubjectAttendanceforDate(int strID, String sesID, AttendanceBean abean, String date,
			ArrayList<Boolean> attnList, int attnCount) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				PreparedStatement ps = conn.prepareStatement("select " + Attendance.Columns.ATTNTABLENAME + " from "
						+ table + String.format("%02d", strID) + sesName + " where " + Attendance.Columns.SUBCODE
						+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
				ps.setString(1, abean.getSubCode());
				ps.setString(2, abean.getSesSecName());
				ps.setInt(3, abean.getSemNo());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String sub_table = rs.getString(1);
					String query = "update " + sub_table + " set ";
					StudentAcademicDetailsDao sadao = new StudentAcademicDetailsDaoImpl();
					ArrayList<Long> rollList = sadao.fetchStudentRoll(strID, sesID, abean.getSesSecName());
					for (long roll : rollList)
						query += "r"+String.valueOf(roll) + "=?,";
					query += Attendance.SubjectTable.Columns.ATTNSTUDCOUNT + "=? where "
							+ Attendance.SubjectTable.Columns.ATTNDATE + "=?";

					ps = conn.prepareStatement(query);
					int count = 1;
					for (Boolean b : attnList) {
						// b==true?ps.setInt(count, 1):ps.setInt(count, 0);
						if (b) {
							ps.setInt(count++, 1);
						} else {
							ps.setInt(count++, 0);
						}
						// count++;
					}
					ps.setInt(count++, attnCount);
					ps.setString(count, date);

					if (ps.executeUpdate() > 0)
						flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!");
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	@Override
	public boolean deleteSubjectAttendanceforDate(int strID,String sesID,AttendanceBean abean,String date){
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				PreparedStatement ps = conn.prepareStatement("select " + Attendance.Columns.ATTNTABLENAME + " from "
						+ table + String.format("%02d", strID) + sesName + " where " + Attendance.Columns.SUBCODE
						+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
				ps.setString(1, abean.getSubCode());
				ps.setString(2, abean.getSesSecName());
				ps.setInt(3, abean.getSemNo());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String sub_table = rs.getString(1);
					ps=conn.prepareStatement("delete from "+sub_table+" where "+Attendance.SubjectTable.Columns.ATTNDATE+"=?");
					ps.setString(1, date);
					if (ps.executeUpdate() > 0)
						flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!");
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ArrayList<ArrayList<String>> fetchSubjectAttendance(int strID, String sesID, AttendanceBean abean) {
		ArrayList<ArrayList<String>> attnList = new ArrayList<ArrayList<String>>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sdao = new SessionDaoImpl();
				String sesName = sdao.createSessionName(sesID);
				PreparedStatement ps = conn.prepareStatement("select " + Attendance.Columns.ATTNTABLENAME + " from "
						+ table + String.format("%02d", strID) + sesName + " where " + Attendance.Columns.SUBCODE
						+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
				ps.setString(1, abean.getSubCode());
				ps.setString(2, abean.getSesSecName());
				ps.setInt(3, abean.getSemNo());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String sub_table = rs.getString(1);
					ps = conn.prepareStatement("select * from " + sub_table);
					ResultSet rs2 = ps.executeQuery();
					ResultSetMetaData metaData = rs2.getMetaData();
					ArrayList<String> alist = new ArrayList<>();
					for (int i = 1; i <= metaData.getColumnCount(); i++)
						alist.add(metaData.getColumnName(i));
					attnList.add(alist);
					while (rs2.next()) {
						alist = new ArrayList<>();
						String attnDate = rs2.getString(1);
						alist.add(attnDate);
						for (int i = 2; i < metaData.getColumnCount(); i++)
							alist.add(String.valueOf(rs2.getInt(i)));
						alist.add(String.valueOf(rs2.getInt(metaData.getColumnCount())));
						attnList.add(alist);
					}

				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!");
				e.printStackTrace();
			}
		}
		return attnList;
	}

	@Override
	public ArrayList<ArrayList<String>> fetchStudentAttnPercentForSubjectsInSem(int strID, String sesID, String sesSecName,
			int semNo,long roll) {
		ArrayList<ArrayList<String>> aalist=new ArrayList<ArrayList<String>>();
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			SessionDao sdao = new SessionDaoImpl();
			String sesName = sdao.createSessionName(sesID);
			SubjectDao sbdao=new SubjectDaoImpl();
			ArrayList<SubjectBean> sblist=sbdao.fetchAllStreamSubjects(strID, semNo);
			PreparedStatement ps=null;
			try{
			for(SubjectBean sb:sblist){
				ps = conn.prepareStatement("select " + Attendance.Columns.ATTNTABLENAME + " from "
						+ table + String.format("%02d", strID) + sesName + " where " + Attendance.Columns.SUBCODE
						+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
				ps.setString(1, sb.getSubCode());
				ps.setString(2, sesSecName);
				ps.setInt(3, semNo);
				
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String sub_table = rs.getString(1);
					ps=conn.prepareStatement("select count(*) from "+sub_table+" where r"+roll+"='1'");
					rs = ps.executeQuery();
					
					if(rs.next()){
						int count=rs.getInt(1);
						ps=conn.prepareStatement("select "+ Attendance.Columns.ATTNTOTAL +  " from "+ table + String.format("%02d", strID) + sesName +" where "+ Attendance.Columns.SUBCODE
								+ "=? and " + Attendance.Columns.SESSECNAME + "=? and " + Attendance.Columns.SEMNO + "=?");
						ps.setString(1, sb.getSubCode());
						ps.setString(2, sesSecName);
						ps.setInt(3, semNo);
						
						rs = ps.executeQuery();
						if(rs.next()){
							System.out.println("pahuch gaye");
						int total=rs.getInt(1);	
						ArrayList<String> alist=new ArrayList<>();
						alist.add(sb.getSubCode());
						float value=((float)count/(float)total)*100;
						System.out.println(value);
						alist.add(String.valueOf(value));
						aalist.add(alist);
						}
					}
				}
			}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! in fetch prcent");
				e.printStackTrace();
			}
		}
		return aalist;
	}

}
