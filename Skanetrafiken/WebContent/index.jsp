<%@page import="MVC.ApiBean"%>
<%@page import="MVC.Controller"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Scrolling Nav - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/scrolling-nav.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger"
				href="/Skanetrafiken/index.jsp">Skånetrafiken</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Skanetrafiken/about.jsp">Om oss</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Skanetrafiken/contact.jsp">Kontakt</a></li>
				</ul>
			</div>
		</div>
	</nav>




	<header class="bg-primary text-white">
		<div class="col-lg-8 mx-auto">
			<h2>Sök:</h2>
			<form action="<%=request.getContextPath()%>" method="get">

				<h6>Avgångstider från:</h6>
				<select name="from" id="from">

					<%
						ApiBean bean = (ApiBean) request.getAttribute("bean");

					for (int i = 0; i < bean.getStations().size(); i++) {

						out.print("<option value=>" + bean.getStations().get(i) + "</option>");

					}

					for (int i = 0; i < bean.getId().size(); i++) {

						System.out.print(bean.getId().get(i));

					}
					%>


				</select><br /> <input name="buttonPressed" type="Submit" value="Sök"
					<%if ("buttonPressed" != null) {

}%> />



			</form>

			<div class="infoDiv col-lg-8 mx-auto">

				<ul>
					<li>
						<%
							for (int i = 0; i < bean.getNames().size(); i++) {
							out.print("Mot: " + bean.getNames().get(i));
							out.print(": ");
							out.print(bean.getDates().get(i));
							out.print("<br>");
						}
						for (int i = 0; i < bean.getDates().size(); i++) {
						}
						%>

					</li>
				</ul>

			</div>
		</div>
	</header>
</body>
</html>