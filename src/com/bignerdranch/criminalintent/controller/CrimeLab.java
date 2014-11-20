package com.bignerdranch.criminalintent.controller;

import java.util.ArrayList;
import java.util.UUID;

import com.bignerdranch.criminalintent.model.Crime;

public enum CrimeLab {

	Singleton;
	
	private ArrayList<Crime> Crimes;
	
	private CrimeLab(){
		Crimes = new ArrayList<Crime>();
		for (int i = 0; i< 100; i++){
			Crime c = new Crime();
			c.setTitle("Crime #"+i);
			c.setSolved(i%2==0);
			Crimes.add(c);
		}
	}
	
	public ArrayList<Crime> getCrimes() {
		return Crimes;
	}
	
	public Crime getCrime(UUID id) {
		for (Crime c : Crimes) {
			if(c.getId().equals(id)) return c;
		}
		return null;
	}
}
