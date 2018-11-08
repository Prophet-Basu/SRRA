package com.util;

public class DBSchema {

	public static final String DB_NAME="srra";
	
	public class Stream{
		public static final String TABLE_NAME="stream";
		
		public class Columns{
			public static final String ID="strID";
			public static final String NAME="strName";
			public static final String NOOFSEM="strNoOfSem";
		}
	}
	
	public class Session{
		public static final String TABLE_NAME="session";
		
		public class Columns{
			public static final String STRID="strID";
			public static final String ID="sesID";
			public static final String SECNAME="sesSecName";
			public static final String START_DATE="sesStartDate";
			public static final String END_DATE="sesEndDate";
		}
	}
	
	public class StudentPersonalDetails{
		public static final String TABLE_NAME="studentpersonaldetails";
		
		public class Columns{
			public static final String ROLL="studRoll";
			public static final String REG="studReg";
			public static final String NAME="studName";
			public static final String FNAME="studFName";
			public static final String LNAME="studLName";
			public static final String PRESENTADDRESS="studPresentAddress";
			public static final String PRESENTADDRESSCITY="studPresentAddressCity";
			public static final String PRESENTADDRESSPIN="studPresentAddressPin";
			public static final String PRESENTADDRESSPOSTOFFICE="studPresentAddressPostOffice";
			public static final String PRESENTADDRESSDISTRICT="studPresentAddressDistrict";
			public static final String PRESENTADDRESSSTATE="studPresentAddressState";
			public static final String PERMANENTADDRESS="studPermanentAddress";
			public static final String PERMANENTADDRESSCITY="studPermanentAddressCity";
			public static final String PERMANENTADDRESSPIN="studPermanentAddressPin";
			public static final String PERMANENTADDRESSPOSTOFFICE="studPermanentAddressPostOffice";
			public static final String PERMANENTADDRESSDISTRICT="studPermanentAddressDistrict";
			public static final String PERMANENTADDRESSSTATE="studPermanentAddressState";
			public static final String DOB="studDOB";
			public static final String GENDER="studGender";
			public static final String BLOODGROUP="studBloodGroup";
			public static final String LANDLINECODE="studLandlineCode";
			public static final String LANDLINE="studLandline";
			public static final String MOB1="studMob1";
			public static final String MOB2="studMob2";
			public static final String EMAIL="studEmail";
			public static final String FATHERNAME="studFatherName";
			public static final String FATHERSERVICE="studFatherService";
			public static final String MOTHERNAME="studMotherName";
			public static final String MOTHERSERVICE="studMotherService";
			public static final String PARENTMOB1="studParentMob1";
			public static final String PARENTMOB2="studParentMob2";
			public static final String LOCALGUARDIANNAME="studLocalGuardianName";
			public static final String LOCALGUARDIANRELATION="studLocalGuardianRelation";
			public static final String LOCALGUARDIANSERVICE="studLocalGuardianService";
			public static final String LOCALGUARDIANMOB="studLocalGuardianMob";
			public static final String STRID="strID";
			public static final String SESID="sesID";
			public static final String SESSECNAME="sesSecName";
		}
	}
	
	public class StudentAcademicDetails{
		public static final String TABLE_NAME="studentacademicdetails";
		
		public class Columns{
			public static final String ROLL="studRoll";
			public static final String CLS10SCHOOLNAME="studCls10SchoolName";
			public static final String CLS10SCHOOLMEDIUM="studCls10SchoolMedium";
			public static final String CLS10BOARDNAME="studCls10BoardName";
			public static final String CLS10EXAMNAME="studCls10ExamName";
			public static final String CLS10AVGMARKS="studCls10AvgMarks";
			public static final String CLS10BESTMARKS="studCls10BestMarks";
			public static final String CLS10YOP="studCls10YOP";
			public static final String CLS12SCHOOLNAME="studCls12SchoolName";
			public static final String CLS12SCHOOLMEDIUM="studCls12SchoolMedium";
			public static final String CLS12BOARDNAME="studCls12BoardName";
			public static final String CLS12EXAMNAME="studCls12ExamName";
			public static final String CLS12AVGMARKS="studCls12AvgMarks";
			public static final String CLS12BESTMARKS="studCls12BestMarks";
			public static final String CLS12YOP="studCls12YOP";
			public static final String DIPLOMABOARDNAME="studDiplomaBoardName";
			public static final String DIPLOMASTREAM="studDiplomaStream";
			public static final String DIPLOMAMARKS="studDiplomaMarks";
			public static final String DIPLOMAYOP="studDiplomaYOP";
			public static final String BTECHSELECTIONRANK="studBTechSelectionRank";
			public static final String BTECHSELECTIONEXAM="studBTechSelectionExam";
			public static final String COLLEGE="studCollege";
			public static final String UNIVERSITY="studUniversity";
			public static final String PRESENTSEM="studPresentSem";
			public static final String SEM1="studSem1SGPA";
			public static final String SEM2="studSem2SGPA";
			public static final String SEM3="studSem3SGPA";
			public static final String SEM4="studSem4SGPA";
			public static final String SEM5="studSem5SGPA";
			public static final String SEM6="studSem6SGPA";
			public static final String SEM7="studSem7SGPA";
			public static final String SEM8="studSem8SGPA";
			public static final String YGPA1="studYGPA1";
			public static final String YGPA2="studYGPA2";
			public static final String YGPA3="studYGPA3";
			public static final String YGPA4="studYGPA4";
			public static final String DGPA="studDGPA";
			public static final String HASBACKLOG="studHasBacklog";
			public static final String BACKLOG1="studBacklog1";
			public static final String BACKLOG2="studBacklog2";
			public static final String BACKLOG3="studBacklog3";
			public static final String HASYEARGAP="studHasYearGap";
			public static final String YEARGAPDURATION="studYearGapDuration";
			public static final String YEARGAPPERIOD="studYearGapPeriod";
			public static final String YEARGAPCAUSE="studYearGapCause";
			public static final String ACHIEVEMENT="studAchievement";
			public static final String STRID="strID";
			public static final String SESID="sesID";
			public static final String SESSECNAME="sesSecName";
		}
	}
	
	public class Student{
		public static final String TABLE_NAME="student";
		
		public class Columns{
			
		}
	}
	
	public class Teacher{
		public static final String TABLE_NAME="teacher";
		
		public class Columns{
			public static final String ID="teacherID";
			public static final String NAME="teacherName";
			public static final String ADDRESS="teacherAddress";
			public static final String DOB="teacherDOB";
			public static final String MOB="teacherMob";
			public static final String EMAIL="teacherEmail";
			public static final String ROLE="teacherRole";
			public static final String DESIGNATION="teacherDesignation";
			public static final String PASSWORD="teacherPassword";
			public static final String APPROVE="teacherApprove";
			//public static final String STRID="strID";
		}
	}
	
	public class Subject{
		public static final String TABLE_NAME="subject";
		
		public class Columns{
			public static final String CODE="subCode";
			public static final String NAME="subName";
			public static final String SEM="subSem";
			public static final String TAUGHTDURATION="subTaughtDuration";
			public static final String STRID="strID";
		}
	}
	
	public class Teaches{
		public static final String TABLE_NAME="teaches";
		
		public class Columns{
			public static final String STRID="strID";
			public static final String SUBCODE="subCode";
			public static final String SESID="sesID";
			public static final String SESSECNAME="sesSecName";
			public static final String TEACHERID="teacherID";
			public static final String VERIFIED="verified";
		}
	}
	
	public class Marks{
		public static final String TABLE_NAME="marks";
		
		public class Columns{
			public static final String SUBCODE="subCode";
			public static final String STUDROLL="studRoll";
			public static final String SESSECNAME="sesSecName";
			public static final String SEMNO="semNo";
			public static final String SEMMARKS="mSem";
			public static final String SEMGRADE="mGrade";
			public static final String INTERNAL1MARKS="mInternal1";
			public static final String INTERNAL2MARKS="mInternal2";
			public static final String CLSPERFMARKS="mClsPerf";
		}
	}
	
	public class Attendance{
		public static final String TABLE_NAME="attendance";
		
		public class Columns{
			public static final String SUBCODE="subCode";
			//public static final String STUDROLL="studRoll";
			public static final String SESSECNAME="sesSecName";
			public static final String SEMNO="semNo";
			public static final String ATTNTABLENAME="attnTableName";
			public static final String ATTNTOTAL="attnTotal";
		}
		
		public class SubjectTable{
			public class Columns{
				public static final String ATTNDATE="attnDate";
				public static final String ATTNSTUDCOUNT="attnStudentCount";
			}
		}
	}
	
	public class Semresult{
		public static final String TABLE_NAME="semresult";
		
		public class Columns{
			public static final String STUDROLL="studRoll";
			public static final String SEMNO="semNo";
			public static final String SEMSCORE="semScore";
		}
	}
	
}
