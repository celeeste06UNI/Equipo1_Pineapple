package com.pineapple.intime.model;

import java.util.ArrayList;

public class Fichaje {
	
	private String email;
	private ArrayList<String> listDate;
	
	public Fichaje(String email, ArrayList<String> listDate) {
		super();
		this.email = email;
		this.listDate = listDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getListDate() {
		return listDate;
	}

	public void setListDate(ArrayList<String> listDate) {
		this.listDate = listDate;
	}
	
	

}
