package com.sip.ams.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Sousdirection {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	
	 @NotBlank(message = "libele is mandatory")
	    @Column(name = "libele")
	    private String libele;
	 
	 @Column(name = "etat")
	    private int etat;
	 
	 
	 @Column(name = "codesousdirection")
	    private int CodeSousdirection;
	 
	 /**** Many To One ****/
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "directiong_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Directiong directiong;
		
		 /**** Many To One ****/
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "direction_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Direction direction;

		public Sousdirection(long id, String libele, int etat,int codeSousdirection) {
		
			this.id = id;
			this.libele = libele;
			this.etat = etat;
			CodeSousdirection = codeSousdirection;
		}
		
		public Sousdirection() {}

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

		public int getCodeSousdirection() {
			return CodeSousdirection;
		}

		public void setCodeSousdirection(int codeSousdirection) {
			CodeSousdirection = codeSousdirection;
		}

		public Directiong getDirectiong() {
			return directiong;
		}

		public void setDirectiong(Directiong directiong) {
			this.directiong = directiong;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
		
		

}
