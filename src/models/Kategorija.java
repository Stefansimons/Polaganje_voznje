package models;

public class Kategorija {

	private int kategorijaID;
	private String naziv;
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
	public Kategorija(String naziv) {
		super();
		this.naziv = naziv;
	}
	public Kategorija() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
