<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="css/aaa.css"> -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kandidat</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
  

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02"   ><!-- style="background: red" -->
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0" >
      <li class="nav-item active">
        <a class="nav-link active"  href="#" style="color: black">kandidat <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="#">pitanja</a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="#">test</a>
      </li>
       <li class="nav-item">
        <a class="nav-link " href="#">prijava </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="Login" method="get">
    <textarea output="outputText" rows=1 cols=25 wrap=on readonly style="background-color: 	#E6E6FA;color: green">Ulogovan je: StefanaSAs </textarea>
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
       <td>1001</td>
      <td>Stefan</td>
      <td>Simonović</td>
      <td>B</td>
    </tr>
   
  </tbody>
</table>
</body>
</html>