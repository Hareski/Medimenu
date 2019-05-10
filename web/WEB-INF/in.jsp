<%@ page import="medimenu.Enregistrement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medimenu</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>

<body>
<section>
    <h1>Bienvenue</h1>

    <h3> Utiliser une configuration existante:</h3>
    <form action="nouvelleConfiguration" method="get">


        <%
            String configurations= Enregistrement.afficherHTMLContenuRepertoire("/medimenu/Regimes/");
            out.println(configurations);

        %>


        <div> <input type="radio" id="newConfiguration" name="newConfiguration" value="newConfiguration" /> <label for="newConfiguration"> Créer une configuration </label></div>
        <button type="submit" value="Submit">Suivant</button>
    </form>


    <!-- Formulaire simple qui enverra une requête GET -->

</section>
</body>
</html>