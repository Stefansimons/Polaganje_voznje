package models;


public class Kandidat {
	private int RB;
	public int getRB() {
		return RB;
	}
	public void setRB(int rB) {
		RB = rB;
	}
	private int kandidatID;
	private String ime;
	private String prezime;
	private String password;
	
	public Kandidat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kandidat(int kandidatID, String ime, String prezime, String password) {
		super();
		this.kandidatID = kandidatID;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		
	}
	@Override
	public String toString() {
		return "Kandidat [kandidatID=" + kandidatID + ", ime=" + ime + ", prezime=" + prezime + ", password=" + password+"]";
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
	
}
