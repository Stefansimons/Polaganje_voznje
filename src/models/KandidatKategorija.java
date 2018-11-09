package models;

public class KandidatKategorija {
	private int RB;
	private int kandidatID;
	private String ime;
	private String prezime;
	private String password;
	private int kategorijaID;
	private String naziv;
	
	public int getRB() {
		return RB;
	}
	public void setRB(int rB) {
		RB = rB;
	}
	public int getKandidatID() {
		return kandidatID;
	}
	public void setKandidatID(int kandidatID) {
		this.kandidatID = kandidatID;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getKategorijaID() {
		return kategorijaID;
	}
	public void setKategorijaID(int kategorijaID) {
		this.kategorijaID = kategorijaID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
