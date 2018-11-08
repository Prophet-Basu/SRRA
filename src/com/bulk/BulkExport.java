package com.bulk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.StreamBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.TeacherBean;
import com.dao.StreamDao;
import com.dao.StreamDaoImpl;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

public class BulkExport {

	public static String teacherBulkExport(int strID,String filepath){
		String msg="";
		TeacherDao tdao=new TeacherDaoImpl();
		ArrayList<String> paramList=tdao.getAllParameterList(strID);
		ArrayList<TeacherBean> tbList=tdao.fetchAllTeacher(strID);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		if(tbList.size()>0){
			XSSFSheet sheet = workbook.createSheet("Details");
			XSSFCellStyle headerstyle = workbook.createCellStyle();
			XSSFFont headerfont = workbook.createFont();
			headerfont.setBold(true);
			headerstyle.setFont(headerfont);
			headerstyle.setFillForegroundColor(IndexedColors.AQUA.index);
			headerstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			int rn = 0;
			Row headerRow = sheet.createRow(rn++);
			int cn = paramList.size()-2;
			Cell cell[] = new Cell[cn];
			
			for (int k = 0; k < cn; k++) {
				cell[k] = headerRow.createCell(k);
				cell[k].setCellStyle(headerstyle);
			}
			int k=0;
			for(String s:paramList){
				System.out.println(s);
				if(s.equalsIgnoreCase("teacherPassword") || s.equalsIgnoreCase("teacherApprove"));
				else
					cell[k++].setCellValue(s.toUpperCase());
			}
			
			for(TeacherBean tb:tbList){
				Row row = sheet.createRow(rn++);

				for (k = 0; k < cn; k++)
					cell[k] = row.createCell(k);
				k=0;
				cell[k++].setCellValue(tb.getTeacherID());
				cell[k++].setCellValue(tb.getTeacherName());
				cell[k++].setCellValue(tb.getTeacherAddress());
				cell[k++].setCellValue(tb.getTeacherDOB());
				cell[k++].setCellValue(tb.getTeacherMob());
				cell[k++].setCellValue(tb.getTeacherEmail());
				cell[k++].setCellValue(tb.getTeacherRole());
				cell[k++].setCellValue(tb.getTeacherDesignation());
				//cell[k++].setCellValue(tb.getTeacherPassword());
				//cell[k++].setCellValue(tb.);
			}
		}
		
		try {
			StreamDao sdao=new StreamDaoImpl();
			StreamBean sb=sdao.fetchStreamDetails(strID);
			FileOutputStream fos = new FileOutputStream(new File(filepath+"\\Teacher_"+sb.getStrName()+".xlsx"));
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("Teacher_"+sb.getStrName()+".xlsx written successfully");
			msg=filepath+"Teacher_"+sb.getStrName()+".xlsx";
		} catch (FileNotFoundException e) {
			msg="-1";
			e.printStackTrace();
		} catch (IOException e) {
			msg="-1";
			e.printStackTrace();
		}

		return msg;
	}
	
	public static String studentBulkExport(int strID,String sesID,String filepath){
		String msg="";
		StudentPersonalDao spdao=new StudentPersonalDaoImpl();
		StudentAcademicDetailsDao sadao=new StudentAcademicDetailsDaoImpl();
		ArrayList<String> paramListPersonal=spdao.getAllParameterList(strID, sesID);
		ArrayList<String> paramListAcademic=sadao.getAllParameterList(strID, sesID);
		ArrayList<StudentPersonalDetailsBean> spList=spdao.fetchAllStudent(strID, sesID);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		if(spList.size()>0){
			XSSFSheet sheet = workbook.createSheet("Details");
			XSSFCellStyle headerstyle = workbook.createCellStyle();
			XSSFFont headerfont = workbook.createFont();
			headerfont.setBold(true);
			headerstyle.setFont(headerfont);
			headerstyle.setFillForegroundColor(IndexedColors.AQUA.index);
			headerstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			int rn = 0;
			Row headerRow = sheet.createRow(rn++);
			int cn = paramListPersonal.size()+paramListAcademic.size()-1;
			Cell cell[] = new Cell[cn];
			
			for (int k = 0; k < cn; k++) {
				cell[k] = headerRow.createCell(k);
				cell[k].setCellStyle(headerstyle);
			}
			int k=0;
			
			for(String s:paramListPersonal){
				cell[k++].setCellValue(s.toUpperCase());
			}
			int c=1;
			for(String s:paramListAcademic){
				if(c!=1)
					cell[k++].setCellValue(s.toUpperCase());
				else
					c++;
			}
		}
		try {
			StreamDao sdao=new StreamDaoImpl();
			StreamBean sb=sdao.fetchStreamDetails(strID);
			FileOutputStream fos = new FileOutputStream(new File(filepath+"\\Student_"+sb.getStrName()+sesID+".xlsx"));
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("Student_"+sb.getStrName()+sesID+".xlsx written successfully");
			msg=filepath+"Student_"+sb.getStrName()+sesID+".xlsx";
		} catch (FileNotFoundException e) {
			msg="-1";
			e.printStackTrace();
		} catch (IOException e) {
			msg="-1";
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
