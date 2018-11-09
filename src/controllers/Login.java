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
import models.Pitanje;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesija=request.getSession();
		
		sesija.invalidate();
		response.sendRedirect("loginForma.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String kandidatID=request.getParameter("kandidatID");
		String password=request.getParameter("password");
	if(kandidatID!=null && kandidatID.length()>0 && password!=null && password.length()>0)
	{
		try {
			int kandID=Integer.parseInt(kandidatID);
			DAO dao=new DAO();
			Kandidat k=dao.selectKandidatByID(kandID);
			if(k!=null){
				k=new Kandidat();
				k=dao.selectKandidatByIDandPass(kandID, password);
				if(k!=null){
					HttpSession sesija=request.getSession();
					
					KandidatKategorija ulogovani=dao.selectKategorijaBykandidatID(kandID);//UZIMA IZ BAZE CELOG KANDIDATA I NJEGOVU KATEGORIJU
					
					ArrayList<Pitanje> lp=dao.getPitanjaByKatID(ulogovani.getKategorijaID());
					
					sesija.setAttribute("ulogovani", ulogovani);
					
					
					request.getRequestDispatcher("pocetna.jsp").forward(request, response);
					
							
				}else{
					request.setAttribute("errmsg", "Pogrešna šifra");
					request.getRequestDispatcher("loginForma.jsp").forward(request, response);

				}
			}else{
				request.setAttribute("errmsg", "Pogrešan ID");
				request.getRequestDispatcher("loginForma.jsp").forward(request, response);

			}
			} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("errmsg", "Pogrešan ID");
			request.getRequestDispatcher("loginForma.jsp").forward(request, response);
		}
	}else{
		request.setAttribute("errmsg", "Popuni oba polja");
		request.getRequestDispatcher("loginForma.jsp").forward(request, response);

	}
	
	}

}
