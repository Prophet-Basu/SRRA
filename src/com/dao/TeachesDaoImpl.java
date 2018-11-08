package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.TeacherBean;
import com.bean.TeachesBean;
import com.util.DBSchema;
import com.util.DBUtil;
import com.util.DBSchema.Stream;
import com.util.DBSchema.Teacher;
import com.util.DBSchema.Teaches;

public class TeachesDaoImpl implements TeachesDao {

	private Connection conn = null;
	private String db = DBSchema.DB_NAME;
	private String table = DBSchema.Teaches.TABLE_NAME;

	@Override
	public boolean addTeachesDetails(TeachesBean tbean) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			if (!findTeachesTable())
				createTeachesTable();
			try {
				PreparedStatement ps = conn.prepareStatement("insert into " + table + "(" + Teaches.Columns.STRID + ","
						+ Teaches.Columns.SUBCODE + "," + Teaches.Columns.SESID + "," + Teaches.Columns.SESSECNAME + ","
						+ Teaches.Columns.TEACHERID + "," + Teaches.Columns.VERIFIED + ") " + "values(?,?,?,?,?,?)");

				ps.setInt(1, tbean.getStrID());
				ps.setString(2, tbean.getSubCode());
				ps.setString(3, tbean.getSesID());
				ps.setString(4, tbean.getSesSecName());
				ps.setInt(5, tbean.getTeacherID());
				ps.setInt(6, tbean.getVerified());
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
	public ArrayList<String> fetchTeacherSubjects(int teacherID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findTeachesTable() {
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
	public boolean createTeachesTable() {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {

				Statement st = conn.createStatement();
				String query = "create table " + table + " (" + Teaches.Columns.STRID + " int ," + Teaches.Columns.SESID
						+ " nvarchar(40) ," + Teaches.Columns.SESSECNAME + " nvarchar(40)," + Teaches.Columns.SUBCODE
						+ " nvarchar(10)," + Teaches.Columns.TEACHERID + " int," + Teaches.Columns.VERIFIED + " int)";

				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: " + table);

				if (findTeachesTable())
					flag = true;

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return flag;
	}

	@Override
	public ArrayList<TeachesBean> fetchNonVerifiedSubjects(int strID) {
		ArrayList<TeachesBean> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + " where "
						+ Teaches.Columns.VERIFIED + "=?" + " and " + Teaches.Columns.STRID + "=?");
				ps.setInt(1, 0);
				ps.setInt(2, strID);

				ResultSet rs = ps.executeQuery();
				TeachesBean tbean;
				while (rs.next()) {
					tbean = new TeachesBean(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(3),
							rs.getInt(5), rs.getInt(6));
					tlist.add(tbean);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query" + e.getMessage());
				e.printStackTrace();
			}
		}
		return tlist;
	}

	@Override
	public boolean verifyTeachesSubject(int strID, String sesID, String secName) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement(
						"update " + table + " set " + Teaches.Columns.VERIFIED + "=? where " + Teaches.Columns.STRID
								+ "=? and " + Teaches.Columns.SESID + "=? and " + Teaches.Columns.SESSECNAME + "=?");
				ps.setInt(1, 1);
				ps.setInt(2, strID);
				ps.setString(3, sesID);
				ps.setString(4, secName);
				
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
	public ArrayList<TeachesBean> fetchParticularSubjectTeacher(String subCode, int strID) {
		ArrayList<TeachesBean> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + " where "
						+ Teaches.Columns.SUBCODE + "=?" + " and " + Teaches.Columns.STRID + "=?");
				ps.setString(1, subCode);
				ps.setInt(2, strID);

				ResultSet rs = ps.executeQuery();
				TeachesBean tbean;
				while (rs.next()) {
					tbean = new TeachesBean(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(3),
							rs.getInt(5), rs.getInt(6));
					tlist.add(tbean);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query" + e.getMessage());
				e.printStackTrace();
			}
		}
		return tlist;
	}

	@Override
	public ArrayList<String> fetchTeacherSessionsTaught(int teacherID) {
		ArrayList<String> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select distinct(sesID) from " + table + " where "
						+ Teaches.Columns.VERIFIED + "=?" + " and " + Teaches.Columns.TEACHERID + "=?");
				ps.setInt(1, 1);
				ps.setInt(2, teacherID);

				ResultSet rs = ps.executeQuery();
				String tbean;
				while (rs.next()) {
					tbean = rs.getString(1);
					tlist.add(tbean);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!! in Teacher Session Retrieval" + e.getMessage());
				e.printStackTrace();
			}
		}
		return tlist;
	}

	@Override
	public ArrayList<String> fetchTeacherSectionsTaught(int teacherID, String sesID) {
		ArrayList<String> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select distinct(sesSecName) from " + table + " where "
						+ Teaches.Columns.VERIFIED + "=?" + " and " + Teaches.Columns.TEACHERID + "=?"+" and " + Teaches.Columns.SESID + "=?");
				ps.setInt(1, 1);
				ps.setInt(2, teacherID);
				ps.setString(3, sesID);

				ResultSet rs = ps.executeQuery();
				String tbean;
				while (rs.next()) {
					tbean = rs.getString(1);
					tlist.add(tbean);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!! in Teacher Section Retrieval" + e.getMessage());
				e.printStackTrace();
			}
		}
		return tlist;
	}

	@Override
	public ArrayList<String> fetchTeacherSubjectTaught(int teacherID, String sesID, String section) {
		ArrayList<String> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select distinct(subCode) from " + table + " where "
						+ Teaches.Columns.VERIFIED + "=?" + " and " + Teaches.Columns.TEACHERID + "=?"+" and " + Teaches.Columns.SESID + "=?"+" and " + Teaches.Columns.SESSECNAME + "=?");
				ps.setInt(1, 1);
				ps.setInt(2, teacherID);
				ps.setString(3, sesID);
				ps.setString(4, section);

				ResultSet rs = ps.executeQuery();
				String tbean;
				while (rs.next()) {
					tbean = rs.getString(1);
					tlist.add(tbean);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!! in Teacher Section Retrieval" + e.getMessage());
				e.printStackTrace();
			}
		}
		return tlist;
	}
	

}
