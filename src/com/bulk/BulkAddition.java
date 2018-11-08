package com.bulk;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.catalina.ant.SessionsTask;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.MarksBean;
import com.bean.StudentAcademicDetailsBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.TeacherBean;
import com.dao.MarksDao;
import com.dao.MarksDaoImpl;
import com.dao.SessionDao;
import com.dao.SessionDaoImpl;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.mysql.fabric.xmlrpc.base.Value;

public class BulkAddition {

	public static boolean checkNA(String s) {
		boolean f = false;
		if (s.equalsIgnoreCase("NA"))
			f = true;
		return f;
	}

	public static boolean checkInvalidData(String s) {
		boolean f = false;
		if (s.equalsIgnoreCase(""))
			f = true;
		return f;
	}

	public static String teacherDataExtract(File file, int strID) {
		String msg = "";
		TeacherBean tbean;
		int rCount = 0, cCount = 0;
		boolean flag = true;
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.rowIterator();
			while (rowIterator.hasNext()) {
				rCount++;
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				Cell cell = null;
				cell = cellIterator.next();
				cCount++;
				String teacherName = cell.getStringCellValue();
				if (checkInvalidData(teacherName)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				cell = cellIterator.next();
				cCount++;
				String teacherAddress = cell.getStringCellValue();
				if (checkInvalidData(teacherAddress)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				cell = cellIterator.next();
				cCount++;
				String teacherDOB = cell.getStringCellValue();
				if (checkInvalidData(teacherDOB)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				cell = cellIterator.next();
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String tMob = cell.getStringCellValue();
				if (checkInvalidData(tMob)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				long teacherMob = Long.parseLong(tMob);
				cell = cellIterator.next();
				cCount++;
				String teacherEmail = cell.getStringCellValue();
				if (checkInvalidData(teacherEmail)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				cell = cellIterator.next();
				cCount++;
				String teacherRole = cell.getStringCellValue();
				if (checkInvalidData(teacherRole)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				if(!teacherRole.equalsIgnoreCase("Teacher")){
					msg += "\nRow:" + rCount + " Column:" + cCount + "Role cannot be anything other than Teacher!!!";
				}
				cell = cellIterator.next();
				cCount++;
				String teacherDesignation = cell.getStringCellValue();
				if (checkInvalidData(teacherDesignation)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				cell = cellIterator.next();
				cCount++;
				String teacherPassword = cell.getStringCellValue();
				if (checkInvalidData(teacherPassword)) {
					msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					flag = false;
				}
				
				if (flag) {
					tbean = new TeacherBean(0, teacherName, teacherAddress, teacherDOB, teacherMob, teacherEmail,
							"Teacher", teacherDesignation, teacherPassword, 0);
					msg=teacherDataAddition(tbean,strID);
						
				}
				else{
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			msg += "\nRow:" + rCount + " Column:" + cCount + "Wrong Data Format in Column";
		}
		if(flag)
			return "Teacher Bulk data Added successfully!!!";
		
		return msg;
	}

	public static String teacherDataAddition(TeacherBean tbean,int strID) {
		String msg = "";
		TeacherDao tdao=new TeacherDaoImpl();
		if(tdao.findTeacherByEmail(tbean.getTeacherEmail(), strID)){
			msg="A teacher with this Email ID already exists!";
		}
		else{
			int id;
			if((id=tdao.addTeacher(tbean, strID))>0){
				System.out.println("Teacher with ID "+id+" inserted!");
				msg="TID: "+id;
			}
			else{
				msg="Teacher Addition Unsuccessful!";
			}
		}
		return msg;
	}

	public static String studentDataExtract(File file, int strID, String sesID) {
		StudentPersonalDetailsBean spbean;
		StudentAcademicDetailsBean sabean;
		String msg = "";
		int rCount = 0, cCount = 0;
		try {
			// FileInputStream fis=new FileInputStream(new File(filename));
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet firstSheet = workbook.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			System.out.println("InFunc");

			Iterator<Row> rowIterator = firstSheet.rowIterator();
			rowIterator.next();
			rCount++;
			while (rowIterator.hasNext()) {
				rCount++;
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				Cell cell = null;
				cellIterator.next(); // 1
				cCount++;
				cell = cellIterator.next();// 2
				cCount++;
				String studName = cell.getStringCellValue();
				System.out.println(studName);
				if (checkInvalidData(studName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 3
				cCount++;
				String studFName = cell.getStringCellValue();
				if (checkInvalidData(studFName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 4
				cCount++;
				String studLName = cell.getStringCellValue();
				if (checkInvalidData(studLName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 5
				cCount++;
				String studGender = cell.getStringCellValue();
				if (checkInvalidData(studGender)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 6
				cCount++;
				String studDOB = cell.getStringCellValue();
				if (checkInvalidData(studDOB)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 7
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String studLandlineCode = cell.getStringCellValue();
				if (checkNA(studLandlineCode))
					studLandlineCode = null;
				else
					Integer.parseInt(studLandlineCode);
				cell = cellIterator.next();// 8
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String studLandline = cell.getStringCellValue();
				if (checkNA(studLandline))
					studLandline = null;
				else
					Integer.parseInt(studLandline);
				cell = cellIterator.next();// 9
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sMob1 = cell.getStringCellValue();
				System.out.println(sMob1);
				long studMob1;
				if (checkNA(sMob1))
					studMob1 = 0;
				else
					studMob1 = 0;// Long.parseLong(sMob1);

				cell = cellIterator.next();// 10
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sMob2 = cell.getStringCellValue();
				long studMob2;
				if (checkNA(sMob2))
					studMob2 = 0;
				else
					studMob2 = Long.parseLong(sMob2);
				cell = cellIterator.next();// 11
				cCount++;
				String studEmail = cell.getStringCellValue();
				if (checkInvalidData(studEmail)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}

				cell = cellIterator.next();// 12
				cCount++;
				String studCls10ExamName = cell.getStringCellValue();
				if (checkInvalidData(studCls10ExamName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 13
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls10YOP = cell.getStringCellValue();
				if (checkInvalidData(sCls10YOP)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				int studCls10YOP = Integer.parseInt(sCls10YOP);
				cell = cellIterator.next();// 14
				cCount++;
				String studCls10BoardName = cell.getStringCellValue();
				if (checkInvalidData(studCls10BoardName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 15
				cCount++;
				String studCls10SchoolName = cell.getStringCellValue();
				if (checkInvalidData(studCls10SchoolName)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 16
				cCount++;
				String studCls10SchoolMedium = cell.getStringCellValue();
				if (checkInvalidData(studCls10SchoolMedium)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 17
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls10BestMarks = cell.getStringCellValue();
				if (checkInvalidData(sCls10BestMarks)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				float studCls10BestMarks = Float.parseFloat(sCls10BestMarks);
				cell = cellIterator.next();// 18
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls10AvgMarks = cell.getStringCellValue();
				if (checkInvalidData(sCls10AvgMarks)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				float studCls10AvgMarks = Float.parseFloat(sCls10AvgMarks);

				cell = cellIterator.next();// 19
				cCount++;
				String studCls12ExamName = cell.getStringCellValue();
				if (checkNA(studCls12ExamName))
					studCls12ExamName = null;
				cell = cellIterator.next();// 20
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls12YOP = cell.getStringCellValue();
				int studCls12YOP;
				if (checkNA(sCls12YOP))
					studCls12YOP = 0;
				else
					studCls12YOP = Integer.parseInt(sCls12YOP);
				cell = cellIterator.next();// 21
				cCount++;
				String studCls12BoardName = cell.getStringCellValue();
				if (checkNA(studCls12BoardName))
					studCls12BoardName = null;
				cell = cellIterator.next();// 22
				cCount++;
				String studCls12SchoolName = cell.getStringCellValue();
				if (checkNA(studCls12SchoolName))
					studCls12SchoolName = null;
				cell = cellIterator.next();// 23
				cCount++;
				String studCls12SchoolMedium = cell.getStringCellValue();
				if (checkNA(studCls12SchoolMedium))
					studCls12SchoolMedium = null;
				cell = cellIterator.next();// 24
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls12BestMarks = cell.getStringCellValue();
				float studCls12BestMarks;
				if (checkNA(sCls12BestMarks))
					studCls12BestMarks = 0.0f;
				else
					studCls12BestMarks = Float.parseFloat(sCls12BestMarks);
				cell = cellIterator.next();// 25
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sCls12AvgMarks = cell.getStringCellValue();
				float studCls12AvgMarks;
				if (checkNA(sCls12AvgMarks))
					studCls12AvgMarks = 0.0f;
				else
					studCls12AvgMarks = Float.parseFloat(sCls12AvgMarks);

				cell = cellIterator.next();// 26
				cCount++;
				String studDiplomaBoardName = cell.getStringCellValue();
				if (checkNA(studDiplomaBoardName))
					studDiplomaBoardName = null;
				cell = cellIterator.next();// 27
				cCount++;
				String studDiplomaStream = cell.getStringCellValue();
				if (checkNA(studDiplomaStream))
					studDiplomaStream = null;
				cell = cellIterator.next();// 28
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sDiplomaYOP = cell.getStringCellValue();
				int studDiplomaYOP;
				if (checkNA(sDiplomaYOP))
					studDiplomaYOP = 0;
				else
					studDiplomaYOP = Integer.parseInt(sDiplomaYOP);
				cell = cellIterator.next();// 29
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sDiplomaMarks = cell.getStringCellValue();
				float studDiplomaMarks;
				if (checkNA(sDiplomaMarks))
					studDiplomaMarks = 0.0f;
				else
					studDiplomaMarks = Float.parseFloat(sDiplomaMarks);

				cell = cellIterator.next();// 30
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sBTechSelectionRank = cell.getStringCellValue();
				int studBTechSelectionRank;
				if (checkInvalidData(sBTechSelectionRank)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				studBTechSelectionRank = Integer.parseInt(sBTechSelectionRank);
				cell = cellIterator.next();// 31
				cCount++;
				String studBTechSelectionExam = cell.getStringCellValue();
				if (checkInvalidData(studBTechSelectionExam)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 32
				cCount++;
				String studCollege = cell.getStringCellValue();
				if (checkInvalidData(studCollege)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cell = cellIterator.next();// 33
				cCount++;
				String studUniversity = cell.getStringCellValue();
				if (checkInvalidData(studUniversity)) {
					msg = "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				cellIterator.next();// 34
				cCount++;
				cell = cellIterator.next();// 35
				cCount++;
				cell=cellIterator.next();
				cCount++;
				String sesSecName=cell.getStringCellValue();
				if (checkInvalidData(sesSecName)) {
					msg = "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				SessionDao sesdao=new SessionDaoImpl();
				ArrayList<String> secList=sesdao.fetchSectionsforSession(strID, sesID);
				boolean flag=false;
				for(int i=0;i<secList.size();i++){
					if(secList.get(i).equalsIgnoreCase(sesSecName)){
						sesSecName=secList.get(i);
						flag=true;
						break;
					}
				}
				if(!flag){
					msg = "Invalid Section Name!!! Does not Exist";
				}
				// String sesID=cell.getStringCellValue();
				// if(checkInvalidData(sesID))
				// {
				// msg="Invalid Excel Format!!! Recheck and Upload again";
				// break;
				// }
				cell = cellIterator.next();// 36
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sReg = cell.getStringCellValue();
				if (checkInvalidData(sReg)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				long studReg = Long.parseLong(sReg);
				cell = cellIterator.next();// 37
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sRoll = cell.getStringCellValue();
				if (checkInvalidData(sRoll)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				long studRoll = Long.parseLong(sRoll);

				cell = cellIterator.next();// 38
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sPresentSem = cell.getStringCellValue();
				int studPresentSem;
				if (checkInvalidData(sPresentSem)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				studPresentSem = Integer.parseInt(sPresentSem);
				

				cell = cellIterator.next();// 47
				cCount++;
				String studHasBacklog = cell.getStringCellValue();
				if (checkInvalidData(studHasBacklog)) {
					msg = "Row:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
					break;
				}
				String studBacklog1 = null, studBacklog2 = null, studBacklog3 = null;
				if (studHasBacklog.equalsIgnoreCase("N")) {
					cCount++;
					cCount++;
					cCount++;
					cellIterator.next();// 48
					cellIterator.next();// 49
					cellIterator.next();// 50
				} else if (studHasBacklog.equalsIgnoreCase("Y")) {
					cell = cellIterator.next();// 48
					cCount++;
					studBacklog1 = cell.getStringCellValue();
					if (checkNA(studBacklog1)) {
						studBacklog1 = null;
					}
					cell = cellIterator.next();// 49
					cCount++;
					studBacklog2 = cell.getStringCellValue();
					if (checkNA(studBacklog2)) {
						studBacklog2 = null;
					}
					cell = cellIterator.next();// 50
					cCount++;
					studBacklog3 = cell.getStringCellValue();
					if (checkNA(studBacklog3)) {
						studBacklog3 = null;
					}
					if (studBacklog1.equals(null) && studBacklog2.equals(null) && studBacklog3.equals(null)) {
						msg = "Row:" + rCount + " Column:" + cCount + "Data Mismatch in Excel! Please upload again";
						break;
					}
				} else {
					msg = "Row:" + rCount + " Column:" + cCount + "Data Mismatch in Excel! Please upload again";
					break;
				}
				cell = cellIterator.next();// 51
				cCount++;
				String studFatherName = cell.getStringCellValue();
				if (checkNA(studFatherName)) {
					studFatherName = null;
				}
				cell = cellIterator.next();// 52
				cCount++;
				String studFatherService = cell.getStringCellValue();
				if (checkNA(studFatherService)) {
					studFatherService = null;
				}
				cell = cellIterator.next();// 53
				cCount++;
				String studMotherName = cell.getStringCellValue();
				if (checkNA(studMotherName)) {
					studMotherName = null;
				}
				cell = cellIterator.next();// 54
				cCount++;
				String studMotherService = cell.getStringCellValue();
				if (checkNA(studMotherService)) {
					studMotherService = null;
				}
				cell = cellIterator.next();// 55
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sParentMob1 = cell.getStringCellValue();
				long studParentMob1;
				if (checkNA(sParentMob1)) {
					studParentMob1 = 0L;
				} else {
					studParentMob1 = Long.parseLong(sParentMob1);
				}
				cell = cellIterator.next();// 56
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sParentMob2 = cell.getStringCellValue();
				long studParentMob2;
				if (checkNA(sParentMob2)) {
					studParentMob2 = 0L;
				} else {
					studParentMob2 = Long.parseLong(sParentMob2);
				}
				cell = cellIterator.next();// 57
				cCount++;
				String studLocalGuardianName = cell.getStringCellValue();
				if (checkNA(studLocalGuardianName)) {
					studLocalGuardianName = null;
				}
				cell = cellIterator.next();// 58
				cCount++;
				String studLocalGuardianRelation = cell.getStringCellValue();
				if (checkNA(studLocalGuardianRelation)) {
					studLocalGuardianRelation = null;
				}
				cell = cellIterator.next();// 59
				cCount++;
				String studLocalGuardianService = cell.getStringCellValue();
				if (checkNA(studLocalGuardianService)) {
					studLocalGuardianService = null;
				}
				cell = cellIterator.next();// 60
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String sLocalGuardianMob = cell.getStringCellValue();
				long studLocalGuardianMob;
				if (checkNA(sLocalGuardianMob)) {
					studLocalGuardianMob = 0L;
				} else {
					studLocalGuardianMob = Long.parseLong(sLocalGuardianMob);
				}
				cell = cellIterator.next();// 61
				cCount++;
				String studPermanentAddress = cell.getStringCellValue();
				if (checkNA(studPermanentAddress)) {
					studPermanentAddress = null;
				}
				cell = cellIterator.next();// 62
				cCount++;
				String studPermanentAddressPostOffice = cell.getStringCellValue();
				if (checkNA(studPermanentAddressPostOffice)) {
					studPermanentAddressPostOffice = null;
				}
				cell = cellIterator.next();// 63
				cCount++;
				String studPermanentAddressCity = cell.getStringCellValue();
				if (checkNA(studPermanentAddressCity)) {
					studPermanentAddressCity = null;
				}
				cell = cellIterator.next();// 64
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String studPermanentAddressPin = cell.getStringCellValue();
				if (checkNA(studPermanentAddressPin)) {
					studPermanentAddressPin = null;
				}
				cell = cellIterator.next();// 65
				cCount++;
				String studPermanentAddressDistrict = cell.getStringCellValue();
				if (checkNA(studPermanentAddressDistrict)) {
					studPermanentAddressDistrict = null;
				}
				cell = cellIterator.next();// 66
				cCount++;
				String studPermanentAddressState = cell.getStringCellValue();
				if (checkNA(studPermanentAddressState)) {
					studPermanentAddressState = null;
				}
				cell = cellIterator.next();// 67
				cCount++;
				String studPresentAddress = cell.getStringCellValue();
				if (checkNA(studPresentAddress)) {
					studPresentAddress = null;
				}
				cell = cellIterator.next();// 68
				cCount++;
				String studPresentAddressPostOffice = cell.getStringCellValue();
				if (checkNA(studPresentAddressPostOffice)) {
					studPresentAddressPostOffice = null;
				}
				cell = cellIterator.next();// 69
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String studPresentAddressPin = cell.getStringCellValue();
				if (checkNA(studPresentAddressPin)) {
					studPresentAddressPin = null;
				}
				cell = cellIterator.next();// 70
				cCount++;
				String studPresentAddressCity = cell.getStringCellValue();
				if (checkNA(studPresentAddressCity)) {
					studPresentAddressCity = null;
				}
				cell = cellIterator.next();// 71
				cCount++;
				String studPresentAddressDistrict = cell.getStringCellValue();
				if (checkNA(studPresentAddressDistrict)) {
					studPresentAddressDistrict = null;
				}
				cell = cellIterator.next();// 72
				cCount++;
				String studPresentAddressState = cell.getStringCellValue();
				if (checkNA(studPresentAddressState)) {
					studPresentAddressState = null;
				}
				cell = cellIterator.next();// 73
				cCount++;
				String studHasYearGap = cell.getStringCellValue();
				if (checkNA(studHasYearGap)) {
					studHasYearGap = null;
				}
				cell = cellIterator.next();// 74
				cCount++;
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String studYearGapDuration = cell.getStringCellValue();
				if (checkNA(studYearGapDuration)) {
					studYearGapDuration = null;
				} else {
					Integer.parseInt(studYearGapDuration);
				}
				cell = cellIterator.next();// 75
				cCount++;
				String studYearGapPeriod = cell.getStringCellValue();
				if (checkNA(studYearGapPeriod)) {
					studYearGapPeriod = null;
				}
				cell = cellIterator.next();// 76
				cCount++;
				String studYearGapCause = cell.getStringCellValue();
				if (checkNA(studYearGapCause)) {
					studYearGapCause = null;
				}
				cell = cellIterator.next();// 77
				cCount++;
				String studAchievement = cell.getStringCellValue();
				if (checkNA(studAchievement)) {
					studAchievement = null;
				}
				cell = cellIterator.next();// 78
				cCount++;
				String studBloodGroup = cell.getStringCellValue();
				if (checkNA(studBloodGroup)) {
					studBloodGroup = null;
				}

				spbean = new StudentPersonalDetailsBean(studRoll, studReg, studName, studFName, studLName,
						studPresentAddress, studPresentAddressCity, studPresentAddressPin, studPresentAddressPostOffice,
						studPresentAddressDistrict, studPresentAddressState, studPermanentAddress,
						studPermanentAddressCity, studPermanentAddressPin, studPermanentAddressPostOffice,
						studPermanentAddressDistrict, studPermanentAddressState, studDOB, studGender, studBloodGroup,
						studLandlineCode, studLandline, studMob1, studMob2, studEmail, studFatherName,
						studFatherService, studMotherName, studMotherService, studParentMob1, studParentMob2,
						studLocalGuardianName, studLocalGuardianRelation, studLocalGuardianService,
						studLocalGuardianMob);
				sabean = new StudentAcademicDetailsBean(studRoll, studCls10SchoolName, studCls10SchoolMedium,
						studCls10BoardName, studCls10ExamName, studCls10AvgMarks, studCls10BestMarks, studCls10YOP,
						studCls12SchoolName, studCls12SchoolMedium, studCls12BoardName, studCls12ExamName,
						studCls12AvgMarks, studCls12BestMarks, studCls12YOP, studDiplomaBoardName, studDiplomaStream,
						studDiplomaMarks, studDiplomaYOP, studBTechSelectionRank, studBTechSelectionExam, studCollege,
						studUniversity, studPresentSem,0,0,0,0,0,0,0,0,0,0,0,0,0, studHasBacklog, studBacklog1,
						studBacklog2, studBacklog3, studHasYearGap, studYearGapDuration, studYearGapPeriod,
						studYearGapCause, studAchievement,sesSecName);
				if (studentDataAddition( strID, sesID,spbean, sabean))
					msg = "Student Bulk Data Added to Database!!!";

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			msg = "Row:" + rCount + " Column:" + cCount + "Wrong Data Format in Column";
		}

		return msg;
	}

	public static boolean studentDataAddition(int strID,String sesID,StudentPersonalDetailsBean spbean, StudentAcademicDetailsBean sabean) {
		boolean f = false;
		StudentPersonalDao spdao = new StudentPersonalDaoImpl();
		System.out.println("\n\nInFunc: StudentAddition");
		if (spdao.findStudentByRoll(strID,sesID,spbean.getStudRoll())) {
			if (spdao.updateStudentPersonalDetails(strID,sesID,spbean)) {
				System.out.println("Student " + spbean.getStudRoll() + " Personal Details updated succesfully!!!");
				f = true;
			}
		} else {
			if (spdao.insertStudentPersonalDetails(strID,sesID,spbean)) {
				System.out.println("Student " + spbean.getStudRoll() + " Personal Details inserted succesfully!!!");
				f = true;
			}
		}
		StudentAcademicDetailsDao sadao = new StudentAcademicDetailsDaoImpl();
		if (sadao.findStudentByRoll(strID,sesID,sabean.getStudRoll())) {
			if (sadao.updateStudentAcademicDetails(strID,sesID,sabean)) {
				System.out.println("Student " + sabean.getStudRoll() + " Academic Details updated succesfully!!!");
				f = true;
			}
		} else {
			if (sadao.insertStudentAcademicDetails(strID,sesID,sabean)) {
				System.out.println("Student " + sabean.getStudRoll() + " Academic Details inserted succesfully!!!");
				f = true;
			}
		}
		return f;
	}
	
	public static String marksDataExtract(File file,int strID,String sesID,int semNo){
		String msg="";
		MarksBean mbean;
		StudentAcademicDetailsBean sabean;
		int rCount=0,cCount=0;
		int gpaCount;
		if(semNo%2==0)
			gpaCount=semNo-1;
		else
			gpaCount=semNo;
		boolean flag=true;
		try{
			FileInputStream fis=new FileInputStream(file);
			Workbook workbook=new XSSFWorkbook(fis);
			Sheet firstSheet =workbook.getSheetAt(0);
			Iterator<Row> rowIterator=firstSheet.rowIterator();
			SubjectDao subdao=new SubjectDaoImpl();
			
			if(rowIterator.hasNext()){
				rCount++;
				Row nextRow=rowIterator.next();
				Iterator<Cell> cellIterator=nextRow.cellIterator();
				
				ArrayList<String> cellName=new ArrayList<String>();
				
				Cell cell=null;
				do{
					if(cCount==0){
						cell=cellIterator.next();
						cell=cellIterator.next();
						cCount+=2;
					}
					else{
						cell=cellIterator.next();
						cCount++;
						String name=cell.getStringCellValue();
						//System.out.println(name);
						if(checkInvalidData(name)){
							msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
							//flag = false;	
						}
						
						if((name.substring(1,4).equalsIgnoreCase("GPA"))){
							
							if(name.substring(0, 4).equalsIgnoreCase("SGPA")){
								//System.out.println("Inside SGPA");
								name="studSem"+gpaCount+"SGPA";
								gpaCount++;
							}
								else if(name.substring(0, 4).equalsIgnoreCase("YGPA"))
									name="studYGPA"+semNo/2;
								else
									name="studDGPA";
							cellName.add(name);
							}
						else
							if(!name.equalsIgnoreCase("Result"))
								if(!subdao.checkSubjectCode(name)){
									msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Subject Code!!! Recheck and Upload again";
								}else
									cellName.add(name);
								
					}
				}while(cellIterator.hasNext());
					//for(String s:cellName)
						//System.out.println(s);
				
					cCount=0;
				while(rowIterator.hasNext()){
					rCount++;
					cCount=0;
					
					nextRow=rowIterator.next();
					cellIterator=nextRow.cellIterator();
					
					cell=cellIterator.next();
					cCount++;
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String rollValue=cell.getStringCellValue();
					if (checkInvalidData(rollValue)) {
						msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Excel Format!!! Recheck and Upload again";
						flag = false;
					}
					long roll=Long.parseLong(rollValue);
					//System.out.println(roll);
					StudentAcademicDetailsDao sadao=new StudentAcademicDetailsDaoImpl();
					String secName=null;
					if(flag){
						
						secName=sadao.fetchStudentSection(strID,sesID,roll);
					}
					//System.out.println(secName);	
					
					cellIterator.next();
					cCount++;
					
					for(String s:cellName){
						//System.out.println(s);
						cell=cellIterator.next();
						cCount++;
						if(s.substring(0,4).equalsIgnoreCase("stud")){
								cell.setCellType(Cell.CELL_TYPE_STRING);
								String value=cell.getStringCellValue();
								//System.out.println("value"+ "  "+s);
								if (checkInvalidData(value)) {
									msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid SGPA/YGPA Format!!! Recheck and Upload again";
									flag = false;
								}
								if(flag){
									//System.out.println(value);
									
									sadao.updateSemDetails(roll, strID, sesID, secName, s,Float.parseFloat(cell.getStringCellValue()) );
								}
						}
						else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String value=cell.getStringCellValue();
							//System.out.println(value);
							if (checkInvalidData(value)) {
								msg += "\nRow:" + rCount + " Column:" + cCount + "Invalid Marks Format!!! Recheck and Upload again";
								flag = false;
							}
							//System.out.println(flag);
								if(flag){
									//System.out.println(value.length());
									//System.out.println(value.substring(value.indexOf("(") + 1,value.indexOf(")")));
									MarksBean mb;
									int marks=Integer.parseInt(value.substring(value.indexOf("(") + 1,value.indexOf(")")));
									
									String grade=value.substring(0,1);
									//System.out.println(marks+ " "+grade);
									mb=new MarksBean(s, roll, secName, semNo, marks,grade,0,0,0);
									MarksDao marksdao=new MarksDaoImpl();
									flag=marksdao.insertMarks(mb, strID, sesID);
									
								}
							}
					}
					
					
				}
			}
			else{
				msg="The File is Empty";
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			msg += "\nRow:" + rCount + " Column:" + cCount + "Wrong Data Format in Column";
		}
		if(flag)
			return "Student Marks Bulk data Added successfully!!!";
		
		return msg;
	}

}
