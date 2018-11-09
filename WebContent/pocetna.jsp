<%@page import="models.KandidatKategorija"%>
<%@page import="models.Kandidat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
 <% 
 HttpSession sesija=request.getSession();
 KandidatKategorija ulogovani=(KandidatKategorija)sesija.getAttribute("ulogovani");
 if(ulogovani!=null){
	 
 %>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="css/aaa.css"> -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kandidat</title>
</head>
<body style="background: LightGray">
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
  

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02"   >
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0" >
      <li class="nav-item active">
        <a class="nav-link "  href="pocetna.jsp" >kandidat </a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=ucenje&str=1">pitanja</a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=test">test</a>
      </li>
       <li class="nav-item">
        <a class="nav-link " href="Polaganje?ID=<%=ulogovani.getKandidatID()%>&akcija=prijava">prijava </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="Login" method="get">
    <textarea output="outputText" rows=1 cols=20 wrap=on readonly style="background-color: 	#E6E6FA;color: green;text-align: top: ;" >ULOGOVAN JE: <%=ulogovani.getIme() %> </textarea>
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >logout</button>
    </form>
  </div>
</nav>
<table class="table table-striped">
  <thead>
    <tr>
   	  <th scope="col">ID</th>
      <th scope="col">Ime</th>
      <th scope="col">Prezime</th>
      <th scope="col">Kategorija</th>
    </tr>
  </thead>
  <tbody>
    <tr>
       <td><%=ulogovani.getKandidatID() %></td>
      <td><%=ulogovani.getIme() %></td>
      <td><%=ulogovani.getPrezime() %></td>
      <td><%=ulogovani.getNaziv() %></td>
    </tr>
   
  </tbody>
</table>

</body>
</html>
<%
 }else{
	 response.sendRedirect("loginForma.jsp");
 }
%>