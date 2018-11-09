package models;

public class Pitanje {

	private int pitanjeID;
	private String pitanje;
	private int broj_bodova;
	private String url_slike;
	private int kategorijaID;
	public Pitanje(String pitanje, int broj_bodova, String url_slike, int kategorijaID) {
		super();
		this.pitanje = pitanje;
		this.broj_bodova = broj_bodova;
		this.url_slike = url_slike;
		this.kategorijaID = kategorijaID;
	}
	public int getKategorijaID() {
		return kategorijaID;
	}
	public void setKategorijaID(int kategorijaID) {
		this.kategorijaID = kategorijaID;
	}
	public int getPitanjeID() {
		return pitanjeID;
	}
	public void setPitanjeID(int pitanjeID) {
		this.pitanjeID = pitanjeID;
	}
	public String getPitanje() {
		return pitanje;
	}
	public void setPitanje(String pitanje) {
		this.pitanje = pitanje;
	}
	public int getBroj_bodova() {
		return broj_bodova;
	}
	public void setBroj_bodova(int broj_bodova) {
		this.broj_bodova = broj_bodova;
	}
	public String getUrl_slike() {
		return url_slike;
	}
	public void setUrl_slike(String url_slike) {
		this.url_slike = url_slike;
	}
	public Pitanje() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pitanje(String pitanje, int broj_bodova, String url_slike) {
		super();
		this.pitanje = pitanje;
		this.broj_bodova = broj_bodova;
		this.url_slike = url_slike;
	}
	@Override
	public String toString() {
		return "Pitanje [pitanjeID=" + pitanjeID + ", pitanje=" + pitanje + ", broj_bodova=" + broj_bodova
				+ ", url_slike=" + url_slike + ", kategorijaID=" + kategorijaID + "]";
	}
	
}
