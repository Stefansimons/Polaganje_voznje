<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.Pitanje"%>
<%@page import="models.Odgovor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.KandidatKategorija"%>
<%@page import="models.Kandidat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
 <% 
 HttpSession sesija=request.getSession();
 KandidatKategorija ulogovani=(KandidatKategorija)sesija.getAttribute("ulogovani");
 if(ulogovani!=null){
		ArrayList<Odgovor> odgovori=(ArrayList)sesija.getAttribute("odgovori");
		ArrayList<Pitanje> pitanja=(ArrayList)sesija.getAttribute("pitanja");
		int trenutnaStrana=(Integer)request.getAttribute("trenutnaStrana");
		int ukupnoStrana=(Integer)request.getAttribute("ukupnoStrana");
		int redovaPoStrani=(Integer)request.getAttribute("redovaPoStrani");
	/* System.out.println("trenutna strana je:"+trenutnaStrana);
	System.out.println("ukupno strana ima:"+ukupnoStrana);
	System.out.println("broj redova po strani je:"+redovaPoStrani);
	System.out.println("ukupno redova liste lp na strani pitanja jsp:"+pitanja.size());
	/* for(Pitanje p:pitanja)
	{
		if(p.getPitanjeID()==1)
		{
			System.out.println(p.toString());
		}
	} */ 
	

 %>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="css/aaa.css"> -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body style="background: LightGray">
<%-- <%for(Odgovor o:odgovori){ if(o.getPitanjeID()==1 && o.getTacan_odgovor().equals("tačan")){ %>
<%=o.getTacan_odgovor() %>
<%}} %> --%>
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
  

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02"   >
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0" >
      <li class="nav-item ">
        <a class="nav-link "  href="pocetna.jsp" >kandidat </a>
      </li>
      <li class="nav-item ">
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=ucenje&str=1">pitanja</a>
      </li>
      <li class="nav-item active" >
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=test">test</a>
      </li>
       <li class="nav-item">
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=prijava">prijava </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="Login" method="get">
    <textarea output="outputText" rows=1 cols=20 wrap=on readonly style="background-color: 	#E6E6FA;color: green;">ULOGOVAN JE: <%=ulogovani.getIme() %></textarea>
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >logout</button>
    </form>
  </div>
</nav>
	<table class="table table-sm" >
		<thread>
			<tr>

				<th scope="col" colspan="2">Pitanje</th>
				<th scope="col" >Odgovori</th>
				<th scope="col">Broj bodova</th>
				
			</tr>
		</thread>
		<tbody>
			<c:forEach var="pitanje" items="${pitanja}">
				<tr>
					<td>${pitanje.pitanje}</td>
					<td><img src="${pitanje.url_slike}" align="right"></td>

					<td><c:forEach var="odgovor" items="${odgovori}">
							<ul>
								<c:if test="${odgovor.pitanjeID == pitanje.pitanjeID}">
									<c:choose>

										<c:when test="${odgovor.tacan_odgovor.equals('tačan')}">

											<li style="color: red;"><c:out
													value="${odgovor.odgovor} " /></li>


										</c:when>
										<c:otherwise>
											<li style="color: black;"><c:out
													value="${odgovor.odgovor} " /></li>
										</c:otherwise>

										<%-- <span style="color: red;"> <c:out value=" ${odgovor.tacan_odgovor}" /></span><br> --%>


									</c:choose>

								</c:if>
							</ul>
						</c:forEach></td>
						
					<td>${pitanje.broj_bodova}</td>

				</tr>

			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Navigation for pitanja ">
		<ul class="pagination">
			<c:if test="${trenutnaStrana!= 1}">
				<li class="page-item"><a class="page-link"
					href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=ucenje&str=${trenutnaStrana-1}">Prethodna</a>
				</li>
			</c:if>

			<c:forEach begin="1" end="${ukupnoStrana}" var="i">
				<c:choose>
					<c:when test="${trenutnaStrana eq i}">
						<li class="page-item active"><a class="page-link">${i}<span
								lass="sr-only"></span></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=ucenje&str=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${trenutnaStrana lt ukupnoStrana}">
				<li class="page-item"><a class="page-link"
					href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=ucenje&str=${trenutnaStrana+1}">sledeća</a>
				</li>
			</c:if>
		</ul>
	</nav>
</body>
</html>
<%
 }else{
	 response.sendRedirect("loginForma.jsp");
 }
%>