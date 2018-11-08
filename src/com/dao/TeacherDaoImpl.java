package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.bean.TeacherBean;
import com.util.DBSchema;
import com.util.DBSchema.Stream;
import com.util.DBSchema.Teacher;
import com.util.DBUtil;

public class TeacherDaoImpl implements TeacherDao {

	private Connection conn = null;
	private String db = DBSchema.DB_NAME;
	private String table = DBSchema.Teacher.TABLE_NAME;

	@Override
	public int addTeacher(TeacherBean trBean, int strID) {
		int id = 0;

		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				if (!findTeacherTable(strID))
					createTeacherTable(strID);
				PreparedStatement ps = conn.prepareStatement("insert into " + table + String.format("%02d", strID) + "("
						+ Teacher.Columns.NAME + "," + Teacher.Columns.ADDRESS + "," + Teacher.Columns.DOB + ","
						+ Teacher.Columns.MOB + "," + Teacher.Columns.EMAIL + "," + Teacher.Columns.ROLE + ","
						+ Teacher.Columns.DESIGNATION + "," + Teacher.Columns.PASSWORD + "," + Teacher.Columns.APPROVE
						+ ")" + "values(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, trBean.getTeacherName());
				ps.setString(2, trBean.getTeacherAddress());
				ps.setString(3, trBean.getTeacherDOB());
				ps.setLong(4, trBean.getTeacherMob());
				ps.setString(5, trBean.getTeacherEmail());
				ps.setString(6, trBean.getTeacherRole());
				ps.setString(7, trBean.getTeacherDesignation());
				ps.setString(8, trBean.getTeacherPassword());
				ps.setInt(9, trBean.getApprove());

				if (ps.executeUpdate() > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next())
						id = rs.getInt(1);
				}

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}

		return id;
	}

	@Override
	public boolean findTeacherByEmail(String email,int strID)
	{
		boolean f=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select * from "+table+String.format("%02d", strID));
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					f=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
	
	@Override
	public ArrayList<TeacherBean> fetchAllTeacher(int strID) {
		ArrayList<TeacherBean> tbList=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try {
				PreparedStatement ps=conn.prepareStatement("select * from "+table+String.format("%02d", strID));
				
				ResultSet rs=ps.executeQuery();
				TeacherBean tbean;
				while(rs.next()){
					tbean = new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getInt(10));
					tbList.add(tbean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tbList;
	}

	@Override
	public boolean delTeacher(int teacherID, int strID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement(
						"delete from " + table + String.format("%02d", strID) + " where " + Teacher.Columns.ID + "=?");

				ps.setInt(1, teacherID);
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean updateTeacher(TeacherBean trBean, int strID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + " set "
						+ Teacher.Columns.NAME + "=?," + Teacher.Columns.ADDRESS + "=?," + Teacher.Columns.DOB + "=?,"
						+ Teacher.Columns.MOB + "=?," + Teacher.Columns.EMAIL + "=?," + Teacher.Columns.ROLE + "=?,"
						+ Teacher.Columns.DESIGNATION + "=?," + Teacher.Columns.PASSWORD + "=?," + Teacher.Columns.APPROVE
						+ "=?" + " where " + Teacher.Columns.ID + "=?");

				ps.setString(1, trBean.getTeacherName());
				ps.setString(2, trBean.getTeacherAddress());
				ps.setString(3, trBean.getTeacherDOB());
				ps.setLong(4, trBean.getTeacherMob());
				ps.setString(5, trBean.getTeacherEmail());
				ps.setString(6, trBean.getTeacherRole());
				ps.setString(7, trBean.getTeacherDesignation());
				ps.setString(8, trBean.getTeacherPassword());
				ps.setInt(9, trBean.getApprove());
				ps.setInt(10, trBean.getTeacherID());
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean createTeacherTable(int strID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {

				Statement st = conn.createStatement();
				String query = "create table " + Teacher.TABLE_NAME + String.format("%02d", strID) + " ("
						+ Teacher.Columns.ID + " int primary key auto_increment," + Teacher.Columns.NAME
						+ " nvarchar(100)," + Teacher.Columns.ADDRESS + " nvarchar(100)," + Teacher.Columns.DOB
						+ " nvarchar(12)," + Teacher.Columns.MOB + " bigint," + Teacher.Columns.EMAIL + " nvarchar(50),"
						+ Teacher.Columns.ROLE + " nvarchar(15)," + Teacher.Columns.DESIGNATION + " nvarchar(50),"
						+ Teacher.Columns.PASSWORD + " nvarchar(20)," + Teacher.Columns.APPROVE + " int)";

				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: " + Stream.TABLE_NAME);

				Statement st2 = conn.createStatement();
				st2.executeUpdate("ALTER TABLE " + Teacher.TABLE_NAME + String.format("%02d", strID)
						+ " AUTO_INCREMENT=130" + String.format("%02d", strID) + "001");

				if (findTeacherTable(strID))
					flag = true;

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}

		}
		return flag;
	}

	private boolean findTeacherTable(int strID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement(
						"SHOW TABLES LIKE '" + Teacher.TABLE_NAME + String.format("%02d", strID) + "'");
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
	public boolean approveTeacher(int teacherID, int strID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + " set "
						+ Teacher.Columns.APPROVE + "=?" + " where " + Teacher.Columns.ID + "=?");

				ps.setInt(1, 1);
				ps.setInt(2, teacherID);
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean validateTeacherLogin(String teacherEmail, String teacherPassword, int strID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + String.format("%02d", strID)
						+ " where " + Teacher.Columns.EMAIL + "=?" + "and " + Teacher.Columns.PASSWORD + "=?" + " and "
						+ Teacher.Columns.APPROVE + "=?");
				ps.setString(1, teacherEmail);
				ps.setString(2, teacherPassword);
				ps.setInt(3, 1);

				ResultSet rs = ps.executeQuery();
				if (rs.next())
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean validateAdminLogin(String teacherEmail, String teacherPassword, int strID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + String.format("%02d", strID)
						+ " where " + Teacher.Columns.EMAIL + "=?" + "and " + Teacher.Columns.PASSWORD + "=?" + " and "
						+ Teacher.Columns.ROLE + "=?");
				ps.setString(1, teacherEmail);
				ps.setString(2, teacherPassword);
				ps.setString(3, "Admin");

				ResultSet rs = ps.executeQuery();
				if (rs.next())
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ArrayList<TeacherBean> fetchNonVerifiedTeachers(int strID) {
		ArrayList<TeacherBean> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + String.format("%02d", strID)
						+ " where " + Teacher.Columns.APPROVE + "=?");
				ps.setInt(1, 0);

				ResultSet rs = ps.executeQuery();
				TeacherBean tbean;
				while (rs.next()) {
					tbean = new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getInt(10));
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
	public ArrayList<TeacherBean> fetchNonAdminTeachers(int strID) {
		ArrayList<TeacherBean> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + String.format("%02d", strID)
						+ " where " + Teacher.Columns.ROLE + "=?");
				ps.setString(1, "Teacher");

				ResultSet rs = ps.executeQuery();
				TeacherBean tbean;
				while (rs.next()) {
					tbean = new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getInt(10));
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
	public boolean promoteTeacherRole(int teacherID, int strID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) + " set "
						+ Teacher.Columns.ROLE + "=?" + " where " + Teacher.Columns.ID + "=?");

				ps.setString(1, "Admin");
				ps.setInt(2, teacherID);
				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public TeacherBean fetchTeacher(String teacherEmail, String teacherPassword, int strID) {
		TeacherBean tbean=null;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select * from " + table + String.format("%02d", strID)
							+ " where " + Teacher.Columns.EMAIL +"=?" + " and "+Teacher.Columns.PASSWORD+"=?");
				ps.setString(1, teacherEmail);
				ps.setString(2, teacherPassword);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					tbean=new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
		}
		return tbean;
	}

	@Override
	public TeacherBean fetchTeacher(int teacherID,int strID) {
		TeacherBean tbean=null;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select * from " + table + String.format("%02d", strID)
							+ " where " + Teacher.Columns.ID +"=?");
				ps.setInt(1, teacherID);
				//ps.setString(2, teacherPassword);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					tbean=new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!"+e.getMessage());
				e.printStackTrace();
			}
		}
		return tbean;
	}

	@Override
	public boolean updateTeacherPassword(int teacherID, int strID, String pass) {
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("update " + table + String.format("%02d", strID) + " set "
							+ Teacher.Columns.PASSWORD +"=? where "+ Teacher.Columns.ID +"=?");
				ps.setString(1, pass);
				ps.setInt(2, teacherID);
				
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
	public ArrayList<TeacherBean> fetchAllTeacher(int strID, int teacherID) {
		ArrayList<TeacherBean> tlist = new ArrayList<>();
		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from " + table + String.format("%02d", strID)
						+ " where " + Teacher.Columns.APPROVE + "=? and "+ Teacher.Columns.ID+"!=?");
				ps.setInt(1, 1);
				ps.setInt(2, teacherID);

				ResultSet rs = ps.executeQuery();
				TeacherBean tbean;
				while (rs.next()) {
					tbean = new TeacherBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getInt(10));
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
	public ArrayList<String> getAllParameterList(int strID) {
		ArrayList<String> paramList=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try {
				PreparedStatement ps=conn.prepareStatement("SELECT COLUMN_NAME "+
						"FROM INFORMATION_SCHEMA.COLUMNS WHERE  TABLE_NAME = '"+table+String.format("%02d", strID)+"' ");
				
				ResultSet rs = ps.executeQuery();
				//rs.next();
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
