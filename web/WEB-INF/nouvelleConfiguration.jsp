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
    <form action="groupes" method="get">
        <fieldset>
            <!-- TO DO -->
            Le temps maximal de calcul est de : <input type="number" name="timeout" min="1" max="1000" value="60"> secondes.
            Le nombre de jours est : <input type="number" name="nbJours" min="1" max="7" value=${nbJ_preconfig}>
        </fieldset>

        <fieldset>
            Le nombre de slots vaut: <input type="number" name="nbSlots" min="1" max="5" value=${nbS_preconfig}>
        </fieldset>

        <%
            //String nutriments=(String) request.getAttribute("nutriments");
            //String groupes=(String) request.getAttribute("groupes");
            //out.println(nutriments);
            //out.println(groupes);

        %>
        <button type="submit" value="Submit">Suivant</button>
    </form>
</section>
</body>
</html>