package com.orgname.querybuilder;

import java.sql.Date;

//to generate getter setter through eclipse... press Alt+Shift+S then press R
public class Employees {
	
	private int ID;
	private String FirstName;
	private String LastName;
	private Date JoiningDate;
		
	public Employees(){
	}
	
	public Employees(int ID, String FirstName, String LastName, Date JoiningDate)
	{
		this.ID=ID;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.JoiningDate=JoiningDate;
	}
	
	//getters
	public int getID(){
		return ID;
	}
	
	public String getFirstName(){
		return FirstName;
	}
	
	public String getLastName(){
		return LastName;
	}
	
	public Date getJoiningDate(){
		return JoiningDate;
	}
		
	//setters
	public void setFirstName(String FirstName){
		this.FirstName=FirstName;
	}
	
	public void setLastName(String LastName){
		this.LastName=LastName;
	}
	
	public void setJoiningDate(Date JoiningDate){
		this.JoiningDate=JoiningDate;
	}
	
	
}
