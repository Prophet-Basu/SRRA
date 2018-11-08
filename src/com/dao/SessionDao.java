package com.dao;

import java.util.ArrayList;

import com.bean.SessionBean;

public interface SessionDao {

	public boolean addSessionDetails(SessionBean sbean); 
	public boolean findSessionTable();
	public boolean createSessionTable();
	public ArrayList<SessionBean> fetchSessionDetails(int strID);
	public ArrayList<String> fetchSectionsforSession(int strID,String sesID);
	public ArrayList<String> fetchDistinctSessions(int strID);
	public ArrayList<SessionBean> fetchAllSession();
	public SessionBean fetchSingleSession(String sesID);
	public String createSessionName(String sesID);
}
