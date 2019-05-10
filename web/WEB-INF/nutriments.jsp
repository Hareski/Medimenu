<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medimenu</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>

<body>
<section>
    <h1>Génération de repas par programmation par contraintes</h1>
    <form action="out" method="get">
        <%
            String nutriments=(String) request.getAttribute("nutriments");
            out.println(nutriments);
        %>
        <button type="submit" value="Submit">Générer un menu</button>
    </form>
</section>
</body>
</html>