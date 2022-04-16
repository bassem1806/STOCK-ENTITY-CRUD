package com.sip.ams.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Directiong {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	 @NotBlank(message = "libele is mandatory")
	    @Column(name = "libele")
	    private String libele;
	 
	 
	 @Column(name = "etat")
	    private int etat;
	 
	 
	 @Column(name = "codeDG")
	    private int CodeDG;


	public Directiong(long id, String libele, int etat, int codeDG) {
	
		this.id = id;
		this.libele = libele;
		this.etat = etat;
		CodeDG = codeDG;
	}
	 
	public Directiong() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getCodeDG() {
		return CodeDG;
	}

	public void setCodeDG(int codeDG) {
		CodeDG = codeDG;
	}
	
	
	
	 
	 
	 
	 

}
