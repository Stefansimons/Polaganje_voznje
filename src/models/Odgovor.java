package models;

public class Odgovor {
private int odgovorID;
private String odgovor;
private int pitanjeID;
private String tacan_odgovor;
public int getOdgovorID() {
	return odgovorID;
}
public void setOdgovorID(int odgovorID) {
	this.odgovorID = odgovorID;
}
public String getOdgovor() {
	return odgovor;
}
public void setOdgovor(String odgovor) {
	this.odgovor = odgovor;
}
public int getPitanjeID() {
	return pitanjeID;
}
public void setPitanjeID(int pitanjeID) {
	this.pitanjeID = pitanjeID;
}
public String getTacan_odgovor() {
	return tacan_odgovor;
}
public void setTacan_odgovor(String tacan_odgovor) {
	this.tacan_odgovor = tacan_odgovor;
}

}
