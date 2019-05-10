<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medimenu</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>

<body>
<section>
    <h1>Génération de repas par programmation par contraintes</h1>
    <!-- Formulaire simple qui enverra une requête GET -->
    <form action="nutriments" method="get">


        <%
            String groupes=(String) request.getAttribute("groupes");
            out.println(groupes);

        %>
        <button type="submit" value="Submit">Suivant</button>
    </form>
</section>
</body>
</html>