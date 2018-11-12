package models;

import java.sql.Timestamp;

public class Test {
	
	
private int polaganjeID;
private int kandidatID;
private int pitanjeID;
private Timestamp datum_polaganja;
private String odgovor ;
public int getPolaganjeID() {
	return polaganjeID;
}
public void setPolaganjeID(int polaganjeID) {
	this.polaganjeID = polaganjeID;
}
public int getKandidatID() {
	return kandidatID;
}
public Test() {
	super();
	// TODO Auto-generated constructor stub
}
public Test(int polaganjeID, int kandidatID, int pitanjeID, Timestamp datum_polaganja, String odgovor) {
	super();
	this.polaganjeID = polaganjeID;
	this.kandidatID = kandidatID;
	this.pitanjeID = pitanjeID;
	this.datum_polaganja = datum_polaganja;
	this.odgovor = odgovor;
}
public void setKandidatID(int kandidatID) {
	this.kandidatID = kandidatID;
}
public int getPitanjeID() {
	return pitanjeID;
}
public void setPitanjeID(int pitanjeID) {
	this.pitanjeID = pitanjeID;
}
public Timestamp getDatum_polaganja() {
	return datum_polaganja;
}
public void setDatum_polaganja(Timestamp datum_polaganja) {
	this.datum_polaganja = datum_polaganja;
}
public String getOdgovor() {
	return odgovor;
}
public void setOdgovor(String odgovor) {
	this.odgovor = odgovor;
}


}
