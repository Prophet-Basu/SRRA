package com.dao;

import java.util.ArrayList;

import com.bean.StreamBean;

public interface StreamDao {
	public int addStream(StreamBean strbean);
	public String fetchStreamName(int strID);
	public int fetchNoOfSem(int strID);
	public boolean deleteStream(int strID);
	public boolean updateStream(StreamBean sb);
	public ArrayList<StreamBean> fetchAllStream();
	public StreamBean fetchStreamDetails(int strID);
	public boolean findStreamTable();
}
