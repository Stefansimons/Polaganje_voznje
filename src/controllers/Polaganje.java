package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import models.Kandidat;
import models.KandidatKategorija;
import models.Odgovor;
import models.Pitanje;
import models.PitanjeOdgovor;
import models.Test;


@WebServlet("/Polaganje")
public class Polaganje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Polaganje() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String kandidatID=request.getParameter("ID");
		String akcija=request.getParameter("akcija");
		String broj_strane=request.getParameter("str");//=1
		//String redovaPostrani=request.getParameter("ps");//=5
		if(kandidatID!=null && kandidatID.length()>0 && akcija!=null && akcija.length()>0){
			try {
				int kanid=Integer.parseInt(kandidatID);
				DAO dao=new DAO();
				HttpSession sesija=request.getSession();
				KandidatKategorija ulogovani=(KandidatKategorija)sesija.getAttribute("ulogovani");
			
				if(akcija.equals("ucenje"))
				{
					if(broj_strane!=null && broj_strane.length()>0 )
					{
						try {
							int brstr=Integer.parseInt(broj_strane);
							
							int redovaPoStrani=10;
							//int brojRedovaPoStrani=5;
							ArrayList<Pitanje> pitanja=dao.getPitanjaByKategorijaID(ulogovani.getKategorijaID());
							int ukupnoPitanja=pitanja.size();
							//System.out.print("broj redova u listi pitanja:"+ukupnoPitanja);
								ArrayList<Pitanje> lp=dao.getPitanjaByPage(ulogovani.getKategorijaID(),brstr,redovaPoStrani);
								//System.out.print("broj redova u listi lp"+lp.size());
								ArrayList<Odgovor> odgovori=dao.getOdgovori();
								ArrayList<Odgovor> listaOdgovoraZaSvaPitanja=new ArrayList<>();
								for(Pitanje p:lp)
								{
									ArrayList<Odgovor> lo=dao.getOdgovoriByPitanjeID(p.getPitanjeID());
									for(Odgovor o:lo)
									{
										listaOdgovoraZaSvaPitanja.add(o);
									}
								}
								 int ukupnoStr = (int) Math.ceil(ukupnoPitanja * 1.0 / redovaPoStrani);//UKUPAN BROJ STRANA NA KOJIMA TREBA DA SE PRIKAZU SVA PITANJA(5 PO STRANI)
								 request.setAttribute("trenutnaStrana", brstr);
								 request.setAttribute("redovaPoStrani", redovaPoStrani);
								 request.setAttribute("ukupnoStrana", ukupnoStr);
								 //HttpSession sesijaa=request.getSession();
								 sesija.setAttribute("pitanja", lp);//SVA PITANJA ZA KATEGORIJU ULOGOVANOG KANDIDATA
								sesija.setAttribute("odgovori", listaOdgovoraZaSvaPitanja);// ODGOVORI ZA SVA PITANJA
							//	System.out.println("broj redova u listi lp"+lp.size());
								request.getRequestDispatcher("pitanja.jsp").forward(request, response);
			
								
						} catch (Exception e) {
							// TODO: handle exception
							response.sendRedirect("404 error.html");

						}
							
					}else {
						response.sendRedirect("404 error.html");
					}
					
				}else if(akcija.equals("test"))
			/*		ArrayList<Pitanje> lp=null;
					//ArrayList<Test> =new ArrayList<>();
					//ArrayList<Test> polaganje=dao.getPolaganjeByKandidatID(kanid);
				ArrayList<Pitanje> lp=dao.get10RandomPitanjaByKategorijaID(ulogovani.getKategorijaID());
			*/	
				{
					
				}else if(akcija.equals("prijava"))
				{
					
				}else {
					response.sendRedirect("404 error.html");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				response.sendRedirect("404 error.html");
			}
		}else{
			response.sendRedirect("404 error.html");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
