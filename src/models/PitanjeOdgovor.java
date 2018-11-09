package models;

public class PitanjeOdgovor {
	private int pitanjeID;
	private String pitanje;
	private int broj_bodova;
	private String url_slike;
	private int kategorijaID;
	private int odgovorID;
	private String odgovor;
	
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

	public int getKategorijaID() {
		return kategorijaID;
	}

	public void setKategorijaID(int kategorijaID) {
		this.kategorijaID = kategorijaID;
	}

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

	public String getTacan_odgovor() {
		return tacan_odgovor;
	}

	public void setTacan_odgovor(String tacan_odgovor) {
		this.tacan_odgovor = tacan_odgovor;
	}

	private String tacan_odgovor;
}
