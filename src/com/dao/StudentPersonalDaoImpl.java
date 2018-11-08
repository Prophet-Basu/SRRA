package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.StreamBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.SubjectBean;
import com.util.DBSchema;
import com.util.DBSchema.Stream;
import com.util.DBSchema.StudentPersonalDetails;
import com.util.DBSchema.Subject;
import com.util.DBSchema.Teacher;
import com.util.DBUtil;

public class StudentPersonalDaoImpl implements StudentPersonalDao{

	private Connection conn=null;
	private String db=DBSchema.DB_NAME;
	private String table=StudentPersonalDetails.TABLE_NAME;
	
	private boolean createTable(int strID,String sesID){
		boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				Statement st = conn.createStatement();
				String query = "create table "+StudentPersonalDetails.TABLE_NAME+String.format("%02d", strID) +sesName+" ("
						+ StudentPersonalDetails.Columns.ROLL+" bigint primary key auto_increment,"
						+ StudentPersonalDetails.Columns.REG+" bigint,"
						+ StudentPersonalDetails.Columns.NAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.FNAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.LNAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESS+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESSCITY+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESSPIN+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESSPOSTOFFICE+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESSDISTRICT+" varchar(100),"
						+ StudentPersonalDetails.Columns.PRESENTADDRESSSTATE+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESS+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESSCITY+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESSPIN+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESSPOSTOFFICE+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESSDISTRICT+" varchar(100),"
						+ StudentPersonalDetails.Columns.PERMANENTADDRESSSTATE+" varchar(100),"
						+ StudentPersonalDetails.Columns.DOB+" varchar(12),"
						+ StudentPersonalDetails.Columns.GENDER+" varchar(8),"
						+ StudentPersonalDetails.Columns.BLOODGROUP+" varchar(4),"
						+ StudentPersonalDetails.Columns.LANDLINECODE+" varchar(100),"
						+ StudentPersonalDetails.Columns.LANDLINE+" varchar(10),"
						+ StudentPersonalDetails.Columns.MOB1+" bigint,"
						+ StudentPersonalDetails.Columns.MOB2+" bigint,"
						+ StudentPersonalDetails.Columns.EMAIL+" varchar(100),"
						+ StudentPersonalDetails.Columns.FATHERNAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.FATHERSERVICE+" varchar(100),"
						+ StudentPersonalDetails.Columns.MOTHERNAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.MOTHERSERVICE+" varchar(100),"
						+ StudentPersonalDetails.Columns.PARENTMOB1+" varchar(10),"
						+ StudentPersonalDetails.Columns.PARENTMOB2+" varchar(10),"
						+ StudentPersonalDetails.Columns.LOCALGUARDIANNAME+" varchar(100),"
						+ StudentPersonalDetails.Columns.LOCALGUARDIANRELATION+" varchar(100),"
						+ StudentPersonalDetails.Columns.LOCALGUARDIANSERVICE+" varchar(100),"
						+ StudentPersonalDetails.Columns.LOCALGUARDIANMOB+" bigint"
						+");";
						
						
				
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new table: "+StudentPersonalDetails.TABLE_NAME);
				
				if(findStudentTable(strID,sesID))
					flag=true;
		
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Student Table Not Created" + e.getMessage());
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
				
				
				PreparedStatement ps=conn.prepareStatement("select * from "+table+String.format("%02d", strID) +sesName+" where "+StudentPersonalDetails.Columns.ROLL+"=?");
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
	public boolean insertStudentPersonalDetails(int strID,String sesID,StudentPersonalDetailsBean spbean)
	{
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try{
				
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				if(!findStudentTable(strID,sesID))
					createTable(strID,sesID);
				
				PreparedStatement ps=conn.prepareStatement(
						"insert into " + table+ String.format("%02d", strID) +sesName+"("
						 +StudentPersonalDetails.Columns.REG+","
						 +StudentPersonalDetails.Columns.NAME+","
						 +StudentPersonalDetails.Columns.FNAME+","
						 +StudentPersonalDetails.Columns.LNAME+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESS+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSCITY+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPIN+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPOSTOFFICE+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSDISTRICT+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSSTATE+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESS+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSCITY+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPIN+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPOSTOFFICE+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSDISTRICT+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSSTATE+","
						 +StudentPersonalDetails.Columns.DOB+","
						 +StudentPersonalDetails.Columns.GENDER+","
						 +StudentPersonalDetails.Columns.BLOODGROUP+","
						 +StudentPersonalDetails.Columns.LANDLINECODE+","
						 +StudentPersonalDetails.Columns.LANDLINE+","
						 +StudentPersonalDetails.Columns.MOB1+","
						 +StudentPersonalDetails.Columns.MOB2+","
						 +StudentPersonalDetails.Columns.EMAIL+","
						 +StudentPersonalDetails.Columns.FATHERNAME+","
						 +StudentPersonalDetails.Columns.FATHERSERVICE+","
						 +StudentPersonalDetails.Columns.MOTHERNAME+","
						 +StudentPersonalDetails.Columns.MOTHERSERVICE+","
						 +StudentPersonalDetails.Columns.PARENTMOB1+","
						 +StudentPersonalDetails.Columns.PARENTMOB2+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANNAME+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANRELATION+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANSERVICE+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANMOB+","
						 +StudentPersonalDetails.Columns.ROLL+") "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ps.setLong(1,spbean.getStudReg());
				ps.setString(2,spbean.getStudName());
				ps.setString(3, spbean.getStudFName());
				ps.setString(4, spbean.getStudLName());
				ps.setString(5, spbean.getStudPresentAddress());
				ps.setString(6, spbean.getStudPresentAddressCity());
				ps.setString(7, spbean.getStudPresentAddressPin());
				ps.setString(8, spbean.getStudPresentAddressPostOffice());
				ps.setString(9, spbean.getStudPresentAddressDistrict());
				ps.setString(10, spbean.getStudPresentAddressState());
				ps.setString(11, spbean.getStudPermanentAddress());
				ps.setString(12, spbean.getStudPermanentAddressCity());
				ps.setString(13, spbean.getStudPermanentAddressPin());
				ps.setString(14, spbean.getStudPermanentAddressPostOffice());
				ps.setString(15, spbean.getStudPermanentAddressDistrict());
				ps.setString(16, spbean.getStudPermanentAddressState());
				ps.setString(17, spbean.getStudDOB());
				ps.setString(18, spbean.getStudGender());
				ps.setString(19, spbean.getStudBloodGroup());
				ps.setString(20, spbean.getStudLandlineCode());
				ps.setString(21, spbean.getStudLandline());
				ps.setLong(22,spbean.getStudMob1());
				ps.setLong(23, spbean.getStudMob2());
				ps.setString(24, spbean.getStudEmail());
				ps.setString(25, spbean.getStudFatherName());
				ps.setString(26, spbean.getStudFatherService());
				ps.setString(27, spbean.getStudMotherName());
				ps.setString(28, spbean.getStudMotherService());
				ps.setLong(29,spbean.getStudParentMob1());
				ps.setLong(30,spbean.getStudParentMob2());
				ps.setString(31, spbean.getStudLocalGuardianName());
				ps.setString(32, spbean.getStudLocalGuardianRelation());
				ps.setString(33, spbean.getStudLocalGuardianService());
				ps.setLong(34,spbean.getStudLocalGuardianMob());
				
				ps.setLong(35, spbean.getStudRoll());
				
				if(ps.executeUpdate()>0){
					flag=true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Addition" + e.getMessage());
			}

		}
		return flag;
	}
	
	@Override
	public boolean updateStudentPersonalDetails(int strID,String sesID,StudentPersonalDetailsBean spbean)
	{
		boolean flag=false;
		conn=DBUtil.getConnection(db);
		if(conn!=null){
			try{
					
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement(
						"update " + table+ String.format("%02d", strID) +sesName+" set "
						 +StudentPersonalDetails.Columns.REG+"=?,"
						 +StudentPersonalDetails.Columns.NAME+"=?,"
						 +StudentPersonalDetails.Columns.FNAME+"=?,"
						 +StudentPersonalDetails.Columns.LNAME+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESS+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESSCITY+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPIN+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPOSTOFFICE+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESSDISTRICT+"=?,"
						 +StudentPersonalDetails.Columns.PRESENTADDRESSSTATE+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESS+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSCITY+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPIN+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPOSTOFFICE+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSDISTRICT+"=?,"
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSSTATE+"=?,"
						 +StudentPersonalDetails.Columns.DOB+"=?,"
						 +StudentPersonalDetails.Columns.GENDER+"=?,"
						 +StudentPersonalDetails.Columns.BLOODGROUP+"=?,"
						 +StudentPersonalDetails.Columns.LANDLINECODE+"=?,"
						 +StudentPersonalDetails.Columns.LANDLINE+"=?,"
						 +StudentPersonalDetails.Columns.MOB1+"=?,"
						 +StudentPersonalDetails.Columns.MOB2+"=?,"
						 +StudentPersonalDetails.Columns.EMAIL+"=?,"
						 +StudentPersonalDetails.Columns.FATHERNAME+"=?,"
						 +StudentPersonalDetails.Columns.FATHERSERVICE+"=?,"
						 +StudentPersonalDetails.Columns.MOTHERNAME+"=?,"
						 +StudentPersonalDetails.Columns.MOTHERSERVICE+"=?,"
						 +StudentPersonalDetails.Columns.PARENTMOB1+"=?,"
						 +StudentPersonalDetails.Columns.PARENTMOB2+"=?,"
						 +StudentPersonalDetails.Columns.LOCALGUARDIANNAME+"=?,"
						 +StudentPersonalDetails.Columns.LOCALGUARDIANRELATION+"=?,"
						 +StudentPersonalDetails.Columns.LOCALGUARDIANSERVICE+"=?,"
						 +StudentPersonalDetails.Columns.LOCALGUARDIANMOB+"=? where "
						 +StudentPersonalDetails.Columns.ROLL+"=?");
						
				
				ps.setLong(1,spbean.getStudReg());
				ps.setString(2,spbean.getStudName());
				ps.setString(3, spbean.getStudFName());
				ps.setString(4, spbean.getStudLName());
				ps.setString(5, spbean.getStudPresentAddress());
				ps.setString(6, spbean.getStudPresentAddressCity());
				ps.setString(7, spbean.getStudPresentAddressPin());
				ps.setString(8, spbean.getStudPresentAddressPostOffice());
				ps.setString(9, spbean.getStudPresentAddressDistrict());
				ps.setString(10, spbean.getStudPresentAddressState());
				ps.setString(11, spbean.getStudPermanentAddress());
				ps.setString(12, spbean.getStudPermanentAddressCity());
				ps.setString(13, spbean.getStudPermanentAddressPin());
				ps.setString(14, spbean.getStudPermanentAddressPostOffice());
				ps.setString(15, spbean.getStudPermanentAddressDistrict());
				ps.setString(16, spbean.getStudPermanentAddressState());
				ps.setString(17, spbean.getStudDOB());
				ps.setString(18, spbean.getStudGender());
				ps.setString(19, spbean.getStudBloodGroup());
				ps.setString(20, spbean.getStudLandlineCode());
				ps.setString(21, spbean.getStudLandline());
				ps.setLong(22,spbean.getStudMob1());
				ps.setLong(23, spbean.getStudMob2());
				ps.setString(24, spbean.getStudEmail());
				ps.setString(25, spbean.getStudFatherName());
				ps.setString(26, spbean.getStudFatherService());
				ps.setString(27, spbean.getStudMotherName());
				ps.setString(28, spbean.getStudMotherService());
				ps.setLong(29,spbean.getStudParentMob1());
				ps.setLong(30,spbean.getStudParentMob2());
				ps.setString(31, spbean.getStudLocalGuardianName());
				ps.setString(32, spbean.getStudLocalGuardianRelation());
				ps.setString(33, spbean.getStudLocalGuardianService());
				ps.setLong(34,spbean.getStudLocalGuardianMob());
				
				ps.setLong(35, spbean.getStudRoll());
				
				if(ps.executeUpdate()>0){
					flag=true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Updation" + e.getMessage());
			}

		}
		return flag;
	}

	@Override
	public long addStudent(int strID,String sesID) {
		long id=0;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				
				if(!findStudentTable(strID,sesID))
					createTable(strID,sesID);
				
				PreparedStatement ps=conn.prepareStatement(
						"insert into " + table+"("+
						 StudentPersonalDetails.Columns.REG+","
						 +StudentPersonalDetails.Columns.NAME+","
						 +StudentPersonalDetails.Columns.FNAME+","
						 +StudentPersonalDetails.Columns.LNAME+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESS+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSCITY+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPIN+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSPOSTOFFICE+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSDISTRICT+","
						 +StudentPersonalDetails.Columns.PRESENTADDRESSSTATE+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESS+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSCITY+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPIN+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSPOSTOFFICE+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSDISTRICT+","
						 +StudentPersonalDetails.Columns.PERMANENTADDRESSSTATE+","
						 +StudentPersonalDetails.Columns.DOB+","
						 +StudentPersonalDetails.Columns.GENDER+","
						 +StudentPersonalDetails.Columns.BLOODGROUP+","
						 +StudentPersonalDetails.Columns.LANDLINECODE+","
						 +StudentPersonalDetails.Columns.LANDLINE+","
						 +StudentPersonalDetails.Columns.MOB1+","
						 +StudentPersonalDetails.Columns.MOB2+","
						 +StudentPersonalDetails.Columns.EMAIL+","
						 +StudentPersonalDetails.Columns.FATHERNAME+","
						 +StudentPersonalDetails.Columns.FATHERSERVICE+","
						 +StudentPersonalDetails.Columns.MOTHERNAME+","
						 +StudentPersonalDetails.Columns.MOTHERSERVICE+","
						 +StudentPersonalDetails.Columns.PARENTMOB1+","
						 +StudentPersonalDetails.Columns.PARENTMOB2+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANNAME+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANRELATION+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANSERVICE+","
						 +StudentPersonalDetails.Columns.LOCALGUARDIANMOB+
						 ") "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
				
				ps.setLong(1,0);
				ps.setString(2,null);
				ps.setString(3, null);
				ps.setString(4, null);
				ps.setString(5, null);
				ps.setString(6, null);
				ps.setString(7, null);
				ps.setString(8, null);
				ps.setString(9, null);
				ps.setString(10, null);
				ps.setString(11, null);
				ps.setString(12, null);
				ps.setString(13, null);
				ps.setString(14, null);
				ps.setString(15, null);
				ps.setString(16, null);
				ps.setString(17, null);
				ps.setString(18, null);
				ps.setString(19, null);
				ps.setString(20, null);
				ps.setString(21, null);
				ps.setLong(22,0);
				ps.setLong(23, 0);
				ps.setString(24, null);
				ps.setString(25, null);
				ps.setString(26, null);
				ps.setString(27, null);
				ps.setString(28, null);
				ps.setLong(29,0);
				ps.setLong(30,0);
				ps.setString(31, null);
				ps.setString(32, null);
				ps.setString(33, null);
				ps.setLong(34,0);
				
				
				if(ps.executeUpdate()>0){
					ResultSet rs=ps.getGeneratedKeys();
					if(rs.next())
						id=rs.getLong(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Addition" + e.getMessage());
			}

		}
		return id;
	}

	@Override
	public StudentPersonalDetailsBean fetchStudentDetails(int strID,String sesID,long roll) {
         StudentPersonalDetailsBean studbean=null;
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("select * from "+table+ String.format("%02d", strID) +sesName+" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				
				ps.setLong(1, roll);
				
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					long rno=rs.getLong(1);
					long reg=rs.getLong(2);
					String studName=rs.getString(3);
					String studFName=rs.getString(4);
					String studLName=rs.getString(5);
					String presentAdd=rs.getString(6);
					String presentAddCity=rs.getString(7);
					String presentAddPin=rs.getString(8);
					String presentAddPostOffice=rs.getString(9);
					String presentAddDistrict=rs.getString(10);
					String presentAddState=rs.getString(11);
					String permanentAdd=rs.getString(12);
					String permanentAddCity=rs.getString(13);
					String permanentAddPin=rs.getString(14);
					String permanentAddPostOffice=rs.getString(15);
					String permanentAddDistrict=rs.getString(16);
					String permanentAddState=rs.getString(17);
					String dob=rs.getString(18);
					String gender=rs.getString(19);
					String bloodgrp=rs.getString(20);
					String landLineCode=rs.getString(21);
					String landLine=rs.getString(22);
					Long mob1=rs.getLong(23);
					Long mob2=rs.getLong(24);
					String email=rs.getString(25);
					String fatherName=rs.getString(26);
					String fatherService=rs.getString(27);
					String motherName=rs.getString(28);
					String motherservice=rs.getString(29);
					Long studParentMob1=rs.getLong(30);
					Long studParentMob2=rs.getLong(31);
					String localGuardian=rs.getString(32);
					String localGuardianRelation=rs.getString(33);
					String localGuardianService=rs.getString(34);
					Long guardianMob=rs.getLong(35);
					
					studbean=new StudentPersonalDetailsBean(rno,reg,studName,studFName,studLName,
											presentAdd,presentAddCity,presentAddPin,presentAddPostOffice,presentAddDistrict,presentAddState,
											permanentAdd,permanentAddCity,permanentAddPin,permanentAddPostOffice,permanentAddDistrict,permanentAddState,
											dob,gender,bloodgrp,landLineCode,landLine,mob1,mob2,email,fatherName,fatherService,motherName,motherservice,
											studParentMob1,studParentMob2,localGuardian,localGuardianRelation,localGuardianService,guardianMob);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Can't Fetch StudentDetails" + e.getMessage());
			}
		}
		return studbean;
	}

	@Override
	public boolean findStudentTable(int strID,String sesID) {
		boolean flag=false;
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps = conn.prepareStatement("SHOW TABLES LIKE '"+StudentPersonalDetails.TABLE_NAME+ String.format("%02d", strID) +sesName+"'");
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					flag=true;
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Problem In Student Find" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public long updateGeneralInfo(int strID,String sesID,String name,String fname,String lname,String gender,String dob,
									String landLineCode,String landLine,long mob1,long mob2,String email,String bloodgrp) {
		long id=addStudent(strID,sesID);
		
		
		long flag=0;
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("update "+table+ String.format("%02d", strID) +sesName+" set "+
															StudentPersonalDetails.Columns.NAME+"=?"+","+
															StudentPersonalDetails.Columns.FNAME+"=?"+","+
															StudentPersonalDetails.Columns.LNAME+"=?"+","+
															StudentPersonalDetails.Columns.GENDER+"=?"+","+
															StudentPersonalDetails.Columns.DOB+"=?"+","+
															StudentPersonalDetails.Columns.LANDLINECODE+"=?"+","+
															StudentPersonalDetails.Columns.LANDLINE+"=?"+","+
															StudentPersonalDetails.Columns.MOB1+"=?"+","+
															StudentPersonalDetails.Columns.MOB2+"=?"+","+
															StudentPersonalDetails.Columns.EMAIL+"=?"+","+
															StudentPersonalDetails.Columns.BLOODGROUP+"=?"+
															" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				
				ps.setString(1,name);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, gender);
				ps.setString(5, dob);
				ps.setString(6, landLineCode);
				ps.setString(7, landLine);
				ps.setLong(8, mob1);
				ps.setLong(9, mob2);
				ps.setString(10, email);
				ps.setString(11, bloodgrp);
				ps.setLong(12,id);
				
				if(ps.executeUpdate()>0)
					flag=id;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! General info cannot be updated" + e.getMessage());
			}
		}
		return flag;
		
		
		
		
		
	}

	@Override
	public boolean updatePersonalInfo(int strID,String sesID,long roll, String fatherName, String fatherService, String motherName,
			String motherService, long mob1, long mob2, String guardianName, String guardianRelation,
			String guardianService, long guardianMob) {
			
			boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("update "+table+ String.format("%02d", strID) +sesName+" set "+
															StudentPersonalDetails.Columns.FATHERNAME+"=?"+","+
															StudentPersonalDetails.Columns.FATHERSERVICE+"=?"+","+
															StudentPersonalDetails.Columns.MOTHERNAME+"=?"+","+
															StudentPersonalDetails.Columns.MOTHERSERVICE+"=?"+","+
															StudentPersonalDetails.Columns.PARENTMOB1+"=?"+","+
															StudentPersonalDetails.Columns.PARENTMOB2+"=?"+","+
															StudentPersonalDetails.Columns.LOCALGUARDIANNAME+"=?"+","+
															StudentPersonalDetails.Columns.LOCALGUARDIANRELATION+"=?"+","+
															StudentPersonalDetails.Columns.LOCALGUARDIANSERVICE+"=?"+","+
															StudentPersonalDetails.Columns.LOCALGUARDIANMOB+"=?"+
															" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				
				ps.setString(1,fatherName);
				ps.setString(2, fatherService);
				ps.setString(3, motherName);
				ps.setString(4, motherService);
				ps.setLong(5, mob1);
				ps.setLong(6, mob1);
				ps.setString(7, guardianName);
				ps.setString(8, guardianRelation);
				ps.setString(9, guardianService);
				ps.setLong(10, guardianMob);
				ps.setLong(11,roll);
				
				if(ps.executeUpdate()>0)
					flag=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Personal info cannot be updated" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean updateAddressInfo(int strID,String sesID,long roll, String presentAddress, String presentCity, String presentPinCode,
			String presentPostOffice, String presentDistrict, String presentState, String permanentAddress,
			String permanentCity, String permanentPinCode, String permanentPostOffice, String permanentDistrict,
			String permanentState) {
			
			boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				PreparedStatement ps=conn.prepareStatement("update "+table+ String.format("%02d", strID) +sesName+" set "+
															StudentPersonalDetails.Columns.PRESENTADDRESS+"=?"+","+
															StudentPersonalDetails.Columns.PRESENTADDRESSCITY+"=?"+","+
															StudentPersonalDetails.Columns.PRESENTADDRESSPIN+"=?"+","+
															StudentPersonalDetails.Columns.PRESENTADDRESSPOSTOFFICE+"=?"+","+
															StudentPersonalDetails.Columns.PRESENTADDRESSDISTRICT+"=?"+","+
															StudentPersonalDetails.Columns.PRESENTADDRESSSTATE+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESS+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESSCITY+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESSPIN+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESSPOSTOFFICE+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESSDISTRICT+"=?"+","+
															StudentPersonalDetails.Columns.PERMANENTADDRESSSTATE+"=?"+
															" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				
				ps.setString(1,presentAddress);
				ps.setString(2, presentCity);
				ps.setString(3, presentPinCode);
				ps.setString(4, presentPostOffice);
				ps.setString(5, presentDistrict);
				ps.setString(6, presentState);
				ps.setString(7, permanentAddress);
				ps.setString(8, permanentCity);
				ps.setString(9, permanentPinCode);
				ps.setString(10, permanentPostOffice);
				ps.setString(11, permanentDistrict);
				ps.setString(12, permanentState);
				ps.setLong(13,roll);
				
				if(ps.executeUpdate()>0)
					flag=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Address info cannot be updated" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public ArrayList<StudentPersonalDetailsBean> fetchAllStudent(int strID, String sesID) {
				ArrayList<StudentPersonalDetailsBean> studentlist=new ArrayList<StudentPersonalDetailsBean>();
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
					PreparedStatement ps=conn.prepareStatement("select * from "+table+ String.format("%02d", strID) +sesName);
					
					
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						long rno=rs.getLong(1);
						long reg=rs.getLong(2);
						String studName=rs.getString(3);
						String studFName=rs.getString(4);
						String studLName=rs.getString(5);
						String presentAdd=rs.getString(6);
						String presentAddCity=rs.getString(7);
						String presentAddPin=rs.getString(8);
						String presentAddPostOffice=rs.getString(9);
						String presentAddDistrict=rs.getString(10);
						String presentAddState=rs.getString(11);
						String permanentAdd=rs.getString(12);
						String permanentAddCity=rs.getString(13);
						String permanentAddPin=rs.getString(14);
						String permanentAddPostOffice=rs.getString(15);
						String permanentAddDistrict=rs.getString(16);
						String permanentAddState=rs.getString(17);
						String dob=rs.getString(18);
						String gender=rs.getString(19);
						String bloodgrp=rs.getString(20);
						String landLineCode=rs.getString(21);
						String landLine=rs.getString(22);
						Long mob1=rs.getLong(23);
						Long mob2=rs.getLong(24);
						String email=rs.getString(25);
						String fatherName=rs.getString(26);
						String fatherService=rs.getString(27);
						String motherName=rs.getString(28);
						String motherservice=rs.getString(29);
						Long studParentMob1=rs.getLong(30);
						Long studParentMob2=rs.getLong(31);
						String localGuardian=rs.getString(32);
						String localGuardianRelation=rs.getString(33);
						String localGuardianService=rs.getString(34);
						Long guardianMob=rs.getLong(35);
						
						
						studentlist.add(new StudentPersonalDetailsBean(rno,reg,studName,studFName,studLName,
								presentAdd,presentAddCity,presentAddPin,presentAddPostOffice,presentAddDistrict,presentAddState,
								permanentAdd,permanentAddCity,permanentAddPin,permanentAddPostOffice,permanentAddDistrict,permanentAddState,
								dob,gender,bloodgrp,landLineCode,landLine,mob1,mob2,email,fatherName,fatherService,motherName,motherservice,
								studParentMob1,studParentMob2,localGuardian,localGuardianRelation,localGuardianService,guardianMob));
					}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}	
		}
		
		return studentlist;
	}

	@Override
	public boolean updateStreamAndSession(long roll,int strID, String sesID) {
			boolean flag=false;
		
		conn = DBUtil.getConnection(db);
		if(conn!=null){
			try{
				PreparedStatement ps=conn.prepareStatement("update "+table+" set "+
															StudentPersonalDetails.Columns.STRID+"=?"+","+
															StudentPersonalDetails.Columns.SESID+"=?"+
															" where "+StudentPersonalDetails.Columns.ROLL+"=?");
				
				ps.setInt(1,strID);
				ps.setString(2, sesID);
				ps.setLong(3,roll);
				
				if(ps.executeUpdate()>0)
					flag=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! Address info cannot be updated" + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public ArrayList<StudentPersonalDetailsBean> fetchAllStudent(int strID, String sesID, String sesSecName) {
		ArrayList<StudentPersonalDetailsBean> studentlist=new ArrayList<StudentPersonalDetailsBean>();
		
		conn = DBUtil.getConnection(db);
		
		if(conn!=null){
			try{
				StudentAcademicDetailsDao sadao=new StudentAcademicDetailsDaoImpl();
				ArrayList<Long> rList=sadao.fetchStudentRoll(strID, sesID, sesSecName);
				SessionDao sesdao=new SessionDaoImpl();
				String sesName=sesdao.createSessionName(sesID);
				
				for(long roll:rList){
					PreparedStatement ps=conn.prepareStatement("select * from "+table+ String.format("%02d", strID) +sesName+" where "+StudentPersonalDetails.Columns.ROLL+"=?");
					
					ps.setLong(1, roll);
					
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						long rno=rs.getLong(1);
						long reg=rs.getLong(2);
						String studName=rs.getString(3);
						String studFName=rs.getString(4);
						String studLName=rs.getString(5);
						String presentAdd=rs.getString(6);
						String presentAddCity=rs.getString(7);
						String presentAddPin=rs.getString(8);
						String presentAddPostOffice=rs.getString(9);
						String presentAddDistrict=rs.getString(10);
						String presentAddState=rs.getString(11);
						String permanentAdd=rs.getString(12);
						String permanentAddCity=rs.getString(13);
						String permanentAddPin=rs.getString(14);
						String permanentAddPostOffice=rs.getString(15);
						String permanentAddDistrict=rs.getString(16);
						String permanentAddState=rs.getString(17);
						String dob=rs.getString(18);
						String gender=rs.getString(19);
						String bloodgrp=rs.getString(20);
						String landLineCode=rs.getString(21);
						String landLine=rs.getString(22);
						Long mob1=rs.getLong(23);
						Long mob2=rs.getLong(24);
						String email=rs.getString(25);
						String fatherName=rs.getString(26);
						String fatherService=rs.getString(27);
						String motherName=rs.getString(28);
						String motherservice=rs.getString(29);
						Long studParentMob1=rs.getLong(30);
						Long studParentMob2=rs.getLong(31);
						String localGuardian=rs.getString(32);
						String localGuardianRelation=rs.getString(33);
						String localGuardianService=rs.getString(34);
						Long guardianMob=rs.getLong(35);
						
						
						studentlist.add(new StudentPersonalDetailsBean(rno,reg,studName,studFName,studLName,
								presentAdd,presentAddCity,presentAddPin,presentAddPostOffice,presentAddDistrict,presentAddState,
								permanentAdd,permanentAddCity,permanentAddPin,permanentAddPostOffice,permanentAddDistrict,permanentAddState,
								dob,gender,bloodgrp,landLineCode,landLine,mob1,mob2,email,fatherName,fatherService,motherName,motherservice,
								studParentMob1,studParentMob2,localGuardian,localGuardianRelation,localGuardianService,guardianMob));
					}
				}
			}catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!" + e.getMessage());
			}	
		}
		
		return studentlist;
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
				
				PreparedStatement ps=conn.prepareStatement("select "+StudentPersonalDetails.Columns.ROLL+" from "+table+ String.format("%02d", strID) +sesName+" where "
					+ StudentPersonalDetails.Columns.SESSECNAME+"=?");
				
				
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
