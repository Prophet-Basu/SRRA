package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.StudentAcademicDetailsBean;
import com.util.DBSchema;
import com.util.DBUtil;

import sun.launcher.resources.launcher;

import com.util.DBSchema.Session;
import com.util.DBSchema.Stream;
import com.util.DBSchema.StudentAcademicDetails;
import com.util.DBSchema.StudentPersonalDetails;

public class StudentAcademicDetailsDaoImpl implements StudentAcademicDetailsDao {

	private Connection conn = null;
	private String db = DBSchema.DB_NAME;
	private String table = StudentAcademicDetails.TABLE_NAME;

	private boolean createTable(int strID,String sesID) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				Statement st = conn.createStatement();
				String query = "create table " + StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+ " ("
						+ StudentAcademicDetails.Columns.ROLL + " bigint primary key,"
						+ StudentAcademicDetails.Columns.CLS10SCHOOLNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS10SCHOOLMEDIUM + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS10BOARDNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS10EXAMNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS10AVGMARKS + " float(5,2),"
						+ StudentAcademicDetails.Columns.CLS10BESTMARKS + " float(5,2),"
						+ StudentAcademicDetails.Columns.CLS10YOP + " int,"
						+ StudentAcademicDetails.Columns.CLS12SCHOOLNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS12SCHOOLMEDIUM + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS12BOARDNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS12EXAMNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.CLS12AVGMARKS + " float(5,2),"
						+ StudentAcademicDetails.Columns.CLS12BESTMARKS + " float(5,2),"
						+ StudentAcademicDetails.Columns.CLS12YOP + " int,"
						+ StudentAcademicDetails.Columns.DIPLOMABOARDNAME + " varchar(100),"
						+ StudentAcademicDetails.Columns.DIPLOMASTREAM + " varchar(100),"
						+ StudentAcademicDetails.Columns.DIPLOMAMARKS + " float(5,2),"
						+ StudentAcademicDetails.Columns.DIPLOMAYOP + " int,"
						+ StudentAcademicDetails.Columns.BTECHSELECTIONRANK + " int,"
						+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM + " varchar(100),"
						+ StudentAcademicDetails.Columns.COLLEGE + " varchar(100),"
						+ StudentAcademicDetails.Columns.UNIVERSITY + " varchar(100),"
						+ StudentAcademicDetails.Columns.PRESENTSEM + " int," + StudentAcademicDetails.Columns.SEM1
						+ " float(5,2)," + StudentAcademicDetails.Columns.SEM2 + " float(5,2),"
						+ StudentAcademicDetails.Columns.SEM3 + " float(5,2)," + StudentAcademicDetails.Columns.SEM4
						+ " float(5,2)," + StudentAcademicDetails.Columns.SEM5 + " float(5,2),"
						+ StudentAcademicDetails.Columns.SEM6 + " float(5,2)," + StudentAcademicDetails.Columns.SEM7
						+ " float(5,2)," + StudentAcademicDetails.Columns.SEM8 + " float(5,2),"
						+ StudentAcademicDetails.Columns.YGPA1 + " float(5,2),"
						+ StudentAcademicDetails.Columns.YGPA2 + " float(5,2),"
						+ StudentAcademicDetails.Columns.YGPA3 + " float(5,2),"
						+ StudentAcademicDetails.Columns.YGPA4 + " float(5,2),"
						+ StudentAcademicDetails.Columns.DGPA + " float(5,2),"
						+ StudentAcademicDetails.Columns.HASBACKLOG + " varchar(100),"
						+ StudentAcademicDetails.Columns.BACKLOG1 + " varchar(100),"
						+ StudentAcademicDetails.Columns.BACKLOG2 + " varchar(100),"
						+ StudentAcademicDetails.Columns.BACKLOG3 + " varchar(100),"
						+ StudentAcademicDetails.Columns.HASYEARGAP + " varchar(100),"
						+ StudentAcademicDetails.Columns.YEARGAPDURATION + " varchar(100),"
						+ StudentAcademicDetails.Columns.YEARGAPPERIOD + " varchar(100),"
						+ StudentAcademicDetails.Columns.YEARGAPCAUSE + " varchar(100),"
						+ StudentAcademicDetails.Columns.ACHIEVEMENT + " varchar(100),"
						+ StudentAcademicDetails.Columns.SESSECNAME + " nvarchar(40))";

				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: " + StudentAcademicDetails.TABLE_NAME +String.format("%02d", strID) +sesName);

				if (findStudentAcademicTable(strID,sesID))
					flag = true;

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Student Academic Table Not Created" + e.getMessage());
			}

		}
		return flag;
	}

	@Override
	public boolean addStudentAcademicDetails(int strID,String sesID,long roll) {

		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
					
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				if (!findStudentAcademicTable(strID,sesID))
					createTable(strID,sesID);

				PreparedStatement ps = conn.prepareStatement("insert into " + table + String.format("%02d", strID) +sesName+ "("
						+ StudentAcademicDetails.Columns.ROLL + "," + StudentAcademicDetails.Columns.CLS10SCHOOLNAME
						+ "," + StudentAcademicDetails.Columns.CLS10SCHOOLMEDIUM + ","
						+ StudentAcademicDetails.Columns.CLS10BOARDNAME + ","
						+ StudentAcademicDetails.Columns.CLS10EXAMNAME + ","
						+ StudentAcademicDetails.Columns.CLS10AVGMARKS + ","
						+ StudentAcademicDetails.Columns.CLS10BESTMARKS + "," + StudentAcademicDetails.Columns.CLS10YOP
						+ "," + StudentAcademicDetails.Columns.CLS12SCHOOLNAME + ","
						+ StudentAcademicDetails.Columns.CLS12SCHOOLMEDIUM + ","
						+ StudentAcademicDetails.Columns.CLS12BOARDNAME + ","
						+ StudentAcademicDetails.Columns.CLS12EXAMNAME + ","
						+ StudentAcademicDetails.Columns.CLS12AVGMARKS + ","
						+ StudentAcademicDetails.Columns.CLS12BESTMARKS + "," + StudentAcademicDetails.Columns.CLS12YOP
						+ "," + StudentAcademicDetails.Columns.DIPLOMABOARDNAME + ","
						+ StudentAcademicDetails.Columns.DIPLOMASTREAM + ","
						+ StudentAcademicDetails.Columns.DIPLOMAMARKS + "," + StudentAcademicDetails.Columns.DIPLOMAYOP
						+ "," + StudentAcademicDetails.Columns.BTECHSELECTIONRANK + ","
						+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM + ","
						+ StudentAcademicDetails.Columns.COLLEGE + "," + StudentAcademicDetails.Columns.UNIVERSITY + ","
						+ StudentAcademicDetails.Columns.PRESENTSEM + "," + StudentAcademicDetails.Columns.SEM1 + ","
						+ StudentAcademicDetails.Columns.SEM2 + "," + StudentAcademicDetails.Columns.SEM3 + ","
						+ StudentAcademicDetails.Columns.SEM4 + "," + StudentAcademicDetails.Columns.SEM5 + ","
						+ StudentAcademicDetails.Columns.SEM6 + "," + StudentAcademicDetails.Columns.SEM7 + ","
						+ StudentAcademicDetails.Columns.SEM8 + "," 
						+ StudentAcademicDetails.Columns.YGPA1 + ","
						+ StudentAcademicDetails.Columns.YGPA2 + ","
						+ StudentAcademicDetails.Columns.YGPA3 + ","
						+ StudentAcademicDetails.Columns.YGPA4 + ","
						+ StudentAcademicDetails.Columns.DGPA + ","
						+ StudentAcademicDetails.Columns.HASBACKLOG + ","
						+ StudentAcademicDetails.Columns.BACKLOG1 + "," + StudentAcademicDetails.Columns.BACKLOG2 + ","
						+ StudentAcademicDetails.Columns.BACKLOG3 + "," + StudentAcademicDetails.Columns.HASYEARGAP
						+ "," + StudentAcademicDetails.Columns.YEARGAPDURATION + ","
						+ StudentAcademicDetails.Columns.YEARGAPPERIOD + ","
						+ StudentAcademicDetails.Columns.YEARGAPCAUSE + "," + StudentAcademicDetails.Columns.ACHIEVEMENT
						+ ") "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps.setLong(1, roll);
				ps.setString(2, null);
				ps.setString(3, null);
				ps.setString(4, null);
				ps.setString(5, null);
				ps.setFloat(6, 0);
				ps.setFloat(7, 0);
				ps.setInt(8, 0);
				ps.setString(9, null);
				ps.setString(10, null);
				ps.setString(11, null);
				ps.setString(12, null);
				ps.setFloat(13, 0);
				ps.setFloat(14, 0);
				ps.setInt(15, 0);
				ps.setString(16, null);
				ps.setString(17, null);
				ps.setFloat(18, 0);
				ps.setInt(19, 0);
				ps.setInt(20, 0);
				ps.setString(21, null);
				ps.setString(22, null);
				ps.setString(23, null);
				ps.setInt(24, 0);
				ps.setFloat(25, 0.0f);
				ps.setFloat(26, 0.0f);
				ps.setFloat(27, 0.0f);
				ps.setFloat(28, 0.0f);
				ps.setFloat(29, 0.0f);
				ps.setFloat(30, 0.0f);
				ps.setFloat(31, 0.0f);
				ps.setFloat(32, 0.0f);
				ps.setFloat(33, 0.0f);
				ps.setFloat(34, 0.0f);
				ps.setFloat(35, 0.0f);
				ps.setFloat(36, 0.0f);
				ps.setFloat(37, 0.0f);
				ps.setString(38, null);
				ps.setString(39, null);
				ps.setString(40, null);
				ps.setString(41, null);
				ps.setString(42, null);
				ps.setString(43, null);
				ps.setString(44, null);
				ps.setString(45, null);
				ps.setString(46, null);
				

				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Addition" + e.getMessage());
			}

		}
		return flag;

	}
	
	@Override
	public boolean findStudentByRoll(int strID,String sesID,long roll)
	{
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("select * from "+table+ String.format("%02d", strID) +sesName+" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				ps.setLong(1, roll);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					flag=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	@Override
	public boolean insertStudentAcademicDetails(int strID,String sesID,StudentAcademicDetailsBean sabean)
	{
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if (conn != null) {
			try {
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				if (!findStudentAcademicTable(strID,sesID))
					createTable(strID,sesID);

				PreparedStatement ps = conn.prepareStatement("insert into " + table + String.format("%02d", strID) +sesName+ "("
						+ StudentAcademicDetails.Columns.ROLL + "," + StudentAcademicDetails.Columns.CLS10SCHOOLNAME
						+ "," + StudentAcademicDetails.Columns.CLS10SCHOOLMEDIUM + ","
						+ StudentAcademicDetails.Columns.CLS10BOARDNAME + ","
						+ StudentAcademicDetails.Columns.CLS10EXAMNAME + ","
						+ StudentAcademicDetails.Columns.CLS10AVGMARKS + ","
						+ StudentAcademicDetails.Columns.CLS10BESTMARKS + "," + StudentAcademicDetails.Columns.CLS10YOP
						+ "," + StudentAcademicDetails.Columns.CLS12SCHOOLNAME + ","
						+ StudentAcademicDetails.Columns.CLS12SCHOOLMEDIUM + ","
						+ StudentAcademicDetails.Columns.CLS12BOARDNAME + ","
						+ StudentAcademicDetails.Columns.CLS12EXAMNAME + ","
						+ StudentAcademicDetails.Columns.CLS12AVGMARKS + ","
						+ StudentAcademicDetails.Columns.CLS12BESTMARKS + "," + StudentAcademicDetails.Columns.CLS12YOP
						+ "," + StudentAcademicDetails.Columns.DIPLOMABOARDNAME + ","
						+ StudentAcademicDetails.Columns.DIPLOMASTREAM + ","
						+ StudentAcademicDetails.Columns.DIPLOMAMARKS + "," + StudentAcademicDetails.Columns.DIPLOMAYOP
						+ "," + StudentAcademicDetails.Columns.BTECHSELECTIONRANK + ","
						+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM + ","
						+ StudentAcademicDetails.Columns.COLLEGE + "," + StudentAcademicDetails.Columns.UNIVERSITY + ","
						+ StudentAcademicDetails.Columns.PRESENTSEM + "," 
						+ StudentAcademicDetails.Columns.HASBACKLOG + ","
						+ StudentAcademicDetails.Columns.BACKLOG1 + "," + StudentAcademicDetails.Columns.BACKLOG2 + ","
						+ StudentAcademicDetails.Columns.BACKLOG3 + "," + StudentAcademicDetails.Columns.HASYEARGAP
						+ "," + StudentAcademicDetails.Columns.YEARGAPDURATION + ","
						+ StudentAcademicDetails.Columns.YEARGAPPERIOD + ","
						+ StudentAcademicDetails.Columns.YEARGAPCAUSE + "," + StudentAcademicDetails.Columns.ACHIEVEMENT
						+ "," 
						 + StudentAcademicDetails.Columns.SESSECNAME +") "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps.setLong(1, sabean.getStudRoll());
				ps.setString(2, sabean.getStudCls10SchoolName());
				ps.setString(3, sabean.getStudCls10SchoolMedium());
				ps.setString(4, sabean.getStudCls10BoardName());
				ps.setString(5, sabean.getStudCls10ExamName());
				ps.setFloat(6, sabean.getStudCls10AvgMarks());
				ps.setFloat(7, sabean.getStudCls10BestMarks());
				ps.setInt(8, sabean.getStudCls10YOP());
				ps.setString(9, sabean.getStudCls12SchoolName());
				ps.setString(10, sabean.getStudCls12SchoolMedium());
				ps.setString(11, sabean.getStudCls12BoardName());
				ps.setString(12, sabean.getStudCls12ExamName());
				ps.setFloat(13, sabean.getStudCls12AvgMarks());
				ps.setFloat(14, sabean.getStudCls12BestMarks());
				ps.setInt(15, sabean.getStudCls12YOP());
				ps.setString(16, sabean.getStudDiplomaBoardName());
				ps.setString(17, sabean.getStudDiplomaStream());
				ps.setFloat(18, sabean.getStudDiplomaMarks());
				ps.setInt(19, sabean.getStudDiplomaYOP());
				ps.setInt(20, sabean.getStudBTechSelectionRank());
				ps.setString(21, sabean.getStudBTechSelectionExam());
				ps.setString(22, sabean.getStudCollege());
				ps.setString(23, sabean.getStudUniversity());
				ps.setInt(24, sabean.getStudPresentSem());
				
				ps.setString(25, sabean.getStudHasBacklog());
				ps.setString(26, sabean.getStudBacklog1());
				ps.setString(27, sabean.getStudBacklog2());
				ps.setString(28, sabean.getStudBacklog3());
				ps.setString(29, sabean.getStudHasYearGap());
				ps.setString(30, sabean.getStudYearGapDuration());
				ps.setString(31, sabean.getStudYearGapPeriod());
				ps.setString(32, sabean.getStudYearGapCause());
				ps.setString(33, sabean.getStudAchievement());
				
				ps.setString(34, sabean.getSesSecName());

				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Addition" + e.getMessage());
			}

		}
		return flag;
	}
	
	@Override
	public boolean updateStudentAcademicDetails(int strID,String sesID,StudentAcademicDetailsBean sabean)
	{
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if (conn != null) {
			try {
					
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) +sesName+ " set "
						+ StudentAcademicDetails.Columns.CLS10SCHOOLNAME
						+ "=?," + StudentAcademicDetails.Columns.CLS10SCHOOLMEDIUM + "=?,"
						+ StudentAcademicDetails.Columns.CLS10BOARDNAME + "=?,"
						+ StudentAcademicDetails.Columns.CLS10EXAMNAME + "=?,"
						+ StudentAcademicDetails.Columns.CLS10AVGMARKS + "=?,"
						+ StudentAcademicDetails.Columns.CLS10BESTMARKS + "=?," + StudentAcademicDetails.Columns.CLS10YOP
						+ "=?," + StudentAcademicDetails.Columns.CLS12SCHOOLNAME + "=?,"
						+ StudentAcademicDetails.Columns.CLS12SCHOOLMEDIUM + "=?,"
						+ StudentAcademicDetails.Columns.CLS12BOARDNAME + "=?,"
						+ StudentAcademicDetails.Columns.CLS12EXAMNAME + "=?,"
						+ StudentAcademicDetails.Columns.CLS12AVGMARKS + "=?,"
						+ StudentAcademicDetails.Columns.CLS12BESTMARKS + "=?," + StudentAcademicDetails.Columns.CLS12YOP
						+ "=?," + StudentAcademicDetails.Columns.DIPLOMABOARDNAME + "=?,"
						+ StudentAcademicDetails.Columns.DIPLOMASTREAM + "=?,"
						+ StudentAcademicDetails.Columns.DIPLOMAMARKS + "=?," + StudentAcademicDetails.Columns.DIPLOMAYOP
						+ "=?," + StudentAcademicDetails.Columns.BTECHSELECTIONRANK + "=?,"
						+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM + "=?,"
						+ StudentAcademicDetails.Columns.COLLEGE + "=?," + StudentAcademicDetails.Columns.UNIVERSITY + "=?,"
						+ StudentAcademicDetails.Columns.PRESENTSEM + "=?," 
						+ StudentAcademicDetails.Columns.HASBACKLOG + "=?,"
						+ StudentAcademicDetails.Columns.BACKLOG1 + "=?," + StudentAcademicDetails.Columns.BACKLOG2 + "=?,"
						+ StudentAcademicDetails.Columns.BACKLOG3 + "=?," + StudentAcademicDetails.Columns.HASYEARGAP
						+ "=?," + StudentAcademicDetails.Columns.YEARGAPDURATION + "=?,"
						+ StudentAcademicDetails.Columns.YEARGAPPERIOD + "=?,"
						+ StudentAcademicDetails.Columns.YEARGAPCAUSE + "=?," + StudentAcademicDetails.Columns.ACHIEVEMENT
						+ "=?,"
						+StudentAcademicDetails.Columns.SESSECNAME + "=?"
						+" where "
						+ StudentAcademicDetails.Columns.ROLL + "=?");
						
				
				ps.setString(1, sabean.getStudCls10SchoolName());
				ps.setString(2, sabean.getStudCls10SchoolMedium());
				ps.setString(3, sabean.getStudCls10BoardName());
				ps.setString(4, sabean.getStudCls10ExamName());
				ps.setFloat(5, sabean.getStudCls10AvgMarks());
				ps.setFloat(6, sabean.getStudCls10BestMarks());
				ps.setInt(7, sabean.getStudCls10YOP());
				ps.setString(8, sabean.getStudCls12SchoolName());
				ps.setString(9, sabean.getStudCls12SchoolMedium());
				ps.setString(10, sabean.getStudCls12BoardName());
				ps.setString(11, sabean.getStudCls12ExamName());
				ps.setFloat(12, sabean.getStudCls12AvgMarks());
				ps.setFloat(13, sabean.getStudCls12BestMarks());
				ps.setInt(14, sabean.getStudCls12YOP());
				ps.setString(15, sabean.getStudDiplomaBoardName());
				ps.setString(16, sabean.getStudDiplomaStream());
				ps.setFloat(17, sabean.getStudDiplomaMarks());
				ps.setInt(18, sabean.getStudDiplomaYOP());
				ps.setInt(19, sabean.getStudBTechSelectionRank());
				ps.setString(20, sabean.getStudBTechSelectionExam());
				ps.setString(21, sabean.getStudCollege());
				ps.setString(22, sabean.getStudUniversity());
				ps.setInt(23, sabean.getStudPresentSem());
				
				ps.setString(24, sabean.getStudHasBacklog());
				ps.setString(25, sabean.getStudBacklog1());
				ps.setString(26, sabean.getStudBacklog2());
				ps.setString(27, sabean.getStudBacklog3());
				ps.setString(28, sabean.getStudHasYearGap());
				ps.setString(29, sabean.getStudYearGapDuration());
				ps.setString(30, sabean.getStudYearGapPeriod());
				ps.setString(31, sabean.getStudYearGapCause());
				ps.setString(32, sabean.getStudAchievement());
				
				ps.setString(33, sabean.getSesSecName());
				ps.setLong(34, sabean.getStudRoll());
				
				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Updation" + e.getMessage());
			}

		}
		return flag;
	}

	@Override
	public String fetchStudentSection(int strID,String sesID,long roll) {
		String secName=null;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				
				PreparedStatement ps=conn.prepareStatement("SELECT "+StudentAcademicDetails.Columns.SESSECNAME +" FROM "+ StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+" where "+ StudentAcademicDetails.Columns.ROLL +"=?");
				ps.setLong(1, roll);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					secName=rs.getString(1);
			
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Academic Find" + e.getMessage());
			}
		}
		return secName;
	}

	@Override
	public boolean findStudentAcademicTable(int strID,String sesID) {
		boolean flag = false;
		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn
						.prepareStatement("SHOW TABLES LIKE '" + StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+ "'");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Academic Find" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean updateEducationDetails(int strID,String sesID,long roll, String cls10SchoolName, String cls10SchoolMedium,
			String cls10BoardName, String cls10Examname, float cls10AvgMarks, float cls10Bestmarks, int cls10yop,
			String cls12SchoolName, String cls12SchoolMedium, String cls12BoardName, String cls12Examname,
			float cls12AvgMarks, float cls12Bestmarks, int cls12yop, String diplBoardName, String diplStream,
			float diplMarks, int diplYOP) {

		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				if (!findStudentAcademicTable(strID,sesID)) {
					createTable(strID,sesID);
				}

				boolean check = addStudentAcademicDetails(strID,sesID,roll);

				if (check) {

					PreparedStatement ps = conn.prepareStatement(
							"update " + table + StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+ " set " + StudentAcademicDetails.Columns.CLS10SCHOOLNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10SCHOOLMEDIUM + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10BOARDNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10EXAMNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10AVGMARKS + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10BESTMARKS + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS10YOP + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12SCHOOLNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12SCHOOLMEDIUM + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12BOARDNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12EXAMNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12AVGMARKS + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12BESTMARKS + "=?" + ","
									+ StudentAcademicDetails.Columns.CLS12YOP + "=?" + ","
									+ StudentAcademicDetails.Columns.DIPLOMABOARDNAME + "=?" + ","
									+ StudentAcademicDetails.Columns.DIPLOMASTREAM + "=?" + ","
									+ StudentAcademicDetails.Columns.DIPLOMAMARKS + "=?" + ","
									+ StudentAcademicDetails.Columns.DIPLOMAYOP + "=?" + " where "
									+ StudentAcademicDetails.Columns.ROLL + "=?");

					ps.setString(1, cls10SchoolName);
					ps.setString(2, cls10SchoolMedium);
					ps.setString(3, cls10BoardName);
					ps.setString(4, cls10Examname);
					ps.setFloat(5, cls10AvgMarks);
					ps.setFloat(6, cls10Bestmarks);
					ps.setInt(7, cls10yop);
					ps.setString(8, cls12SchoolName);
					ps.setString(9, cls12SchoolMedium);
					ps.setString(10, cls12BoardName);
					ps.setString(11, cls12Examname);
					ps.setFloat(12, cls12AvgMarks);
					ps.setFloat(13, cls12Bestmarks);
					ps.setInt(14, cls12yop);
					ps.setString(15, diplBoardName);
					ps.setString(16, diplStream);
					ps.setFloat(17, diplMarks);
					ps.setInt(18, diplYOP);
					ps.setLong(19, roll);

					if (ps.executeUpdate() > 0)
						flag = true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! General info cannot be updated" + e.getMessage());
			}
		}
		return flag;

	}

	@Override
	public boolean updateBTechDetails(long roll, int selectionRank, String selectionExam, String college,
			String university, int presentSem, float sem1, float sem2, float sem3, float sem4, float sem5, float sem6,
			float sem7, float sem8, String hasBackLog, String backLog1, String backLog2, String backLog3,
			String hasYearGap, String yearGapDuration, String yearGapPeriod, String yearGapCause, String achievement,
			int strID, String sesID) {

		boolean flag = false;

		conn = DBUtil.getConnection(db);
		if (conn != null) {
			try {
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("update " + table + StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+ " set "
						+ StudentAcademicDetails.Columns.BTECHSELECTIONRANK + "=?" + ","
						+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM + "=?" + ","
						+ StudentAcademicDetails.Columns.COLLEGE + "=?" + ","
						+ StudentAcademicDetails.Columns.UNIVERSITY + "=?" + ","
						+ StudentAcademicDetails.Columns.PRESENTSEM + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM1 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM2 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM3 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM4 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM5 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM6 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM7 + "=?" + ","
						+ StudentAcademicDetails.Columns.SEM8 + "=?" + ","
						+ StudentAcademicDetails.Columns.HASBACKLOG + "=?" + ","
						+ StudentAcademicDetails.Columns.BACKLOG1 + "=?" + "," + StudentAcademicDetails.Columns.BACKLOG2
						+ "=?" + "," + StudentAcademicDetails.Columns.BACKLOG3 + "=?" + ","
						+ StudentAcademicDetails.Columns.HASYEARGAP + "=?" + ","
						+ StudentAcademicDetails.Columns.YEARGAPDURATION + "=?" + ","
						+ StudentAcademicDetails.Columns.YEARGAPPERIOD + "=?" + ","
						+ StudentAcademicDetails.Columns.YEARGAPCAUSE + "=?" + ","
						+ StudentAcademicDetails.Columns.ACHIEVEMENT + "=?"+" where "
						+ StudentAcademicDetails.Columns.ROLL + "=?");

				ps.setInt(1, selectionRank);
				ps.setString(2, selectionExam);
				ps.setString(3, college);
				ps.setString(4, university);
				ps.setInt(5, presentSem);
				ps.setFloat(6, sem1);
				ps.setFloat(7, sem2);
				ps.setFloat(8, sem3);
				ps.setFloat(9, sem4);
				ps.setFloat(10, sem5);
				ps.setFloat(11, sem6);
				ps.setFloat(12, sem7);
				ps.setFloat(13, sem8);
				ps.setString(14, hasBackLog);
				ps.setString(15, backLog1);
				ps.setString(16, backLog2);
				ps.setString(17, backLog3);
				ps.setString(18, hasYearGap);
				ps.setString(19, yearGapDuration);
				ps.setString(20, yearGapPeriod);
				ps.setString(21, yearGapCause);
				ps.setString(22, achievement);
				
				ps.setLong(23, roll);

				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! BTech info cannot be updated" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean updateSemDetails(long roll, int strID, String sesID, String sesSecName, String attrName,
			float attrValue) {
		boolean flag = false;

		conn = DBUtil.getConnection(db);
		
		if (conn != null) {
			try {
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("update " + table + String.format("%02d", strID) +sesName+ " set "
						+ attrName + "=?"
						+ " where "
						+ StudentAcademicDetails.Columns.ROLL + "=? and "
						+ StudentAcademicDetails.Columns.SESSECNAME+ "=?");
				
				//System.out.println(roll);
				//System.out.println(strID+ " "+sesID+" "+sesSecName);
				
				
				ps.setFloat(1, attrValue);
				ps.setLong(2, roll);
				
				ps.setString(3, sesSecName);
				

				if (ps.executeUpdate() > 0)
					flag = true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! BTech info cannot be updated" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public StudentAcademicDetailsBean fetchStudent(int strID,String sesID,long roll) {
		StudentAcademicDetailsBean student=null;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("SELECT *"+" FROM "+ StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+" where "+ StudentAcademicDetails.Columns.ROLL +"=?");
				ps.setLong(1, roll);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
				
				long studRoll=rs.getLong(1);
				String studCls10SchoolName=rs.getString(2);
				String studCls10SchoolMedium=rs.getString(3);
				String studCls10BoardName=rs.getString(4);
				String studCls10ExamName=rs.getString(5);
				float studCls10AvgMarks=rs.getFloat(6);
				float studCls10BestMarks=rs.getFloat(7);
				int studCls10YOP=rs.getInt(8);
				String studCls12SchoolName=rs.getString(9);
				String studCls12SchoolMedium=rs.getString(10);
				String studCls12BoardName=rs.getString(11);
				String studCls12ExamName=rs.getString(12);
				float studCls12AvgMarks=rs.getFloat(13);
				float studCls12BestMarks=rs.getFloat(14);
				int studCls12YOP=rs.getInt(15);
				String studDiplomaBoardName=rs.getString(16);
				String studDiplomaStream=rs.getString(17);
				float studDiplomaMarks=rs.getFloat(18);
				int studDiplomaYOP=rs.getInt(19);
				int studBTechSelectionRank=rs.getInt(20);
				String studBTechSelectionExam=rs.getString(21);
				String studCollege=rs.getString(22);
				String studUniversity=rs.getString(23);
				int studPresentSem=rs.getInt(24);
				float studSem1SGPA=rs.getFloat(25);
				float studSem2SGPA=rs.getFloat(26);
				float studSem3SGPA=rs.getFloat(27);
				float studSem4SGPA=rs.getFloat(28);
				float studSem5SGPA=rs.getFloat(29);
				float studSem6SGPA=rs.getFloat(30);
				float studSem7SGPA=rs.getFloat(31);
				float studSem8SGPA=rs.getFloat(32);
				float studYGPA1=rs.getFloat(33);
				float studYGPA2=rs.getFloat(34);
				float studYGPA3=rs.getFloat(35);
				float studYGPA4=rs.getFloat(36);
				float studDGPA=rs.getFloat(37);
				String studHasBacklog=rs.getString(38);
				String studBacklog1=rs.getString(39);
				String studBacklog2=rs.getString(40);
				String studBacklog3=rs.getString(41);
				String studHasYearGap=rs.getString(42);
				String studYearGapDuration=rs.getString(43);
				String studYearGapPeriod=rs.getString(44);
				String studYearGapCause=rs.getString(45);
				String studAchievement=rs.getString(46);
				
				String sesSecName=rs.getString(47);
				
				student=new StudentAcademicDetailsBean(studRoll, studCls10SchoolName, studCls10SchoolMedium, studCls10BoardName, studCls10ExamName, studCls10AvgMarks, studCls10BestMarks, studCls10YOP, studCls12SchoolName, studCls12SchoolMedium, studCls12BoardName, studCls12ExamName, studCls12AvgMarks, studCls12BestMarks, studCls12YOP, studDiplomaBoardName, studDiplomaStream, studDiplomaMarks, studDiplomaYOP, studBTechSelectionRank, studBTechSelectionExam, studCollege, studUniversity, studPresentSem, studSem1SGPA, studSem2SGPA, studSem3SGPA, studSem4SGPA, studSem5SGPA, studSem6SGPA, studSem7SGPA, studSem8SGPA, studYGPA1, studYGPA2, studYGPA3, studYGPA4, studDGPA, studHasBacklog, studBacklog1, studBacklog2, studBacklog3, studHasYearGap, studYearGapDuration, studYearGapPeriod, studYearGapCause, studAchievement,sesSecName);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Academic Find" + e.getMessage());
			}
		}
		return student;
	}

	@Override
	public ArrayList<StudentAcademicDetailsBean> fetchDiplomaStudents(int strID, String sesID) {
		ArrayList<StudentAcademicDetailsBean> academic=new ArrayList<StudentAcademicDetailsBean>();
		conn=DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				
				PreparedStatement ps=conn.prepareStatement("SELECT *"+" FROM "+ StudentAcademicDetails.TABLE_NAME + String.format("%02d", strID) +sesName+
						" where "+ StudentAcademicDetails.Columns.BTECHSELECTIONEXAM +"=?");
				ps.setString(1,"JELET");
				
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
				
				long studRoll=rs.getLong(1);
				String studCls10SchoolName=rs.getString(2);
				String studCls10SchoolMedium=rs.getString(3);
				String studCls10BoardName=rs.getString(4);
				String studCls10ExamName=rs.getString(5);
				float studCls10AvgMarks=rs.getFloat(6);
				float studCls10BestMarks=rs.getFloat(7);
				int studCls10YOP=rs.getInt(8);
				String studCls12SchoolName=rs.getString(9);
				String studCls12SchoolMedium=rs.getString(10);
				String studCls12BoardName=rs.getString(11);
				String studCls12ExamName=rs.getString(12);
				float studCls12AvgMarks=rs.getFloat(13);
				float studCls12BestMarks=rs.getFloat(14);
				int studCls12YOP=rs.getInt(15);
				String studDiplomaBoardName=rs.getString(16);
				String studDiplomaStream=rs.getString(17);
				float studDiplomaMarks=rs.getFloat(18);
				int studDiplomaYOP=rs.getInt(19);
				int studBTechSelectionRank=rs.getInt(20);
				String studBTechSelectionExam=rs.getString(21);
				String studCollege=rs.getString(22);
				String studUniversity=rs.getString(23);
				int studPresentSem=rs.getInt(24);
				float studSem1SGPA=rs.getFloat(25);
				float studSem2SGPA=rs.getFloat(26);
				float studSem3SGPA=rs.getFloat(27);
				float studSem4SGPA=rs.getFloat(28);
				float studSem5SGPA=rs.getFloat(29);
				float studSem6SGPA=rs.getFloat(30);
				float studSem7SGPA=rs.getFloat(31);
				float studSem8SGPA=rs.getFloat(32);
				float studYGPA1=rs.getFloat(33);
				float studYGPA2=rs.getFloat(34);
				float studYGPA3=rs.getFloat(35);
				float studYGPA4=rs.getFloat(36);
				float studDGPA=rs.getFloat(37);
				String studHasBacklog=rs.getString(38);
				String studBacklog1=rs.getString(39);
				String studBacklog2=rs.getString(40);
				String studBacklog3=rs.getString(41);
				String studHasYearGap=rs.getString(42);
				String studYearGapDuration=rs.getString(43);
				String studYearGapPeriod=rs.getString(44);
				String studYearGapCause=rs.getString(45);
				String studAchievement=rs.getString(46);
				
				String sesSecName=rs.getString(47);
				
				StudentAcademicDetailsBean student=new StudentAcademicDetailsBean(studRoll, studCls10SchoolName, studCls10SchoolMedium, studCls10BoardName, studCls10ExamName, studCls10AvgMarks, studCls10BestMarks, studCls10YOP, studCls12SchoolName, studCls12SchoolMedium, studCls12BoardName, studCls12ExamName, studCls12AvgMarks, studCls12BestMarks, studCls12YOP, studDiplomaBoardName, studDiplomaStream, studDiplomaMarks, studDiplomaYOP, studBTechSelectionRank, studBTechSelectionExam, studCollege, studUniversity, studPresentSem, studSem1SGPA, studSem2SGPA, studSem3SGPA, studSem4SGPA, studSem5SGPA, studSem6SGPA, studSem7SGPA, studSem8SGPA, studYGPA1, studYGPA2, studYGPA3, studYGPA4, studDGPA, studHasBacklog, studBacklog1, studBacklog2, studBacklog3, studHasYearGap, studYearGapDuration, studYearGapPeriod, studYearGapCause, studAchievement,sesSecName);
				//System.out.println(student.getStudRoll()+"Inside Student Dao");
				academic.add(student);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Academic Find" + e.getMessage());
			}
		}
		return academic;
	}
	
	
	@Override
	public ArrayList<Long> fetchStudentRoll(int strID, String sesID, String sesSecName) {
		ArrayList<Long> rollList=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null)
		{
			try {
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("select "+StudentAcademicDetails.Columns.ROLL+" from "+table+ String.format("%02d", strID) +sesName+" where "
					+ StudentAcademicDetails.Columns.SESSECNAME+"=?");
				
				
				ps.setString(1, sesSecName);
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
					long studRoll=rs.getLong(1);
					rollList.add(studRoll);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!");
				e.printStackTrace();
			}
		}
		return rollList;
	}
	
	@Override
	public ArrayList<String> getAllParameterList(int strID, String sesID) {
		ArrayList<String> paramList=new ArrayList<>();
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try {
				SessionDao sdao=new SessionDaoImpl();
				String sesName=sdao.createSessionName(sesID);
				PreparedStatement ps=conn.prepareStatement("SELECT COLUMN_NAME "+
						"FROM INFORMATION_SCHEMA.COLUMNS WHERE  TABLE_NAME = '"+table+String.format("%02d", strID)+sesName+"' ");
				
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
