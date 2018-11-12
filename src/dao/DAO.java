package dao;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controllers.Polaganje;
import models.Kandidat;
import models.KandidatKategorija;
import models.Odgovor;
import models.Pitanje;
import models.PitanjeOdgovor;
import models.Test;

import java.util.ArrayList;
public class DAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
   //KANDIDATI:
	private static String SELECTKANDIDATI="SELECT * FROM kandidati";
	private static String GETALLKANDIDATSBYKAT="SELECT kat.naziv ,k.ime, k.prezime,k.kandidatID FROM kandidati k,kategorije kat,kandidati_kategorije kk WHERE k.kandidatID=kk.kandidatID AND kat.kategorijaID=kk.kategorijaID AND kat.naziv=?";
	private static String GETKANDIDATBYIDANDPASSWORD=" SELECT * FROM kandidati WHERE kandidatID=? AND sifra=?";
	private static String GETKANDIDATBYID=" SELECT * FROM kandidati WHERE kandidatID=?";
	
	//KANDIDATI-KATEGORIJE:
	private static String GETKATEGORIJABYKANDIDATID=" SELECT k.* ,kat.kategorijaID,kat.naziv FROM kandidati k,kategorije kat,kandidati_kategorije kk WHERE k.kandidatID=kk.kandidatID AND kat.kategorijaID=kk.kategorijaID AND k.kandidatID=?";

	
	//PITANJA
	private static String GETPITANJABYKATID="SELECT p.*,kat.naziv FROM pitanja p,kategorije kat WHERE kat.kategorijaID=p.IDkategorije AND kat.kategorijaID=?";
	private static String GETPITANJABYKATEGORIJAID="SELECT * FROM pitanja WHERE IDkategorije=? ";
	private static String GETPITANJABYPAGE="SELECT p.*,kat.naziv FROM pitanja p,kategorije kat WHERE kat.kategorijaID=p.IDkategorije AND kat.kategorijaID=? LIMIT ? OFFSET ?";
	//PITANJA-ODGOVORI
	private static String GETODGOVORIBYPITANJEID="SELECT * FROM odgovori WHERE pitanjeID=?";
	
	//ODGOVORI:
	private static String GETODGOVORI="SELECT * FROM odgovori";
	private static String GETODGOVORBYID="SELECT * FROM odgovori WHERE odgovorID=?";
	//POLAGANJE TESTA;
	private static String GETRANDOMPITANJABYKATEGORIJAID=" SELECT * FROM pitanja WHERE IDkategorije=? ORDER BY RAND() LIMIT 10";
	private static String INSERTPOLAGANJE=" INSERT INTO  polaganja(polaganjeID,kandidatID,pitanjeID,odgovor_kandidata) VALUES (?,?,?,?)";
	private static String GETPOLAGANJEBYKANDIDATID="SELECT * FROM polaganja WHERE kandidatID=?";
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE – UVEK ISTO
	public DAO(){
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysqlDB" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	// DEFINICIJA METODE 
	public ArrayList<Kandidat> selectKandidati(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Kandidat> lk = new ArrayList<Kandidat>();
		Kandidat pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTKANDIDATI);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			//pstm.setString(1, ime);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Kandidat();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setRB(rs.getInt("RB"));
				pom.setKandidatID(rs.getInt("kandidatID"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setPassword(rs.getString("sifra"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lk.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lk; 
	}
	public ArrayList<Odgovor> getOdgovori(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Odgovor> lo = new ArrayList<Odgovor>();
		Odgovor pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETODGOVORI);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			//pstm.setString(1, ime);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
					pom = new Odgovor();
					// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
					//pom.setRB(rs.getInt("RB"));
					pom.setOdgovorID(rs.getInt("odgovorID"));
					pom.setOdgovor(rs.getString("odgovor"));
					pom.setPitanjeID(rs.getInt("pitanjeID"));
					pom.setTacan_odgovor(rs.getString("tacan_odgovor"));
					
					// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
					lo.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 
	}
	public Odgovor getOdgovorByID(int pitanjeID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
	
		Odgovor pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETODGOVORBYID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, pitanjeID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
					pom = new Odgovor();
					pom.setOdgovorID(rs.getInt("odgovorID"));
					pom.setOdgovor(rs.getString("odgovor"));
					pom.setPitanjeID(rs.getInt("pitanjeID"));
					pom.setTacan_odgovor(rs.getString("tacan_odgovor"));
					
					// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
					
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return pom; 
	}
	public ArrayList<Pitanje> getPitanjaByKategorijaID(int kategorijaID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Pitanje> lp = new ArrayList<Pitanje>();
		Pitanje pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPITANJABYKATID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, kategorijaID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Pitanje();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setPitanje(rs.getString("pitanje"));
				pom.setBroj_bodova(rs.getInt("broj_bodova"));
				pom.setKategorijaID(rs.getInt("IDkategorije"));
				pom.setUrl_slike(rs.getString("url_slike"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lp.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lp; 
	}
	public ArrayList<Pitanje> get10RandomPitanjaByKategorijaID(int kategorijaID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Pitanje> lp = new ArrayList<Pitanje>();
		Pitanje pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETRANDOMPITANJABYKATEGORIJAID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, kategorijaID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Pitanje();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setPitanje(rs.getString("pitanje"));
				pom.setBroj_bodova(rs.getInt("broj_bodova"));
				pom.setKategorijaID(rs.getInt("IDkategorije"));
				pom.setUrl_slike(rs.getString("url_slike"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lp.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lp; 
	}
	public ArrayList<Test> getPolaganjeByKandidatID(int kandidatID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Test> lp = new ArrayList<Test>();
		Test pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPOLAGANJEBYKANDIDATID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, kandidatID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom=new Test();
			
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setKandidatID(rs.getInt("kandidatID"));
				pom.setPolaganjeID(rs.getInt("polaganjeID"));
				pom.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				pom.setOdgovor(rs.getString("odgovor_kandidata"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lp.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lp; 
	}
	public ArrayList<Pitanje> getPitanjaByPage(int kategorijaID,int trenutnaStrana,int redovaPoStrani){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Pitanje> lp = new ArrayList<Pitanje>();
		Pitanje pom = null;
		int start=(trenutnaStrana*redovaPoStrani)-redovaPoStrani;//OVO MI JE 0 NA PRVOJ STRANI,ZATO JE LISTA PRAZNA		
		
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPITANJABYPAGE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, kategorijaID);
			pstm.setInt(2, redovaPoStrani);
			pstm.setInt(3, start);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Pitanje();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setPitanje(rs.getString("pitanje"));
				pom.setBroj_bodova(rs.getInt("broj_bodova"));
				pom.setKategorijaID(rs.getInt("IDkategorije"));
				pom.setUrl_slike(rs.getString("url_slike"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lp.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lp; 
	}
	public Kandidat selectKandidatByID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		Kandidat pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETKANDIDATBYID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Kandidat();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setRB(rs.getInt("RB"));
				pom.setKandidatID(rs.getInt("kandidatID"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setPassword(rs.getString("sifra"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return pom; 
	}
	public Kandidat selectKandidatByIDandPass(int id,String pass){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		Kandidat pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETKANDIDATBYIDANDPASSWORD);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.setString(2, pass);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Kandidat();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				pom.setRB(rs.getInt("RB"));
				pom.setKandidatID(rs.getInt("kandidatID"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setPassword(rs.getString("sifra"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return pom; 
	}
	public KandidatKategorija selectKategorijaBykandidatID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		KandidatKategorija pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETKATEGORIJABYKANDIDATID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new KandidatKategorija();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				//pom.setRB(rs.getInt("RB"));
				pom.setKategorijaID(rs.getInt("kategorijaID"));
				pom.setKandidatID(rs.getInt("kandidatID"));
				pom.setIme(rs.getString("ime"));
				pom.setPrezime(rs.getString("prezime"));
				pom.setPassword(rs.getString("sifra"));
				pom.setNaziv(rs.getString("naziv"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return pom; 
	}
	public ArrayList<Pitanje> getPitanjaByKatID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Pitanje> lp=new ArrayList<>();
		Pitanje pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPITANJABYKATID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Pitanje();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				//pom.setRB(rs.getInt("RB"));
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setPitanje(rs.getString("pitanje"));
				pom.setBroj_bodova(rs.getInt("broj_bodova"));
				pom.setKategorijaID(rs.getInt("IDkategorije"));
				pom.setUrl_slike(rs.getString("url_slike"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lp.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lp; 
	}
	public ArrayList<Odgovor> getOdgovoriByPitanjeID(int pitanjeID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Odgovor> lo=new ArrayList<>();
		Odgovor pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETODGOVORIBYPITANJEID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, pitanjeID);
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				pom = new Odgovor();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				//pom.setRB(rs.getInt("RB"));
				pom.setOdgovorID(rs.getInt("odgovorID"));
				pom.setOdgovor(rs.getString("odgovor"));
				pom.setPitanjeID(rs.getInt("pitanjeID"));
				pom.setTacan_odgovor(rs.getString("tacan_odgovor"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lo.add(pom);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 
	}
	public void insertPolaganje(int polaganjeID,int kandidatID,int pitanjeID,String odgovor){
		Connection con = null;
		PreparedStatement pstm = null;
		
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Odgovor> lo=new ArrayList<>();
		Odgovor pom = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTPOLAGANJE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, polaganjeID);
			pstm.setInt(2, kandidatID);
			pstm.setInt(3, pitanjeID);
			pstm.setString(4, odgovor);
			
			pstm.execute();
			System.out.println("ubaceno polaganje u bazu");

	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// DEFINICIJA OSTALIH METODA ... 
}